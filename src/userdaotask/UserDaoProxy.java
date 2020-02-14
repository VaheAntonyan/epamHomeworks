package userdaotask;

import java.util.HashMap;

public class UserDaoProxy implements UserDao {
    private UserDao userDao;
    private HashMap<Integer, User> map = new HashMap<>();

    public UserDaoProxy(UserDao ud) {
        userDao = ud;
    }

    @Override
    public User getUserById(int id) {
        if (!map.containsKey(id)) {
            User u = userDao.getUserById(id);
            map.put(id, u);
            return u;
        }
        return map.get(id);
    }
}
