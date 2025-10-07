package jwp.controller;

import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/updateForm")
public class UserUpdateFormController extends HttpServlet {
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
}
