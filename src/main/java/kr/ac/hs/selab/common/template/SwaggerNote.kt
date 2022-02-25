package kr.ac.hs.selab.common.template

const val MEMBER_INSERT = """
    회원가입
    
    POST /api/v1/members/sign
    
    Request Body
    {
	    "email" : "wrjs@naver.com", // 이메일
	    "password" : "sdputd2423", // 비밀번호 
	    "studentId" : "201658109", // 학번
	    "name" : "김동건", // 김동건
	    "nickname" : "야생의 구피" // 닉네임
    }
    
    Response Body
    {
	    "message": "MEMBER_INSERT_SUCCESS", // api name
	    "code": "R-M-0001", // api code
	    "serverDateTime": "2022-02-18T22:53:46.270418", // server date time
	    "data": {
		    "email": "wrjs@naver.com" // 이메일
	    }
    }
"""