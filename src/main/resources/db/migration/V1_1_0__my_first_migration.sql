CREATE TABLE IF NOT EXISTS groups (
    id SERIAL PRIMARY KEY,
    group_name character varying(30)
);

CREATE TABLE IF NOT EXISTS courses (
    id SERIAL PRIMARY KEY,
    course_name varchar (30),
    course_description  varchar(60)
);


CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    group_id integer,
    first_name  varchar (30),
    last_name varchar (30),
    FOREIGN KEY (group_id) REFERENCES groups (id)  ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS studentsandcourses (
    id SERIAL PRIMARY KEY,
    student_id integer,
    course_id integer,
    FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses (id)   ON DELETE CASCADE
);
