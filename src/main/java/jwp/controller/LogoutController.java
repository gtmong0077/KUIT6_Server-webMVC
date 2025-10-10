package jwp.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// @WebServlet("/user/logout")
public class LogoutController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); // 기존 세션이 있으면 가져오기
        if (session != null) {
            session.invalidate(); // 세션 무효화로 로그아웃 처리
        }
        // 로그아웃 후 메인페이지로 리다이렉트
        return "redirect:/";
    }
    /*
    @Override
    //doGet? doPost? doDelete?? 느낌상 doDelete같음
    /*protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        resp.sendRedirect("/");
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        resp.sendRedirect("/");
    }
    */
}
