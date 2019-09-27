public class Teste {

    public static void main(String[] args) throws Exception{

        Usuario Bob = new Usuario("Bob"); // Cria um Usuario
        Usuario Alice = new Usuario("Alice"); // Cria um Usuario
        Usuario Felipe = new Usuario("Felipe"); // Cria um Usuario

        Bob.ImprimirKey(); //Imprime a KEY do Usuario
        Alice.ImprimirKey(); //Imprime a KEY do Usuario

        String chave = KDC.SKey_Gen("Bob","Alice"); // Gera a K_S entre dois usuarios
        String texto = "Zap No Chama"; // Texto aleatorio para cifrar

        byte[] Cifrado = AES.Encrypt(chave,texto); // Texto Cifrado

         String Decifrado = AES.Decrypt(chave,Cifrado); // Texto Decifrado

        System.out.println("Chave: " + chave + "\n" + "Texto Cifrado: " + Cifrado + "\n" + "Texto Decifrado: " + Decifrado); //Printa toda a baboseira anterior

    }

}
