/* Drop tables from database */
DROP TABLE Enrolled;
DROP TABLE Courses;
DROP TABLE Student;
DROP TABLE Faculty;
DROP TABLE Staff;
DROP TABLE Department;


/* Create new tables with constraints */
/* Create Department table */
CREATE TABLE Department(did INTEGER, dname VARCHAR(50), PRIMARY KEY(did));
/* Create Student table */
CREATE TABLE Student(sid INTEGER, sname VARCHAR(100), major VARCHAR(50), s_level VARCHAR(30), age INTEGER,
PRIMARY KEY(sid), CHECK (s_level = 'Freshman' OR s_level = 'Sophomore' OR s_level = 'Junior' OR
                         s_level = 'Senior' OR s_level = 'Masters' OR s_level = 'PhD'));

/* Create Faculty table */
CREATE TABLE Faculty(fid INTEGER, fname VARCHAR(100), deptid INTEGER REFERENCES Department(did), PRIMARY KEY(fid));

/* Create Staff table */
CREATE TABLE Staff(sid INTEGER, sname VARCHAR(100), deptid INTEGER REFERENCES Department(did), PRIMARY KEY(sid));

/* Create Courses table */
CREATE TABLE Courses(cid VARCHAR(10), cname VARCHAR(100), meets_at VARCHAR(20), room VARCHAR(30), fid INTEGER REFERENCES
Faculty(fid), limit INTEGER CHECK(limit < 200), PRIMARY KEY(cid));

/* Create Enrolled table */
CREATE TABLE Enrolled(sid INTEGER, cid VARCHAR(10), exam1 INTEGER, exam2 INTEGER, final INTEGER, FOREIGN KEY(sid) REFERENCES
Student(sid), FOREIGN KEY(cid) REFERENCES Courses(cid));