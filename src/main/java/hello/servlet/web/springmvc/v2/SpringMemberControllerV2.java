package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // 회원 등록 폼
    @RequestMapping("/new-form") // 매핑 URL이 겹치면 오류난다.
    public ModelAndView newForm() {
        // 뷰 리졸버에서 JSP를 처리하기 위한 뷰가 찾아져서 걔가 render된다.
        return new ModelAndView("new-form");
    }

    // 회원 저장
    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age")); // 문자로 오니깐 숫자로 바꾸자

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
//        mv.getModel().put("member", member);
        // -> 모델에서 찾아서 넣는 거 보다 더 편한 메서드
        mv.addObject("member", member);
        return mv;
    }

    // 회원 목록
//    @RequestMapping("/members") // /springmvc/v2/members/members 이렇게 되서 그냥 지우자
    @RequestMapping // /springmvc/v2/members
    public ModelAndView members() {
        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
//        mv.getModel().put("members", members);
        mv.addObject("members", members);
        return mv;
    }

}
