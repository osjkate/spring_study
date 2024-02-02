package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);
        return controller.process(paramMap);
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
/**
 * 애플리케이션의 구조를 바꿀때는
 * 큰 구조를 바꾸는 것과 세세한 구조를 바꾸는 것을 병행하지 마라
 *
 * view로 처리하는 dispatch forward가 반복되는 것을 해결
 *
 * 서블릿 삭제
 * 뷰 리졸버 이름 중복 제거
 *
 * 단순하고 실용적인 컨트롤러로 바꿈
 *
 * 어댑터 도입 -> 유연한 컨트롤러가 가능했음!
 *
 * @RequestMapping("/hello")
 * RequestMappingHandlerAdapter
 *
 * if else -> 다형성 : 좀 더 유연해짐
 */