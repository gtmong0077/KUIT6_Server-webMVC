//package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//public abstract class SampleJdbcTemplate {
//    public void execute(String sql,PreparedStatementSetter ps) throws SQLException {
//  try (Connection conn = ConnectionManager.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            ps.setParameters(pstmt);
//            doExecute(pstmt); // 구체적 실행 방식은 서브클래스에서 구현
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    protected abstract void doExecute(PreparedStatement pstmt) throws SQLException;
//}
