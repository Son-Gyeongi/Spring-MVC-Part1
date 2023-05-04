package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// HTTP 요청 데이터 - (API 메시지 바디 - 단순 텍스트)
// 보통 문자로 주고 받지 않는다. 보통 JSON형식으로 주고 받는다.
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 이렇게 하면 메시지 바디의 내용을 바이트 코드로 바로 얻을 수 있다.
        // getInputStream()로 데이터 읽을을수 있다.
        ServletInputStream inputStream = request.getInputStream();
        // 바이트 코드를 String으로 바꿔야 한다. 여러가지 방법이 있다.
        // 스프링이 제공해주는 StreamUtils 유틸리티 사용하자.
        // StandardCharsets.UTF_8 : inputStream를 꺼낼 때 인코딩 정보가 뭔지 알려줘야 한다., 대부분 UTF-8쓴다.
        // 항상 바이트를 문자로(문자를 바이트로)변환할 때는 어떤 인코딩인지 알려줘야 한다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        // 응답 설정
        response.getWriter().write("ok");
    }
}
