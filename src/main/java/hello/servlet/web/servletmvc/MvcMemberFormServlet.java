package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 회원 등록 부터 MVC 패턴으로 개발
 * 서블릿을 Controller로 사용하고 JSP를 View로 사용할 거다.
 * 해당 클래스가 Controller가 된다.
 * Controller에서는 request.setAttribute() 로 값을 담고 (HttpServletRequest 객체를 Model 처럼 쓰는 거다)
 * View에서는 request.getAttribute()로 값을 꺼내는 거다.
 */
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 얘는 할일이 없다. 그냥 JSP로 가주면 된다.
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // Controller에서 View로 이동해야한다. 그 떄 사용하는 거다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // forward() : 서블릿에서 JSP 호출
        dispatcher.forward(request, response);
        // => 이렇게 하면 JSP를 찾아서 넘어가서 호출이 된다.
    }
}
