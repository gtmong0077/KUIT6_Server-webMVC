package jwp.dao;


import jwp.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class QuestionDao {
    private final EntityManager em;
    @Transactional
    public Question insert(Question question){
        em.persist(question);
        return question;
    }
    @Transactional
    public void update(Question question){
        em.merge(question);
    }
    @Transactional
    public void delete(int questionId){
        Question managedQuestion = em.find(Question.class, questionId);
        if (managedQuestion != null) {
            em.remove(managedQuestion);
        }
    }

    public List<Question> findAll() {
        return em.createQuery("select q from Question q", Question.class).getResultList();
    }

    public Question findByQuestionId(int questionId) {
        return em.find(Question.class, questionId);
    }
}
