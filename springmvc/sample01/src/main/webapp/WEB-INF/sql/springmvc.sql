	create user springmvc identified by 1234;
grant connect, resource to springmvc;

-- springmvc 접속
-- 회원 테이블
create table tbl_member(
    user_id varchar2(50) primary key,
    user_pw varchar2(50) not null,
    user_name varchar2(50) not null,
    user_email varchar2(50),
    reg_date timestamp default sysdate,
    update_date timestamp
);

insert into tbl_member(user_id, user_pw, user_name)
values('hong', '1234', '홍길동');

commit;

select * from tbl_member;

-- 게시판 테이블(tbl_board)
truncate table tbl_board;
create table tbl_board(
    b_no number primary key, -- 글번호
    b_title varchar2(100) not null, -- 글제목
    b_content varchar(1000), -- 글내용
    user_id varchar(50) references tbl_member(user_id), -- 작성자
    b_regdate timestamp default sysdate, -- 글 작성시간
    b_viewcnt number default 0, -- 조회수
    re_group number default 0, -- 글그룹(원글번호)
    re_seq number default 0, -- 같은 글그룹 내에서 출력 순서
    re_level number default 0  -- 답글 들여쓰기용
);

drop sequence seq_board_bno;
create sequence seq_board_bno; -- 1부터 1씩 증가

select * from tbl_board;

select count(*) from tbl_board;

commit;

-- 데이터 500개
begin
    for i in 1..500 loop
        insert into tbl_board(b_no, b_title, b_content, user_id, re_group)
        values (seq_board_bno.nextval,
            '제목' || i,
            '내용' || i,
            'hong',
            seq_board_bno.nextval);
    end loop;
end;
/
commit;


select * from
(select rownum rnum, a.* from
(select * from tbl_board
order by re_group desc)a)
where rnum between 1 and 10;

select * from tbl_board
where b_title like '%제목3%';

select * from tbl_board
where b_title like '%'||'제목3'||'%';