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
                    i.IniciarConversa(this.nome);
                }
            }
        }

    }

    public void EnviarMensagem(String destinatario,String mensagem){
        int index = 0;
        int index2 = 0;
        for(Conversa i: conversas) {
            String name = i.Name();
            if(destinatario.equals(name)) index = i;
        }
        for(int j = 0; j < users_created.size();j++){
            Usuario destination = users_created.get(j);
            if(destination.Name().equals(destinatario)) index2 = j;
            }
        conversas.get(index).AddMensagem(mensagem,false);
        users_created.get(index2).ReceberMensagem(this.nome,mensagem);
    }

    public void ReceberMensagem(String remetente, String mensagem){
        for(Conversa i: conversas) {
            String name = i.Name();
            if(remetente.equals(name)) i.AddMensagem(mensagem,true);;
        }
        }

    public void ImprimirMensagens(String contato){
        int index = 0;
        for(int i = 0; i < conversas.size();i++) {
            String name = conversas.get(i).Name();
            if(contato.equals(name)) index = i;
        }
        System.out.println(contato + " " + conversas.get(index).ImprimirMensagens());
    }
}