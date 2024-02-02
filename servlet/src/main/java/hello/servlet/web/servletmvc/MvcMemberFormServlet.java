package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // 외부에서 직접 호출하지 않고 컨트롤러를 통해서 호출하고 싶으면 WEB-INF라는 디렉토리 하위에 위치시키면 된다.
        // 직접 호출하려하면 오류남!

        // 다른 서블릿이나 JSP로 이동할 수 있는 기능
        // 서버 내부에서 다시 호출이 발생한다. (redirect 와는 다름 - client 에서 다시 호출) -> url이 바뀌지 않는다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
