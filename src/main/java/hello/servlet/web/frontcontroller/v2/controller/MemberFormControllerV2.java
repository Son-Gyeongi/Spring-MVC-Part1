package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 회원 등록 폼
public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 생성자에 viewPath를 넣어주면 된다.
        // 깔끔해졌다. 지저분한 로직 없이 경로만 넣어주면 된다. 중복이 제거가 되었다.
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
