public class SecurePassword {
    public static void main(String[] args) throws Exception{
        Usuario Dani = new Usuario("Daniela","Xablau");
        String Texto = "Opa";
        byte[] Cifrado = Dani.Encrypt(Texto);
        String Decifrado = Dani.Decrypt(Cifrado);
        Sys.Login("Daniela","Xablau");
        Dani.ImprimirUK();
        System.out.println("Texto Original: " + Texto + "\n Texto Cifrado: " + Cifrado + "\n Texto Decifrado: " + Decifrado);
    }
}
