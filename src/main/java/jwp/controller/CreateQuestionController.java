//package jwp.controller;
//
//import jwp.dao.QuestionDao;
//import jwp.model.Question;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Controller
//@RequestMapping("/qna")
//@RequiredArgsConstructor
//public class CreateQuestionController{
//
//    //questionDao가 여기서 객체 선언을 꼭 해야할까??
//    private final QuestionDao questionDao;
//
//
//    //질문을 받으려면 매개변수가 필요하죠
//    @PostMapping("/create")
//    public String createQuestion(@ModelAttribute Question q, Model model) throws Exception {
//        questionDao.insert(q);
//
////        Question question = new Question(
////                req.getParameter("writer"),
////                req.getParameter("title"),
////                req.getParameter("contents"),
////                0);
////        Question savedQuestion = questionDao.insert(question);
////        System.out.println("saved question id= " + savedQuestion.getQuestionId());
//        return "redirect:/";
//    }
//}
