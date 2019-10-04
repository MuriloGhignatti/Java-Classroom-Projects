import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

public class Usuario {
    private String nome;
    private String keyVault;
    public String userKey;

    public Usuario(String nome, String keyVault) {
        this.nome = nome;
        this.keyVault = keyVault;
        this.userKey();
    }

    private String userKey(){
        Random random = new Random();
        String CHARACTERS = "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        this.userKey = sb.toString();
        return sb.toString();
    } // Gera a CHAVE PRIVADA

    public String hashPass(String pass) throws Exception {
        MessageDigest m = MessageDigest.getInstance("SHA-256");

        m.update(pass.getBytes(),0,pass.length());

        return new BigInteger(1,m.digest()).toString(16);
    }
}
