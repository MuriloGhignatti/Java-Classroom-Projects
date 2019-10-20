import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

public class Usuario {
    private String nome;
    private String keyVault;
    private String userKey;
    private String fileName = "Users.txt";

    public Usuario(String nome, String keyVault) throws Exception{
        this.nome = nome;
        this.keyVault = keyVault;
        this.userKey();
        saveLogin(nome,keyVault);
    }

    public void ImprimirUK(){
        System.out.println(this.userKey);
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

    private String hashPass(String pass) throws Exception {
        MessageDigest m = MessageDigest.getInstance("SHA-256");

        m.update(pass.getBytes(),0,pass.length());

        return new BigInteger(1,m.digest()).toString(16);
    }

    public byte[] Encrypt(String Mensagem) throws Exception{
        return AES.Encrypt(this.userKey,Mensagem);
    }

    public String Decrypt(byte[] Mensagem) throws Exception{
        return AES.Decrypt(this.userKey,Mensagem);
    }

    private void saveLogin(String Login, String Password) throws Exception{
        FileWriter fileWriter = new FileWriter(fileName,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try{
            bufferedWriter.write(Login + ":" + hashPass(Password) + "\n");
        }
        finally {
            bufferedWriter.close();
        }
    }
}
