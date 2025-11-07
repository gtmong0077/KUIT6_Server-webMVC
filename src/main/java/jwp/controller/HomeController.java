package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.dao.UserDao;
import jwp.model.Question;
import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequiredArgsConstructor

public class HomeController{

    private final QuestionDao questionDao ;
    //private final UserDao userDao ;

    @GetMapping("/")
    public String execute(Model model){
        List<Question> questions = questionDao.findAll();
        model.addAttribute("questions", questions);
        return "home";
    }
}
