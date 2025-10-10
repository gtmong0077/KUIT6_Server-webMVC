package jwp.controller;

import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// @WebServlet("/user/updateForm")
public class UserUpdateFormController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        HttpSession session = req.getSession(false); // 기존 세션만 조회
        User user = null;

        if (session != null) {
            Object value = session.getAttribute("user");
            if (value != null) {
                user = (User) value;
            }
        }

        if (user == null) {
            // 로그인 안 된 경우 로그인 페이지로 리다이렉트
            return "redirect:/user/login.jsp";
        }

        if (user.getUserId().equals(userId)) {
            // 요청 사용자와 로그인 사용자가 동일하면 updateForm.jsp 포워드
            return "/user/updateForm.jsp";
        }

        // 그렇지 않으면 메인페이지 리다이렉트
        return "redirect:/";
    }
    /*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId=req.getParameter("userId");
        HttpSession session = req.getSession();
        User user = null;
        if (session != null) {
            Object value = session.getAttribute("user");
            if (value != null) {
                user = (User) value;
            }
        }
        if (user == null) {
            resp.sendRedirect("/user/login.jsp");
            return;
        }

        if(user.getUserId().equals(userId)) {
            req.getRequestDispatcher("/user/updateForm.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/");
    }

     */
}
