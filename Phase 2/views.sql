create view amount_of_customer_in_class as -- To check if the quota is full
select LessonID, EnrollTimeDay, count(*)
from Purchase
group by LessonID, EnrollTimeDay;

create view Customer_System_User as
select 
	c.UsernameID , 
    s.Password,
    MembershipTypeName ,
    TrainerID , 
    `Name` , 
    Surname , 
    Weight, 
    Length, 
    Age, 
    FatRatio, 
    CreditCardNumber, 
    CreditCardExpireDate
from customer c, system_user s
where c.UsernameID = s.UsernameID;

create view Trainer_System_User as
select 
	t.UsernameID , 
    s.Password,
    `Name`, 
    Surname , 
    Salary
from trainer as t, system_user as s
where t.UsernameID = s.UsernameID;

create view Program_Exercises_SportTools as
select Program.ID, Exercise.`Name` as ExerciseName, Exercise.`Set`, 
	Exercise.`RepetitionPerSet`, Sport_Tools.`Name` as SportToolsName
from Program, Consist_Of, Exercise, Make_With, Sport_Tools
where Program.ID = Consist_Of.ProgramID and Consist_Of.ExerciseName = Exercise.`Name`
	and Exercise.`Name` = Make_With.ExerciseName 
    and Sport_Tools.`Name` = Make_With.SportToolsName
group by Program.ID, Exercise.`Name`, Sport_Tools.`Name`;

create view Customer_BMIs as
 select UsernameID as username , 10000*Weight/(Length*Length)  as bmi
from Customer


