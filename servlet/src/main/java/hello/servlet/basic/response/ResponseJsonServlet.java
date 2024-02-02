package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        // 나중에 spring mvc 사용하면 여기 코드가 다 필요 없어짐

        HelloData helloData = new HelloData();
        helloData.setUsername("oh");
        helloData.setAge(20);

        //{"username":"oh", "age":20} 으로 바꾸기
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
