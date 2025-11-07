package jwp.dao;


import jwp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDao{

    private final EntityManager em;
    //위에 requiredArgsConstructor 어노테이션으로 인해 자동으로 생성자가 생겼다고 생각해도됌
    @Transactional
    public void insert(User user){
        em.persist(user);
    }
    @Transactional
    public void update(User user) throws SQLException {
        em.merge(user);
    }
//    @Transactional
//    public void delete(User user) {
//        // JPA의 삭제 로직으로 변경
//        // em.remove()는 영속성 컨텍스트에 관리되는 객체로만 가능합니다.
//        // merge 후 remove 하거나, find로 조회 후 remove 합니다.
//        User managedUser = em.find(User.class, user.getUserId());
//        if (managedUser != null) {
//            em.remove(managedUser);
//        }
//    }

    public List<User> findAll() {
        return em.createQuery("select u from User u",User.class).getResultList();
    }

    public User findByUserId(String userId){
        return em.find(User.class, userId);
    }
}
