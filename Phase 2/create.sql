CREATE schema IF NOT EXISTS `Gym_Management`;

create table IF NOT EXISTS Profession(
	`Name` varchar(100) NOT NULL, 
    primary key(`Name`)
);

create table IF NOT EXISTS `System_User`(
	UsernameID varchar(50) NOT NULL PRIMARY KEY,
    StartDate datetime DEFAULT CURRENT_TIMESTAMP,
    EndDate datetime,
    `Password` varchar(50));
    
CREATE table IF NOT EXISTS Trainer(
	UsernameID varchar(50) not null, 
    `Name` varchar(50), 
    Surname varchar(50), 
    Salary float NOT NULL,
    foreign key(UsernameID) references system_user(UsernameID) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key(UsernameID)
    );
    
create table IF NOT EXISTS Membership_Type(
	`Name` varchar(50) NOT NULL, 
    price float,
    primary key(Name));
    
create table IF NOT EXISTS Customer(
	UsernameID varchar(50) not null, 
    MembershipTypeName varchar(100),
    TrainerID varchar(50), 
    `Name` varchar(50), 
    Surname varchar(50), 
    Weight float, 
    Length float, 
    Age int, 
    FatRatio float, 
    CreditCardNumber varchar(16) not null, 
    CreditCardExpireDate datetime not null,
    foreign key(MembershipTypeName) references Membership_Type(`Name`) ON DELETE SET NULL ON UPDATE CASCADE,
    foreign key(TrainerID) references Trainer(UsernameID) ON DELETE SET NULL ON UPDATE CASCADE,
    foreign key(UsernameID) references system_user(UsernameID) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key(UsernameID));

create table IF NOT EXISTS Program(
	ID int unsigned NOT NULL AUTO_INCREMENT, 
    bmi float,
    primary key(ID)
    );

create table IF NOT EXISTS Exercise(
	`Name` varchar(100) not null primary key,
	`Set` int,
    RepetitionPerSet int
    );
    
create table IF NOT EXISTS Consist_Of(
	ProgramID int unsigned not null,
    ExerciseName varchar(100) not null,
    foreign key(ProgramID) references Program(ID) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key(ExerciseName) references Exercise(`Name`) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key(ProgramID, ExerciseName));

create table IF NOT EXISTS Sport_Tools(
	`Name` varchar(100) not null primary key,
    Amount int,
    urlImage varchar(2000) );

create table IF NOT EXISTS Lesson(
	ID int unsigned not null auto_increment,
	`Name` varchar(250),
    TrainerID varchar(50) ,
    ProfessionName varchar(100) ,
    Price float,
    primary key(ID),
    foreign key(TrainerID) references Trainer(UsernameID) ON DELETE SET NULL ON UPDATE CASCADE,
    foreign key(ProfessionName) references Profession(`Name`) ON DELETE SET NULL ON UPDATE CASCADE);

create table IF NOT EXISTS Enroll_Time(
	LessonID int unsigned not null,
    `Day` varchar(10) not null,
    StartTime time,
    EndTime time,
    Quota int,
    foreign key(LessonID) references Lesson(ID) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key(LessonID, `Day`));

create table IF NOT EXISTS Purchase(
	CustomerID varchar(50) not null,
    LessonID int unsigned not null,
    EnrollTimeDay varchar(10) not null,
    PurchasedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    foreign key(CustomerID) references Customer(UsernameID) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key(LessonID, EnrollTimeDay) references Enroll_Time(LessonID, `Day`) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key(CustomerID, LessonID, EnrollTimeDay));

create table IF NOT EXISTS Physical_Ailment(
	`Name` varchar(100) not null primary key
    );

create table IF NOT EXISTS Not_Fit(
	PyhsicalAilmentName varchar(100) not null,
    ProgramID int unsigned not null,
    foreign key(PyhsicalAilmentName) references Physical_Ailment(`Name`) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key(ProgramID) references Program(ID) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key(PyhsicalAilmentName, ProgramID)
    );
    
create table IF NOT EXISTS Used_In(
	SportToolsName varchar(100) not null,
    LessonID int unsigned not null,
    Quantity int,
    foreign key(SportToolsName) references Sport_Tools(`Name`) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key(LessonID) references Lesson(ID) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key(SportToolsName, LessonID));

    
create table IF NOT EXISTS Be_Profession(
	ProfessionName varchar(100) not null,
    TrainerID varchar(50 ) not null,
    foreign key(ProfessionName) references Profession(`Name`) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key(TrainerID) references Trainer(UsernameID) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key(ProfessionName, TrainerID)
    );
  
create table IF NOT EXISTS Has(
	CustomerID varchar(50) not null,
    PyhsicalAilmentName varchar(100) not null,
    foreign key(CustomerID) references Customer(UsernameID) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key(PyhsicalAilmentName) references Physical_Ailment(`Name`) ON DELETE CASCADE ON UPDATE CASCADE
    );
    
        
create table IF NOT EXISTS Asked_For(
	CustomerID varchar(50) not null,
    ProgramID int unsigned not null,
    foreign key(CustomerID) references Customer(UsernameID) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key(ProgramID) references Program(`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key(CustomerID, ProgramID));
    
create table IF NOT EXISTS Make_With(
	SportToolsName varchar(100) not null,
    ExerciseName varchar(100) not null,
    foreign key(SportToolsName) references Sport_Tools(`Name`) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key(ExerciseName) references Exercise(`Name`) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key(SportToolsName, ExerciseName));  


DROP EVENT IF EXISTS checkCreditCardExpireDate;
DROP TRIGGER IF EXISTS insertCustomerTrigger;
DROP TRIGGER IF EXISTS insertTrainerTrigger;
DROP TRIGGER IF EXISTS deleteCustomerTrigger;
DROP TRIGGER IF EXISTS deleteTrainerTrigger;

DELIMITER $$
CREATE EVENT checkCreditCardExpireDate
  ON SCHEDULE
    EVERY 1 DAY 
    STARTS CURRENT_DATE
  DO
    BEGIN
        UPDATE system_user, customer
        SET system_user.EndDate = CURRENT_TIMESTAMP
		WHERE system_user.UsernameID = customer.UsernameID and customer.CreditCardExpireDate <= CURRENT_DATE;
    END $$
   
create trigger insertTrainerTrigger before insert on trainer
   for each row
	   begin
		   insert into system_user(UsernameID) values (new.UsernameID);
	   end$$

create trigger insertCustomerTrigger before insert on customer
   for each row
	   begin
		   insert into system_user(UsernameID) values (new.UsernameID);
	   end$$

create trigger deleteCustomerTrigger after delete on customer
	for each row
		begin
			delete from system_user where system_user.UsernameID = old.UsernameID;
		end$$
        
create trigger deleteTrainerTrigger after delete on trainer
	for each row
		begin
			delete from system_user where system_user.UsernameID = old.UsernameID;
		end$$
DELIMITER ;

SET GLOBAL event_scheduler = ON; 


DELIMITER $$

CREATE PROCEDURE insert_asked_for(
     CustomerID varchar(50),
	ProgramID  int unsigned ) 
BEGIN    
    INSERT INTO asked_for(CustomerID,ProgramID) VALUES(CustomerID, ProgramID);
END$$

CREATE PROCEDURE delete_asked_for(CustomerID varchar(50), 
								 ProgramID int unsigned)
BEGIN
DELETE FROM asked_for where asked_for.CustomerID = asked_for and  asked_for.ProgramID = ProgramID;
END$$


CREATE PROCEDURE insert_be_profession(ProfessionName varchar(100),TrainerID varchar(50)) 
BEGIN    
    INSERT INTO be_profession(ProfessionName,TrainerID) VALUES(ProfessionName, TrainerID);
END$$

CREATE PROCEDURE delete_be_profession(ProfessionName varchar(100), TrainerID varchar(50))
BEGIN
    DELETE FROM be_profession where ProfessionName = be_profession.ProfessionName and TrainerID = be_profession.TrainerID;
END$$

CREATE PROCEDURE insert_consist_of(
     ProgramID int unsigned ,
    ExerciseName varchar(100)) 
BEGIN    
    INSERT INTO consist_of(ProgramID,ExerciseName) VALUES(ProgramID, ExerciseName);
END$$


CREATE PROCEDURE delete_consist_of(ProgramID int unsigned,ExerciseName varchar(100))
BEGIN
    DELETE FROM consist_of where ProgramID = consist_of.ProgramID and ExerciseName = consist_of.ExerciseName;
END$$


CREATE PROCEDURE insert_customer(
     UsernameID varchar(50),
     MembershipTypeName varchar(100),
     TrainerID varchar(50),
     `Name` varchar(50),
     Surname varchar(50),
     Weight float,
     Length float,
     Age int,
     FatRatio float,
     CreditCardNumber varchar(16),
     CreditCardExpireDate datetime
     ) 
BEGIN    
    INSERT INTO customer( UsernameID,MembershipTypeName,TrainerID,`Name`,Surname,Weight,Length,Age,FatRatio,CreditCardNumber,
    CreditCardExpireDate) VALUES(UsernameID,MembershipTypeName,
     TrainerID,
     `Name`,
     Surname,
     Weight,
     Length,
     Age,
     FatRatio,
     CreditCardNumber,
     CreditCardExpireDate);
END$$


CREATE PROCEDURE delete_customer(UsernameID varchar(50))
BEGIN
    DELETE FROM customer where customer.UsernameID = ID ;
END$$


CREATE PROCEDURE insert_enroll_time(
     LessonID int unsigned,
	`Day` varchar(10),
    StartTime time,
    EndTime time,
    Quota int)
BEGIN    
    INSERT INTO enroll_time(LessonID,`Day`,Starttime,EndTime, Quota) VALUES(LessonID, `Day`,StartTime,EndTime,Quota);
END$$


CREATE PROCEDURE delete_enroll_time(LessonName varchar(250) , `Day` varchar(10))
BEGIN
    DELETE FROM enroll_time where  enroll_time.LessonName = LessonName and  enroll_time.Day = `Day`;
END$$

CREATE PROCEDURE insert_exercise(
     `Name` varchar(100),
	`Set` int,
	RepetitionPerSet int)
BEGIN    
    INSERT INTO exercise( `Name` ,`Set`,RepetitionPerSet) VALUES( `Name` ,`Set`,RepetitionPerSet);
END$$


CREATE PROCEDURE delete_exercise(`Name` varchar(100))
BEGIN
    DELETE FROM exercise where exercise.`Name` = `Name`;
END$$


CREATE PROCEDURE insert_has(
     CustomerID varchar(50),
	 PyhsicalAilmentName varchar(100)
    )
BEGIN    
    INSERT INTO has(CustomerID, PyhsicalAilmentName ) VALUES(CustomerID, PyhsicalAilmentName);
END$$


CREATE PROCEDURE delete_has(CustomerID varchar(50), PyhsicalAilmentName varchar(100))
BEGIN
    DELETE FROM has where has.CustomerID = CustomerID and has.PyhsicalAilmentName=PyhsicalAilmentName;
END$$


CREATE PROCEDURE insert_lesson( `Name` varchar(250), TrainerID varchar(50),ProfessionName varchar(100) 
, Price float) 
BEGIN    
    INSERT INTO lesson(`Name` , TrainerID ,ProfessionName  ,
 Price ) VALUES(`Name` , TrainerID ,ProfessionName  ,
 Price);
END$$

CREATE PROCEDURE delete_lesson(ID int unsigned)
BEGIN
    DELETE FROM lesson where lesson.ID = ID;
END$$

CREATE PROCEDURE insert_make_with( SportToolsName varchar(100), ExerciseName varchar(100)) 
BEGIN    
    INSERT INTO make_with(SportToolsName ,ExerciseName) 
    VALUES(SportToolsName , ExerciseName);
END$$


CREATE PROCEDURE delete_make_with(SportToolsName varchar(100), ExerciseName varchar(100))
BEGIN
DELETE FROM make_with where make_with.SportToolsName = SportToolsName and make_with.ExerciseName = ExerciseName ;
END$$

CREATE PROCEDURE insert_membership_type(`Name` varchar(50), price float) 
BEGIN    
    INSERT INTO membership_type(`Name`,price ) VALUES(`Name`,price);
END$$

CREATE PROCEDURE delete_membership_type(`Name` varchar(50))
BEGIN
DELETE FROM membership_type where membership_type.`Name` =  `Name`;
END$$

CREATE PROCEDURE insert_not_fit(PyhsicalAilmentName varchar(100), ProgramID int unsigned) 
BEGIN    
    INSERT INTO not_fit(PyhsicalAilmentName,ProgramID ) VALUES(PyhsicalAilmentName,ProgramID);
END$$


CREATE PROCEDURE delete_not_fit(PyhsicalAilmentName varchar(100), ProgramID int unsigned)
BEGIN
    DELETE FROM not_fit where not_fit.PyhsicalAilmentName = PyhsicalAilmentName and 
    not_fit.ProgramID = ProgramID;
END$$


CREATE PROCEDURE insert_physical_ailment(`Name` varchar(100)) 
BEGIN    
    INSERT INTO physical_ailment(`Name`) VALUES(`Name`);
END$$

CREATE PROCEDURE delete_physical_ailment(`Name` varchar(100))
BEGIN
    DELETE FROM physical_ailment where physical_ailment.`Name` = `Name`;
END$$

CREATE PROCEDURE insert_profession(`Name` varchar(100)) 
BEGIN    
    INSERT INTO profession(`Name`) VALUES(`Name`);
END$$

CREATE PROCEDURE delete_profession(`Name` varchar(100))
BEGIN
    DELETE FROM profession where profession.`Name` = `Name`;
END$$



CREATE PROCEDURE insert_program(bmi float) 
BEGIN    
    INSERT INTO program(bmi) VALUES(bmi);
END$$

CREATE PROCEDURE delete_program(ID int unsigned)
BEGIN
    DELETE FROM program where program.ID = ID;
END$$


CREATE PROCEDURE insert_purchase(CustomerID varchar(50), 
LessonID int unsigned ,
EnrollTimeDay varchar(10) , 
PurchasedDate datetime) 
BEGIN    
    INSERT INTO purchase(CustomerID , 
	LessonID,
	EnrollTimeDay, 
	PurchasedDate) VALUES(CustomerID , 
	LessonID,
	EnrollTimeDay, 
	PurchasedDate);
END$$


CREATE PROCEDURE delete_purchase(CustomerID varchar(50) ,
LessonID int unsigned , 
EnrollTimeDay varchar(10))
BEGIN
    DELETE FROM purchase where purchase.CustomerID = CustomerID and 
    purchase.LessonID = LessonID and 
    purchase.EnrollTimeDay =EnrollTimeDay;
    
END$$


CREATE PROCEDURE insert_sport_tools(`Name` varchar(100) ,Amount int(11) ,urlImage varchar(2000)) 
BEGIN    
    INSERT INTO sport_tools(`Name`,Amount,urlImage) VALUES(`Name`,Amount,urlImage);
END$$

CREATE PROCEDURE delete_sport_tools(`Name` varchar(100))
BEGIN
DELETE FROM sport_tools where sport_tools.`Name` = `Name`;
END$$

CREATE PROCEDURE insert_system_user(UsernameID varchar(50),
StartDate datetime,
EndDate datetime,
`Password` varchar(50)) 
BEGIN    
    INSERT INTO system_user(UsernameID,StartDate,EndDate,`Password`) VALUES(UsernameID,StartDate,EndDate,`Password`);
END$$


CREATE PROCEDURE delete_system_user(UsernameID varchar(50))
BEGIN
DELETE FROM system_user where system_user.UsernameID = usernameID;
END$$

CREATE PROCEDURE insert_trainer(UsernameID varchar(50),
`Name` varchar(50) ,
Surname varchar(50) , 
Salary float) 
BEGIN    
    INSERT INTO trainer(UsernameID,`Name`,Surname,Salary) VALUES (UsernameID,`Name`,Surname,Salary);
END$$


CREATE PROCEDURE delete_trainer(UsernameID varchar(50))
BEGIN
    DELETE FROM trainer where trainer.UsernameID = UsernameID;
END$$


CREATE PROCEDURE insert_used_in(SportToolsName varchar(100), 
LessonID int unsigned ,
Quantity int(11)) 
BEGIN    
    INSERT INTO used_in(SportToolsName,LessonID,Quantity) VALUES(SportToolsName,LessonID,Quantity);
END$$

CREATE PROCEDURE delete_used_in(SportToolsName varchar(100),
LessonID int unsigned )
BEGIN
    DELETE FROM used_in where used_in.SportToolsName = SportToolsName and
    used_in.LessonID = LessonID;
END$$

CREATE PROCEDURE updateProfessionName
	(oldName varchar(100), newName varchar(100) )
	BEGIN
		update profession set `Name` = newName 
        where `Name` = oldName;
	END$$
    
    
CREATE PROCEDURE updateSystemUserUsernameID
	(oldUserNameID varchar(50), newUsernameID varchar(50))
	BEGIN
		update System_User set UsernameID = newUsernameID 
        where oldUserNameID = UsernameID;
	END$$
    
CREATE PROCEDURE updateSystemUserStartDate
	(updatedUserNameID varchar(50), newStartDate datetime)
	BEGIN
		update System_User set StartDate = newStartDate
        where UserNameID = updatedUserNameID;
	END$$
    
CREATE PROCEDURE updateSystemUserEndDate
	(updatedUserNameID varchar(50), newEndDate datetime)
	BEGIN
		update System_User set EndDate = newEndDate
        where UserNameID = updatedUserNameID;
	END$$
    
CREATE PROCEDURE updateSystemUserPassword
	(updatedUserNameID varchar(50), newPassword varchar(50))
	BEGIN
		update System_User set `Password`  = newPassword
        where UserNameID = updatedUserNameID;
	END$$
    
    
CREATE PROCEDURE updateTrainerName
	(updatedUserNameID varchar(50), newName varchar(50))
	BEGIN
		update Trainer set `Name` = newName
        where UserNameID = updatedUserNameID;
	END$$
    
CREATE PROCEDURE updateTrainerSurname
	(updatedUserNameID varchar(50), newSurname varchar(50))
	BEGIN
		update Trainer set Surname = newSurname
        where UserNameID = updatedUserNameID;
	END$$
    
CREATE PROCEDURE updateTrainerSalary 
	(updatedUserNameID varchar(50), newSalary float)
	BEGIN
		update Trainer set Salary = newSalary
        where UserNameID = updatedUserNameID;
	END$$
    
CREATE PROCEDURE updateMembershipTypeName
	(updatedName varchar(50), newName varchar(50))
	BEGIN
		update Membership_Type set `Name` = newName
        where `Name` = updatedName;
	END$$
    
CREATE PROCEDURE updateMembershipTypePrice
	(updatedName varchar(50), newPrice float)
	BEGIN
		update Membership_Type set Price = newPrice
        where `Name` = updatedName;
	END$$
    
CREATE PROCEDURE updateCustomerMembershipTypeName
	(updatedUserNameID varchar(50), newMembershipTypeName varchar(100))
	BEGIN
		update Customer set MembershipTypeName = newMembershipTypeName
        where UsernameID = updatedUserNameID;
	END$$


CREATE PROCEDURE updateCustomerTrainerID
	(updatedUserNameID varchar(50), newTrainerID varchar(50))
	BEGIN
		update Customer set TrainerID = newTrainerID
        where UsernameID = updatedUserNameID;
	END$$
    
    
CREATE PROCEDURE updateCustomerName
	(updatedUserNameID varchar(50), newName varchar(50))
	BEGIN
		update Customer set `Name` = newName
        where UsernameID = updatedUserNameID;
	END$$
    
CREATE PROCEDURE updateCustomerSurname 
	(updatedUserNameID varchar(50), newSurname  varchar(50))
	BEGIN
		update Customer set Surname = newSurname
        where UsernameID = updatedUserNameID;
	END$$
    
CREATE PROCEDURE updateCustomerWeight
	(updatedUserNameID varchar(50), newWeight float)
	BEGIN
		update Customer set Weight = newWeight
        where UsernameID = updatedUserNameID;
	END$$
    
    
CREATE PROCEDURE updateCustomerLength
	(updatedUserNameID varchar(50), newLength float)
	BEGIN
		update Customer set Length = newLength
        where UsernameID = updatedUserNameID;
	END$$
    
    
CREATE PROCEDURE updateCustomerAge
	(updatedUserNameID varchar(50), newAge int)
	BEGIN
		update Customer set Age = newAge
        where UsernameID = updatedUserNameID;
	END$$
    
    
CREATE PROCEDURE updateCustomerFatRatio
	(updatedUserNameID varchar(50), newFatRatio float)
	BEGIN
		update Customer set FatRatio = newFatRatio
        where UsernameID = updatedUserNameID;
	END$$
    
    
CREATE PROCEDURE updateCustomerCreditCardNumber 
	(updatedUserNameID varchar(50), newCreditCardNumber varchar(16))
	BEGIN
		update Customer set CreditCardNumber = newCreditCardNumber
		where UsernameID = updatedUserNameID;
	END$$
    
    
CREATE PROCEDURE updateCustomerCreditCardExpireDate  
	(updatedUserNameID varchar(50), newCreditCardExpireDate varchar(16))
	BEGIN
		update Customer set CreditCardExpireDate = newCreditCardExpireDate
        where UsernameID = updatedUserNameID;
	END$$
    
    
CREATE PROCEDURE updateProgramID
	(updatedID int unsigned , newID int)
	BEGIN
		update Program set ID = newID
        where ID = updatedID;
	END$$
    
CREATE PROCEDURE updateProgramBMI
	(updatedID int unsigned ,newBMI float)
	BEGIN
		update Program set BMI = newBMI
        where ID = updatedID;
	END$$
    
    
CREATE PROCEDURE updateExerciseName
	(updatedName varchar(100), newName varchar(100))
	BEGIN
		update Exercise set `Name` = newName
        where `Name` = updatedName;
	END$$
    
    
CREATE PROCEDURE updateExerciseSet
	(updatedName varchar(100), newSet int)
	BEGIN
		update Exercise set `Set` = newSet
        where `Name` = updatedName;
	END$$
    
    
CREATE PROCEDURE updateExerciseRepetitionPerSet 
	(updatedName varchar(100), newRepetitionPerSet int)
	BEGIN
		update Exercise set RepetitionPerSet = newRepetitionPerSet
        where `Name` = updatedName;
	END$$
    
CREATE PROCEDURE updateSportToolsName 
	(updatedName varchar(100), newName varchar(100))
	BEGIN
		update Sport_Tools set `Name` = newName
        where `Name` = updatedName;
	END$$
    
CREATE PROCEDURE updateSportToolsAmount  
	(updatedName varchar(100), newAmount int)
	BEGIN
		update Sport_Tools set Amount = newAmount
        where `Name` = updatedName;
	END$$
    
CREATE PROCEDURE updateSportToolsUrlImage  
	(updatedName varchar(100), newUrlImage varchar(2000))
	BEGIN
		update Sport_Tools set UrlImage = newUrlImage
        where `Name` = updatedName;
	END$$
    
    
CREATE PROCEDURE updateLessonName
	(updatedID int unsigned, newName varchar(250))
	BEGIN
		update Lesson set `Name` = newName
        where ID = updatedID;
	END$$
    
    
CREATE PROCEDURE updateLessonTrainerID 
	(updatedID int unsigned, newTrainerID varchar(50))
	BEGIN
		update Lesson set TrainerID = newTrainerID
        where ID = updatedID;
	END$$
    
    
CREATE PROCEDURE updateLessonProfessionName 
	(updatedID int unsigned, newProfessionName varchar(100))
	BEGIN
		update Lesson set ProfessionName = newProfessionName
        where ID = updatedID;
	END$$
 
    
    
CREATE PROCEDURE updateLessonPrice 
	(updatedID int unsigned, newPrice float)
	BEGIN
		update Lesson set Price = newPrice
        where ID = updatedID;
	END$$
    
    
CREATE PROCEDURE updateEnrollTimeLessonName
	(updatedID int unsigned, updatedDay varchar(10), newLessonName varchar(250))
	BEGIN
		update Enroll_Time set LessonName = newLessonName
        where ID = updatedID and `Day` = updatedDay;
	END$$
    
CREATE PROCEDURE updateEnrollTimeDay
	(updatedLessonID int unsigned, updatedDay varchar(10), newDay varchar(10))
	BEGIN
		update Enroll_Time set `Day` = newDay
        where LessonID = updatedLessonID and `Day` = updatedDay;
	END$$
    
CREATE PROCEDURE updateEnrollTimeStartTime 
	(updatedLessonID int unsigned, updatedDay varchar(10), newStartTime time)
	BEGIN
		update Enroll_Time set newStartTime = newStartTime 
        where LessonID = updatedLessonID and `Day` = updatedDay;
	END$$
    
    
CREATE PROCEDURE updateEnrollTimeEndTime 
	(updatedLessonID int unsigned, updatedDay varchar(10), newEndTime time)
	BEGIN
		update Enroll_Time set EndTime = newEndTime
        where LessonID = updatedLessonID and `Day` = updatedDay;
	END$$
    
CREATE PROCEDURE updateEnrollTimeQuota 
	(updatedLessonID int unsigned, updatedDay varchar(10), newQuota int)
	BEGIN
		update enroll_time set Quota = newQuota
        where LessonID = updatedLessonID and `Day` = updatedDay;
	END$$
    
    
CREATE PROCEDURE updatePurchasePurchasedDate 
	(updatedCustomerID varchar(50), updatedLessonID int unsigned, updatedEnrollTimeDay varchar(10), newPurchasedDate datetime)
	BEGIN
		update Purchase set PurchasedDate = newPurchasedDate
        where CustomerID = updatedCustomerID and LessonID = updatedLessonID and EnrollTimeDay = updatedEnrollTimeDay;
	END$$
    
    
CREATE PROCEDURE updatePhysicalAilmentName
	(updatedName varchar(100), newName varchar(100))
	BEGIN
		update Physical_Ailment set `Name` = newName 
        where `Name` = updatedName;
	END$$
    
    
CREATE PROCEDURE updateUsedInQuantity
	(updatedSportToolsName varchar(100), updatedLessonID int unsigned, newQuantity int)
	BEGIN
		update used_in set Quantity = newQuantity
        where SportToolsName = updatedSportToolsName and  LessonID = updatedLessonID;
	END$$
    
DELIMITER ;






  