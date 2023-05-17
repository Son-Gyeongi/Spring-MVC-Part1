package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 대망의 FrontController를 만들어 볼거다.
// 얘는 HttpServlet을 상속 받아야 한다.
// /front-controller/v4/* : v4/ 하위에 어떤 url이 들어와도 일단 이 servlet이 무조건 호출이 된다.
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    // 매핑 정보 만들기
    // Map<url을 넣고, ControllerV3을 넣을거다.>
    // '어떤 url이 호출이 되면 ControllerV4을 꺼내서 호출 해'를 구현할 거다.
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    // 생성자에다가 이 서블릿이 생성될 때 매핑 정보를 담아 둘거다.
    public FrontControllerServletV4() {
        // 서블릿이 처음 생성이 될 때 controllerMap에 값을 넣어둔다.
        // /front-controller/v1/members/new-form 이렇게 요청이 오면 MemberFormControllerV1가 실행이 된다.
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 서비스로 구현되는 코드를 한번 보자
        // 로직을 만들어보자
        // "/front-controller/v4/members/new-form" 해당 부분 받을 수 있다.
        String requestURI = request.getRequestURI();

        // controllerMap에서 꺼낸다.
        // "/front-controller/v4/members/new-form" URI가 요청이 오면
        // new MemberFormControllerV1() 객체 인스턴스가 반환이 된다.
        // 참조변수를 ControllerV1 인터페이스로 하게되면 다형성이 적용되어서 해당 코드를 일관성 있게 사용할 수 있다.
        ControllerV4 controller = controllerMap.get(requestURI);
        // 예외처리
        if (controller == null) {
            // 페이지가 없거나 못 찾을 때 404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            // 404인 경우 바로 return
            return;
        }

        // 파라미터 Map을 만든다.
        // paramMap을 넘겨줘야 한다. Map을 넘겨줘야 하니깐 request에서 getParameter()를 다 꺼내야 한다.
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>(); // 추가

        // process()에 결과가 반환이 된다.
        String viewName = controller.process(paramMap, model);

// view 논리 이름을 물리 이름으로 바꿔야 한다.
        // 이름만 넘어간다. 이거를 MyView로 반환해주어야 한다.
        // viewResolver가 있다. 실제 view를 찾아주는 해결자 역할
// 물리 이름으로 만들고 view객체까지 만들어주는 걸 viewResolver가 해줄거다.
        MyView view = viewResolver(viewName);
        // model을 render에 같이 넘겨줘야 한다.
        // view가 렌더링되려면 model이 필요하다.
        view.render(model, request, response);
    }

    // view를 해결해준다.
    // view 논리 이름을 물리 이름으로 바꿔준다.
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
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
