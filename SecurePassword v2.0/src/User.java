import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

public class User {
    private String Name;
    private String Vault_Password;
    private byte[] User_Key;
    private Vault Vault;

    public User(String Login, String Password) throws Exception{
        this.Name = Login;
        this.Vault_Password =  Password;
        this.User_Key = generateUserKey();
        this.Vault = new Vault(this);
    }

    public String getName(){
        return Name;
    }

    private byte[] generateUserKey()throws Exception{
            Random random = new Random();
            String CHARACTERS = "!/\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
            StringBuilder sb = new StringBuilder(16);
            for (int i = 0; i < 16; i++) {
                sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            return AES.Encrypt(Salt(this.Vault_Password),sb.toString());
        } // Gera a CHAVE PRIVADA

    private static String Salt(String Password)throws Exception{
        if(Password.length() < 16)  Password += "0wefjkewmopfihbu".substring(0,16-Password.length());
        if(Password.length() > 16) Password = Password.substring(0,16);
        return hashPass(Password).substring(0,16);
    }

    private static String hashPass(String pass) throws Exception {
        MessageDigest m = MessageDigest.getInstance("SHA-256");

        m.update(pass.getBytes(),0,pass.length());

        return new BigInteger(1,m.digest()).toString(16);
    }
}
