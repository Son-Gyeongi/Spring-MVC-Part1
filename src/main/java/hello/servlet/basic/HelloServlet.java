package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * helloServlet name은 아무거나 줘도 된다. 클래스 이름이랑 비슷하게 줬다.
 * 첫 글자는 소문자로 주었다.
 * /hello로 들어오면 실행된다.
 */
@WebServlet(name = "helloServlet", urlPatterns = "/hello") // name, urlPatterns 중복이 있으면 안된다.
public class HelloServlet extends HttpServlet { // servlet은 HttpServlet를 상속받는다.

    // HelloServlet이 호출되면 service메서드가 호출된다.

    /**
     * HTTP 요청이 오면 WAS가 서블릿 컨테이너에 request, response 객체를 만들어서 서블릿에 던져준다.
     * http://localhost:8080/hello로 요청 하면 웹 브라우저가 HTTP메시지를 만든다. 그거를 서버에 던진다.- request
     * 응답을 위한 response 객체를 서버가 만들어서 내려준다.
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        /**
         * 결과
         * request = org.apache.catalina.connector.RequestFacade@7408e9db
         * response = org.apache.catalina.connector.ResponseFacade@b94968e
         * HttpServletRequest 나 HttpServletResponse는 interface로 되어있다.
         * 톰캣이나 제티나 언더토우 여러가지 WAS서버가 많다.
         * WAS 서버들이 servlet 표준 스펙을 구현하는 거다. 그러면 구현체가 있을거다.
         * 그 구현체들이 결과로 나온거다.
         */

        // http://localhost:8080/hello?username=kim 이렇게 만들어보자
        // ?username=kim HTTP 요청 메시지를 만들 때 '쿼리 파라미터'라고 한다.
        // 쿼리 파라미터를 서블릿이 굉장히 편하게 읽도록 지원해준다.
        // request.getParameter로 편리하게 쿼리 파라미터를 조회할 수 있다.
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 이번에는 응답 메시지를 보내보자
        // 응답은 HttpServletResponse에다가 찍어줘야 한다.
        // 여기에 값을 넣으면 웹 브라우저에 응답하는 response HTTP 응답 메시지에 데이터가 담겨서 나가게 된다.
        response.setContentType("text/plain"); // text/plain - 단순 문자로 보낼거다. HTTP 메시지 헤더 Content-Type 정보에 들어간다.
        response.setCharacterEncoding("utf-8"); // 문자 설정 인코딩 정보를 알려줘야 한다. HTTP 메시지 헤더 Content-Type 정보에 들어간다.
        // 참고로 요즘에는 문자 인코딩을 utf-8로 써야한다. EUC-KR을 지금은 쓰면 안된다.
        // 옛날 시스템이 아니면 utf-8을 쓰면 된다.
        response.getWriter().write("hello " + username); // HTTP 메시지 바디에 데이터가 들어간다.
    }
    // HTTP 요청 메시지 로그로 확인하기 => resources/application.properties 설정하기
    // 운영서버에 이렇게 모든 요청 정보를 다 남기면 성능저하가 발생할 수 있으니 개발 단계에서만 적용하자
}
