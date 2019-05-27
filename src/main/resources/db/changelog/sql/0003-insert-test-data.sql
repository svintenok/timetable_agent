--liquibase formatted sql

--changeset svintenok:1
INSERT INTO study_course(course_num) VALUES (1); --1
INSERT INTO study_course(course_num) VALUES (2); --2
INSERT INTO study_course(course_num) VALUES (3); --3
INSERT INTO study_course(course_num) VALUES (4); --4

INSERT INTO study_group(group_num, course_id, student_count) VALUES ('11-501', 4, 18);--1
INSERT INTO study_group(group_num, course_id, student_count) VALUES ('11-502', 4, 19);--2
INSERT INTO study_group(group_num, course_id, student_count) VALUES ('11-503', 4, 16);--3


INSERT INTO group_set(name) VALUES ('четвертый курс');--1

INSERT INTO group_set_group(study_group_id, group_set_id) VALUES (1, 1);--1
INSERT INTO group_set_group(study_group_id, group_set_id) VALUES (2, 1);--2
INSERT INTO group_set_group(study_group_id, group_set_id) VALUES (3, 1);--3


INSERT INTO subject_course(name, pair_count, study_course_id) VALUES ('Основы информационного поиска', 2, 4);--1
INSERT INTO subject_course(name, pair_count, study_course_id) VALUES ('Методология научных исследований', 2, 4);--2
--INSERT INTO subject_course(name, pair_count, study_course_id) VALUES ('Проектирование цифровых образовательных сред', 2, 4);--3
--INSERT INTO subject_course(name, pair_count, study_course_id) VALUES ('Интернет вещей', 2, 4);--4
INSERT INTO subject_course(name, pair_count, study_course_id, opt_course_block) VALUES ('Курс по выбору', 2, 4, TRUE );--3

--INSERT INTO opt_course_block(name, study_course_id) VALUES ('блок № 1', 4);--1

--INSERT INTO opt_course_block_subject(opt_course_block_id, subject_course_id) VALUES (1, 3);--1
--INSERT INTO opt_course_block_subject(opt_course_block_id, subject_course_id) VALUES (1, 4);--2
INSERT INTO opt_subject_course(name, subject_course, student_count, study_course_id) VALUES ('Проектирование цифровых образовательных сред', 3, 7, 4);--1
INSERT INTO opt_subject_course(name, subject_course, student_count, study_course_id) VALUES ('Интернет вещей', 3, 40, 4);--2


INSERT INTO timeslot_day(day_num, day_name) VALUES (1, 'Понедельник');--1
INSERT INTO timeslot_day(day_num, day_name) VALUES (2, 'Вторник');--2
INSERT INTO timeslot_day(day_num, day_name) VALUES (3, 'Среда');--3
INSERT INTO timeslot_day(day_num, day_name) VALUES (4, 'Четверг');--4
INSERT INTO timeslot_day(day_num, day_name) VALUES (5, 'Пятница');--5
INSERT INTO timeslot_day(day_num, day_name) VALUES (6, 'Суббота');--6


INSERT INTO timeslot_time(pair_num, time_interval) VALUES (1, '8:30-10:00');--1
INSERT INTO timeslot_time(pair_num, time_interval) VALUES (2, '10:10-11:40');--2
INSERT INTO timeslot_time(pair_num, time_interval) VALUES (3, '11:50-13:20');--3
INSERT INTO timeslot_time(pair_num, time_interval) VALUES (4, '14:00-15:30');--4
INSERT INTO timeslot_time(pair_num, time_interval) VALUES (5, '15:40-17:10');--5
INSERT INTO timeslot_time(pair_num, time_interval) VALUES (6, '17:20-18:50');--6

INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 1);--1
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 2);--2
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 3);--3
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 4);--4
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 5);--5
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 6);--6
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 1);--7
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 2);--8
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 3);--9
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 4);--10
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 5);--11
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 6);--12
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 1);--13
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 2);--14
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 3);--15
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 4);--16
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 5);--17
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 6);--18
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 1);--19
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 2);--20
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 3);--21
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 4);--22
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 5);--23
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 6);--24
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 1);--25
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 2);--26
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 3);--27
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 4);--28
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 5);--29
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 6);--30
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 1);--31
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 2);--32
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 3);--33
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 4);--34
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 5);--35
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 6);--36


INSERT INTO professor(name) VALUES ('Абрамский М. М.');--1
INSERT INTO professor(name) VALUES ('Хайдаров Ш. М.');--2
INSERT INTO professor(name) VALUES ('Галицина И. Н.');--3
INSERT INTO professor(name) VALUES ('Невзорова О. А.');--4
INSERT INTO professor(name) VALUES ('Камалетдинов И. Р.');--5

INSERT INTO auditory(capacity, auditory_number) VALUES (25, '1409');--1
INSERT INTO auditory(capacity, auditory_number) VALUES (200, '108');--2
INSERT INTO auditory(capacity, auditory_number) VALUES (25, '1306');--3
INSERT INTO auditory(capacity, auditory_number) VALUES (50, '1310');--4


INSERT INTO pair_type(type) VALUES ('практика');--1
INSERT INTO pair_type(type) VALUES ('лекция');--2
INSERT INTO pair_type(type) VALUES ('курс по выбору');--3


INSERT INTO assigned_pair(timeslot, timeslot_day, timeslot_time, study_group_id, group_set_id, subject_course_id, type, opt_subject_course_id, professor_id, auditory_id) VALUES (5, 1, 5, 1, NULL, 1, 1, NULL, 2, 3);--1
INSERT INTO assigned_pair(timeslot, timeslot_day, timeslot_time, study_group_id, group_set_id, subject_course_id, type, opt_subject_course_id, professor_id, auditory_id) VALUES (6, 1, 6, NULL, 1, 2, 2, NULL, 3, 2);--2
INSERT INTO assigned_pair(timeslot, timeslot_day, timeslot_time, study_group_id, group_set_id, subject_course_id, type, opt_subject_course_id, professor_id, auditory_id) VALUES (11, 2, 5, NULL, 1, 3, 3, 1, 1, 1);--3
INSERT INTO assigned_pair(timeslot, timeslot_day, timeslot_time, study_group_id, group_set_id, subject_course_id, type, opt_subject_course_id, professor_id, auditory_id) VALUES (12, 2, 6, NULL, 1, 3, 3, 1, 1, 1);--4
INSERT INTO assigned_pair(timeslot, timeslot_day, timeslot_time, study_group_id, group_set_id, subject_course_id, type, opt_subject_course_id, professor_id, auditory_id) VALUES (11, 2, 5, NULL, 1, 3, 3, 2, 5, 4);--5
INSERT INTO assigned_pair(timeslot, timeslot_day, timeslot_time, study_group_id, group_set_id, subject_course_id, type, opt_subject_course_id, professor_id, auditory_id) VALUES (12, 2, 6, NULL, 1, 3, 3, 2, 5, 4);--6
INSERT INTO assigned_pair(timeslot, timeslot_day, timeslot_time, study_group_id, group_set_id, subject_course_id, type, opt_subject_course_id, professor_id, auditory_id) VALUES (24, 4, 6, NULL, 1, 1, 2, NULL, 4, 2);--7
INSERT INTO assigned_pair(timeslot, timeslot_day, timeslot_time, study_group_id, group_set_id, subject_course_id, type, opt_subject_course_id, professor_id, auditory_id) VALUES (34, 6, 4, 1, NULL, 2, 1, NULL, 1, 1);--8

--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (1, 1);--1
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (1, 2);--2
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (2, 2);--3
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (3, 2);--4
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (1, 3);--5
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (2, 3);--6
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (3, 3);--7
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (1, 4);--8
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (2, 4);--9
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (3, 4);--10
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (1, 5);--11
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (2, 5);--12
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (3, 5);--13
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (1, 6);--14
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (2, 6);--15
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (3, 6);--16
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (1, 7);--17
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (2, 7);--18
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (3, 7);--19
--INSERT INTO group_pair(study_group_id, assigned_pair_id) VALUES (1, 8);--20


INSERT INTO operation(name, operation) VALUES ('больше', '>');--1
INSERT INTO operation(name, operation) VALUES ('меньше', '<');--2
INSERT INTO operation(name, operation) VALUES ('не больше', '<=');--3
INSERT INTO operation(name, operation) VALUES ('не меньше', '>=');--4
INSERT INTO operation(name, operation) VALUES ('равно', '=');--5


INSERT INTO professor_timeslot_resource(professor_id, timeslot) VALUES (1, 11);--1
INSERT INTO professor_timeslot_resource(professor_id, timeslot) VALUES (1, 12);--2
INSERT INTO professor_timeslot_resource(professor_id, timeslot) VALUES (1, 34);--3
INSERT INTO professor_timeslot_resource(professor_id, timeslot) VALUES (2, 5);--4
INSERT INTO professor_timeslot_resource(professor_id, timeslot) VALUES (3, 6);--5
INSERT INTO professor_timeslot_resource(professor_id, timeslot) VALUES (4, 24);--6
INSERT INTO professor_timeslot_resource(professor_id, timeslot) VALUES (5, 11);--7
INSERT INTO professor_timeslot_resource(professor_id, timeslot) VALUES (5, 12);--8

INSERT INTO auditory_resource(auditory_id, timeslot) VALUES (1, 11);--1
INSERT INTO auditory_resource(auditory_id, timeslot) VALUES (1, 12);--2
INSERT INTO auditory_resource(auditory_id, timeslot) VALUES (1, 34);--3
INSERT INTO auditory_resource(auditory_id, timeslot) VALUES (2, 6);--4
INSERT INTO auditory_resource(auditory_id, timeslot) VALUES (2, 24);--5
INSERT INTO auditory_resource(auditory_id, timeslot) VALUES (3, 5);--6
INSERT INTO auditory_resource(auditory_id, timeslot) VALUES (4, 11);--7
INSERT INTO auditory_resource(auditory_id, timeslot) VALUES (4, 12);--8


--1
INSERT INTO factor(name, sql_expression) VALUES ('число окон в неделю',
'WITH parms as (select ? as group_id, ? as opt_choice)
SELECT count(*) FROM timeslot as cur_timeslot
WHERE NOT EXISTS (
	SELECT * FROM assigned_pair as cur_pair WHERE
      (cur_pair.study_group_id=(SELECT group_id FROM parms)
				OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
													WHERE group_set_group.group_set_id=cur_pair.group_set_id))
          AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY ((SELECT opt_choice FROM parms)::int[]))
					AND cur_pair.timeslot=cur_timeslot.id AND NOT cur_pair.replacement)

	  AND EXISTS (SELECT * FROM assigned_pair as prev_pair WHERE
      (prev_pair.study_group_id=(SELECT group_id FROM parms)
				OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
									WHERE group_set_group.group_set_id=prev_pair.group_set_id))
        AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY ((SELECT opt_choice FROM parms)::int[]))
				AND not prev_pair.replacement AND prev_pair.timeslot_day = cur_timeslot.timeslot_day
				AND prev_pair.timeslot_time < cur_timeslot.timeslot_time)

	  AND EXISTS (SELECT * FROM assigned_pair as next_pair WHERE
      (next_pair.study_group_id=(SELECT group_id FROM parms)
				OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
									WHERE group_set_group.group_set_id=next_pair.group_set_id))
        AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY ((SELECT opt_choice FROM parms)::int[]))
				AND not next_pair.replacement AND next_pair.timeslot_day = cur_timeslot.timeslot_day
				AND next_pair.timeslot_time > cur_timeslot.timeslot_time);');
--2
INSERT INTO factor(name, sql_expression) VALUES ('максимальное число пар в день',
'WITH parms as (select ? as group_id)
SELECT COALESCE(MAX(pair_count), 0) FROM
	(SELECT COUNT(*) AS pair_count FROM assigned_pair WHERE
	 	(study_group_id=(SELECT group_id FROM parms)
	 	OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
									WHERE group_set_group.group_set_id=assigned_pair.group_set_id))
    AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY (?::int[]))
	 	AND NOT replacement
		GROUP BY timeslot_day) pair_counts;');
--3
INSERT INTO factor(name, sql_expression) VALUES ('максимальное число пар по курсу в день',
'WITH parms as (select ? as group_id)
SELECT COALESCE(MAX(course_pair_count), 0) FROM
	(SELECT COUNT(*) AS course_pair_count FROM assigned_pair WHERE
	(study_group_id=(SELECT group_id FROM parms)
	OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
		WHERE group_set_group.group_set_id=assigned_pair.group_set_id))
  AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY (?::int[]))
	AND NOT replacement
	GROUP BY timeslot_day, subject_course_id) course_pair_counts;');

--4
INSERT INTO factor(name, sql_expression) VALUES ('минимальное число пар в день',
'WITH parms as (select ? as group_id)
SELECT COALESCE(MIN(course_pair_count), 0) FROM
(SELECT COUNT(*) AS course_pair_count FROM assigned_pair WHERE
(study_group_id=(SELECT group_id FROM parms)
OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
WHERE group_set_group.group_set_id=assigned_pair.group_set_id))
AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY (?::int[]))
AND NOT replacement
GROUP BY timeslot_day) pair_counts;');

--5
INSERT INTO factor(name, sql_expression) VALUES ('максимальное количество окон подряд',
'WITH parms as (select ? as group_id, ? as opt_choice),
pair_window as (SELECT * FROM timeslot as cur_timeslot
WHERE NOT EXISTS (
	SELECT * FROM assigned_pair as cur_pair WHERE
      (cur_pair.study_group_id=(SELECT group_id FROM parms)
				OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
													WHERE group_set_group.group_set_id=cur_pair.group_set_id))
          AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY ((SELECT opt_choice FROM parms)::int[]))
					AND cur_pair.timeslot=cur_timeslot.id AND NOT cur_pair.replacement)

	  AND EXISTS (SELECT * FROM assigned_pair as prev_pair WHERE
      (prev_pair.study_group_id=(SELECT group_id FROM parms)
				OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
									WHERE group_set_group.group_set_id=prev_pair.group_set_id))
        AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY ((SELECT opt_choice FROM parms)::int[]))
				AND not prev_pair.replacement AND prev_pair.timeslot_day = cur_timeslot.timeslot_day
				AND prev_pair.timeslot_time < cur_timeslot.timeslot_time)

	  AND EXISTS (SELECT * FROM assigned_pair as next_pair WHERE
      (next_pair.study_group_id=(SELECT group_id FROM parms)
				OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
									WHERE group_set_group.group_set_id=next_pair.group_set_id))
        AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY ((SELECT opt_choice FROM parms)::int[]))
				AND not next_pair.replacement AND next_pair.timeslot_day = cur_timeslot.timeslot_day
				AND next_pair.timeslot_time > cur_timeslot.timeslot_time))
SELECT MAX(seq_pair_windows_count) FROM
(SELECT COUNT(id) as seq_pair_windows_count FROM
(SELECT id, id - ROW_NUMBER() OVER(ORDER BY id) as diff FROM pair_window) win_seq
GROUP BY diff) seq_pair_windows_count');

--6
INSERT INTO factor(name, sql_expression) VALUES ('максимальное количество пар подряд',
'WITH parms as (select ? as group_id, ? as opt_choice),
pair as (SELECT * FROM assigned_pair as pair
WHERE (pair.study_group_id=(SELECT group_id FROM parms)
		OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
													WHERE group_set_group.group_set_id=pair.group_set_id))
        AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY ((SELECT opt_choice FROM parms)::int[]))
		AND NOT pair.replacement)
SELECT MAX(seq_pair_count) FROM
(SELECT COUNT(timeslotbv, l) as seq_pair_count FROM
(SELECT timeslot, timeslot - ROW_NUMBER() OVER(ORDER BY timeslot) as diff FROM pair) pair_seq
GROUP BY diff) seq_pair_count;');

--7
INSERT INTO factor(name, sql_expression) VALUES ('равномерность нагрузки по количеству пар',
 'WITH parms as (select ? as group_id)
SELECT COALESCE(stddev(course_pair_count), 0) FROM
(SELECT COUNT(*) AS course_pair_count FROM assigned_pair WHERE
(study_group_id=(SELECT group_id FROM parms)
OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
WHERE group_set_group.group_set_id=assigned_pair.group_set_id))
AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY (?::int[]))
AND NOT replacement
GROUP BY timeslot_day) pair_counts;');


INSERT INTO restriction(name, factor_id, operation_id, restriction_value, hard, priority) VALUES ('Число окон в неделю не должно превышать пяти', 1, 3, 5, FALSE, 3);--1
INSERT INTO restriction(name, factor_id, operation_id, restriction_value, hard, priority) VALUES ('Максимальное число пар в день не должно превышать шести', 2, 3, 6, TRUE, 5);--2
INSERT INTO restriction(name, factor_id, operation_id, restriction_value, hard, priority) VALUES ('Максимальное число пар по курсу в день не должно превышать двух', 3, 3, 2, FALSE, 5);--3
INSERT INTO restriction(name, factor_id, operation_id, restriction_value, hard, priority) VALUES ('ЧИсло окон подряд не должно превышать двух', 5, 3, 2, FALSE, 3);--3
--1
INSERT INTO resource_checking(name, sql_expression) VALUES ('group_timeslot',
'WITH parms as (select ? as group_id)
SELECT timeslot FROM assigned_pair WHERE
	(study_group_id=(SELECT group_id FROM parms)
		OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
											WHERE group_set_group.group_set_id=assigned_pair.group_set_id))
  AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY (?::int[]))
	AND NOT replacement
	GROUP BY timeslot, even_week
	HAVING COUNT(*) > 1;');
--2
INSERT INTO resource_checking(name, sql_expression) VALUES ('auditory_timeslot',
'SELECT auditory_id, timeslot FROM assigned_pair WHERE
	NOT replacement
	GROUP BY auditory_id, timeslot, even_week
	HAVING COUNT(*) > 1;');
--3
INSERT INTO resource_checking(name, sql_expression) VALUES ('professor_timeslot',
'SELECT professor_id, timeslot FROM assigned_pair WHERE
	NOT replacement
	GROUP BY professor_id, timeslot, even_week
	HAVING COUNT(*) > 1;');