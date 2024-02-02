package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/hello-basic", "/hello"}, method = RequestMethod.GET)
    // 배열이 들어가도 됨 (or처리, 둘 중에 하나면 실행됨)
    // "/hello-basic" != "/hello-basic/" 이지만 스프링은 같은 url로 매핑한다.
    // method 지정안하면 모든 매서드에서 동작함
    // mehod 지정 후 다른 method로 호출하면 405 (Method Not Allowed) 반환
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")    // url 경로를 템플릿화
    public String mappingPath(@PathVariable String userId) {    // 변수이름 같으면 ("userId") 생략해도 괜찮
        log.info("mappingPath user-Id = {}", userId);
        return "ok";
    }

    // PathValue를 여러개 넣을 수도 있다.
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId) {
        log.info("mapping userId = {}, orderId = {}", userId, orderId);
        return "ok";
    }

    // 잘 사용하지는 않음
    // 특정 파라미터를 필수로 가져야 실행됨
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    // 포스트맨으로 헤더 넣어줘야 실행
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    // Content-type 과 accept 헤더의 차이점
    // Content-Type : 현재 전송하는 데이터가 어떤 타입인지 설명
    // Accept : 클라이언트가 서버에게 요청할때 데이터 타입을 설정해서 요청하면, 서버 클라이언트가 보낸 특정 데이터 타입으로만 응답해야 함.
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    // Accept 헤더 필요함
    // 브라우저에서 웹서버로 요청 시 요청 메시지에 담기는 헤더
    // 자신에게 이러한 데이터 타입만 허용하겠다는 뜻
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
