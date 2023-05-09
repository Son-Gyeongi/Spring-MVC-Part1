<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%--회원 저장 로직 개발하기--%>

<%--MemberSaveServlet에 있는 로직이랑 같은 걸 넣으면 된다.--%>
<%--자바 코드 넣는 곳 <% %>--%>
<%
    /**
     * request, response 는 그냥 사용 가능하도록 JSP에서 문법상 지원된다.
     * HttpServletRequest, HttpServletResponse는 JSP도 결국 서블릿으로 바뀐다.
     * 서블릿으로 자동으로 변환되서 사용된다. service 로직이 그대로 호출된다고 이해하면 된다.
     */
    // page import 해줘야 한다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    // 값을 가져와서 읽어야 한다.
    // url의 쿼리 스트링이든 HTML-form(POST방식) 데이터든 username=kim&&age=20 이거를 읽어야 한다.
    String username = request.getParameter("username");
    // request.getParameter의 응답 결과는 항상 문자이다.  숫자타입으로 변환해주자.
    int age = Integer.parseInt(request.getParameter("age"));

    // Member 객체 만들기
    Member member = new Member(username, age);
    // 저장하기
    // memberRepository 땡겨와야 한다.
    memberRepository.save(member);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
