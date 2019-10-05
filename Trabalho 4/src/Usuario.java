import java.util.ArrayList;

public class Usuario {

    private String nome;
    private ArrayList<Conversa> conversas;
    private ArrayList<Usuario> users_created = Inicio.GetUsuarios();
    private int checker = 0;

    public Usuario(String nome){
        this.nome = nome;
        conversas = new ArrayList<Conversa>();
    }

    public String Name(){
        return this.nome;
    }

    public ArrayList<Conversa> Conversas(){
        return this.conversas;
    }

    public void IniciarConversa(String contato){
        Conversa user1 = new Conversa(contato);
        conversas.add(user1);
        if(checker == 0){
            for(Usuario i: users_created){
                if(i.Name().equals(contato)){
                    checker ++;
                    i.checker ++;
                    i.IniciarConversa(this.nome);
                }
            }
        }
        checker = 0;
    }

    public void EnviarMensagem(String destinatario,String mensagem){
        for(Conversa i: conversas) {
            if(i.Name().equals(destinatario)){
                i.AddMensagem(mensagem,true);
            }
        }
        for(Usuario i: users_created){
            if(i.Name().equals(destinatario)){
                i.ReceberMensagem(this.nome,mensagem);
            }
        }
    }

    public void ReceberMensagem(String remetente, String mensagem){
        for(Conversa i: conversas) {
            if(remetente.equals(i.Name())) i.AddMensagem(mensagem,false);
        }
    }

    public void ImprimirMensagens(String contato){
        for(Conversa i: conversas){
            if(contato.equals(i.Name())) System.out.println(this.nome + " " + i.ImprimirMensagens());
        }
    }
}
