package jwp.controller;

import jwp.util.UserSessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/qna")
public class CreateQuestionFormController{

    @PostMapping("/form")
    public String execute(HttpSession session, Model model) throws Exception {
        //HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {          // 회원만 질문 등록 가능
            return "/qna/form";
        }
        return "redirect:/user/loginForm";
    }
}
