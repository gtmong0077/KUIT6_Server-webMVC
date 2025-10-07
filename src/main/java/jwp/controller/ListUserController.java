package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/user/list")
public class ListUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // 기존 세션만 반환
        // 세션에 저장된 정보 가져오기
        User user = null;
        if (session != null) {
            Object value = session.getAttribute("user");
            if (value != null) {
                user = (User) value;
            }
        }
        if (user == null) {
            // 로그인 안 된 경우 로그인 페이지로 리다이렉트
            resp.sendRedirect("/user/login.jsp");
            return;
        }
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);
        //createusercontroller에서는 sendredirect를 사용했는데 이거랑 연관성이없는건가?
        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }
}
