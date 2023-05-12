package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 회원 목록 컨트롤러
// MvcMemberListServlet 클래스에서 그대로 가져온다.
// 로직은 기존 서블릿이랑 완전 똑같다. 대신에 HttpServlet을 상속 받는게 아니라
// ControllerV1 인터페이스를 상속 받는다. 인터페이스를 구현하고 @WebServlet이 없다.
public class MemberListControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        // Model에 담아야 한다.
        request.setAttribute("members", members); // key, value

        // viewPath 잡아주기
        String viewPath = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
