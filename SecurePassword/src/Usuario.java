import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

public class Usuario extends Sys{
    private String nome;
    private String keyVault;
    private String userKey;

    public Usuario(String nome, String keyVault) throws Exception{
        this.nome = nome;
        this.keyVault = keyVault;
        this.userKey();
        //super.saveLogin(this.nome,this.keyVault,this.userKey);
    }

    public String getKeyVault(){
        return this.keyVault;
    }

    public String getNome(){
        return this.nome;
    }

    public void ImprimirUK(){
        System.out.println(this.userKey);
    }

    private String userKey(){
        Random random = new Random();
        String CHARACTERS = "!/\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        this.userKey = sb.toString();
        return sb.toString();
    } // Gera a CHAVE PRIVADA

    public byte[] Encrypt(String Mensagem) throws Exception{
        return AES.Encrypt(this.userKey,Mensagem);
    }

    public String Decrypt(byte[] Mensagem) throws Exception{
        return AES.Decrypt(this.userKey,Mensagem);
    }
}
