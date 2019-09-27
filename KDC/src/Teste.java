public class Teste {

    public static void main(String[] args) throws Exception {

        Usuario Bob = new Usuario("Bob"); // Cria um Usuario
        Usuario Alice = new Usuario("Alice"); // Cria um Usuario
        Usuario Felipe = new Usuario("Felipe"); // Cria um Usuario

        KDC.SKey_Gen("Bob","Alice");

        KDC.SKey_Send("Bob","Alice");

        KDC.ImprimirSKey();

        Bob.sendS_Key(Alice);

        Bob.ImprimirKey();
        Alice.ImprimirKey();

        Bob.ImprimirSKey();
        Alice.ImprimirSKey();
    }

}
