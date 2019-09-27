import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class KDC {
    private static String S_Key;
    public static ArrayList<Usuario> Keys = new ArrayList<Usuario>(); // Array dos Usuarios CRIADOS

    public static void addUser(Usuario user){
        Keys.add(user);
    } // Adiciona um Usuario na Array de Usuarios

    public static String SKey_Gen(String Remetente, String Destinatario) {
        Random random = new Random();
        //String CHARACTERS = "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        String CHARACTERS = "";
        for (int i = 0; i < Keys.size();i++){
           if(Keys.get(i).getNome() == Remetente || Keys.get(i).getNome() == Destinatario){
               CHARACTERS += Keys.get(i).getKey();
               System.out.println(Keys.get(i).getNome() + ": " + Keys.get(i).getKey());
           }
        }
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        S_Key = sb.toString();
        return sb.toString();
    } // Gera a K_S
}
