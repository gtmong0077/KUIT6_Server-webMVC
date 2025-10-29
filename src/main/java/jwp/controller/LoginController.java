package jwp.controller;

//import core.db.MemoryUserRepository;
import jwp.dao.UserDao;
import jwp.model.User;
import org.springframework.cglib.proxy.Dispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

// @WebServlet("/user/login")
public class LoginController implements Controller {
    //doGet이어야하는거 아닌가?? 로그인정보를 받아와야하니까?
    //로그인 요청은 ID와 비밀번호를 전달받아 인증 처리를 하므로 doPost()에서 개발하는 것이 표준적이다.
    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        //MemoryUserRepository userRepository = MemoryUserRepository.getInstance();
        //User user = userRepository.findUserById(userId);
        UserDao userDao = new UserDao();
        User user = null;
        try {
            user = userDao.findByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user != null && password.equals(user.getPassword())) {
            session.setAttribute("user", user);
            // 로그인 성공 후 메인 페이지로 리다이렉트
            return "redirect:/";
        } else {
            // 로그인 실패 시 loginFailed.jsp로 리다이렉트
            return "redirect:/user/loginFailed.jsp";
        }
    }
}
    /*
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        // 세션 정보 저장 아이디랑 비밀번호
        HttpSession session = req.getSession();
        //User user = MemoryUserRepository.findUserById(userId);

        //getInstance()로 싱글톤을 얻는다.
        MemoryUserRepository userRepository =MemoryUserRepository.getInstance();
        User user = userRepository.findUserById(userId);

        if(user!=null && password.equals(user.getPassword())) {
            //로그인 성공
            session.setAttribute("user", user);
            //session.setAttribute("userId", userId);
            //session.setAttribute("password", password);
            resp.sendRedirect("/");
        }else{
            //로그인 실패
            resp.sendRedirect("/user/loginFailed.jsp");
        }

        //로그인 성공시 메인페이지로 실패시 loginFailed.jsp 페이지로 리다이렉트
        /*if (session.getAttribute("user") !=  null) {

            session.setAttribute("userId", userId);
            session.setAttribute("password", password);
            resp.sendRedirect("/");
        }else{
            resp.sendRedirect("/user/list.jsp");
        }*/

        //세션 데이터 삭제
        //HttpSession session = req.getSession();
        //session.removeAttribute("user");

/*
로그인 세션 처리가 잘 동작하지 않는 이유는, 인증(로그인) 성공 여부를 실제로 검사하지 않고, 세션에 "user"라는 속성이 이미 있는지만 확인하고 있기 때문입니다.
또한, 로그인 성공 시에는 반드시 사용자 정보를 세션의 "user" 속성에 저장해야 하며, 실패 시에는 loginFailed.jsp로 리다이렉트해야 합니다.
 */