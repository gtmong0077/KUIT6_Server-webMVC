package jwp.controller;

import jwp.dao.UserDao;
import jwp.model.User;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserDao userDao;
    /**
     * 1. 회원가입 폼을 보여주는 GET 핸들러
     */
    @GetMapping("/form")
    public String showSignupForm() {
        return "/user/form";
    }
    @PostMapping("/signup")
    //@ModelAttribute User user : User 자동으로 binding 해준다.
    public String createUser(@ModelAttribute User user) throws SQLException {
        userDao.insert(user);
        System.out.println("user 회원가입 완료");
        return "redirect:/user/list";
    }

    /**
     * 3. 로그인 폼을 보여주는 GET 핸들러
     */
    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        return "user/login"; // "login.jsp" (로그인 폼)
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId,@RequestParam String password, HttpSession session){
        User loginUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.isSameUser(loginUser)) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    //log-out

    @GetMapping("/logout")
    public String LogOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/list")
    public String listUsers(HttpSession session, Model model){
        if (UserSessionUtils.isLogined(session)) {
            model.addAttribute( "users", userDao.findAll());
            return "user/list";
        }
        return "redirect:/user/loginForm";
    }



    /**
     * 1. HttpServletRequest/Response 제거
     * 2. @ModelAttribute 를 사용해 폼 데이터를 User 객체로 자동 바인딩
     * 3. (중요) PRG 패턴 적용 (redirect:/user/list)
     * 4. 메서드 이름 변경 (Update -> update)
     */

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam String userId,Model model, HttpSession session){

        // 1. 로그인 여부 확인
        if (!UserSessionUtils.isLogined(session)) {
            return "redirect:/user/loginForm"; // 로그인 폼으로 리다이렉트
        }
        // 2. 세션의 사용자와 수정할 사용자가 동일한지 확인
        User sessionUser = UserSessionUtils.getUserFromSession(session);
        User user = userDao.findByUserId(userId);

        if (user != null && user.equals(sessionUser)) {
            model.addAttribute("user", user);
            return "user/updateForm";
        }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User modifiedUser) throws Exception {
        userDao.update(modifiedUser);
        return "redirect:/user/list";
    }
}
