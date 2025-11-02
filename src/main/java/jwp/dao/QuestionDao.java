package jwp.dao;
import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;


import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate=new JdbcTemplate<>();
    private static QuestionDao dao=new QuestionDao();
    private QuestionDao() {}

    // 2. 정적 팩토리 메서드 (싱글톤)
    public static QuestionDao getInstance() {
        return dao;
    }
    private RowMapper<Question> rowMapper = (ResultSet rs) -> new Question(
            rs.getLong("questionId"),
            rs.getString("writer"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getTimestamp("createdDate"),
            rs.getInt("countOfAnswer")
    );
    public List<Question> findAll() {
        String sql = "SELECT * FROM QUESTIONS ";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // 특정 사용자 질문 가져오기 (findByUserId)
    public Question findByQuestionId(long id) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ? ";
        PreparedStatementSetter pstmtSetter= pstmt->{
            pstmt.setLong(1,id);
        };
        RowMapper<Question> rowMapper = (rs) -> new Question(
                rs.getLong("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("countOfAnswer")
        );
        return jdbcTemplate.queryForObject(sql,pstmtSetter,rowMapper);
    }
    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, 0)";

        KeyHolder keyHolder = new KeyHolder();

        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setTimestamp(4, new Timestamp(currentTimeMillis()));
            pstmt.setInt(5, question.getCountOfAnswer());
        };

        jdbcTemplate.update(sql, pss, keyHolder);

        // 생성된 questionId를 이용해 DB에서 다시 조회
        return findByQuestionId(keyHolder.getId());
    }
}
