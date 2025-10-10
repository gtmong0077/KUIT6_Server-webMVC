package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebServlet("/user/update")
public class UserUpdateController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        MemoryUserRepository userRepository = MemoryUserRepository.getInstance();
        User user = userRepository.findUserById(userId);
        if (user != null) {
            User updateUser = new User(userId, password, name, email);
            user.update(updateUser);
            userRepository.changeUserInfo(user);
        }

        return "redirect:/user/list";
    }
    /*
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");


        MemoryUserRepository userRepository = MemoryUserRepository.getInstance();
        User user = userRepository.findUserById(userId);
        if(user != null){
            User updateUser=new User(userId,password,name,email);
            user.update(updateUser);
            userRepository.changeUserInfo(user);

        }
        resp.sendRedirect("/user/list");
    }
     */
}
