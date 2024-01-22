package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
        // 헤더와 바디 정보를 가지는 객체
        // 요청 파라미터를 조회하는 기능과 관계없음(get, form.html만 쿼리 파라미터와 관련)
        // view 조회 아예 안함(당연)
    }

//    @PostMapping("/request-body-string-v3")
//    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {
//        String messageBody = httpEntity.getBody();
//
//        log.info("messageBody={}", messageBody);
//
//        return new ResponseEntity<String>("ok", HttpStatus.CREATED);
//    }

    /**
     * 참고
     * HTTP 메시지 바디를 읽어서 객체(HttpEntity)로 반환해 줌
     * 이 때, HTTP 메시지 컨버터(HttpMessageConverter)라는 기능을 사용한다.
     */

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);

        return "ok";    // 응답 코드에 박아서 줌
        /**
         * @RequesBody
         * @ResponseBody
         *
         * 헤더 정보가 필요할 때는 HttpEntity 같이 사용하면 됨
         *
         * 수동으로 Stream으로 바꿨던 코드들을 스프링이 대신 해주는 것
         */

        /**
         * 정리
         * 1. 쿼리 파라미터 조회 : @RequestParam, @ModelAttribute
         * 2. Http 메시지 바디 조회 : @requestBody
         */
    }
}
