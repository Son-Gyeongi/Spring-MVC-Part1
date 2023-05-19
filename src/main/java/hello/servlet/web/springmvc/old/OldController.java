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

        // 뷰 리졸버를 알아보자
        // 논리적 이름만 넣었다. 뷰 리졸버에서 물리적이름으로 바꿔야한다.
        // 뷰가 없어서 오류 페이지가 나온다. -> 뷰 리졸버를 만들어줘야 한다.
        /**
         * application.properties에
         * spring.mvc.view.prefix=/WEB-INF/views/
         * spring.mvc.view.suffix=.jsp 적어주니깐 페이지가 잘 나왔다.
         */
        return new ModelAndView(("new-form"));
    }
}
