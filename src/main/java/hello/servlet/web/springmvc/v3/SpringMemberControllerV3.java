package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // 회원 등록 폼
    // GET인 경우에만 호출
//    @RequestMapping(value = "/new-form", method = RequestMethod.GET) // 매핑 URL이 겹치면 오류난다.
    @GetMapping("/new-form")
    public String newForm() {
        return "new-form"; // viewName만 반환
    }

    // 회원 저장
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age, // 타입 캐스팅, 타입 변환까지 스프링에서 자동으로 처리해준다.
            Model model) {

        // 비즈니스 로직
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 파라미터로 넘어온 model에 member를 넣어주면 된다.
        model.addAttribute("member", member);
        return "save-result"; // viewName만 반환
    }

    // 회원 목록
//    @RequestMapping(method = RequestMethod.GET) // /springmvc/v3/members
    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members"; // viewName만 반환
    }
}
