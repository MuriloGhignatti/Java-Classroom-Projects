public class Mensagem {

    private String texto;
    private boolean enviada;

    public Mensagem(String texto, boolean enviada){
        this.texto = texto;
        this.enviada = enviada;
    }

    public String ImprimirMensagem(){
        if(enviada) return texto + " (Enviada)";
        return texto + " (Recebida)";
    }

    public String getMessage(){
        return this.texto;
    }

    public boolean getEnvianda(){
        return this.enviada;
    }

}
