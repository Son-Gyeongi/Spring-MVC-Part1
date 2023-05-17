package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

// 회원 저장
public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 파라미터에서 값 꺼낸다.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age")); // 문자로 오니깐 숫자로 바꾸자

        // 비즈니스 로직 실행
        Member member = new Member(username, age);
        memberRepository.save(member);

        // model에 값을 put으로 넣어준다.
        // 모델이 파라미터로 전달되기 때문에, 모델을 직접 생성하지 않아도 된다.
        model.put("member", member);
        // returen을 문자로 하면 끝난다.
        return "save-result";
    }
}
