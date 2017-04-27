USE mldn ;
CREATE TABLE dept(
	deptno	INT AUTO_INCREMENT ,
	dname	VARCHAR(32) ,
	CONSTRAINT pk_deptno PRIMARY KEY(deptno)
) engine="innodb";
INSERT INTO dept(dname) VALUES ('技术部') ;
INSERT INTO dept(dname) VALUES ('市场部') ;
INSERT INTO dept(dname) VALUES ('财务部') ;