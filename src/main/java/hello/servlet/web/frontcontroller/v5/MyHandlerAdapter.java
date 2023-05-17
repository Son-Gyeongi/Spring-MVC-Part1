package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 다양한 종류의 컨트롤러를 호출할 수 있다.
public interface MyHandlerAdapter {

    // 컨트롤러가 넘어왔을 때 지원할 수 있는지 유무 검사
    // MemberFormControllerV3
    boolean supports(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;

    // ControllerV3를 지원하는 어댑터를 먼저 만들어보자 ControllerV3HandlerAdapter
}
