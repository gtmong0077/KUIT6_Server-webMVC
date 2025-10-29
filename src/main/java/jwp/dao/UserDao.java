package jwp.dao;

import core.jdbc.*;
import jwp.model.User;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;


public class UserDao{
    //TODO insert, update, delete
    private final JdbcTemplate<User> jdbcTemplate=new JdbcTemplate();
    //private final InsertJdbcTemplate insertTemplate = new InsertJdbcTemplate();
    //private final UpdateJdbcTemplate updateTemplate = new UpdateJdbcTemplate();
    //SQL에 넣어줄 함수 작성
    public void insert(User user) throws SQLException {
        String sql="INSERT INTO USERS VALUES(?, ?, ?, ?)";
        PreparedStatementSetter pss=pstmt->{
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        };
        jdbcTemplate.update(sql, pss);
    }
    public void update(User user) throws SQLException {
        String sql="UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
        PreparedStatementSetter pstmtSetter=pstmt->{
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        };
        //updateTemplate.execute(sql,pstmtSetter);
        jdbcTemplate.update(sql, pstmtSetter);

    }
    public void delete(User user) throws SQLException {

            String sql="DELETE FROM USERS WHERE userId=?";
            PreparedStatementSetter pstmtSetter=pstmt-> {
                pstmt.setString(1, user.getUserId());
            };
            jdbcTemplate.update(sql,pstmtSetter);

    }

    //TODO findAll, findByUserId
    //여러 User 행을 조회해 각각 User 객체로 변환해서 List<User>로 반환합니다.
    //여기서 반환되는 "응답 객체"는 User 객체들의 리스트입니다.
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM USERS";
        RowMapper rowMapper = rs -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    //단일 행을 User 객체로 매핑해 반환합니다.
    //여기서 응답 객체는 단일 User 객체입니다.
    public User findByUserId(String userId) throws SQLException {

        String sql = "SELECT * FROM USERS WHERE userId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, userId);
        };
        RowMapper rowMapper = rs -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"));
        return jdbcTemplate.queryForObject(sql,pstmtSetter,rowMapper);

    }
}
