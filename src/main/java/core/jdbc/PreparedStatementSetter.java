package core.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementSetter {
    void setParameters(PreparedStatement ps) throws SQLException;
    //함수형 인터페이스
    //UserDao에서는 함수형 인터페이스에 넣는 과정을 구현체로 만든다.
    //jdbc에서 구현된 실제함수를 받아서 실행시킨다.
}
