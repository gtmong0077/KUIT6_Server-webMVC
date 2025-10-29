package jwp.controller;

//import core.db.MemoryUserRepository;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

// @WebServlet("/user/update")
public class UserUpdateController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        //MemoryUserRepository userRepository = MemoryUserRepository.getInstance();
        //User user = userRepository.findUserById(userId);
        // UserDao를 새로 생성(한번만 생성하거나, 주입해서 사용하는 것을 추천)
        UserDao userDao = new UserDao();
        User user = null;  // DB에서 사용자 조회
        try {
            user = userDao.findByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user != null) {
            User updateUser = new User(userId, password, name, email);
            user.update(updateUser);
            try {
                userDao.findByUserId(userId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //userRepository.changeUserInfo(user);
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
