1) 
mysql> select first_name, last_name from student;
+------------+-----------+
| first_name | last_name |
+------------+-----------+
| Eric       | Ephram    |
| Greg       | Gould     |
| Adam       | Ant       |
| Howard     | Hess      |
| Charles    | Caldwell  |
| James      | Joyce     |
| Doug       | Dumas     |
| Kevin      | Kraft     |
| Frank      | Fountain  |
| Brian      | Biggs     |
+------------+-----------+
10 rows in set (0.00 sec)
-----------------------------------
2)

mysql> select * from student where years_of_experience < 8;
+-----+------------+-----------+---------------------+------------+
| id  | first_name | last_name | years_of_experience | start_date |
+-----+------------+-----------+---------------------+------------+
| 100 | Eric       | Ephram    |                   2 | 2016-03-31 |
| 110 | Greg       | Gould     |                   6 | 2016-09-30 |
| 120 | Adam       | Ant       |                   5 | 2016-06-02 |
| 130 | Howard     | Hess      |                   1 | 2016-02-28 |
| 140 | Charles    | Caldwell  |                   7 | 2016-05-07 |
| 170 | Kevin      | Kraft     |                   3 | 2016-04-15 |
| 190 | Brian      | Biggs     |                   4 | 2015-12-25 |
+-----+------------+-----------+---------------------+------------+
7 rows in set (0.00 sec)
-------------------------------------
3)
mysql> select last_name, start_date, id from student order by last_name;
+-----------+------------+-----+
| last_name | start_date | id  |
+-----------+------------+-----+
| Ant       | 2016-06-02 | 120 |
| Biggs     | 2015-12-25 | 190 |
| Caldwell  | 2016-05-07 | 140 |
| Dumas     | 2016-07-04 | 160 |
| Ephram    | 2016-03-31 | 100 |
| Fountain  | 2016-01-31 | 180 |
| Gould     | 2016-09-30 | 110 |
| Hess      | 2016-02-28 | 130 |
| Joyce     | 2016-08-27 | 150 |
| Kraft     | 2016-04-15 | 170 |
+-----------+------------+-----+
10 rows in set (0.00 sec)
---------------------------------------
4)
mysql> select * from student where first_name in ('Adam', 'James', 'Frank') order by last_name desc;

+-----+------------+-----------+---------------------+------------+
| id  | first_name | last_name | years_of_experience | start_date |
+-----+------------+-----------+---------------------+------------+
| 150 | James      | Joyce     |                   9 | 2016-08-27 |
| 180 | Frank      | Fountain  |                   8 | 2016-01-31 |
| 120 | Adam       | Ant       |                   5 | 2016-06-02 |
+-----+------------+-----------+---------------------+------------+
3 rows in set (0.00 sec)
-----------------------------------------
5)
mysql> select first_name, last_name, start_date from student where start_date >= '2016-01-01' and start_date <= '2016-06-30' order by start_date desc;
+------------+-----------+------------+
| first_name | last_name | start_date |
+------------+-----------+------------+
| Adam       | Ant       | 2016-06-02 |
| Charles    | Caldwell  | 2016-05-07 |
| Kevin      | Kraft     | 2016-04-15 |
| Eric       | Ephram    | 2016-03-31 |
| Howard     | Hess      | 2016-02-28 |
| Frank      | Fountain  | 2016-01-31 |
+------------+-----------+------------+
6 rows in set (0.00 sec)
----------------------------
----------------------------
Advanced

mysql> explain assignment;
+----------------+---------+------+-----+---------+-------+
| Field          | Type    | Null | Key | Default | Extra |
+----------------+---------+------+-----+---------+-------+
| id             | int(11) | NO   | PRI | NULL    |       |
| student_id     | int(11) | NO   | MUL | NULL    |       |
| assignment_nbr | int(11) | NO   |     | NULL    |       |
| grade_id       | int(11) | YES  | MUL | NULL    |       |
+----------------+---------+------+-----+---------+-------+
4 rows in set (0.00 sec)
---------------------------------------------
mysql> select * from assignment; # Wrote a Java program to generate random grades for each id
+----+------------+----------------+----------+
| id | student_id | assignment_nbr | grade_id |
+----+------------+----------------+----------+
|  1 |        100 |              1 |        0 |
|  2 |        100 |              2 |        2 |
|  3 |        100 |              3 |        2 |
|  4 |        100 |              4 |        2 |
|  5 |        100 |              5 |        4 |
|  6 |        110 |              1 |        3 |
|  7 |        110 |              2 |        1 |
|  8 |        110 |              3 |        1 |
|  9 |        110 |              4 |        3 |
| 10 |        110 |              5 |        1 |
| 11 |        120 |              1 |        3 |
| 12 |        120 |              2 |        4 |
| 13 |        120 |              3 |        3 |
| 14 |        120 |              4 |        2 |
| 15 |        120 |              5 |        1 |
| 16 |        130 |              1 |        2 |
| 17 |        130 |              2 |        1 |
| 18 |        130 |              3 |        0 |
| 19 |        130 |              4 |        1 |
| 20 |        130 |              5 |        2 |
| 21 |        140 |              1 |        1 |
| 22 |        140 |              2 |        4 |
| 23 |        140 |              3 |        4 |
| 24 |        140 |              4 |        0 |
| 25 |        140 |              5 |        4 |
| 26 |        150 |              1 |        2 |
| 27 |        150 |              2 |        3 |
| 28 |        150 |              3 |        2 |
| 29 |        150 |              4 |        0 |
| 30 |        150 |              5 |        0 |
| 31 |        160 |              1 |        3 |
| 32 |        160 |              2 |        4 |
| 33 |        160 |              3 |        2 |
| 34 |        160 |              4 |        0 |
| 35 |        160 |              5 |        0 |
| 36 |        170 |              1 |        1 |
| 37 |        170 |              2 |        3 |
| 38 |        170 |              3 |        4 |
| 39 |        170 |              4 |        4 |
| 40 |        170 |              5 |        4 |
| 41 |        180 |              1 |        1 |
| 42 |        180 |              2 |        4 |
| 43 |        180 |              3 |        4 |
| 44 |        180 |              4 |        1 |
| 45 |        180 |              5 |        2 |
| 46 |        190 |              1 |        2 |
| 47 |        190 |              2 |        2 |
| 48 |        190 |              3 |        1 |
| 49 |        190 |              4 |        4 |
| 50 |        190 |              5 |        3 |
+----+------------+----------------+----------+
50 rows in set (0.00 sec)
---------------------------------------------
mysql> explain grade;
+-------------+-------------+------+-----+---------+-------+
| Field       | Type        | Null | Key | Default | Extra |
+-------------+-------------+------+-----+---------+-------+
| id          | int(11)     | NO   | PRI | NULL    |       |
| description | varchar(30) | YES  |     | NULL    |       |
+-------------+-------------+------+-----+---------+-------+
2 rows in set (0.00 sec)
------------------------------
mysql> select * from grade;
+----+-----------------------------+
| id | description                 |
+----+-----------------------------+
|  0 | Not graded                  |
|  1 | Incomplete                  |
|  2 | Complete and unsatisfactory |
|  3 | Complete and satisfactory   |
|  4 | Exceeds expectations        |
+----+-----------------------------+
5 rows in set (0.00 sec)
------------------------------
------------------------------
Pro

mysql> alter table assignment add constraint fk_grade_id foreign key (grade_id) references grade (id);
Query OK, 0 rows affected (0.95 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> alter table assignment add constraint fk_student_id foreign key (student_id) references student (id);
Query OK, 0 rows affected (0.84 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> describe assignment;
+----------------+---------+------+-----+---------+-------+
| Field          | Type    | Null | Key | Default | Extra |
+----------------+---------+------+-----+---------+-------+
| id             | int(11) | NO   | PRI | NULL    |       |
| student_id     | int(11) | NO   | MUL | NULL    |       |
| assignment_nbr | int(11) | NO   |     | NULL    |       |
| grade_id       | int(11) | YES  | MUL | NULL    |       |
+----------------+---------+------+-----+---------+-------+
4 rows in set (0.00 sec)

mysql> select * from information_schema.table_constraints where table_name = 'assignment';
+--------------------+-------------------+-------------------+--------------+------------+-----------------+
| CONSTRAINT_CATALOG | CONSTRAINT_SCHEMA | CONSTRAINT_NAME   | TABLE_SCHEMA | TABLE_NAME | CONSTRAINT_TYPE |
+--------------------+-------------------+-------------------+--------------+------------+-----------------+
| def                | tiy               | PRIMARY           | tiy          | assignment | PRIMARY KEY     |
| def                | tiy               | assignment_ibfk_1 | tiy          | assignment | FOREIGN KEY     |
| def                | tiy               | fk_grade_id       | tiy          | assignment | FOREIGN KEY     |
| def                | tiy               | fk_student_id     | tiy          | assignment | FOREIGN KEY     |
+--------------------+-------------------+-------------------+--------------+------------+-----------------+
4 rows in set (0.00 sec)

12 does not exist as a student id so it cannot be inserted into assignment
mysql> insert into assignment values (1, 12, 1, 1); 
ERROR 1452 (23000): Cannot add or update a child row: a foreign key constraint fails (`tiy`.`assignm
ent`, CONSTRAINT `assignment_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`))

---------------------------------------------
---------------------------------------------
Pro+

mysql> explain major;
+-------------+-------------+------+-----+---------+-------+
| Field       | Type        | Null | Key | Default | Extra |
+-------------+-------------+------+-----+---------+-------+
| id          | int(11)     | NO   | PRI | NULL    |       |
| description | varchar(30) | YES  |     | NULL    |       |
+-------------+-------------+------+-----+---------+-------+
2 rows in set (0.00 sec)

mysql> select * from major;
+----+---------------------+
| id | description         |
+----+---------------------+
|  1 | Accounting          |
|  2 | Business            |
|  3 | English             |
|  4 | Mathematics         |
|  5 | Education           |
|  6 | Biology             |
|  7 | Chemistry           |
|  8 | Engineering         |
|  9 | Art                 |
| 10 | Drama               |
| 11 | Philosophy          |
| 12 | Computer Science    |
| 13 | History             |
| 14 | Nursing             |
| 15 | Information Systems |
+----+---------------------+
15 rows in set (0.00 sec)
----------------------------------------
mysql> alter table student add column major_id int;
Query OK, 0 rows affected (0.74 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> alter table student add constraint fk_major_id foreign key (major_id) references major (id);
Query OK, 10 rows affected (3.70 sec)
Records: 10  Duplicates: 0  Warnings: 0

mysql> explain student;
+---------------------+-------------+------+-----+---------+-------+
| Field               | Type        | Null | Key | Default | Extra |
+---------------------+-------------+------+-----+---------+-------+
| id                  | int(11)     | NO   | PRI | NULL    |       |
| first_name          | varchar(30) | NO   |     | NULL    |       |
| last_name           | varchar(30) | NO   |     | NULL    |       |
| years_of_experience | int(11)     | NO   |     | NULL    |       |
| start_date          | date        | NO   |     | NULL    |       |
| major_id            | int(11)     | YES  | MUL | NULL    |       |
+---------------------+-------------+------+-----+---------+-------+
6 rows in set (0.00 sec)
-----------------------------------
mysql> explain classes;
+------------+------------+------+-----+---------+-------+
| Field      | Type       | Null | Key | Default | Extra |
+------------+------------+------+-----+---------+-------+
| id         | int(11)    | NO   | PRI | NULL    |       |
| department | varchar(6) | YES  |     | NULL    |       |
| number     | int(11)    | YES  |     | NULL    |       |
+------------+------------+------+-----+---------+-------+
3 rows in set (0.00 sec)

-----------------------------------
mysql> alter table majorclassreq add constraint fk_major_id_req foreign key (major_id) references major (id);
Query OK, 94 rows affected (0.97 sec)
Records: 94  Duplicates: 0  Warnings: 0

mysql> alter table majorclassreq add constraint fk_class_id_req foreign key (class_id) references classes (id);
Query OK, 94 rows affected (1.19 sec)
Records: 94  Duplicates: 0  Warnings: 0

mysql> explain majorClassReq;
+----------+---------+------+-----+---------+-------+
| Field    | Type    | Null | Key | Default | Extra |
+----------+---------+------+-----+---------+-------+
| id       | int(11) | NO   | PRI | NULL    |       |
| major_id | int(11) | YES  | MUL | NULL    |       |
| class_id | int(11) | YES  | MUL | NULL    |       |
+----------+---------+------+-----+---------+-------+
3 rows in set (0.04 sec)
-------------------------------
mysql> alter table classesTaken add constraint fk_student_id_classes foreign key (student_id) refere
nces student (id);
Query OK, 0 rows affected (0.69 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> alter table classesTaken add constraint fk_class_id_classes foreign key (class_id) references
 classes (id);
Query OK, 0 rows affected (0.69 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> explain classesTaken;
+------------+---------+------+-----+---------+-------+
| Field      | Type    | Null | Key | Default | Extra |
+------------+---------+------+-----+---------+-------+
| id         | int(11) | NO   | PRI | NULL    |       |
| student_id | int(11) | YES  | MUL | NULL    |       |
| class_id   | int(11) | YES  | MUL | NULL    |       |
+------------+---------+------+-----+---------+-------+
3 rows in set (0.00 sec)
------------------------------------
Brian Biggs, student id 190 has declared major 12, which is Computer Science

Query to produce the report for classes not taken for the major:
1) Subtract the class ids of the classes taken from the ids of the classes required for the major
2) Using the resulting set of ids to look up the corresponding class name in the class table

select department, number 
from class where id in(select class_id 
                       from majorclassreq 
                       where major_id = 12 and class_id not in 
                      (select class_id from classesTaken where student_id = 190));

Query to produce the report for classes taken for the major:
1) For the classes taken, retrieve those that are part of the major
2) Using the resulting set of ids to look up the corresponding class name in the class table

select department, number
from classes where id in (select class_id 
                          from classesTaken 
                          where student_id = 190 and class_id in 
                         (select class_id from majorclassreq where major_id = 12));