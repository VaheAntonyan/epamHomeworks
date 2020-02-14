package userdaotask;

public class UserDaoFactory {
    public static UserDao getUserDao(String type) {
        if (type.equals("MSSQL")) {
            return new UserDaoProxy(new UserDaoMSSQLImpl());
        }
        return null;
    }
}
