DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;
CREATE TABLE teacher
(
    id integer NOT NULL,
    name varchar(50) NOT NULL,
    age integer NOT NULL,
    CONSTRAINT teacher_pkey PRIMARY KEY (id)
);
CREATE TABLE student
(
    id integer NOT NULL,
    name varchar(50) NOT NULL,
    age integer NOT NULL,
    CONSTRAINT student_pkey PRIMARY KEY (id)
);
INSERT INTO teacher VALUES (1, 'Max', 20);
INSERT INTO teacher VALUES (2, 'Bob', 21);
INSERT INTO teacher VALUES (3, 'Smith', 22);
INSERT INTO teacher VALUES (4, 'Cat', 23);
