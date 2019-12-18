import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SecurePassword {
    public static void main(String[] args) throws Exception{
        try{
            Usuario Dani = new Usuario("Daniela","Xablau");
            Usuario Murilo = new Usuario("Muriloghignatti","1234567890-874585472565854262547862");
            Cofre CDani = new Cofre(Dani);
            String Texto = "Opa";
            byte[] Cifrado = Dani.Encrypt(Texto);
            String Decifrado = Dani.Decrypt(Cifrado);
            if(Sys.Login("Daniela","Xablau")) System.out.println("Logado com Sucesso");
            if(Sys.Login("Muriloghignatti","1234567890-874585472565854262547862")) System.out.println("Logado com Sucesso");;
            Dani.ImprimirUK();
            System.out.println("Texto Original: " + Texto + "\n Texto Cifrado: " + Cifrado +
                    "\n Texto Decifrado: " + Decifrado);
            CDani.saveLoginSite("www.up.edu.br","dani.msilveiradanone@gmail.com","senha");
            String prt = CDani.ShowSite("www.up.edu.br");
            System.out.println(prt);

        }

        catch (ExecaoContaExistente e){
            System.out.println("Conta Já Existe");
        }

        catch (ExecaoSiteRegistrado e){
            System.out.println("Esse site já foi registrado");
        }
    }

}
