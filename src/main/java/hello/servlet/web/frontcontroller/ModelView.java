package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

// ModelView 이름 지은 이유 - 스프링에는 ModelAndView가 있다. 거의 비슷한 역할울 한다.
public class ModelView {

    // view의 논리적인 이름을 가져간다.
    private String viewName;

    // Model 객체를 직접 만든다.
    private Map<String, Object> model = new HashMap<>();
    // model에 put해서 원하는 데이터를 넣어두면 나중에 걔를 꺼내서 JPS에 쓸 수 있도록 후처리를 해줄거다.

    // view의 이름만 생성자로 만든다.
    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    // getter, setter 만든다.
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
