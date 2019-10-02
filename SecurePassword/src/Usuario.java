import java.util.Random;

public class Usuario {
    private String nome;
    private String keyVault;
    private String userKey;

    public Usuario(String nome,String keyVault){
        this.nome = nome;
        this.keyVault = keyVault;
    }

    public String userKey(){
        Random rnd = new Random();
    }
}
