package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

// 회원 저장
public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        /**
         * HttpServletRequest에서 getParameter()해서 꺼내는 게 아니라 이런건 프론트 컨트롤러에서 처리를 하고
         * Map에다가 request 요청 파라미터 정보를 다 넣어서 넘겨 줄거다.
         * 여기서는 단순히 꺼내서 쓰면 된다.
         */
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age")); // 문자로 오니깐 숫자로 바꾸자

        // 예제라서 예외 처리 부분은 많이 안되어있다. 그거까지하면 코드가 복잡해진다.
        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        // 저장된 결과를 member에 넣어주었다. 뒤에 프론트 컨트롤러에서 처리를 하고 JSP쪽으로 forward 해줄거다.
        mv.getModel().put("member", member);
        return mv;
    }
}
