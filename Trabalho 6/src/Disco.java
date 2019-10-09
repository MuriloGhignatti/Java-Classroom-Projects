import java.util.ArrayList;

public class Disco {
    private char nome;
    private ArrayList<Pasta> pastas;

    public Disco(char nome){
        this.nome  = nome;
        this.pastas = new ArrayList<Pasta>();
    }

    public Pasta Criar_Pasta(String nome){
        pastas.add(new Pasta(nome));
        return new Pasta(nome);
    }

    public void Remover_Pasta(String nome){
        for(Pasta i: pastas){
            if(i.getNome().equals(nome)) pastas.remove(i);
        }
    }

    public void Listar_Pastas(){
        for(Pasta i: pastas){
            System.out.println(i.getNome());
        }
    }
}
