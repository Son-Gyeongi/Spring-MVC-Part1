<%--회원 저장 뷰 결과 JSP--%>
<%--<%@ page import="hello.servlet.domain.member.Member" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%--    <li>id=<%=((Member)request.getAttribute("member")).getId()%></li>--%>
<%--    <li>username=<%=((Member)request.getAttribute("member")).getUsername()%></li>--%>
<%--    <li>age=<%=((Member)request.getAttribute("member")).getAge()%></li>--%>
<%--    위와 같이 할 수 있지만 번잡하다
        JSP가 제공하는 표현식이 있다. ${} 아래와 같이 쓰면 위에 쓰는 거랑 같다.
        이렇게 하면 request.getAttrubute()해서 가지고 온다.
        거기에 있는 property 접근법 getter,setter
        username이라고 하면 getUsername이 호출이 된다.
        property 접근법으로 JSP를 제공하는 이 표현법으로 하면 편하게 조회할 수 있다.--%>
<%--    view를 출력하기 위한 표현번도 있고 view에 특화되서 view를 위한 깔끔한 로직이 나왔다.--%>
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
