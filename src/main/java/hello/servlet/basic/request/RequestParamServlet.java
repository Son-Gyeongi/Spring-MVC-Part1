package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * HTTP 요청 데이터 - GET방식 쿼리 파라미터(= 쿼리 스트링)
 * 1. 파라미터 전송 기능
 * http://localhsot:8080/request-param?username=hello&age=20
 *
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("RequestParamServlet.service"); // 일단 실행이 잘 되는지 보자

        System.out.println("[전체 파라미터 조회] - start");
        // 전체 파라미터 조회, request parameter 꺼내기, 모든 요청 파라미터 다 꺼낼 수 있다.
//        Enumeration<String> parameterNames = request.getParameterNames(); // 옛날 방식
        request.getParameterNames().asIterator()
                        .forEachRemaining(paramName
                                -> System.out.println(paramName+"="+request.getParameter(paramName)));
        // 키:paramName, 밸류:request.getParameter(paramName)

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");
        // 파라미터 하나 조회, 이걸 제일 많이 쓴다.
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        String age = request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end");
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회] - start");
        // 하나의 파라미터 이름에 여러값들을 넘길 수 있다.
        // 이름의 값이 중복되서 넘어갈 수 있다.
        // 예시 - ?username=hello&age=20&username=hello2
        // 내부 우선순위에서 먼저 잡히는 애가 나온다.
        // 대부분 단일 파라미터로 보내지 복수 파라미터 거의 없다.
        String[] usernames = request.getParameterValues("username"); // 배열로 나온다.
        for (String name : usernames) {
            System.out.println("username = " + name);
        }
        System.out.println("[이름이 같은 복수 파라미터 조회] - end");

        // 웹 브라우저에 아무것도 없어서 허전해서 작성
        response.getWriter().write("ok");
    }
}
