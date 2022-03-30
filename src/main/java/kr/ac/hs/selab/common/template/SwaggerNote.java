package kr.ac.hs.selab.common.template;

public class SwaggerNote {
    public static final String MEMBER_INSERT = """
            회원가입
            POST /api/v1/members/sign
                                                    
            Request Body
            {
                "email" : "wrjs@naver.com", // 이메일
                "password" : "paskjdljd!2312", // 비밀번호
                "studentId" : "201658109", // 학번
                "name" : "김동간", // 이름
                "nickname" : "야생의 구피" // 닉네임
            }
                                                    
            Response Body
            {
                "message" : "MEMBER_INSERT_SUCCESS", // API MESSAGE
                "code" : "R-M-0001", // API CODE
                "serverDateTime" : "2022-02-18T22:53:46.270418", // 서버 타임
                "data" : {
                            "email" : "wrjs@naver.com" // 이메일
                         }
            }                                                                   
            """;

    public static final String AUTH_LOGIN = """
            로그인
            POST /api/v1/auth/login
                                    
            Request Body
            {
                "email" : "wrjs@naver.com", // 이메일
                "password" : "aslkjdfloaksjd1!23" // 비밀번호
            }
                                    
            Response Body
            {
                "message": "AUTH_LOGIN_SUCCESS", // API MESSAGE
                "code": "R-A-0001", // API CODE
                "serverDateTime": "2022-03-28T13:37:07.676Z", // 서버 타임
                "data": {
                    "email": "wrjs@naver.com", // 사용자 이메일
                    "token": "asdk.jasldkjklwejfmasldjasjdljasldfjasdj" // 사용자 토큰 데이터
                }
            }
            """;
}