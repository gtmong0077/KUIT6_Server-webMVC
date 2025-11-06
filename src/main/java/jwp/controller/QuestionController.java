package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionDao questionDao;
    @PostMapping("/create")
    public String createQuestion(@ModelAttribute Question q, Model model) throws Exception {
        questionDao.insert(q);

//        Question question = new Question(
//                req.getParameter("writer"),
//                req.getParameter("title"),
//                req.getParameter("contents"),
//                0);
//        Question savedQuestion = questionDao.insert(question);
//        System.out.println("saved question id= " + savedQuestion.getQuestionId());
        return "redirect:/";
    }
}
