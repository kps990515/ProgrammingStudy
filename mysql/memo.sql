# 1. 테이블 생성
# create table 테이블이름 
# (
# 	column 1 이름 + column 타입 + 옵션1 + 옵션2
# 	column 2 이름 + column 타입 
# 	column 3 이름 + column 타입
# );

create table memo
(	#이름이 예약어인 경우 ` `로 감싸준다
	`no` int primary key not null auto_increment, #int형의 기본키 + null 값이 아님 + 자동 증가함수memo
    `name` varchar(100),
    `content` text,
    `datetime` datetime
);