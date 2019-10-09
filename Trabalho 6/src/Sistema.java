import java.lang.annotation.Documented;

public class Sistema {

    public static void main(String[] args) {
        Disco c = new Disco('C');

        Pasta teste = c.Criar_Pasta("Teste");

        c.Listar_Pastas();

        teste.Inserir("Documento");

        Imagem i = new Imagem("teste.png");

        i.Abrir();

        teste.Listar();

        c.Remover_Pasta("Teste");
    }
}
