package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController     // 매우 중요함!
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);
        // 이거는 걍 다 출력됨
        // 로그가 지저분해짐 -> log.xxx() 를 사용하면 설정이 가능함.
        // 다 콘솔에 남음

//        log.trace("trace log = " + name); // 연산이 일어남 -> 메모리 소요
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name); // 정보
        log.warn("warn log = {}", name);    // 경고 로그
        log.error("error log = {}", name);
        // 설정 하면 파일로 남길 수 있고, 네트워크로 남길 수도 있다.

        // 보통 컨트롤러(@Controller)에 반환되는 string은 뷰 이름이다.
        // @RestController이면 문자가 그대로 반환됨(메시지 바디에 문자를 그냥 담아서 보내버림) -> restapi에 주요하게 사용됨
        return "ok";
    }
}
