package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

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
// /front-controller/v1/* : v1/ 하위에 어떤 url이 들어와도 일단 이 servlet이 무조건 호출이 된다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // 매핑 정보 만들기
    // Map<url을 넣고, ControllerV1을 넣을거다.>
    // '어떤 url이 호출이 되면 ControllerV1을 꺼내서 호출 해'를 구현할 거다.
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 생성자에다가 이 서블릿이 생성될 때 매핑 정보를 담아 둘거다.
    public FrontControllerServletV1() {
        // 서블릿이 처음 생성이 될 때 controllerMap에 값을 넣어둔다.
        // /front-controller/v1/members/new-form 이렇게 요청이 오면 MemberFormControllerV1가 실행이 된다.
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 실행이 잘 되는 지 확인, 참고로 아래와 같은 출력문 logger로 찍는 게 좋다.
        System.out.println("FrontControllerServletV1.service");

        // 서비스로 구현되는 코드를 한번 보자
        // 로직을 만들어보자
        // "/front-controller/v1/members/new-form" 해당 부분 받을 수 있다.
        String requestURI = request.getRequestURI();

        // controllerMap에서 꺼낸다.
        // "/front-controller/v1/members/new-form" URI가 요청이 오면
        // new MemberFormControllerV1() 객체 인스턴스가 반환이 된다.
        // 참조변수를 ControllerV1 인터페이스로 하게되면 다형성이 적용되어서 해당 코드를 일관성 있게 사용할 수 있다.
        ControllerV1 controller = controllerMap.get(requestURI);
        // 예외처리
        if (controller == null) {
            // 페이지가 없거나 못 찾을 때 404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            // 404인 경우 바로 return
            return;
        }

        controller.process(request, response);
    }
}
