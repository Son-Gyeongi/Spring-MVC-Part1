package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 회원 등록 컨트롤러
// MvcMemberFormServlet 클래스에서 그대로 가져온다.
// 로직은 기존 서블릿이랑 완전 똑같다. 대신에 HttpServlet을 상속 받는게 아니라
// ControllerV1 인터페이스를 상속 받는다. 인터페이스를 구현하고 @WebServlet이 없다.
public class MemberFormControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 얘는 할일이 없다. 그냥 JSP로 가주면 된다.
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // Controller에서 View로 이동해야한다. 그 떄 사용하는 거다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // forward() : 서블릿에서 JSP 호출
        dispatcher.forward(request, response);
        // => 이렇게 하면 JSP를 찾아서 넘어가서 호출이 된다.
    }
}
