INSERT INTO Department VALUES (479680, 'Computer Sceince');
INSERT INTO Department VALUES (423231, 'Biology');
INSERT INTO Department VALUES (435339, 'Chemistry');
INSERT INTO Department VALUES (449959, 'Law');
INSERT INTO Department VALUES (426304, 'Electrical Engineering');
INSERT INTO Department VALUES (407709, 'English');
INSERT INTO Department VALUES (498654, 'Philosophy');
INSERT INTO Department VALUES (470741, 'Information Systems Technology');
INSERT INTO Department VALUES (477363, 'Business Administration');
INSERT INTO Department VALUES (416684, 'Medicine');

INSERT INTO Faculty VALUES (221320, 'Carolyn Murphy', 416684);
INSERT INTO Faculty VALUES (269040, 'Thomas Carpenter', 423231);
INSERT INTO Faculty VALUES (214074, 'Lisa Bowman', 479680);
INSERT INTO Faculty VALUES (217222, 'Albert Watson', 449959);
INSERT INTO Faculty VALUES (268862, 'Janice Gutierrez', 426304);
INSERT INTO Faculty VALUES (283132, 'Louis Pierce', 407709);
INSERT INTO Faculty VALUES (299657, 'Doris Gibson', 470741);
INSERT INTO Faculty VALUES (271928, 'Denise Patterson', 477363);
INSERT INTO Faculty VALUES (292016, 'Janice Cunningham', 498654);
INSERT INTO Faculty VALUES (215748, 'Emily Ward', 435339);

INSERT INTO Staff VALUES (350089, 'Rose Peterson', 479680);
INSERT INTO Staff VALUES (330543, 'Dennis Lawson', 423231);
INSERT INTO Staff VALUES (355611, 'Timothy Austin', 435339);
INSERT INTO Staff VALUES (351317, 'Daniel Morrison', 449959);
INSERT INTO Staff VALUES (334916, 'Paula Arnold', 426304);
INSERT INTO Staff VALUES (379154, 'Linda Bennett', 407709);
INSERT INTO Staff VALUES (338500, 'Russell Bryant', 498654);
INSERT INTO Staff VALUES (369356, 'Judy Stanley', 470741);
INSERT INTO Staff VALUES (346411, 'Bruce Montgomery', 477363);
INSERT INTO Staff VALUES (378544, 'Helen Ross', 416684);

INSERT INTO Student VALUES(119731, 'Kimberleigh Vernon', 'Computer Science', 'Junior', 21);
INSERT INTO Student VALUES(139386, 'Fern Aukes', 'Electrical Engineering', 'Sophomore', 20);
INSERT INTO Student VALUES(159995, 'Gerlinde Ravenna', 'English', 'Freshman', 18);
INSERT INTO Student VALUES(117335, 'Lois P. Huff', 'Philosophy', 'Masters', 24);
INSERT INTO Student VALUES(123932, 'Lorraine J. Bailey', 'Computer Science', 'Senior', 22);
INSERT INTO Student VALUES(192090, 'Anthony B. Lucero', 'English', 'PhD', 26);
INSERT INTO Student VALUES(115982, 'Donna A. Wegman', 'Information Systems Technology', 'Freshman', 18);
INSERT INTO Student VALUES(173541, 'Margaret J. Bishop', 'Business Administration', 'Masters', 24);
INSERT INTO Student VALUES(169810, 'Vivian D. Shive', 'Computer Science', 'PhD', 25);
INSERT INTO Student VALUES(130412, 'Joann O. Cormack', 'Electrical Engineering', 'Junior', 30);

INSERT INTO Courses VALUES ('CS 430', 'Introduction to Databases', 'MWF 10-11', 'A320', 221320, 35);
INSERT INTO Courses VALUES ('ENGL 101', 'English Literature and Comp', 'MWF 1-2', 'B1024', 269040, 100);
INSERT INTO Courses VALUES ('EL 102', 'Introduction to Electrical Eng', 'TR 10-12', 'B1020', 214074, 40);
INSERT INTO Courses VALUES ('PHIL 304', 'Philosophy Through the Years', 'MWF 8-9', 'A200', 217222, 30);
INSERT INTO Courses VALUES ('LAW 400', 'Contract Law', 'TR 6-8', 'B2048', 268862, 150);
INSERT INTO Courses VALUES ('CS 201', 'Introduction to CS', 'TR 1-3', 'A304', 283132, 150);
INSERT INTO Courses VALUES ('MED 101', 'Introduction to Med', 'MWF 12-1', 'C5113', 299657, 30);
INSERT INTO Courses VALUES ('IST 302', 'Network Security', 'MWF 3-4', 'A102', 271928, 45);
INSERT INTO Courses VALUES ('CHEM 310', 'Organic Chemistry', 'TR 3-5', 'C1020', 215748, 50);
INSERT INTO Courses VALUES ('BIO 101', 'Introduction to Biology', 'TR 8-10', 'A100', 292016, 150);

INSERT INTO Enrolled VALUES (119731, 'CS 430', 70, 80, 75);
INSERT INTO Enrolled VALUES (139386, 'ENGL 101', 85, 90, 87);
INSERT INTO Enrolled VALUES (159995, 'EL 102', 65, 70, 85);
INSERT INTO Enrolled VALUES (117335, 'PHIL 304', 90, 95, 100);
INSERT INTO Enrolled VALUES (123932, 'LAW 400', 100, 98, 100);
INSERT INTO Enrolled VALUES (192090, 'CS 201', 86, 91, 88);
INSERT INTO Enrolled VALUES (115982, 'MED 101', 96, 97, 95);
INSERT INTO Enrolled VALUES (173541, 'IST 302', 56, 62, 75);
INSERT INTO Enrolled VALUES (169810, 'CHEM 310', 76, 82, 87);
INSERT INTO Enrolled VALUES (130412, 'BIO 101', 91, 93, 97);

