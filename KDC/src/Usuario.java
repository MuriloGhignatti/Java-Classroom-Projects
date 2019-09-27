import java.util.Random;

public class Usuario {
    private String nome;
    private String Key;
    private String S_Key;

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

    public void ImprimirKey(){
        System.out.println("Chave do(a) " + this.nome + ": " +this.Key);
    } // Imprime a CHAVE PRIVADA

    public String getNome(){
        return this.nome;
    } // Retorna o Nome

    public String getKey(){
        return this.Key;
    } // Retorna a Key
}
