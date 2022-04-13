package kr.ac.hs.selab.common.template;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SwaggerNote {
    public final String MEMBER_CREATE = """
            회원가입
            POST /api/v1/members/sign
                                                    
            Request Body
            {
                "email": "leeheefull@gmail.com", // 이메일
                "password": "zxcv1234!", // 비밀번호
                "studentId": "201658007", // 학번
                "name": "이희찬", // 이름
                "nickname": "leeheefull", // 닉네임
                "avatar": "null" // 이미지
            }
                                                    
            Response Body
            {
                "message": "MEMBER_INSERT_SUCCESS", // API 메세지
                "code": "R-M-0001", // API 코드
                "serverDateTime": "2022-04-12T22:31:29.465069", // 서버 시간
                "data": {
                    "email": "leeheefull@gmail.com" // 이메일
                }
            }
            """;

    public final String AUTH_LOGIN = """
            로그인
            POST /api/v1/auth/login
                                    
            Request Body
            {
                "email": "leeheefull@gmail.com", // 이메일
                "password": "zxcv1234!" // 비밀번호
            }
                                    
            Response Body
            {
                "message": "AUTH_LOGIN_SUCCESS", // API 메세지
                "code": "R-A-0001", // API 코드
                "serverDateTime": "2022-04-12T17:06:24.605293", // 서버 시간
                "data": {
                    "email": "leeheefull@gmail.com", // 이메일
                    "token": "eyJhbGciOiJIUzUxMiJ9"  // 토큰 데이터
                }
            }
            """;

    public final String FREE_POST_CREATE = """
            자유 게시글 생성
            POST /api/v1/free-posts
                                    
            Request Body
            {
              "title": "환영합니다. 홈페이지가 개설됐습니다.", // 자유 게시글 제목
              "content": "소프트웨어 엔지니어 연구실 연구원분들을 위한 홈페이지입니다." // 자유 게시글 내용
            }
                                    
            Response Body
            {
                "message": "FREE_POST_CREATE_SUCCESS",
                "code": "R-F-0001",
                "serverDateTime": "2022-04-13T09:03:36.179145",
                "data": {
                    "id": 1 // 자유 게시글 아이디
                }
            }
            """;

    public final String FREE_POST_FIND = """
            자유 게시글 조회
            GET api/v1/free-posts/{id}
            
            Path Variable
            "id": 1 // 자유 게시글 아이디
                                    
            Response Body
            {
                "message": "FREE_POST_FIND_SUCCESS",
                "code": "R-F-0002",
                "serverDateTime": "2022-04-13T09:10:03.105152",
                "data": {
                    "memberId": 1, // 회원 아이디
                    "freePostId": 1, // 자유 게시글 아이디
                    "title": "환영합니다. 홈페이지가 개설됐습니다.", // 자유 게시글 제목
                    "content": "소프트웨어 엔지니어 연구실 연구원분들을 위한 홈페이지입니다.", // 자유 게시글 내용
                    "createdAt": "2022-04-13T09:03:36", // 자유 게시글 생성 날짜
                    "modifiedAt": "2022-04-13T09:03:36" // 자유 게시글 수정 날짜
                }
            }
            """;

    public final String FREE_POST_FIND_BY_PAGE = """
            자유 게시글 전체 조회
            GET api/v1/free-posts?page=0
                                    
            Params
            "page": 0 // 페이지 쪽수
            
            Response Body
            {
                "message": "FREE_POST_FIND_SUCCESS",
                "code": "R-F-0002",
                "serverDateTime": "2022-04-13T09:11:52.330891",
                "data": {
                    "totalCount": 2, // 게시글 전체 개수
                    "pageNumber": 0, // 페이지 쪽수
                    "pageSize": 20, // 한 페이지의 가져올 게시글 개수
                    "sort": "createdAt: DESC", // 정렬 기준
                    "freePosts": [
                        {
                            "memberId": 1, // 회원 아이디
                            "freePostId": 2, // 자유 게시글 아이디
                            "title": "환영합니다. 홈페이지가 개설됐습니다.2", // 자유 게시글 제목
                            "content": "소프트웨어 엔지니어 연구실 연구원분들을 위한 홈페이지입니다.2", // 자유 게시글 내용
                            "createdAt": "2022-04-13T09:11:46", // 자유 게시글 생성 날짜
                            "modifiedAt": "2022-04-13T09:11:46" // 자유 게시글 수정 날짜
                        },
                        {
                            "memberId": 1,
                            "freePostId": 1,
                            "title": "환영합니다. 홈페이지가 개설됐습니다.",
                            "content": "소프트웨어 엔지니어 연구실 연구원분들을 위한 홈페이지입니다.",
                            "createdAt": "2022-04-13T09:03:36",
                            "modifiedAt": "2022-04-13T09:03:36"
                        }
                    ]
                }
            }
            """;

    public final String FREE_POST_UPDATE = """
            자유 게시글 수정
            PUT api/v1/free-posts/{id}
            
            Path Variable
            "id": 1 // 자유 게시글 아이디
                                    
            Request Body
            {
              "title": "자유게시글 수정이 있겠습니다.",
              "content": "수정 내용은 아래와 같습니다."
            }
                                    
            Response Body
            {
                "message": "FREE_POST_UPDATE_SUCCESS",
                "code": "R-F-0003",
                "serverDateTime": "2022-04-13T09:22:57.076397",
                "data": {
                    "id": 1 // 자유 게시글 아이디
                }
            }
            """;

    public final String FREE_POST_DELETE = """
            자유 게시글 소프트 삭제
            PATCH api/v1/free-posts/{id}
            
            Path Variable
            "id": 1 // 자유 게시글 아이디
                                    
            Response Body
            {
                "message": "FREE_POST_DELETE_SUCCESS",
                "code": "R-F-0004",
                "serverDateTime": "2022-04-13T09:26:22.850328",
                "data": {
                    "id": 1 // 자유 게시글 댓글 아이디
                }
            }
            """;

    public final String FREE_POST_COMMENT_CREATE = """
            자유 게시글 댓글 생성
            POST api/v1/free-posts/{freePostId}/free-post-comments
            
            Path Variable
            "freePostId": 1 // 자유 게시글 아이디
                        
            Request Body
            {
              "freePostCommentContent": "푸항항 너무 웃겨요" // 자유 게시글 댓글 내용
            }
                                    
            Response Body
            {
                "message": "FREE_POST_COMMENT_CREATE_SUCCESS",
                "code": "R-FC-0001",
                "serverDateTime": "2022-04-13T09:32:03.224535",
                "data": {
                    "freePostCommentId": 1 // 자유 게시글 댓글 아이디
                }
            }
            """;

    public final String FREE_POST_COMMENT_FIND = """
            자유 게시글 댓글 조회
            GET api/v1/free-post-comments/{commentId}
                                    
            Path Variable
            "commentId": 1 // 자유 게시글 댓글 아이디
                                    
            Response Body
            {
                "message": "FREE_POST_COMMENT_FIND_SUCCESS",
                "code": "R-FC-0002",
                "serverDateTime": "2022-04-13T09:42:43.747681",
                "data": {
                    "freePostId": 1, // 자유 게시글 아이디
                    "memberId": 1, // 회원 아이디
                    "freePostCommentId": 1, // 자유 게시글 댓글 아이디
                    "freePostCommentContent": "푸항항 너무 웃겨요", // 자유 게시글 댓글 내용
                    "createdAt": "2022-04-13T09:32:03", // 자유 게시글 댓글 생성 시간
                    "modifiedAt": "2022-04-13T09:32:03" // 자유 게시글 댓글 수정 시간
                }
            }
            """;

    public final String FREE_POST_COMMENT_FIND_BY_PAGE = """
            자유 게시글 댓글 전체 조회
            GET api/v1/free-posts/{freePostId}/free-post-comments?page=0

            Path Variable
            "freePostId": 1 // 자유 게시글 아이디
            
            Params
            "page": 0 // 페이지 쪽수
                                    
            Response Body
            {
                "message": "FREE_POST_COMMENT_FIND_SUCCESS",
                "code": "R-FC-0002",
                "serverDateTime": "2022-04-13T09:46:43.895765",
                "data": {
                    "freePostId": 1, // 자유 게시글 아이디
                    "totalCount": 2, // 자유 게시글 아이디에 대한 댓글 전체 개수
                    "pageNumber": 0, // 페이지 쪽수
                    "pageSize": 20, // 한 페이지당 개수
                    "sort": "createdAt: DESC", // 정렬 기준
                    "freePostComments": [
                        {
                            "memberId": 1, // 회원 아이디
                            "freePostCommentId": 2, // 자유 게시글 댓글 아이디
                            "freePostCommentContent": "푸항항 너무 웃겨요", // 자유 게시글 댓글 내용
                            "createdAt": "2022-04-13T09:46:41", // 자유 게시글 댓글 생성 시간
                            "modifiedAt": "2022-04-13T09:46:41" // 자유 개시글 댓글 수정 시간
                        },
                        {
                            "memberId": 1,
                            "freePostCommentId": 1,
                            "freePostCommentContent": "푸항항 너무 웃겨요",
                            "createdAt": "2022-04-13T09:46:40",
                            "modifiedAt": "2022-04-13T09:46:40"
                        }
                    ]
                }
            }
            """;

    public final String FREE_POST_COMMENT_UPDATE = """
            자유 게시글 댓글 수정
            PUT api/v1/free-post-comments/{commentId}
            
            Path Variable
            "commentId": 1 // 자유 게시글 댓글 아이디
                                    
            Request Body
            {
              "freePostCommentContent": "웃기긴 뭐가 웃깁니까" // 자유 게시글 댓글 내용
            }
                                    
            Response Body
            {
                "message": "FREE_POST_COMMENT_UPDATE_SUCCESS",
                "code": "R-FC-0003",
                "serverDateTime": "2022-04-13T10:20:10.116645",
                "data": {
                    "freePostCommentId": 1 // 자유 게시글 댓글 아이디
                }
            }
            """;

    public final String FREE_POST_COMMENT_DELETE = """
            자유 게시글 소프트 삭제
            PATCH api/v1/free-post-comments/{commentId}
            
            Path Variable
            "commentId": 1 // 자유 게시글 댓글 아이디
                                    
            Response Body
            {
                "message": "FREE_POST_COMMENT_DELETE_SUCCESS",
                "code": "R-FC-0004",
                "serverDateTime": "2022-04-13T10:21:52.667213",
                "data": {
                    "freePostCommentId": 1 // 자유 게시글 댓글 아이디
                }
            }
            """;
}