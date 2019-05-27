--liquibase formatted sql

--changeset svintenok:1
INSERT INTO study_course(course_num) VALUES (1); --1
INSERT INTO study_course(course_num) VALUES (2); --2
INSERT INTO study_course(course_num) VALUES (3); --3
INSERT INTO study_course(course_num) VALUES (4); --4


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
INSERT INTO timeslot_time(pair_num, time_interval) VALUES (7, '19:00-20:30');--7

INSERT INTO pair_type(type) VALUES ('практика');--1
INSERT INTO pair_type(type) VALUES ('лекция');--2
INSERT INTO pair_type(type) VALUES ('курс по выбору');--3
INSERT INTO pair_type(type) VALUES ('физкультура');--4

INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 1);--1
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 2);--2
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 3);--3
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 4);--4
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 5);--5
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 6);--6
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (1, 7);--7
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 1);--8
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 2);--9
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 3);--10
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 4);--11
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 5);--12
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 6);--13
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (2, 7);--14
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 1);--15
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 2);--16
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 3);--17
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 4);--18
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 5);--19
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 6);--20
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (3, 7);--21
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 1);--22
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 2);--23
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 3);--24
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 4);--25
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 5);--26
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 6);--27
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (4, 7);--28
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 1);--29
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 2);--30
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 3);--31
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 4);--32
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 5);--33
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 6);--34
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (5, 7);--35
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 1);--36
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 2);--37
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 3);--38
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 4);--39
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 5);--40
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 6);--41
INSERT INTO timeslot(timeslot_day, timeslot_time) VALUES (6, 7);--42


INSERT INTO operation(name, operation) VALUES ('больше', '>');--1
INSERT INTO operation(name, operation) VALUES ('меньше', '<');--2
INSERT INTO operation(name, operation) VALUES ('не больше', '<=');--3
INSERT INTO operation(name, operation) VALUES ('не меньше', '>=');--4
INSERT INTO operation(name, operation) VALUES ('равно', '=');--5

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
                                                 SELECT COALESCE(MAX(seq_pair_windows_count), 0) FROM
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
                                                 SELECT COALESCE(MAX(seq_pair_count), 0) FROM
                                                 (SELECT COUNT(timeslot) as seq_pair_count FROM
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
INSERT INTO restriction(name, factor_id, operation_id, restriction_value, hard, priority) VALUES ('Максимальное число пар подряд не должно превышать четырех', 6, 3, 4, FALSE, 3);--3
INSERT INTO restriction(name, factor_id, operation_id, restriction_value, hard, priority) VALUES ('Не должно быть единственной пары в день', 4, 1, 1, FALSE, 2);--3
INSERT INTO restriction(name, factor_id, operation_id, restriction_value, hard, priority) VALUES ('ЧИсло окон подряд не должно превышать двух', 5, 3, 2, FALSE, 3);--3

--1
INSERT INTO resource_checking(name, sql_expression) VALUES ('group_timeslot',
    'WITH parms as (select ? as group_id, ? as opt_choice)
    SELECT timeslot FROM assigned_pair WHERE
      (study_group_id=(SELECT group_id FROM parms)
        OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
                          WHERE group_set_group.group_set_id=assigned_pair.group_set_id))
      AND (even_week=null OR even_week = false)
      AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY ((SELECT opt_choice FROM parms)::int[]))
      AND NOT replacement
      GROUP BY timeslot
      HAVING COUNT(*) > 1
    UNION
    SELECT timeslot FROM assigned_pair WHERE
      (study_group_id=(SELECT group_id FROM parms)
        OR (SELECT group_id FROM parms) IN (SELECT study_group_id from group_set_group
                          WHERE group_set_group.group_set_id=assigned_pair.group_set_id))
      AND (even_week=null OR even_week = true)
      AND (opt_subject_course_id IS null OR opt_subject_course_id = ANY ((SELECT opt_choice FROM parms)::int[]))
      AND NOT replacement
      GROUP BY timeslot
      HAVING COUNT(*) > 1;
    ');
--2
INSERT INTO resource_checking(name, sql_expression) VALUES ('auditory_timeslot_using',
    'SELECT auditory_id, timeslot FROM assigned_pair WHERE
      professor_id IS NOT NULL AND
      (even_week=null OR even_week = false) AND
      NOT replacement
      GROUP BY auditory_id, timeslot
      HAVING COUNT(*) > 1
    UNION
    SELECT auditory_id, timeslot FROM assigned_pair WHERE
      professor_id IS NOT NULL AND
      (even_week=null OR even_week = true) AND
      NOT replacement
      GROUP BY auditory_id, timeslot
      HAVING COUNT(*) > 1;');

--3
INSERT INTO resource_checking(name, sql_expression) VALUES ('professor_timeslot_using',
    'SELECT professor_id, timeslot FROM assigned_pair WHERE
      professor_id IS NOT NULL AND
      (even_week=null OR even_week = false) AND
      NOT replacement
      GROUP BY professor_id, timeslot, even_week
      HAVING COUNT(*) > 1
    UNION
    SELECT professor_id, timeslot FROM assigned_pair WHERE
      professor_id IS NOT NULL AND
      (even_week=null OR even_week = true) AND
      NOT replacement
      GROUP BY professor_id, timeslot, even_week
      HAVING COUNT(*) > 1');
--4
INSERT INTO resource_checking(name, sql_expression) VALUES ('auditory_timeslot_existing',
    'SELECT auditory_id, timeslot FROM assigned_pair pair WHERE
      NOT EXISTS (SELECT * FROM auditory_timeslot_resource aud_res WHERE
          pair.auditory_id = aud_res.auditory_id AND
            pair.timeslot = aud_res.timeslot)
      AND NOT replacement;');

--5
INSERT INTO resource_checking(name, sql_expression) VALUES ('professor_timeslot_existing',
    'SELECT professor_id, timeslot FROM assigned_pair pair WHERE
      professor_id IS NOT NULL AND
      NOT EXISTS (SELECT * FROM professor_timeslot_resource pr_res WHERE
          pair.auditory_id = pr_res.professor_id AND
            pair.timeslot = pr_res.timeslot)
      AND NOT replacement;');