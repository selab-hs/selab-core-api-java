package kr.ac.hs.selab.common.template;

public class SwaggerNote {
    public static final String MEMBER_INSERT = "" +
            "회원가입\n" +
            "\n" +
            "POST /api/v1/members/sign\n" +
            "\n" +
            "Request Body\n" +
            "{\n" +
            "   \"email\" : \"wrjs@naver.com\", // 이메일\n" +
            "   \"password\" : \"sdputd2423\", // 비밀번호 \n" +
            "   \"studentId\" : \"201658109\", // 학번\n" +
            "   \"name\" : \"김동건\", // 김동건\n" +
            "   \"nickname\" : \"야생의 구피\" // 닉네임\n" +
            "}\n" +
            "\n" +
            "Response Body\n" +
            "{\n" +
            "   \"message\": \"MEMBER_INSERT_SUCCESS\", // api name\n" +
            "   \"code\": \"R-M-0001\", // api code\n" +
            "   \"serverDateTime\": \"2022-02-18T22:53:46.270418\", // server date time\n" +
            "   \"data\": {\n" +
            "       \"email\": \"wrjs@naver.com\" // 이메일\n" +
            "     }\n" +
            "}";
}