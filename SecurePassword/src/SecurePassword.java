public class SecurePassword {
    public static void main(String[] args) throws Exception{
        try{
            Usuario Dani = new Usuario("Daniela","Xablau");
            String Texto = "Opa";
            byte[] Cifrado = Dani.Encrypt(Texto);
            String Decifrado = Dani.Decrypt(Cifrado);
            Sys.Login("Daniela","Xablau");
            Dani.ImprimirUK();
            System.out.println("Texto Original: " + Texto + "\n Texto Cifrado: " + Cifrado + "\n Texto Decifrado: " + Decifrado);
        }

        catch (ExecaoContaExistente e){
            System.out.println("Conta Já Existe");
        }

    }
}
