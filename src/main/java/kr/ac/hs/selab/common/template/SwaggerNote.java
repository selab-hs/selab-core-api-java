package kr.ac.hs.selab.common.template;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SwaggerNote {
    public final String MEMBER_INSERT = """
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
}