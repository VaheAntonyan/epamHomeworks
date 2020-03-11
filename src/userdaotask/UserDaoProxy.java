package userdaotask;

import java.util.HashMap;

public class UserDaoProxy implements UserDao {
    private UserDao userDao;
    private HashMap<Integer, User> map = new HashMap<>();

    public UserDaoProxy(UserDao ud) {
        userDao = ud;
    }

    private User updateMap(int id) {
        User u = userDao.getUserById(id);
        map.put(id, u);
        return u;
    }

    @Override
    public User getUserById(int id) {
        if (!map.containsKey(id)) {
            return updateMap(id);
        }
        System.out.println("WAS cached");
        return map.get(id);
    }

    @Override
    public int deleteUser(int id) {
        map.remove(id);
        return userDao.deleteUser(id);
    }

    @Override
    public int updateUser(User u) {
        int rowsAffected = userDao.updateUser(u);
        int userID = u.getId();
        if (map.containsKey(userID)) {
            updateMap(userID);
        }
        return rowsAffected;
    }

    @Override
    public int insertUser(User u) {
        return userDao.insertUser(u);
    }

    @Override
    public void close() {
        userDao.close();
        map = null;
    }
}
