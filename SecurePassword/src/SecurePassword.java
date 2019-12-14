public class SecurePassword {
    public static void main(String[] args) throws Exception{
        try{
            Usuario Dani = new Usuario("Daniela","Xablau");
            Cofre CDani = new Cofre(Dani);
            String Texto = "Opa";
            byte[] Cifrado = Dani.Encrypt(Texto);
            String Decifrado = Dani.Decrypt(Cifrado);
            Sys.Login("Daniela","Xablau");
            Dani.ImprimirUK();
            System.out.println("Texto Original: " + Texto + "\n Texto Cifrado: " + Cifrado +
                    "\n Texto Decifrado: " + Decifrado);
            CDani.saveLoginSite("www.gmail.com.br","dani.msilveiradanone@gmail.com","digimon02");

        }

        catch (ExecaoContaExistente e){
            System.out.println("Conta Já Existe");
        }

        catch (ExecaoSiteRegistrado e){
            System.out.println("Esse site já foi registrado");
        }

    }
}
