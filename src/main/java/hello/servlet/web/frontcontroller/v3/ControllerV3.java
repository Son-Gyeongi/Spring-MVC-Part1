package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // v2는 서블릿 기술들이 들어갔었다. HttpServletRequest request, HttpServletResponse response
    // ControllerV3는 굉장히 단순하고 직접 만든 ModelView를 반환한다.
    // framework에 종속적이지 서블릿에 종속적이지 않다.
    ModelView process(Map<String, String> paramMap);
}
