<%--회원 목록 조회 뷰--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
<%--    <%--%>
<%--        for (Member member : members) {--%>
<%--            // 출력할 수 있는 약어 out--%>
<%--            out.write(" <tr>");--%>
<%--            out.write(" <td>" + member.getId() + "</td>");--%>
<%--            out.write(" <td>" + member.getUsername() + "</td>");--%>
<%--            out.write(" <td>" + member.getAge() + "</td>");--%>
<%--            out.write(" </tr>");--%>
<%--        }--%>
<%--    %>--%>
    <%--    JSP의 JSTL이라는 기능 JSP가 특별한 태그를 제공한다.
위와 같이 안 써도 화면 출력을 깔끔하게 할 수 있다.
MvcMemberListServlet에서 Model에 담은 members를 가져오는 거다.
하나씩 꺼내서 item에 담는다.--%>
    <c:forEach var="item" items="${members}">
        <tr>
            <td>${item.id}</td>
            <td>${item.username}</td>
            <td>${item.age}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
