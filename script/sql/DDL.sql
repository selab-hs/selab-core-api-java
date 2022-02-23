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

create table terms
(
    terms_id       bigint auto_increment
        primary key,
    created_at     datetime     null,
    modified_at    datetime     null,
    terms_category varchar(255) null,
    member_id      bigint       null,
    constraint FKfxtbtqu1tos5iweudpsmh7j2i
        foreign key (member_id) references member (member_id)
);

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

create table post
(
    post_id          bigint auto_increment
        primary key,
    created_at       datetime     null,
    modified_at      datetime     null,
    post_content     varchar(255) not null,
    post_delete_flag bit          not null,
    post_title       varchar(255) not null,
    board_id         bigint       not null,
    member_id        bigint       not null,
    constraint FK2t7katxxymxif93a9osshl0ns
        foreign key (board_id) references board (board_id),
    constraint FK83s99f4kx8oiqm3ro0sasmpww
        foreign key (member_id) references member (member_id)
);

create table post_like
(
    post_like_id bigint auto_increment
        primary key,
    created_at   datetime null,
    modified_at  datetime null,
    member_id    bigint   not null,
    post_id      bigint   not null,
    constraint UK6b8s3f8oiog3im43ddg35bxnn
        unique (member_id, post_id),
    constraint FKj7iy0k7n3d0vkh8o7ibjna884
        foreign key (post_id) references post (post_id),
    constraint FKqjxwr6kkv6pw2e4pwy4yktxyk
        foreign key (member_id) references member (member_id)
);

create table comment
(
    comment_id          bigint auto_increment
        primary key,
    created_at          datetime     null,
    modified_at         datetime     null,
    comment_content     varchar(255) not null,
    comment_delete_flag bit          not null,
    member_id           bigint       not null,
    post_id             bigint       not null,
    constraint FKmrrrpi513ssu63i2783jyiv9m
        foreign key (member_id) references member (member_id),
    constraint FKs1slvnkuemjsq2kj4h3vhx7i1
        foreign key (post_id) references post (post_id)
);

create table comment_like
(
    comment_like_id bigint auto_increment
        primary key,
    created_at      datetime null,
    modified_at     datetime null,
    comment_id      bigint   not null,
    member_id       bigint   not null,
    constraint UKne7gphq9o8iuq6o6qr61olqwc
        unique (member_id, comment_id),
    constraint FKjtrao5djvpcj49cxcmbenif3g
        foreign key (member_id) references member (member_id),
    constraint FKqlv8phl1ibeh0efv4dbn3720p
        foreign key (comment_id) references comment (comment_id)
);

