public class Mensagem{

    private String texto;
    private boolean enviada;

    public Mensagem(String texto, boolean enviada){
        this.texto = texto;
        this.enviada = enviada;
    }

    public String ImprimirMensagem(){
        if(enviada) return  Colors.ANSI_YELLOW + texto + Colors.ANSI_PURPLE +" (Enviada)" + Colors.ANSI_RESET;
        return Colors.ANSI_YELLOW + texto + Colors.ANSI_GREEN +" (Recebida)" + Colors.ANSI_RESET;
    }

    public String getMessage(){
        return this.texto;
    }

    public boolean getEnvianda(){
        return this.enviada;
    }

}
