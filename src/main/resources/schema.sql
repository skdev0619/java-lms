create table course (
    id bigint generated by default as identity,
    title varchar(255) not null,
    creator_id bigint not null,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);

create table session (
    id bigint generated by default as identity,
    course_id bigint not null /*과정 pk*/,
    start_date timestamp not null /*강의 시작일*/,
    end_date   timestamp not null /*강의 종료일*/,
    pricing_type varchar(10) not null /*무료(FREE)/유료(PAID)*/,
    price int not null /*강의 가격*/,
    progress_status varchar(20)  /*준비중(PREPARING), 진행중(IN_PROGRESS), 종료(COMPLETED)*/,
    recruit_status varchar(20) /*모집중(RECRUITING), 비모집중(NOT_RECRUITING)*/,
    available_seat int not null /*최대 수강 인원*/,
    creator_id bigint not null  /*생성한 user id pk*/,
    created_at timestamp not null /*생성일자*/,
    updated_at timestamp /*수정일자*/,
    primary key (id)
);

create table session_image (
    id bigint generated by default as identity,
    session_id bigint not null /*강의 pk*/,
    file_path varchar(500) not null /*파일서버 이미지 파일 경로*/,
    file_size int not null  /*파일 크기*/,
    width int not null /*가로 길이*/,
    height int not null /*세로 길이*/,
    creator_id bigint not null  /*생성한 user id pk*/,
    created_at timestamp not null /*생성일자*/,
    updated_at timestamp /*수정일자*/,
    primary key (id)
);

create table session_users(
    id bigint generated by default as identity,
    session_id bigint not null /*강의 pk*/,
    ns_user_id bigint not null /*유저 pk*/,
    primary key (id)
);

create table ns_user (
    id bigint generated by default as identity,
    user_id varchar(20) not null,
    password varchar(20) not null,
    name varchar(20) not null,
    email varchar(50),
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);

create table question (
    id bigint generated by default as identity,
    created_at timestamp not null,
    updated_at timestamp,
    contents clob,
    deleted boolean not null,
    title varchar(100) not null,
    writer_id bigint,
    primary key (id)
);

create table answer (
    id bigint generated by default as identity,
    created_at timestamp not null,
    updated_at timestamp,
    contents clob,
    deleted boolean not null,
    question_id bigint,
    writer_id bigint,
    primary key (id)
);

create table delete_history (
    id bigint not null,
    content_id bigint,
    content_type varchar(255),
    created_date timestamp,
    deleted_by_id bigint,
    primary key (id)
);
