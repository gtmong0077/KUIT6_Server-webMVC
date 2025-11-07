package jwp.controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.UserSessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionDao questionDao;
    /**
     * @ModelAttribute: 폼에서 전송된 데이터를 Question 객체(q)에 자동으로 바인딩합니다.
     */
    @PostMapping("/create")
    public String createQuestion(@ModelAttribute Question q){
        questionDao.insert(q);
        return "redirect:/";
    }
    /**
     * 1. @PostMapping -> @GetMapping 변경 (데이터를 '가져오는' 페이지)
     * 2. 불필요한 Model 파라미터 제거
     */
    @GetMapping("/form")
    public String showCreateForm(HttpSession session){
        if (UserSessionUtils.isLogined(session)) {// 회원만 질문 등록 가능
            return "/qna/form";
        }
        return "redirect:/user/loginForm";
    }
    /**
     * 1. @PostMapping -> @GetMapping 변경 (데이터를 '조회하는' 페이지)
     * 2. HttpServletRequest/Response 제거
     * 3. @RequestParam을 사용해 questionId를 받고, Spring이 자동으로 int로 변환합니다.
     * 4. Model 객체를 사용해 View로 데이터를 전달합니다.
     * 5. .jsp 확장자 제거
     */
    @GetMapping("/show")
    public String showQuestion(@RequestParam int questionId,Model model){
        Question question = questionDao.findByQuestionId(questionId);
        model.addAttribute("question", question);
        return "/qna/show";
    }
    /**
     * 1. @RequestParam을 사용해 폼 데이터를 명시적으로 받습니다.
     */
    @PostMapping("/update")
    public String LoginForm(@RequestParam int questionId,
                            @RequestParam String title,
                            @RequestParam String contents,
                            HttpSession session){
        // 1. 세션 및 사용자 유효성 검사
        if (!UserSessionUtils.isLogined(session)) {
            return "redirect:/users/loginForm";
        }
        User user = UserSessionUtils.getUserFromSession(session);

        // 2. 질문 소유권 검사
        Question question = questionDao.findByQuestionId(questionId);
        if (!question.isSameUser(user)) {
            throw new IllegalArgumentException("로그인된 유저와 질문 작성자가 다르면 질문을 수정할 수 없습니다.");
        }

        // 3. 업데이트 로직
        question.updateTitleAndContents(title, contents);
        questionDao.update(question);
        return "redirect:/";
    }
/**
 * 1. @PostMapping -> @GetMapping 변경 (데이터를 '조회하는' 폼 페이지
 * */
    @GetMapping("/updateForm")
    public String UpdateForm(@RequestParam int questionId,Model model, HttpSession session){
        if (!UserSessionUtils.isLogined(session)) {          // 회원만 질문 등록 가능
            return "redirect:/user/loginForm";
        }
        Question question = questionDao.findByQuestionId(questionId);
        User user = UserSessionUtils.getUserFromSession(session);
        if (!question.isSameUser(user)) {
            throw new IllegalArgumentException();
        }
        model.addAttribute("question", question);
        return "/qna/updateForm";
    }
}
