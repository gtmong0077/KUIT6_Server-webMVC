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
    public void insert(User user) throws SQLException {
        em.persist(user);
//        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
//        PreparedStatementSetter pstmtSetter = pstmt -> {
//            pstmt.setString(1, user.getUserId());
//            pstmt.setString(2, user.getPassword());
//            pstmt.setString(3, user.getName());
//            pstmt.setString(4, user.getEmail());
//        };
//        jdbcTemplate.update(sql, pstmtSetter);
    }

    public void update(User user) throws SQLException {
//        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
//        PreparedStatementSetter pstmtSetter = pstmt -> {
//            pstmt.setString(1, user.getPassword());
//            pstmt.setString(2, user.getName());
//            pstmt.setString(3, user.getEmail());
//            pstmt.setString(4, user.getUserId());
//        };
//        jdbcTemplate.update(sql, pstmtSetter);
        em.merge(user);
    }

    public void delete(User user) {
//        String sql = "DELETE FROM USERS WHERE userId = ?";
//        PreparedStatementSetter pstmtSetter = pstmt -> {
//            pstmt.setString(1, user.getUserId());
//        };
//        jdbcTemplate.update(sql, pstmtSetter);

    }

    public List<User> findAll() {
//        String sql = "SELECT * FROM USERS";
//        RowMapper rowMapper = rs -> new User(rs.getString("userId"),
//                rs.getString("password"),
//                rs.getString("name"),
//                rs.getString("email"));
//        return jdbcTemplate.query(sql, rowMapper);
        return em.createQuery("select u from User u",User.class).getResultList();
    }

    public User findByUserId(String userId){
        return em.find(User.class, userId);
    }
}
