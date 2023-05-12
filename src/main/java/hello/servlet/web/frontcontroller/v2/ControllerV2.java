package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    // 기존의 ControllerV1이랑 똑같은데 반환을 MyView로 할 거다.
    // 모든 컨트롤러가 JSP로 이동한다.
    // 기존에는 void였고 컨트롤러가 알아서 다 forward로 이동했는데
    // 이제는 그냥 MyView로 만들어서 넘기는 식으로 인터페이스 설계했다.
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
