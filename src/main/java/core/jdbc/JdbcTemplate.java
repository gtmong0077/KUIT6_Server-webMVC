package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {
    public void update(String sql,PreparedStatementSetter pstmtSetter, KeyHolder holder){
        try(Connection conn= ConnectionManager.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);){

            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<T> query(String sql,RowMapper<T> rowMapper){
        List<T> objects = new ArrayList<>();
        try(Connection conn= ConnectionManager.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();){

            while(rs.next()){
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return objects;
    }
    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper){
        ResultSet rs = null;
        T object = null;
        try(Connection conn= ConnectionManager.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);){
            // PreparedStatementSetter로 파라미터 설정 부분 추가 필요
            if (pstmtSetter != null) {
                pstmtSetter.setParameters(pstmt);
            }
            rs=pstmt.executeQuery();
            if(rs.next()){
                object = rowMapper.mapRow(rs);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return object;
    }
}
