package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ControllerV1 만드는 이유
 * 이거를 가지고 여러가지 구현을 할거다.
 * 회원 폼을 하는 컨트롤러, 회원을 저장하는 컨트롤러, 회원 리스트 컨트롤러 3가지가 다 보일거다.
 * 인터페이스를 다 구현할 거다.
 * 클라이언트가 요청이 왔을 때 매핑 정보에서 찾아서 호출할 때 다형성을 사용해서 일관성 있게
 * 프론트 컨트롤러는 인터페이스에 의존하면서 되게 편리하게 호출할 수 있다.
 */
public interface ControllerV1 {
    // 서블릿이랑 모양을 똑같은 인터페이스를 하나 만들었다. 이름만 다르다.
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
