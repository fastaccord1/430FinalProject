/* Drop tables from database */
DROP TABLE Student;
DROP TABLE Courses;
DROP TABLE Enrolled;
DROP TABLE Faculty;
DROP TABLE Staff;
DROP TABLE Department;


/* Create new tables with constraints */
/* Create Department table */
CREATE TABLE Department(did INTEGER, dname VARCHAR(50), PRIMARY KEY(did));
/* Create Student table */
CREATE TABLE Student(sid INTEGER, sname VARCHAR(100), major VARCHAR(50), s_level VARCHAR(30), age INTEGER,
CHECK (s_level = 'Freshman' OR 'Sophomore' OR 'Junior' OR 'Senior' OR 'Masters' OR 'PHD'), PRIMARY KEY(sid));

/* Create Faculty table */
CREATE TABLE Faculty(fid INTEGER, fname VARCHAR(100), deptid INTEGER REFERENCES Department(did), PRIMARY KEY(fid));

/* Create Staff table */
CREATE TABLE Staff(sid INTEGER, sname VARCHAR(100), deptid INTEGER REFERENCES Department(did), PRIMARY KEY(sid));

/* Create Courses table */
CREATE TABLE Courses(cid VARCHAR(7), cname VARCHAR(50), meets_at VARCHAR(20), room VARCHAR(30), fid INTEGER REFERENCES
Faculty(fid), limit INTEGER CHECK(limit < 200), PRIMARY KEY(cid));

/* Create Enrolled table */
CREATE TABLE Enrolled(sid INTEGER, cid INTEGER, exam1 INTEGER, exam2 INTEGER, final INTEGER, FOREIGN KEY(sid) REFERENCES
STudent(sid), FOREIGN KEY(cid) REFERENCES Courses(cid));