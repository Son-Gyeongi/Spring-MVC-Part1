<%--회원 등록 폼 개발하기--%>
<%--webapp 밑에 있는 건 기본적으로 호출된다.
그러면 폴더를 localhost:8080/jsp/members/new-form.jsp로 페이지 열면 된다.--%>

<%--JSP는 아래 줄이 꼭 있어야 한다. JSP파일 입니다라는 뜻이다.--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--webapp/basic/hello-form.html 참고--%>
<%--저장 누르면 /jsp/members/save.jsp에 로직이 있다--%>
<form action="/jsp/members/save.jsp" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
