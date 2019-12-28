import java.util.Scanner;

public class Interface {

    public static boolean Registred_Logged = false;
    private static String User = "";

    public static void StartReading() throws Exception {
        String Pass;
        String Site;
        String Login;
        String PSite;
        Scanner in = new Scanner(System.in);
        System.out.println("[1] Login");
        System.out.println("[2] Registrar");
        if(Registred_Logged){
            System.out.println("[3] Adicionar Site");
            System.out.println("[4] Ver Site");
            System.out.println("[5] Remover Site");
            System.out.println("[6] Logout");
        }
        System.out.println("[7] Sair do Programa");
        System.out.println("\n Escolha uma Opção: ");
        int choice = in.nextInt();
        switch(choice) {
            case 1:
                System.out.println("Usuario: ");
                User = in.next();
                System.out.println("Senha: ");
                Pass = in.next();
                Registred_Logged = Sys.Login(User,Pass);
                Sys.Salvar();
                StartReading();
            case 2:
                System.out.print("Nome Do Novo Usuario: ");
                User = in.next();
                System.out.println("Senha Do Novo Usuario: ");
                Pass = in.next();
                new User(User, Pass);
                Registred_Logged = Sys.Login(User,Pass);
                Sys.Salvar();
                StartReading();
            case 3:
                System.out.println("Site: ");
                Site = in.next();
                System.out.println("Login: ");
                Login = in.next();
                System.out.println("Senha: ");
                PSite = in.next();
                Sys.SaveLogin(User,Site,Login,PSite);
                Sys.Salvar();
                StartReading();
            case 4:
                Sys.SearchUserByName(User).mostrarSites();
                System.out.println("Escolha um dos Sites registrados: ");
                Site = in.next();
                Sys.SeeLogin(User,Site);
                Sys.Salvar();
                StartReading();
            case 5:
                System.out.println("Site: ");
                Site = in.next();
                Sys.RemoveLogin(User,Site);
                Sys.Salvar();
                StartReading();
            case 6:
                Sys.Logout(User);
                Registred_Logged = false;
                Sys.Salvar();
                StartReading();
            case 7:
                Sys.Salvar();
                System.exit(0);
        }

    }
}
