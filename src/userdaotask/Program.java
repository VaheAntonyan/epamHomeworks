package userdaotask;

public class Program {
    public static void main(String[] args) {
        UserDao userDao = UserDaoFactory.getUserDao(RDBMS.Microsoft_SQL_Server);
        assert userDao != null;
        System.out.println("userDao.getUserById(3) = " + userDao.getUserById(3));
        userDao.close();
    }
}
