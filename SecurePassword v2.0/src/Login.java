import java.io.*;

public class Login implements Serializable {
    private String Site;
    private String Login;
    private byte[] Password;

    public Login(String Site, String Login, byte[] Password){
        this.Site = Site;
        this.Login = Login;
        this.Password = Password;
    }
}
