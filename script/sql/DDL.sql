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


# 질의 응답 테이블 정의
create table core_qa
(
    core_qa_id      bigint auto_increment
        primary key,
    created_at      datetime     null,
    modified_at     datetime     null,
    core_qa_content varchar(255) null,
    member_id       bigint       null,
    core_qa_title   varchar(255) null
);


# 공지사항 테이블 정의
create table notice
(
    notice_id          bigint auto_increment
        primary key,
    created_at         datetime     null,
    modified_at        datetime     null,
    notice_content     varchar(255) not null,
    notice_delete_flag bit          not null,
    member_id          bigint       not null,
    notice_title       varchar(255) not null
);


# 공지사항 댓글 테이블 정의
create table notice_comment
(
    notice_comment_id          bigint auto_increment
        primary key,
    created_at                 datetime     null,
    modified_at                datetime     null,
    notice_comment_content     varchar(255) not null,
    notice_comment_delete_flag bit          not null,
    member_id                  bigint       not null,
    notice_id                  bigint       not null
);


# 공지사항 좋아요 테이블 정의
create table notice_like
(
    notice_like_id bigint auto_increment
        primary key,
    created_at     datetime null,
    modified_at    datetime null,
    member_id      bigint   not null,
    notice_id      bigint   not null,
    constraint UKe482ut32ct92bn6l50tmost58
        unique (member_id, notice_id)
);


# 자유 게시글 테이블 정의
create table free_post
(
    free_post_id          bigint auto_increment
        primary key,
    created_at            datetime     null,
    modified_at           datetime     null,
    free_post_content     varchar(255) not null,
    free_post_delete_flag bit          not null,
    member_id             bigint       not null,
    free_post_title       varchar(255) not null
);


# 자유 게시글 댓글 테이블 정의
create table free_post_comment
(
    free_post_comment_id          bigint auto_increment
        primary key,
    created_at                    datetime     null,
    modified_at                   datetime     null,
    free_post_comment_content     varchar(255) not null,
    free_post_comment_delete_flag bit          not null,
    free_post_id                  bigint       not null,
    member_id                     bigint       not null
);