public class Teste {

    public static void main(String[] args) throws Exception {

        Usuario Bob = new Usuario("Bob"); // Cria um Usuario
        Usuario Alice = new Usuario("Alice"); // Cria um Usuario
        Usuario Felipe = new Usuario("Felipe"); // Cria um Usuario


        Bob.startConversation(Alice);

    }

}
