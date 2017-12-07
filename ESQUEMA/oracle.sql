
drop table EMP;
drop table DEPT;
drop table SALGRADE;


create table DEPT( DEPTNO	NUMBER(2) NOT NULL,
		   DNAME	VARCHAR2(14),
		   LOC		VARCHAR2(13),
		   PRIMARY KEY (DEPTNO),
		   CONSTRAINT DEPTNO_CK CHECK (DEPTNO BETWEEN 10 AND 99) );


create table EMP ( EMPNO	NUMBER(4) NOT NULL,
		   ENAME	VARCHAR2(10) NOT NULL,
		   JOB		VARCHAR2(9),
		   MGR		NUMBER(4),
		   HIREDATE	DATE,
		   SAL		NUMBER(7,2),
		   COMM		NUMBER(7,2),
		   DEPTNO	NUMBER(2) NOT NULL,
		   PRIMARY KEY (EMPNO),
		   FOREIGN KEY (MGR) REFERENCES EMP (EMPNO),
		   FOREIGN KEY (DEPTNO) REFERENCES DEPT );


create table
	 SALGRADE( GRADE	NUMBER(1) NOT NULL,
		   LOSAL	NUMBER(7,2),
		   HISAL	NUMBER(7,2),
		   PRIMARY KEY (GRADE) );


insert into DEPT values ( 10, 'ACCOUNTING', 'NEW YORK' );
insert into DEPT values ( 20, 'RESEARCH',   'DALLAS'   );
insert into DEPT values ( 30, 'SALES',      'CHICAGO'  );
insert into DEPT values ( 40, 'OPERATIONS', 'BOSTON'   );


insert into EMP values ( 7839, 'KING',   'PRESIDENT', null, '17-NOV-81', 5000.00,    null, 10 );
insert into EMP values ( 7782, 'CLARK',  'MANAGER',   7839, '09-JUN-81', 2450.00,    null, 10 );
insert into EMP values ( 7934, 'MILLER', 'CLERK',     7782, '12-ENE-83', 1300.00,    null, 10 );
insert into EMP values ( 7566, 'JONES',  'MANAGER',   7839, '02-ABR-81', 2975.00,    null, 20 );
insert into EMP values ( 7902, 'FORD',   'ANALYST',   7566, '03-DIC-81', 3000.00,    null, 20 );
insert into EMP values ( 7369, 'SMITH',  'CLERK',     7902, '17-DIC-80',  800.00,    null, 20 );
insert into EMP values ( 7788, 'SCOTT',  'ANALYST',   7566, '05-NOV-82', 3000.00,    null, 20 );
insert into EMP values ( 7876, 'ADAMS',  'CLERK',     7788, '09-DIC-82', 1100.00,    null, 20 );
insert into EMP values ( 7698, 'BLAKE',  'MANAGER',   7839, '01-MAY-81', 2850.00,    null, 30 );
insert into EMP values ( 7499, 'ALLEN',  'SALESMAN',  7698, '20-FEB-81', 1600.00,  300.00, 30 );
insert into EMP values ( 7654, 'MARTIN', 'SALESMAN',  7698, '28-SEP-81', 1250.00, 1400.00, 30 );
insert into EMP values ( 7900, 'JAMES',  'CLERK',     7698, '03-DIC-81',  950.00,    null, 30 );
insert into EMP values ( 7844, 'TURNER', 'SALESMAN',  7698, '08-SEP-81', 1500.00,    null, 30 );
insert into EMP values ( 7521, 'WARD',   'SALESMAN',  7698, '22-FEB-81', 1250.00,  500.00, 30 );


insert into SALGRADE values ( 1,    0.00, 1199.00 );
insert into SALGRADE values ( 2, 1200.00, 1399.00 );
insert into SALGRADE values ( 3, 1400.00, 1999.00 );
insert into SALGRADE values ( 4, 2000.00, 3999.00 );
insert into SALGRADE values ( 5, 4000.00, 5000.00 );
