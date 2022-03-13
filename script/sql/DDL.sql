# 회원 테이블 정의
create table member
(
    member_id         bigint auto_increment
        primary key,
    created_at        datetime     null,
    modified_at       datetime     null,
    member_avatar     varchar(255) null,
    member_email      varchar(255) null,
    member_name       varchar(255) null,
    member_nickname   varchar(255) null,
    member_password   varchar(255) null,
    member_role       varchar(255) null,
    member_student_id varchar(255) null,
    constraint UK_3orqjaukiw2b73e2gw8rer4rq
        unique (member_email),
    constraint UK_6xw0l4de4bliu5jps17bxllyf
        unique (member_student_id),
    constraint UK_j0kdf0m8cdj4uy6l7ntpgxrlo
        unique (member_nickname)
);


# 약관 테이블 정의
create table terms
(
    terms_id       bigint auto_increment
        primary key,
    created_at     datetime     null,
    modified_at    datetime     null,
    terms_category varchar(255) null,
    member_id      bigint       null
);


# 게시판 테이블 정의
create table board
(
    board_id          bigint auto_increment
        primary key,
    created_at        datetime     null,
    modified_at       datetime     null,
    board_delete_flag bit          not null,
    board_description varchar(255) not null,
    board_title       varchar(255) not null,
    constraint UK_2qo5svskanh4old2dn1r7tdar
        unique (board_title)
);


# 게시글 테이블 정의
create table post
(
    post_id          bigint auto_increment
        primary key,
    created_at       datetime     null,
    modified_at      datetime     null,
    board_id         bigint       not null,
    post_content     varchar(255) not null,
    post_delete_flag bit          not null,
    member_id        bigint       not null,
    post_title       varchar(255) not null
);


# 댓글 테아블 정의
create table comment
(
    comment_id          bigint auto_increment
        primary key,
    created_at          datetime     null,
    modified_at         datetime     null,
    comment_content     varchar(255) not null,
    comment_delete_flag bit          not null,
    member_id           bigint       not null,
    post_id             bigint       not null
);


# 게시글 좋아요 테이블 정의
create table post_like
(
    post_like_id bigint auto_increment
        primary key,
    created_at   datetime null,
    modified_at  datetime null,
    member_id    bigint   not null,
    post_id      bigint   not null,
    constraint UK6b8s3f8oiog3im43ddg35bxnn
        unique (member_id, post_id)
);


# 댓글 좋아요 테이블 정의
create table comment_like
(
    comment_like_id bigint auto_increment
        primary key,
    created_at      datetime null,
    modified_at     datetime null,
    comment_id      bigint   not null,
    member_id       bigint   not null,
    constraint UKne7gphq9o8iuq6o6qr61olqwc
        unique (member_id, comment_id)
);