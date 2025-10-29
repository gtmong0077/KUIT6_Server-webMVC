
package core.db;

import jwp.dao.UserDao;             // UserDao import
import jwp.model.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class MemoryUserRepository {
    private final UserDao userDao = new UserDao();

    // DB에 유저 추가
    public void addUser(User user) throws SQLException {
        userDao.insert(user);
    }

    // DB에서 유저 ID로 조회
    public User findUserById(String userId) throws SQLException {
        return userDao.findByUserId(userId);
    }

    // DB에서 전체 유저 조회
    public Collection<User> findAll() throws SQLException {
        // UserDao는 List<User> 반환 → 바로 Collection으로 사용할 수 있음
        return userDao.findAll();
    }

    // DB에서 유저 정보 수정
    public void changeUserInfo(User user) throws SQLException {
        userDao.update(user);
    }
}

//package core.db;
//
//import jwp.model.User;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MemoryUserRepository {
//    private Map<String, User> users = new HashMap<>();
//    private static MemoryUserRepository memoryUserRepository;
//
//    private MemoryUserRepository() {
//    }
//
//    public static MemoryUserRepository getInstance() {
//        if (memoryUserRepository == null) {
//            memoryUserRepository = new MemoryUserRepository();
//            return memoryUserRepository;
//        }
//        return memoryUserRepository;
//    }
//
//    public void addUser(User user) {
//        users.put(user.getUserId(), user);
//    }
//
//    public User findUserById(String userId) {
//        return users.get(userId);
//    }
//
//    public Collection<User> findAll() {
//        return users.values();
//    }
//
//    public void changeUserInfo(User user) {
//        if (users.get(user.getUserId()) != null) {
//            users.put(user.getUserId(), user);
//        }
//    }
//}
