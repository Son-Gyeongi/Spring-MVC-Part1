<%--JSP는 항상 이 줄이 있어야 한다.--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save]
다른데서도 해당 코드 재활용하려고 상대경로 썼다. action에 이렇게 썼으면 /save 절대경로
보통은 절대경로로 해주는 게 더 좋다.-->
<form action="save" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
