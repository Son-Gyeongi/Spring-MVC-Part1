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
import java.util.List;

// 저장된 모든 회원 목록을 조회하는 기능
@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        // members 가져왔으니깐 이걸 동적으로 뿌리면 된다.
        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write("   <meta charset=\"UTF-8\">");
        w.write("   <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write("   <thead>");
        w.write("   <th>id</th>");
        w.write("   <th>username</th>");
        w.write("   <th>age</th>");
        w.write("   </thead>");
        w.write("   <tbody>");
/*
정적인 값
 w.write(" <tr>");
 w.write(" <td>1</td>");
 w.write(" <td>userA</td>");
 w.write(" <td>10</td>");
 w.write(" </tr>");
*/
        // 동적인 값
        for (Member member : members) {
            w.write(" <tr>");
            w.write("   <td>" + member.getId() + "</td>");
            w.write("   <td>" + member.getUsername() + "</td>");
            w.write("   <td>" + member.getAge() + "</td>");
            w.write(" </tr>");
        }
        w.write("   </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }
}

/**
 * 템플릿 엔진
 * HTML에다가 자바 코드를 넣는거다.
 * 위에 코드는 자바 코드에 HTML을 넣은거다.
 *
 * 템플릿이 있고 중간중간 값을 바꾸는 기능을 제공하는 걸 템플릿 엔진이라고 한다.
 */
