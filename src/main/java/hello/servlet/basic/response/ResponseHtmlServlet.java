package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// HTTP 응답 데이터 - 단순 텍스트, HTML
@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // html로 나갈거다. Content-Type를 먼저 잡아줘야 한다.
        // Content-Type: text/html;charset=utf-8 // charset: 인코딩
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        // [message body]
        PrintWriter writer = response.getWriter();
        // 자바 코드로 HTML 작성하기 쉽지 않다.
        writer.println("<html>");
        writer.println("<body>");
        writer.println("  <div>안녕?</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
