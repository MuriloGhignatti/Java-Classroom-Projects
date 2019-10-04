import java.util.ArrayList;

public class Conversa {

    private String nome;
    private Usuario user;
    private ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();

    public Conversa(String nome){
        this.nome = nome;
    }

    public void AddMensagem(String mensagem, boolean enviada){
        mensagens.add(new Mensagem(mensagem,enviada));
    }

    public ArrayList<String> ImprimirMensagens(){
        ArrayList<String> msgs = new ArrayList<String>();
        for(int i = 0; i < mensagens.size();i++) msgs.add(mensagens.get(i).ImprimirMensagem());
        return msgs;
    }

    public String getMessage(){
        String msg = "Default Text";
        for(int i = 0; i < mensagens.size();i++){
            msg = mensagens.get(i).getMessage();
        }
        return msg;
    }

    public boolean getEnviada(){
        boolean enviada = false;
        for(int i = 0; i < mensagens.size();i++){
            enviada = mensagens.get(i).getEnvianda();
        }
        return enviada;
    }

    public String Name(){
        return nome;
    }
}
