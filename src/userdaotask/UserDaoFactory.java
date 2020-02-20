package userdaotask;

public class UserDaoFactory {
    public static UserDao getUserDao(RDBMS rdbms) {
        if (rdbms.equals(RDBMS.Microsoft_SQL_Server)) {
            return new UserDaoProxy(new UserDaoMSSQLImpl());
        }
        return null;
    }
}
