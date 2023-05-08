package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    // MemberRepository 저장하려면 필요
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        // 값을 가져와서 읽어야 한다.
        // url의 쿼리 스트링이든 HTML-form(POST방식) 데이터든 username=kim&&age=20 이거를 읽어야 한다.
        String username = request.getParameter("username");
        // request.getParameter의 응답 결과는 항상 문자이다.  숫자타입으로 변환해주자.
        int age = Integer.parseInt(request.getParameter("age"));

        // Member 객체 만들기
        Member member = new Member(username, age);
        // 저장하기
        memberRepository.save(member);
        
        // 저장이 잘 됐는지 보자
        // 결과 : 응답을 HTML 코드로 내려보내기
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        // HTML이 동적으로 코드가 들어갔다.
        w.write("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "   <li>id="+member.getId()+"</li>\n" +
                "   <li>username="+member.getUsername()+"</li>\n" +
                "   <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
