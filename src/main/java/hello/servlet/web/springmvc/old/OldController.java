package hello.servlet.web.springmvc.old;

//import org.springframework.stereotype.Controller; // 이 Controller는 @Controller이다.
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @Controller 써주는 게 아니라 핸들러 매핑을 따로 처리해야 한다.
@Component("/springmvc/old-controller") // 스프링 빈 이름을 url패턴으로 맞춘거다.
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 호출이 되는지만 확인
        System.out.println("OldController.handleRequest");
        return null;
    }
}
