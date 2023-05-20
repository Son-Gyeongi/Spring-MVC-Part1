package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        // 뷰 리졸버에서 JSP를 처리하기 위한 뷰가 찾아져서 걔가 render된다.
        return new ModelAndView("new-form");
    }
}
