/* Drop tables from database */
DROP TABLE Enrolled;
DROP TABLE Courses;
DROP TABLE Student;
DROP TABLE Faculty;
DROP TABLE Staff;
DROP TABLE Department;
DROP VIEW staffView;
DROP VIEW facultyView;
DROP VIEW enrolledStudent;
DROP VIEW courseView;


/* Create new tables with constraints */
/* Create Department table */
CREATE TABLE Department(did INTEGER, dname VARCHAR(50), PRIMARY KEY(did));
/* Create Student table */
CREATE TABLE Student(sid INTEGER, sname VARCHAR(100), major VARCHAR(50), s_level VARCHAR(30), age INTEGER,
PRIMARY KEY(sid), CHECK (s_level = 'Freshman' OR s_level = 'Sophomore' OR s_level = 'Junior' OR
                         s_level = 'Senior' OR s_level = 'Masters' OR s_level = 'PhD'));

/* Create Faculty table */
CREATE TABLE Faculty (
  fid    INTEGER,
  fname  VARCHAR(100),
  deptid INTEGER,
  PRIMARY KEY (fid),
  FOREIGN KEY (deptid) REFERENCES
    Department (did) ON DELETE CASCADE
);

/* Create Staff table */
CREATE TABLE Staff (
  sid    INTEGER,
  sname  VARCHAR(100),
  deptid INTEGER,
  PRIMARY KEY (sid),
  FOREIGN KEY (deptid) REFERENCES
    Department (did) ON DELETE CASCADE
);

/* Create Courses table */
CREATE TABLE Courses(cid   VARCHAR(10), cname VARCHAR(100), meets_at VARCHAR(20), room VARCHAR(30), fid INTEGER REFERENCES
  Faculty (fid) ON DELETE CASCADE,
                     limit INTEGER CHECK (limit < 200),
  PRIMARY KEY (cid)
);

/* Create Enrolled table */
CREATE TABLE Enrolled(sid INTEGER, cid VARCHAR(10), exam1 INTEGER, exam2 INTEGER, final INTEGER, FOREIGN KEY(sid) REFERENCES
  Student (sid) ON DELETE CASCADE,
  FOREIGN KEY (cid) REFERENCES Courses (cid) ON DELETE CASCADE
);

CREATE VIEW staffView AS
  SELECT
    sid,
    sname,
    dname
  FROM Staff, Department
  WHERE did = Staff.deptid;

CREATE VIEW facultyView AS
  SELECT
    fid,
    fname,
    dname
  FROM Faculty, Department
  WHERE did = Faculty.deptid;

CREATE VIEW enrolledStudent AS
  SELECT *
  FROM Student
    NATURAL JOIN Enrolled
    NATURAL JOIN Courses
    NATURAL JOIN Faculty;

CREATE VIEW courseView AS
  SELECT *
  FROM Courses
    NATURAL JOIN Faculty;