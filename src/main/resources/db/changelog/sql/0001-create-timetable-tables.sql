--liquibase formatted sql

--changeset svintenok:1

CREATE AGGREGATE MULT(bigint) ( SFUNC = int8mul, STYPE=bigint);

CREATE TABLE study_course(
	id serial PRIMARY KEY,
	course_num integer UNIQUE NOT NULL
);

CREATE TABLE study_group(
	id serial PRIMARY KEY,
	group_num varchar(6) UNIQUE NOT NULL,
	course_id integer REFERENCES study_course (id),
  student_count integer DEFAULT -1
);

CREATE TABLE group_set(
  id serial PRIMARY KEY,
  name varchar(50) UNIQUE NOT NULL
);

CREATE TABLE group_set_group(
  id serial PRIMARY KEY,
  study_group_id integer REFERENCES study_group (id),
  group_set_id integer REFERENCES group_set (id)
);

CREATE TABLE subject_course(
	id serial PRIMARY KEY,
	pair_count integer,
	name varchar(100) NOT NULL,
	study_course_id integer REFERENCES study_course (id),
  in_two_weeks bool DEFAULT FALSE,
  opt_course_block bool DEFAULT FALSE
);

--CREATE TABLE opt_course_block(
--  id serial PRIMARY KEY,
--  name varchar(100) NOT NULL,
--  study_course_id integer REFERENCES study_course (id)
--);

--CREATE TABLE opt_course_block_subject(
--  id serial PRIMARY KEY,
--  subject_course_id integer REFERENCES subject_course (id),
--  opt_course_block_id integer REFERENCES opt_course_block (id)
--);



CREATE TABLE opt_subject_course(
  id serial PRIMARY KEY,
  name varchar(100) NOT NULL,
  subject_course integer REFERENCES subject_course (id),
	study_course_id integer REFERENCES study_course (id),
  student_count integer DEFAULT -1
);

CREATE TABLE professor(
	id serial PRIMARY KEY,
	name varchar(50) NOT NULL
);

CREATE TABLE auditory(
	id serial PRIMARY KEY,
	capacity integer DEFAULT -1,
	lecture_room bool DEFAULT FALSE,
	auditory_number varchar(20) NOT NULL
);

CREATE TABLE timeslot_day(
	id serial PRIMARY KEY,
	day_num integer UNIQUE NOT NULL,
	day_name varchar
);

CREATE TABLE timeslot_time(
	id serial PRIMARY KEY,
	pair_num integer UNIQUE NOT NULL,
	time_interval varchar
);

CREATE TABLE timeslot(
	id serial PRIMARY KEY,
	timeslot_day integer REFERENCES timeslot_day (day_num),
	timeslot_time integer REFERENCES timeslot_time (pair_num)
);

CREATE TABLE pair_type(
  id serial PRIMARY KEY,
  type varchar(50) UNIQUE NOT NULL
);

CREATE TABLE assigned_pair(
	id serial PRIMARY KEY,
	timeslot integer REFERENCES timeslot (id),
	timeslot_day integer REFERENCES timeslot_day (day_num),
	timeslot_time integer REFERENCES timeslot_time (pair_num),
	study_group_id integer REFERENCES study_group (id),
  group_set_id integer REFERENCES group_set (id),
  type integer REFERENCES pair_type (id),
	subject_course_id integer REFERENCES subject_course (id),
	professor_id integer REFERENCES professor (id),
	auditory_id integer REFERENCES auditory (id),
	opt_subject_course_id integer REFERENCES opt_subject_course (id),
	in_two_weeks bool DEFAULT FALSE,
	even_week bool,
	replacement bool DEFAULT FALSE,
	offer bool DEFAULT FALSE,
	offer_pair_id integer REFERENCES assigned_pair (id)
);

CREATE TABLE group_pair(
  id serial PRIMARY KEY,
  study_group_id integer REFERENCES study_group (id),
  assigned_pair_id integer REFERENCES assigned_pair (id)
);


CREATE TABLE factor(
	id serial PRIMARY KEY,
	name varchar(60) NOT NULL,
	sql_expression text NOT NULL
);

CREATE TABLE operation(
	id serial PRIMARY KEY,
	name varchar(15) NOT NULL,
	operation varchar(3) NOT NULL
);

CREATE TABLE restriction(
	id serial PRIMARY KEY,
	name varchar(150) NOT NULL,
	factor_id integer REFERENCES factor (id),
	operation_id integer REFERENCES operation (id),
	restriction_value numeric,
  hard bool NOT NULL DEFAULT FALSE,
	priority integer DEFAULT 5,
	enabled bool DEFAULT TRUE
);

CREATE TABLE professor_timeslot_resource(
	id serial PRIMARY KEY,
	timeslot integer REFERENCES timeslot (id),
	professor_id integer REFERENCES professor (id)
);

CREATE TABLE auditory_timeslot_resource(
	id serial PRIMARY KEY,
	timeslot integer REFERENCES timeslot (id),
	auditory_id integer REFERENCES auditory (id)
);

CREATE TABLE resource_checking(
	id serial PRIMARY KEY,
	name varchar(50) NOT NULL,
	sql_expression text NOT NULL
);

