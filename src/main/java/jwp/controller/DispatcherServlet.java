package jwp.controller;

import jwp.controller.Controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")  // 모든 요청 처리
public class DispatcherServlet extends HttpServlet {
    private Map<String, Controller> controllerMap = new HashMap<>();
    private RequestMapper requestMapper;


    @Override
    public void init() {
        requestMapper = new RequestMapper();
        controllerMap.put("/user/signup", new CreateUserController());
        controllerMap.put("/user/list", new ListUserController());
        // 필요한 컨트롤러들 등록
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        System.out.println("Requested path: " + path);

        Controller controller = requestMapper.getController(path);

        if(controller == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String viewName = controller.handleRequest(req, resp);
        System.out.println("View to forward: " + viewName);
        if(viewName.startsWith("redirect:")) {
            resp.sendRedirect(viewName.substring("redirect:".length()));
        } else {
            req.getRequestDispatcher(viewName).forward(req, resp);
        }
    }
}
