DROP SEQUENCE MYSEQ;
DROP TABLE MYBOARD;

CREATE SEQUENCE MYSEQ;

CREATE TABLE MYBOARD(
 MYNO NUMBER PRIMARY KEY,
 MYNAME VARCHAR2(1000) NOT NULL,
 MYTITLE VARCHAR2(1000) NOT NULL,
 MYCONTENT VARCHAR2(2000) NOT NULL,
 MYDATE DATE NOT NULL
);


INSERT INTO MYBOARD
VALUES(MYSEQ.NEXTVAL, '관리자','글쓰기 테스트','테스트 내용입니다.', SYSDATE);

SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE
FROM MYBOARD;