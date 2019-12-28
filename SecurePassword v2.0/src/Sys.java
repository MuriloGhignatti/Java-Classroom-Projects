import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Sys{
    public static Users users;

    static {
        try {
            users = new Users();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String hashPass(String pass) throws Exception {
        MessageDigest m = MessageDigest.getInstance("SHA-256");

        m.update(pass.getBytes(), 0, pass.length());

        return new BigInteger(1, m.digest()).toString(16);
    }

    public static boolean RegisterUser(User user){
        if(!users.IsUserRegistred(user.getName())){
            users.AddUser(user);
            return true;
        }
        return false;
    }

    public static User SearchUserByName(String Name){
       return users.SearchUserByName(Name);
    }

    public static boolean IsUserRegistred(String Name){
        return users.IsUserRegistred(Name);
    }

    public static boolean Login(String Login, String Password) throws Exception {
        User currentUser = SearchUserByName(Login);
        if(currentUser == null) return false;
        if(currentUser.getHVault_Password().equals(hashPass(Password))){
            currentUser.setVault_Password(Password);
            currentUser.setLogged(true);
            return true;
        }
        return false;
    }

    public static boolean Logout(String Login) throws Exception {
        User currentUser = SearchUserByName(Login);
        if(currentUser == null) return false;
        if(currentUser.isLogged()) {
            currentUser.setLogged(false);
            return true;
        }
        return false;
    }

    public static void Salvar() throws IOException {
        users.salvar();
    }

    public static void SeeLogin(String Usuario, String Site) throws Exception {
        User currentUser = SearchUserByName(Usuario);
        currentUser.SeeSavedLogin(Site);
    }

    public static void SaveLogin(String Usuario,String Site, String Login, String Password) throws Exception {
        User currentUser = SearchUserByName(Usuario);
        currentUser.SaveLogin(Site,Login,Password);
    }

    public static void RemoveLogin(String Usuario, String Site){
        User currentUser = SearchUserByName(Usuario);
        if(currentUser.isLogged()) currentUser.RemoveSite(Site);
    }

    public static void mostrarSites(User user){
        if(user.isLogged()) user.mostrarSites();
    }
}
