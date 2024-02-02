package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

// GET 방식으로 쿼리 파라미터 전달
// HTML Form 방식으로 쿼리 파라미터 전달

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
        // @Controller인 상태에서 String을 반환하면 viewResolver가 그 이름을 가진 handler를 찾는다.
        // 그냥 String을 반환하기
        // 1. @Controller를 @RestController로 바꾸기
        // 2. 메서드 위에 @ResponseBody 어노테이션 붙이기
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            // 변수명이 파라미터 이름과 같으면 ("") 생략 가능
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        // 심지어 이름이 같고 String, int, Integer 등 단순 타입이면 아예 어노테이션 자체를 생략할 수 있다.
        // 사용자가 만든 클래스 타입이 아닌 단순 타입이면
        // 생략해도 되지만 강사님은 그냥 쓰는게 명확하고 직관적이라는 설명
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    // 필수 파라미터 여부를 설정할 수 있다.
    // 디폴트 값은 true : 없으면 오류난다!
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age
            // 주의
            // int에는 null이 들어갈 수 없어서 오류 발생
            // Integer(객체형)로 바꿔주면 오류 안남
    ) {
//        int a = null;
        Integer b = null;
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }
    // 주의
    // /request-param-required?username=
    // 와 같이 값이 주어지지 않으면 빈 문자가 대입됨

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age
            // 만약에 파라미터가 안들어오면 defaultValue가 됨
            // 참고 : 빈 문자의 경우에도 default값이 들어감!
    ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));

        // 주의 변수로 꺼내려면 타입 캐스팅 해줘야 함
        String username = (String) paramMap.get("username");
        return "ok";

        // 참고 MultiValueMap으로도 받을 수 있다.
        // ?userIds=id1&userIds=id2 -> paramMap.get("userIds") == [id1, id2]
        // 근데 보통 파라미터 값은 1개를 사용한다.
    }

//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(
//            @RequestParam String username,
//            @RequestParam int age
//    ) {
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
//
//        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
//        log.info("helloData = {}", helloData);
//
//        return "ok";
//    }


    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {   // 타입에 맞추어서 넣어주어야 함!
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);

        return "ok";
    }
    /**
     * @ModelAttribute 진행과정
     * 1. HelloData 의 객체를 생성한다.
     * 2. HelloData 객체 프로퍼티를 찾는다. (getter setter)
     * 3. setter를 호출해서 값을 바인딩해준다.
     */

    /**
     * 프로퍼티
     * getXxx -> xxx
     * setXxx -> xxx
     */

    // ModelAttribute 생략 가능
    // 단순 타입시 RequestParam이 생략되었다고 보고
    // 사용자 생성 객체 타입 시 ModelAttribute가 생략되었다고 본다.
    // 단, argumentResolver는 제외 (ex HttpServletResponse 등) -> 뒤에서 배울 것
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);

        return "ok";
    }
}


