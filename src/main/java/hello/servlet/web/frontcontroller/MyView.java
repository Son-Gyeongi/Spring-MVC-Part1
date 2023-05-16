package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

// 뷰 객체는 이후 다른 버전에서도 함께 사용하므로 패키지 위치를 frontcontroller 에 두었다.
// 기존에 각 컨트롤러에서 했던 로직을 MyView 라는 곳에 로직을 만들어서 넣는다.
public class MyView {
    private String viewPath; // "/WEB-INF/views/new-form.jsp"

    // 생성자
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // 기존에 JSP로 이동하고 그런 걸 렌더링 한다고 표현할 거다.
    // 뷰가 실제로 렌더링 되도록 동작하는 거
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // forward()하면 실제로 렌더링 끝난다.
        // new-form.jsp로 dispatcher.forward 된다.
        // 그래서 JSP에서 실제 HTML결과가 응답으로 클라이언트 웹 브라우저에 내려간다.
        dispatcher.forward(request, response);
        /**
         * 이렇게 JSP를 렌더링하거나 렌더링하도록 forward해서 이동을 하든 뭐든
         * 어쨋든 view를 만든 행위 자체를 렌더링한다.
         * render() 안에서 forward 할 수 있고 직접 그림을 그릴 수도 있고 여러가지를 할 건데
         * 우선 JSP니깐 dispatcher.forward()하면 JSP가 자동으러 렌더링 된다.
         * 그래서 메서드 이름을 render()라고 했다.
         */
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // model에 있는 데이터를 RequestAttribute로 바꾼다.
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    // model에 있는 데이터를 RequestAttribute로 바꾼다.
    // model에 있는 값을 다 꺼내서 HttpServletRequest의 setAttribute에 다 넣는다.
    // 여기 넣어야 JSP 표현식대로 편하게 꺼내서 쓸 수 있다.
    // jsp는 request.setAttribute() 이게 필요하다. 여기서 값을 다 꺼내기 때문에
    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
