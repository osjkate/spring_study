package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// 1. bean 으로 자동 등록
// 2. controller mapping 정보가 등록된다.
//@Component  // 빈 등록
//@RequestMapping // 핸들러와 어댑터가 인식함 (@Controller, @RequestMapping 둘 중에 하나가 있으면 isHandler에 의해 핸들러로 인식된다. )
public class SpringMemberFormControllerV1 {

    // 요청 정보를 매핑한다. (해당 url이 호출되면 메서드 호출됨)
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
