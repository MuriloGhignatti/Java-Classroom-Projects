import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Boolean init = false;
    private static NewUser User;
    private static NewUser User2;
    private static ArrayList<NewUser> Users = new ArrayList<NewUser>();
    public static void main(String[] args) throws Exception{

        /*
        NewUser User = new NewUser("Murilo","Teste");
        NewUser User2 = new NewUser("Felipe","Arroz");
        User.CreateNewUser();
        User2.CreateNewUser();
        User.AuthUser("Murilo","Teste");
        User2.AuthUser("Felipe","Arroz");
         */
        Choices();

    }

    public static void Choices() throws Exception{
        Scanner in = new Scanner(System.in);

        if(init != true){
            System.out.print("Login: ");
            String login = in.nextLine();
            System.out.print("Password: ");
            String pass = in.nextLine();
            User = new NewUser(login,pass);
            User.CreateNewUser();
            Users.add(User);
            init = true;
        }
        System.out.println("[0] Criar Novo Usuario");
        System.out.println("[1] Login");
        System.out.println("[2] Sair");
        System.out.println("Escolha Um Numero: ");
        int choice = in.nextInt();
        switch(choice){
            case 0:
                in.nextLine();
                System.out.print("Login: ");
                String LoginC = in.nextLine();
                System.out.print("Password: ");
                String PassC = in.nextLine();
                User2 = new NewUser(LoginC,PassC);
                User2.CreateNewUser();
                Users.add(User2);
                Choices();
                break;
            case 1:
                in.nextLine();
                System.out.print("Login: ");
                String LoginA = in.nextLine();
                System.out.print("Password: ");
                String PassA = in.nextLine();
                for(NewUser i: Users){
                    if(i.getName().equals(LoginA)){
                        i.AuthUser(LoginA,PassA);
                    }
                }
                break;
            case 2:
                System.exit(0);
                break;
            default:
                System.out.println("Opcao Invalida, Verifique as Opcoes Disponiveis");
        }
    }
}
