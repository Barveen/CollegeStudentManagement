package DataClass;

public class UserDetails {
 String userName;
 String userId;
 String password;
 String mailId;

    public UserDetails(String userName,String userId, String password, String mailId) {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.mailId = mailId;
    }

    public UserDetails(String userId, String passwrd) {
        this.userId = userId;
        this.password = passwrd;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "DataClass.UserDetails{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", mailId='" + mailId + '\'' +
                '}';
    }
}
