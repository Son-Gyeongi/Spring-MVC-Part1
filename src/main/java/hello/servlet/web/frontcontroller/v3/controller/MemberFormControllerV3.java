package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

// 회원 등록 폼
public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // view의 전체path 이름을 넣는게 아니라 논리적 이름만 넣는다.
        return new ModelView("new-form");
    }
}
