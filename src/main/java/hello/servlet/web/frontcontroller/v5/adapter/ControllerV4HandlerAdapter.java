package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// ControllerV4를 지원하는 어댑터
public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        // ControllerV4타입을 지원하는지 체크
        // MemberFormControllerV4
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // 핸들러 캐스팅
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);

        // 어댑터의 역할을 한다. - 110v 꽂을 걸 220v로 만들어준다.
        // 반환타입 ModelView를 어댑터에서 생성
        ModelView mv = new ModelView(viewName);
        // model 세팅 - ModelView에 모델 설정
        // controller에서 model에 필요한 데이터를 다 담아 놓는다. 그걸 쓰면 된다.
        mv.setModel(model);

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
