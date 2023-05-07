package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HTTP 응답 데이터 - API JSON
 * HTTP 응답으로 JSON을 반환할 때는 content-type을 ‘application/json’으로 지정해야 한다.
 * Jackson 라이브러리가 제공하는 objectMapper.writeValueAsString()을 사용하면 객체를 JSON문자로 변경할 수 있다.
 */
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    // 스프링에서 제공해주는 jackson - JSON 변환 라이브러리
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        // 객체 HelloData를 남긴 이유가 있다.
        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        // JSON도 그냥 문자이다. 이거를 JSON 형태로 바꿔야 한다.
        // {"username":"kim", "age":20} 이렇게 바꾸려면 ObjectMapper 필요하다.
        // writeValueAsString : 객체 값을 문자로 바꿔라.
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result); // 출력
    }
}
