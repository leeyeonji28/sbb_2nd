# DB 생성
DROP DATABASE IF EXISTS sbb;
CREATE DATABASE sbb;
USE sbb;

# 회원 테이블 생성
CREATE TABLE `user` (
    id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    reg_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL,
    email VARCHAR(100) NOT NULL,
    `password` VARCHAR(150) NOT NULL,
    `name` CHAR(50) NOT NULL
);


# 회원 데이터 생성
INSERT INTO `user`
SET reg_date = NOW(),
update_date = NOW(),
email = 'use1@test.com',
`password` = '1234',
`name` = '유저1';

INSERT INTO `user`
SET reg_date = NOW(),
update_date = NOW(),
email = 'use2@test.com',
`password` = '1234',
`name` = '유저2';

INSERT INTO `user`
SET reg_date = NOW(),
update_date = NOW(),
email = 'use3@test.com',
`password` = '1234',
`name` = '유저3';

# 게시물 테이블 생성
CREATE TABLE `article` (
    id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    reg_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL,
    title VARCHAR(100) NOT NULL,
    `body` TEXT NOT NULL,
    `user_id` BIGINT UNSIGNED NOT NULL
);

# 게시물 데이터 생성
INSERT INTO `article`
SET reg_date = NOW(),
update_date = NOW(),
title = '제목 1',
`body` = '내용 1',
`user_id` = 1;

INSERT INTO `article`
SET reg_date = NOW(),
update_date = NOW(),
title = '제목 2',
`body` = '내용 2',
`user_id` = 2;

INSERT INTO `article`
SET reg_date = NOW(),
update_date = NOW(),
title = '제목 3',
`body` = '내용 3',
`user_id` = 3;

# 질문 테이블 생성
CREATE TABLE Question (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `subject` VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL
);

#질문 데이터 생성
INSERT INTO Question SET
    create_date = NOW(),
`subject` = "question1",
content = "content1";

INSERT INTO Question SET
    create_date = NOW(),
`subject` = "question2",
content = "content2";

INSERT INTO Question SET
    create_date = NOW(),
`subject` = "question3",
content = "content3";

SELECT * FROM Question;

# 답변 테이블 생성
CREATE TABLE Answer(
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL,
    question_id INT(11) UNSIGNED NOT NULL
);

# 답변 데이터 생성
INSERT INTO Answer SET
create_date = NOW(),
content = "answer1",
question_id = 1;

INSERT INTO Answer SET
    create_date = NOW(),
content = "answer2",
question_id = 2;

INSERT INTO Answer SET
    create_date = NOW(),
content = "answer3",
question_id = 3;

SELECT * FROM Answer;