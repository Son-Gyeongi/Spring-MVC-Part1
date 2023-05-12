package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 회원 저장 컨트롤러
// MvcMemberSaveServlet 클래스에서 그대로 가져온다.
// 로직은 기존 서블릿이랑 완전 똑같다. 대신에 HttpServlet을 상속 받는게 아니라
// ControllerV1 인터페이스를 상속 받는다. 인터페이스를 구현하고 @WebServlet이 없다.
public class MemberSaveControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 파라미터 받고
        // 값을 가져와서 읽어야 한다.
        // url의 쿼리 스트링이든 HTML-form(POST방식) 데이터든 username=kim&&age=20 이거를 읽어야 한다.
        String username = request.getParameter("username");
        // request.getParameter의 응답 결과는 항상 문자이다.  숫자타입으로 변환해주자.
        int age = Integer.parseInt(request.getParameter("age"));

        // 2. 실제 비즈니스 로직 호출하고
        // Member 객체 만들기
        Member member = new Member(username, age);
        // 저장하기
        memberRepository.save(member);

        /**
         * 위 코드는 서블릿에서 사용하던 코드와 똑같다.
         * 우선 요청 정보 받고 실제 비즈니스 로직 컨트롤러로 호출
         *
         * 다른점
         * Model에 데이터를 보관한다.
         *
         * 아래에 JSP View가 나와야 한다.
         */
        // 3. Model에 데이터 담고
        // request 객체 내부에 Map같은 내부 저장소가 있다. 거기에 member가 저장이 된다.
        request.setAttribute("member", member);

        // 4. view로 던지고 컨트롤러의 역할을 충실하게 하고 있다.
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);// 이 경로로 넘어가겠다.
        dispatcher.forward(request, response); // 내부에서 호출
    }
}
