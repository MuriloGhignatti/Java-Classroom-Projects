import java.util.ArrayList;
import java.util.Random;

public class Usuario {
    private String nome;
    private String Key;
    private String Clean_Key;
    private ArrayList<byte[]> S_Key_Cripto = new ArrayList<byte[]>();

    public Usuario(String nome){
        this.nome = nome;
        this.Key = Key_Gen();
        KDC.addUser(this);
    } // Construtor do Usuario, Atribui Nome, Gera uma CHAVE PRIVADA e adiciona o Usuario no KDC

    private String Key_Gen(){
        Random random = new Random();
        String CHARACTERS = "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        Key = sb.toString();
        return sb.toString();
    } // Gera a CHAVE PRIVADA

    public void addS_Key(byte[] Key){
        S_Key_Cripto.add(Key);
    }

    public void addS_Key_Clean(byte[] Key) throws Exception{
       Clean_Key = AES.Decrypt(this.Key,Key);
    }

    public void sendS_Key(Usuario Destinatario) throws Exception{
        Destinatario.addS_Key(this.S_Key_Cripto.get(1));
        Destinatario.addS_Key_Clean(Destinatario.S_Key_Cripto.get(0));
        this.addS_Key_Clean(this.S_Key_Cripto.get(0));
    }

    public void ImprimirKey(){
        System.out.println("Chave do(a) " + this.nome + ": " +this.Key);
    } // Imprime a CHAVE PRIVADA

    public void ImprimirSKey(){
        System.out.println("Chave Criptografada do " + this.getNome() + " " + this.S_Key_Cripto.get(0));
        System.out.println("Chave Descriptografada do " + this.getNome() + " " + this.Clean_Key);
    }

    public String getNome(){
        return this.nome;
    } // Retorna o Nome

    public String getKey(){
        return this.Key;
    } // Retorna a Key
}
