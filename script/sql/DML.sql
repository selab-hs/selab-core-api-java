##########
# member #
##########

insert
into member
(created_at, modified_at, member_avatar, member_email, member_name, member_nickname, member_password, member_role,
 member_student_id)
values (?, ?, ?, ?, ?, ?, ?, ?, ?);



#########
# terms #
#########

insert
into terms
(created_at, modified_at, terms_category, member_id)
values (?, ?, ?, ?);



#########
# board #
#########

insert
into board
(created_at, modified_at, board_delete_flag, board_description, board_title)
values (?, ?, ?, ?, ?);



########
# post #
########

insert
into post
(created_at, modified_at, board_id, post_content, post_delete_flag, member_id, post_title)
values (?, ?, ?, ?, ?, ?, ?);



#############
# post_like #
#############

insert
into post_like
(created_at, modified_at, member_id, post_id)
values (?, ?, ?, ?);



###########
# comment #
###########

insert
into comment
(created_at, modified_at, comment_content, comment_delete_flag, member_id, post_id)
values (?, ?, ?, ?, ?, ?);



################
# comment_like #
################

insert
into comment_like
(created_at, modified_at, comment_id, member_id)
values (?, ?, ?, ?);



##########
# notice #
##########

insert
into notice
(created_at, modified_at, notice_content, notice_delete_flag, member_id, notice_title)
values (?, ?, ?, ?, ?, ?);



##################
# notice comment #
##################

insert
into notice_comment
(created_at, modified_at, notice_comment_content, notice_comment_delete_flag, member_id, notice_id)
values (?, ?, ?, ?, ?, ?);



###############
# notice like #
###############

insert
into notice_like
(created_at, modified_at, member_id, notice_id)
values (?, ?, ?, ?);



#############
# free post #
#############

insert
into free_post
(created_at, modified_at, free_post_content, free_post_delete_flag, member_id, free_post_title)
values (?, ?, ?, ?, ?, ?);



#####################
# free post comment #
#####################

insert
into free_post_comment
(created_at, modified_at, free_post_comment_content, free_post_comment_delete_flag, free_post_id, member_id)
values (?, ?, ?, ?, ?, ?)