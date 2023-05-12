package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 회원 저장
public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 파라미터 받고
        // 값을 가져와서 읽어야 한다.
        // url의 쿼리 스트링이든 HTML-form(POST방식) 데이터든 username=kim&&age=20 이거를 읽어야 한다.
        String username = request.getParameter("username");
        // request.getParameter의 응답 결과는 항상 문자이다.  숫자타입으로 변환해주자.
        int age = Integer.parseInt(request.getParameter("age"));

        // 2. 실제 비즈니스 로직 호출하고
        // Member 객체 만들기
        Member member = new Member(username, age);
        // 저장하기
        memberRepository.save(member);

        // 3. Model에 데이터 담고
        // request 객체 내부에 Map같은 내부 저장소가 있다. 거기에 member가 저장이 된다.
        request.setAttribute("member", member);

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
