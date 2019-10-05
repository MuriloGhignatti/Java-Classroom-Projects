import javax.naming.AuthenticationNotSupportedException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Inicio {
    private static ArrayList<Usuario> users = new ArrayList();
    private static Boolean init = false;
    public Inicio() {
    }

    public static void main(String[] args) {

        StartReading();

        //teste();

    }

    public static void teste(){
        Usuario Murilo = new Usuario("Murilo");
        Usuario Bernardo = new Usuario("Bernardo");

        users.add(Murilo);
        users.add(Bernardo);

        Murilo.IniciarConversa("Bernardo");
        Murilo.EnviarMensagem("Bernardo","Teste");

        Murilo.ImprimirMensagens("Bernardo");
        Bernardo.ImprimirMensagens("Murilo");
    }

    public static void StartReading() {
        Scanner in = new Scanner(System.in);
        if (init != true) {

            System.out.print("Nome: ");
            String name = in.nextLine();
            users.add(new Usuario(name));
            init = true;
        }
        System.out.println("[1] Criar um novo usuario");
        System.out.println("[2] Mostrar todos os usuarios criados");
        System.out.println("[3] Iniciar uma conversa entre dois usuarios");
        System.out.println("[4] Mostrar a lista de usuários com os quais um usuário mantém conversas");
        System.out.println("[5] Enviar uma mensagem de um usuário para outro");
        System.out.println("[6] Mostrar todas as mensagens de uma conversa de um usuário");
        System.out.println("[7] Encerrar o programa");
        System.out.println("Escolha uma Opção: ");
        int choice = in.nextInt();
        switch(choice) {
            case 1:
                System.out.print("Nome Do Novo Usuario: ");
                in.nextLine();
                String User = in.nextLine();
                users.add(new Usuario(User));
                StartReading();
            case 2:
                System.out.print("Os Usuarios Criados São: ");
                for(Usuario i: users) System.out.println(i.Name());
                StartReading();
            case 3:
                System.out.print("Quem Quer Iniciar Uma Conversa? ");
                in.nextLine();
                String InitialUser = in.nextLine();
                in.nextLine();
                System.out.print("Com Quem Deseja Iniciar Uma Conversa? ");
                String Conversa = in.nextLine();
                Usuario user = null;
                for(Usuario i: users){
                    if(i.Name().equals(InitialUser)) user = i;
                }
                user.IniciarConversa(Conversa);
                StartReading();
            case 4:
                System.out.print("Quem Quer Ver as Conversas? ");
                in.nextLine();
                String convUser =  in.nextLine();
                Usuario localUser = null;
                for(Usuario i: users){
                    if(i.Name().equals(convUser)) localUser = i;
                }
                for(Conversa i: localUser.Conversas()) System.out.println(i.Name());
                StartReading();
            case 5:
                System.out.print("Quem Quer Enviar Mensagem? ");
                in.nextLine();
                String remetente = in.nextLine();
                in.nextLine();
                System.out.print("Para quem você quer enviar uma mensagem? ");
                String msgTo = in.nextLine();
                in.nextLine();
                System.out.print("Mensagem: ");
                String msg = in.nextLine();
                Usuario remet = null;
                for(Usuario i: users){
                    if(i.Name().equals(remetente)) remet = i;
                }
                remet.EnviarMensagem(msgTo,msg);
                StartReading();
            case 6:
                System.out.print("Quem Gostaria de ver as mensagens? ");
                in.nextLine();
                String msgSee = in.nextLine();
                in.nextLine();
                System.out.print("Você gostaria de ver as mensagens de qual contato? ");
                String mensagens = in.nextLine();
                Usuario userMSG = null;
                for(Usuario i: users){
                    if(i.Name().equals(msgSee)) userMSG = i;
                }
                userMSG.ImprimirMensagens(mensagens);
                StartReading();
            case 7:
                System.exit(0);
        }

    }


    public void ImprimeUsuarios() {
        for(int i = 0; i < users.size(); ++i) {
            System.out.println("Contato: " + ((Usuario)users.get(i)).Name());
        }

    }

    public static ArrayList<Usuario> GetUsuarios(){
        return users;
    }
}