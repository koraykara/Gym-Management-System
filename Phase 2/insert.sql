
call insert_trainer('james1', 'James', 'Arthur', 10000.0);
call insert_trainer('jany', 'Janothan', 'Can', 10000.0);
call insert_trainer('mustapha', 'Mustapha', 'Can', 10000.0);
call insert_trainer('jam', 'James', 'Michael', 10000.0);
call insert_trainer('mic', 'Michael', 'Schwartzenegger', 10000.0);
call insert_trainer('darnold', 'Arnold', 'Schwartzenegger', 10000.0);
call insert_trainer('arnold', 'Arnold', 'Civardagezer', 10000.0);
call insert_trainer('anakin', 'Anakin', 'Skywalker', 12000.0);
call insert_trainer('jack', 'Jack', 'Johns', 2000.0);
call insert_trainer('liv', 'Liv', 'Tyler', 10000.0);
call insert_trainer('willy', 'Willy', 'Wonka', 11000.0);
call insert_trainer('james', 'James', 'Bond', 6000.0);
call insert_trainer('cprice', 'Captain', 'Price', 10000.0);
call insert_trainer('claudius11', 'Claudius', 'Schwarzenegger', 13000.0);
call insert_trainer('brendand', 'Brendan', 'Schwarzenegger', 1330.0);
call insert_trainer('arnold98', 'Arnold', 'Claudius', 4700.0);

call insert_profession('Body Builder');
call insert_profession('Yoga Instructor');
call insert_profession('Crossfit');
call insert_profession('Mixed Martial Arts Trainer');
call insert_profession('Athlete');
call insert_profession('Far East Martial Arts Trainer');
call insert_profession('Physical Therapist');
call insert_profession('Pilates Instructor');
call insert_profession('Dance Instructor');
call insert_profession('Swimming Coach');

call insert_be_profession('Body Builder', 'arnold');
call insert_be_profession( 'Yoga Instructor', 'anakin');
call insert_be_profession( 'Dance Instructor', 'anakin');
call insert_be_profession('Athlete', 'anakin');
call insert_be_profession( 'Body Builder', 'jack');
call insert_be_profession('Far East Martial Arts Trainer', 'liv');
call insert_be_profession( 'Crossfit', 'willy');
call insert_be_profession( 'Mixed Martial Arts Trainer', 'james');
call insert_be_profession( 'Physical Therapist', 'cprice');
call insert_be_profession( 'Pilates Instructor', 'claudius11');
call insert_be_profession( 'Swimming Coach', 'brendand');
call insert_be_profession( 'Body Builder', 'arnold98');


call insert_lesson('Body Building', 'arnold', 'Body Builder', 100);
call insert_lesson('Power Building', 'willy', 'Crossfit',200);
call insert_lesson('Kick Box', 'james', 'Mixed Martial Arts Trainer', 100);
call insert_lesson('Yoga', 'anakin', 'Yoga Instructor', 250);
call insert_lesson('Step Aerobik', 'cprice', 'Physical Therapist', 350);
call insert_lesson('Aikido', 'liv', 'Far East Martial Arts Trainer', 200);
call insert_lesson('Pilates', 'claudius11', 'Pilates Instructor', 220);
call insert_lesson('Group Running', 'anakin', 'Athlete', 80);
call insert_lesson('Zumba', 'anakin', 'Dance Instructor', 50);
call insert_lesson('Swimming', 'brendand', 'Swimming Coach', 100);

call insert_enroll_time(1, 'Sunday','12:00:00', '13:00:00', 10);
call insert_enroll_time(2, 'Sunday','10:00:00', '11:00:00', 10);
call insert_enroll_time(3, 'Tuesday','16:00:00', '17:00:00', 15);
call insert_enroll_time(4, 'Monday','15:00:00', '16:00:00', 12);
call insert_enroll_time(4, 'Thursday','15:00:00', '16:00:00', 27);
call insert_enroll_time(6, 'Saturday','18:00:00', '19:00:00', 10);
call insert_enroll_time(8, 'Friday','18:00:00', '17:00:00', 30);
call insert_enroll_time(8, 'Monday','12:00:00', '13:00:00', 30);
call insert_enroll_time(2, 'Tuesday','10:00:00', '11:00:00', 10);
call insert_enroll_time(10, 'Friday','20:00:00', '21:00:00', 20);

call insert_membership_type('Monthly', 100.0);
call insert_membership_type('1 Year', 1000.0);
call insert_membership_type('6 Months', 500.0);
call insert_membership_type('9 Months', 700.0);
call insert_membership_type('1 Months', 150.0);
call insert_membership_type('24 Months', 2000.0);
call insert_membership_type('3 Months', 200.0);
call insert_membership_type('10 Months', 800.0);
call insert_membership_type('16 Months', 1200.0);
call insert_membership_type('3 Year', 2500.0);

call insert_customer('oktay98', 'Monthly','liv', 'Oktay', 'Ugurlu', 110.0, 191, 40, 20.0, '1111111111111112', '20201207');
call insert_customer('aybart', 'Monthly','james', 'Aybar', 'Tas', 70.0, 171, 22, 20.0, '2222222222222222', '20201205');
call insert_customer('koray', '1 Year','james', 'Koray', 'Kara', 80.0, 195, 22, 12.0, '3333333333333332', '20241008');
call insert_customer('antonina', '6 Months','anakin', 'Antonina', 'Paulina', 90.0, 163, 35, 30.0, '4444444444444444', '20220403');
call insert_customer('anthony', 'Monthly','cprice', 'Anthony', 'Hopkins', 100.0, 191, 22, 20.0, '5555555555555555', '20201208');
call insert_customer('ryana', '3 Year','brendand', 'Ryana', 'Tas', 60.0, 175, 28, 12.0, '2222222222222222', '20211208');
call insert_customer('fernando', 'Monthly','willy', 'Fernando', 'Faustino', 100.0, 191, 22, 20.0, '2222222222222233', '20201218');
call insert_customer('fernando1', 'Monthly','willy', 'Fernando', 'Tas', 120.0, 188, 32, 30.0, '1616161616161616', '20230228');
call insert_customer('muzaffer', '10 Months','arnold98', 'Lovisa', 'Sandra', 77.0, 178, 19, 10.0, '6161616161616161', '20171218');
call insert_customer('jackie', '3 Year','jack', 'Jack', 'James', 80.0, 168, 44, 18.0, '2222222333333339', '20201208');

call insert_purchase ('oktay98', 1, 'Sunday', '20140607');
call insert_purchase ('oktay98', 2, 'Sunday', '20160608');
call insert_purchase ('jackie', 3, 'Tuesday', '20191127');
call insert_purchase ('fernando1', 3, 'Tuesday', '20190914');
call insert_purchase ('anthony', 2, 'Sunday', '20190613');
call insert_purchase ('jackie', 4, 'Monday', '20190928');
call insert_purchase ('antonina', 6, 'Saturday', '20190307');
call insert_purchase ('koray', 8, 'Friday', '20190127');
call insert_purchase ('aybart', 10, 'Friday', '20191224');
call insert_purchase ('muzaffer', 10, 'Friday', '20191219');

call insert_sport_tools ('dumbell 16 kg', 50, 'https://media.gettyimages.com/photos/dumbbells-with-copy-space-picture-id103932734?s=2048x2048');
call insert_sport_tools ('dumbell 6 kg', 50, 'https://media.gettyimages.com/photos/dumbbells-with-copy-space-picture-id103932734?s=2048x2048');
call insert_sport_tools ('dumbell 10 kg', 50, 'https://media.gettyimages.com/photos/dumbbells-with-copy-space-picture-id103932734?s=2048x2048');
call insert_sport_tools ('Power Rack', 30, 'https://i.pinimg.com/236x/1f/0a/07/1f0a07206cd6caa6546642988657fcf6.jpg');
call insert_sport_tools ('Barbell 30 kg', 30, 'https://media.istockphoto.com/photos/side-view-of-athletic-woman-exercising-deadlift-in-a-gym-picture-id1097313896');
call insert_sport_tools ('Barbell 60 kg', 30, 'https://media.istockphoto.com/photos/side-view-of-athletic-woman-exercising-deadlift-in-a-gym-picture-id1097313896');
call insert_sport_tools ('Incline Bench Press', 45, 'https://image.shutterstock.com/z/stock-photo-young-bodybuilder-training-in-the-gym-chest-barbell-incline-bench-press-wide-grip-68880229.jpg');
call insert_sport_tools ('Stability Ball', 50, 'https://image.shutterstock.com/z/stock-photo-the-blue-exercise-ball-placed-in-the-center-of-the-fitness-room-on-the-exercise-ball-floor-is-for-717396415.jpg');
call insert_sport_tools ('dumbell 12 kg', 50, 'https://media.gettyimages.com/photos/dumbbells-with-copy-space-picture-id103932734?s=2048x2048');
call insert_sport_tools ('dumbell 8 kg', 50, 'https://media.gettyimages.com/photos/dumbbells-with-copy-space-picture-id103932734?s=2048x2048');
call insert_sport_tools ('treadmill', 40, 'https://image.shutterstock.com/z/stock-photo-trademill-isolated-on-white-background-1531722590.jpg');
call insert_sport_tools ('sandbag', 20, 'https://image.shutterstock.com/z/stock-photo-a-sandbag-hanging-in-front-of-a-boxing-ring-have-a-janitor-was-cleaning-1387323953.jpg');
call insert_sport_tools ('Meditating Mat', 60, 'https://image.shutterstock.com/z/stock-photo-mindfulness-silent-relaxation-meditating-mat-and-cushions-zen-buddhist-center-1224135292.jpg');
call insert_sport_tools ('Training Mat', 60, 'https://image.shutterstock.com/z/stock-photo-mindfulness-silent-relaxation-meditating-mat-and-cushions-zen-buddhist-center-1224135292.jpg');

call insert_used_in('dumbell 16 kg', 1, 20);
call insert_used_in('Incline Bench Press', 1, 10);
call insert_used_in('sandbag', 3, 20);
call insert_used_in('Power Rack', 2, 10);
call insert_used_in('Barbell 60 kg', 2, 10);
call insert_used_in('Barbell 30 kg', 2,10);
call insert_used_in('Meditating Mat', 4,30);
call insert_used_in('Stability Ball', 7,30);
call insert_used_in('Training Mat', 9,30);
call insert_used_in('treadmill', 8,30);
call insert_used_in('dumbell 12 kg', 1,10);


call insert_lesson('Body Building', 'arnold', 'Body Builder', 100);
call insert_lesson('Power Building', 'willy', 'Crossfit',200);
call insert_lesson('Kick Box', 'james', 'Mixed Martial Arts Trainer', 100);
call insert_lesson('Yoga', 'anakin', 'Yoga Instructor', 250);
call insert_lesson('Step Aerobik', 'cprice', 'Physical Therapist', 350);
call insert_lesson('Aikido', 'liv', 'Far East Martial Arts Trainer', 200);
call insert_lesson('Pilates', 'claudius11', 'Pilates Instructor', 220);
call insert_lesson('Group Running', 'anakin', 'Athlete', 80);
call insert_lesson('Zumba', 'anakin', 'Dance Instructor', 50);
call insert_lesson('Swimming', 'brendand', 'Swimming Coach', 100);



call insert_program (18.5);
call insert_program (20.5);
call insert_program (15.5);
call insert_program (22.5);
call insert_program (24.5);
call insert_program (12.5);
call insert_program (21.5);
call insert_program (29.5);
call insert_program (27.5);
call insert_program (23.5);

call insert_physical_ailment('asthma');
call insert_physical_ailment('hernia');
call insert_physical_ailment('cardiac disease');
call insert_physical_ailment('gestation');
call insert_physical_ailment('epilepsy');
call insert_physical_ailment('skolyoz');
call insert_physical_ailment('stable angina');
call insert_physical_ailment('meniscus');
call insert_physical_ailment('acute rheumatic fever');
call insert_physical_ailment('hypertension');

call insert_has('oktay98', 'asthma');
call insert_has('oktay98', 'hernia');
call insert_has('jackie', 'asthma');
call insert_has('jackie', 'meniscus');
call insert_has('fernando1', 'hypertension');
call insert_has('koray', 'hernia');
call insert_has('fernando1', 'cardiac disease');
call insert_has('muzaffer', 'epilepsy');
call insert_has('anthony', 'asthma');
call insert_has('aybart', 'asthma');

call insert_asked_for('oktay98', 2);
call insert_asked_for('jackie', 2);
call insert_asked_for('muzaffer', 1);
call insert_asked_for('muzaffer', 2);
call insert_asked_for('muzaffer', 3);
call insert_asked_for('aybart', 2);
call insert_asked_for('aybart', 10);
call insert_asked_for('ryana', 2);
call insert_asked_for('fernando', 5);
call insert_asked_for('fernando', 6);
call insert_asked_for('fernando', 7);

call insert_not_fit('asthma', 1);
call insert_not_fit('meniscus', 1);
call insert_not_fit('gestation', 1);
call insert_not_fit('meniscus', 2);
call insert_not_fit('gestation', 2);
call insert_not_fit('meniscus', 5);
call insert_not_fit('gestation', 5);
call insert_not_fit('meniscus', 7);
call insert_not_fit('gestation', 7);
call insert_not_fit('meniscus', 9);
call insert_not_fit('gestation', 9);
call insert_not_fit('skolyoz', 10);
call insert_not_fit('epilepsy', 10);
call insert_not_fit('meniscus', 10);


call insert_exercise('push up-easy', 3, 20);
call insert_exercise('push up-medium', 5, 50);
call insert_exercise('push up-hard', 5, 100);
call insert_exercise('squat-easy', 3, 20);
call insert_exercise('squat-medium', 4, 30);
call insert_exercise('deadlift-easy', 3, 10);
call insert_exercise('chest fly-easy', 3, 10);
call insert_exercise('biceps curl-easy', 5, 20);
call insert_exercise('biceps curl-medium', 4, 20);
call insert_exercise('triceps extension-easy', 3, 20);
call insert_exercise('triceps extension-medium', 4, 20);
call insert_exercise('shoulder fly-easy', 3, 20);
call insert_exercise('split running-500m', 3, NULL);
call insert_exercise('zumba', NULL, NULL);
call insert_exercise('swimming', NULL, NULL);

call insert_consist_of(1,'push up-easy');
call insert_consist_of(1,'swimming');
call insert_consist_of(1,'split running-500m');
call insert_consist_of(2,'deadlift-easy');
call insert_consist_of(2,'squat-easy');
call insert_consist_of(2,'biceps curl-medium');
call insert_consist_of(2,'triceps extension-medium');
call insert_consist_of(3,'shoulder fly-easy');
call insert_consist_of(3,'chest fly-easy');
call insert_consist_of(3,'triceps extension-easy');

call insert_consist_of(4,'chest fly-easy');
call insert_consist_of(5,'squat-medium');
call insert_consist_of(6,'triceps extension-easy');
call insert_consist_of(7,'squat-medium');
call insert_consist_of(8,'shoulder fly-easy');
call insert_consist_of(9,'split running-500m');
call insert_consist_of(10,'zumba');


call insert_make_with('Power Rack', 'deadlift-easy' );
call insert_make_with('Barbell 30 kg', 'deadlift-easy');
call insert_make_with('Power Rack', 'squat-easy' );
call insert_make_with('Barbell 30 kg', 'squat-easy' );
call insert_make_with('Power Rack', 'squat-medium' );
call insert_make_with('Barbell 60 kg', 'squat-medium');
call insert_make_with('dumbell 8 kg', 'chest fly-easy');
call insert_make_with('dumbell 10 kg', 'biceps curl-easy');
call insert_make_with('dumbell 12 kg', 'biceps curl-medium');
call insert_make_with('dumbell 8 kg', 'triceps extension-easy');
call insert_make_with( 'dumbell 16 kg', 'triceps extension-medium');
call insert_make_with('dumbell 6 kg', 'shoulder fly-easy');



call updateSystemUserPassword('arnold', '1244'); 
call updateSystemUserPassword('anakin', '23434633gd'); 
call updateSystemUserPassword('jack', 'sdff4t353'); 
call updateSystemUserPassword('liv', 'df5yrt56'); 
call updateSystemUserPassword('willy', 'Asfr4353'); 
call updateSystemUserPassword('james', 'asSDDFEe45'); 
call updateSystemUserPassword('cprice', '23r4sdfs'); 
call updateSystemUserPassword('claudius11', '24afewdsf'); 
call updateSystemUserPassword('brendand', '13424sdfs'); 
call updateSystemUserPassword('arnold98', 'ad333'); 

call updateSystemUserPassword('oktay98', '12435dfg44'); 
call updateSystemUserPassword('aybart', '232ffgd434633gd'); 
call updateSystemUserPassword('koray', 's2342dff4t353'); 
call updateSystemUserPassword('antonina', 'df23425yrt56'); 
call updateSystemUserPassword('anthony', 'Asfr4353'); 
call updateSystemUserPassword('ryana', 'asSDDFEe45'); 
call updateSystemUserPassword('fernando', '23r4sdfs'); 
call updateSystemUserPassword('fernando1', '24atrhfewdsf'); 
call updateSystemUserPassword('muzaffer', '13424sdfs'); 
call updateSystemUserPassword('jackie', 'ad3WE33'); 

call updateSystemUserEndDate('oktay98', '20190707');
call updateSystemUserEndDate('darnold', '20150413'); 
call updateSystemUserEndDate('mic', '20181204'); 
call updateSystemUserEndDate('jam', '20171204'); 
call updateSystemUserEndDate('mustapha', '20161026'); 
call updateSystemUserEndDate('jan', '20131221'); 
call updateSystemUserEndDate('james1', '20181221');  








