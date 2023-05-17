package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// "/front-controller/v5/*" v5로 오는 모든 요청
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // 기존꺼와의 차이
    // 핸들러가 매핑된 맵 데이터
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    // 기존에 없었던 거
    // 어댑터가 여러개 담겨 있고 그 중에 하나를 찾아서 꺼내 쓴다.
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        // 핸들러 매핑 초기화(등록)
        initHandlerMappingMap();
        // 어댑터 초기화(등록)
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        // 기존처럼 매핑정보를 먼저 넣자.
        // 서블릿이 처음 생성이 될 때 controllerMap에 값을 넣어둔다.
        // /front-controller/v3/members/new-form 이렇게 요청이 오면 MemberFormControllerV3가 실행이 된다.
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 매핑 정보를 가지고 handlerMappingMap에서 찾는 거
        // 요청정보를 가지고 핸들러를 찾아오세요.
        // MemberFormControllerV3
        Object handler = getHandler(request);

        // 예외처리
        if (handler == null) {
            // 페이지가 없거나 못 찾을 때 404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            // 404인 경우 바로 return
            return;
        }

        // 핸들러 어댑터 목록에서 핸들러 찾기
        // 단순하게 handlerAdapters를 반복해서 찾으면 된다.
        // 핸들러 어댑터 찾아와
        // ControllerV3HandlerAdapter
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 핸들러 호출
        ModelView mv = adapter.handle(request, response, handler);

        String viewName = mv.getViewName();// 여기서는 view의 논리 이름만 얻는다. 예)new-form
// view 논리 이름을 물리 이름으로 바꿔야 한다.
        // 이름만 넘어간다. 이거를 MyView로 반환해주어야 한다.
        // viewResolver가 있다. 실제 view를 찾아주는 해결자 역할
// 물리 이름으로 만들고 view객체까지 만들어주는 걸 viewResolver가 해줄거다.
        MyView view = viewResolver(viewName);

        // model을 render에 같이 넘겨줘야 한다.
        // view가 렌더링되려면 model이 필요하다.
        view.render(mv.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        // "/front-controller/v3/members/new-form" 해당 부분 받을 수 있다.
        String requestURI = request.getRequestURI();
        // handlerMappingMap에서 꺼낸다.
        // "/front-controller/v3/members/new-form" URI가 요청이 오면
        // handlerMappingMap에 URI보내서 handler를 찾는다.
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        // MemberFormControllerV3
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        // 예외 반환
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler"+handler);
    }

    // view를 해결해준다.
    // view 논리 이름을 물리 이름으로 바꿔준다.
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
