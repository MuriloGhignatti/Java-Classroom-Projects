import java.io.*;

public class SecurePassword {
    public static void main(String[] args) throws Exception{
        Users users = new Users();
    User test = users.SearchUserByName("Dani");
    users.salvar();
    }
}
