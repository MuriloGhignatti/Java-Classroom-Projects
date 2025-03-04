import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

/*
Verificar o uso dos hashPass para armazenar e encryptar as senhas!
 */

public class User implements Serializable {
    private String Name;
    private String HVault_Password;
    private transient String Vault_Password;
    private byte[] User_Key;
    private Vault Vault;
    private transient boolean Logged = false;

    public User(String Login, String Password) throws Exception {
        this.Name = Login;
        this.Vault_Password = Password;
        this.HVault_Password = hashPass(Password);
        this.User_Key = generateUserKey();
        this.Vault = new Vault();
        Sys.RegisterUser(this);
    }

    public String getName() {
        return Name;
    }

    public String getHVault_Password(){
        return this.HVault_Password;
    }

    public boolean isLogged(){
        return this.Logged;
     }

    public void setLogged(boolean value){
        this.Logged = value;
    }

    public void setVault_Password(String Pass){
        this.Vault_Password = Pass;
    }

    private byte[] generateUserKey() throws Exception {
        Random random = new Random();
        String CHARACTERS = "!/\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return AES.Encrypt(Salt(this.Vault_Password), sb.toString());
    } //Generates a Random String For User_Key

    private static String Salt(String Password) throws Exception {
        if (Password.length() < 16) Password += "0wefjkewmopfihbu".substring(0, 16 - Password.length());
        if (Password.length() > 16) Password = Password.substring(0, 16);
        return hashPass(Password).substring(0, 16);
    }

    private static String hashPass(String pass) throws Exception {
        MessageDigest m = MessageDigest.getInstance("SHA-256");

        m.update(pass.getBytes(), 0, pass.length());

        return new BigInteger(1, m.digest()).toString(16);
    }

    public void SaveLogin(String Site, String Login, String Password) throws Exception {
        if(Logged) this.Vault.AddSite(Site, Login, AES.Encrypt(Salt(this.Vault_Password),Password));
    }

    public void SeeSavedLogin(String Site) throws Exception { //Fazer metodo para setar a Vault-Password se o login for bem sucedido
        if(Logged){
           Login currentSite = Vault.SearchSite(Site);
           String WebSite = currentSite.getSite();
           String Login = currentSite.getLogin();
           byte[] cryptoPassword = currentSite.getPassword();
           String Password = AES.Decrypt(Salt(this.Vault_Password),cryptoPassword);
            System.out.println("Site: " + WebSite + "\n Login: " + Login + "\n Senha: " + Password);
        }
    }

    public void RemoveSite(String Site){
        Vault.RemoveSite(Site);
    }

    public void mostrarSites(){
        Vault.mostrarSites();
    }
}
