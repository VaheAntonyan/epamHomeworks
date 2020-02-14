package userdaotask;

public class Program {
    public static void main(String[] args) {
        UserDao userDao = UserDaoFactory.getUserDao("MSSQL");
        System.out.println("userDao.getUserById(1) = " + userDao.getUserById(1));
    }
}
