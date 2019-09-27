import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{

        NewUser User = new NewUser("Murilo","Teste");
        NewUser User2 = new NewUser("Felipe","Arroz");

        User.CreateNewUser();
        User2.CreateNewUser();

    }

    public static void Choices(){
        Scanner in = new Scanner(System.in);
        System.out.println("[0] Criar Novo Usuario");
        System.out.println("[1] Login");
        System.out.println("[2] Sair");
        int choice = in.nextInt();
        switch(choice){
            case 0:
                System.out.println("Login: ");
                String Login = in.nextLine();
                System.out.println("Pass: ");
                String Pass = in.nextLine();
                NewUser User = new NewUser(Login,Pass);
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                System.out.println("Opcao Invalida, Verifique as Opcoes Disponiveis");
        }
    }
}
