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
              "title": "환영합니다. 홈페이지가 개설됐습니다.",
              "content": "소프트웨어 엔지니어 연구실 연구원분들을 위한 홈페이지입니다."
            }
                                    
            Response Body
            {
                "message": "FREE_POST_CREATE_SUCCESS",
                "code": "R-F-0001",
                "serverDateTime": "2022-04-13T09:03:36.179145",
                "data": {
                    "id": 1
                }
            }
            """;

    public final String FREE_POST_FIND = """
            자유 게시글 조회
            GET /api/v1/free-posts/1
                                    
            Response Body
            {
                "message": "FREE_POST_FIND_SUCCESS",
                "code": "R-F-0002",
                "serverDateTime": "2022-04-13T09:10:03.105152",
                "data": {
                    "memberId": 1,
                    "freePostId": 1,
                    "title": "환영합니다. 홈페이지가 개설됐습니다.",
                    "content": "소프트웨어 엔지니어 연구실 연구원분들을 위한 홈페이지입니다.",
                    "createdAt": "2022-04-13T09:03:36",
                    "modifiedAt": "2022-04-13T09:03:36"
                }
            }
            """;

    public final String FREE_POST_FIND_BY_PAGE = """
            자유 게시글 전체 조회
            GET api/v1/free-posts?page=0
                                    
            Response Body
            {
                "message": "FREE_POST_FIND_SUCCESS",
                "code": "R-F-0002",
                "serverDateTime": "2022-04-13T09:11:52.330891",
                "data": {
                    "totalCount": 2,
                    "pageNumber": 0,
                    "pageSize": 20,
                    "sort": "createdAt: DESC",
                    "freePosts": [
                        {
                            "memberId": 1,
                            "freePostId": 2,
                            "title": "환영합니다. 홈페이지가 개설됐습니다.2",
                            "content": "소프트웨어 엔지니어 연구실 연구원분들을 위한 홈페이지입니다.2",
                            "createdAt": "2022-04-13T09:11:46",
                            "modifiedAt": "2022-04-13T09:11:46"
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
            PUT api/v1/free-posts/1
                                    
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
                    "id": 1
                }
            }
            """;

    public final String FREE_POST_DELETE = """
            자유 게시글 소프트 삭제
            PATCH api/v1/free-posts/1
                                    
            Response Body
            {
                "message": "FREE_POST_DELETE_SUCCESS",
                "code": "R-F-0004",
                "serverDateTime": "2022-04-13T09:26:22.850328",
                "data": {
                    "id": 1
                }
            }
            """;
}