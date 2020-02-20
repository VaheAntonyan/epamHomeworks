package userdaotask;

public interface UserDao {
    User getUserById(int id);
    int deleteUser(int id);
    int updateUser(User u);
    int insertUser(User u);

    void close();
}
