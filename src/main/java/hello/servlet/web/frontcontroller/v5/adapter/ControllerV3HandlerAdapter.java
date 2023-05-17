package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// ControllerV3를 지원하는 어댑터
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException {
        // MemberFormControllerV3
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);
        // paramMap이 필요하다. paramMap 바꾸는게 필요하다.
        ModelView mv = controller.process(paramMap);

        // 어댑터의 역할이 핸들러를 호출해 주고 결과가 오면 반환타입을 ModelView에 맞춰서 반환하면 된다.
        return mv;
    }

    // 파라미터 Map을 만든다.
    // HttpServletRequest에 있는 파라미터를 다 뽑는다. paramMap을 만들어서 반환해준다.
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        // put()해서 꺼낸 거 다 넣어줘야 한다.
        // HttpServletRequest에서 getParameterNames()로 모든 파라미터 이름을 가져온다.
        // forEachRemaining() 돌리면서 파라미터이름은 paramName가 되고 값은 request.getParameter(paramName)로
        // 모든 파라미터 다 꺼내온다.
        return paramMap;
    }

}
