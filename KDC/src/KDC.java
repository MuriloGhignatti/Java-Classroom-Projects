import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class KDC {
    private static String S_Key;
    private static ArrayList<Usuario> Keys = new ArrayList<Usuario>(); // Array dos Usuarios CRIADOS

    public static void addUser(Usuario user){
        Keys.add(user);
    } // Adiciona um Usuario na Array de Usuarios

    public static void userChecker(String Remetente, byte[] CRemetente, byte[] CDestinatario) throws Exception{
        for(Usuario i:Keys){
            if(i.getNome().equals(Remetente)){
                if(i.getNome().equals(AES.Decrypt(i.getKey(),CRemetente))){
                    for(Usuario j: Keys){
                        if(j.getNome().equals(AES.Decrypt(i.getKey(),CDestinatario))){
                            KDC.SKey_Gen(i.getNome(),j.getNome());

                            KDC.SKey_Send(i.getNome(),j.getNome());

                            System.out.println("Usuario Verificado Com Sucesso!");
                        }
                    }
                }
            }
        }
    }

    private static String SKey_Gen(String Remetente, String Destinatario) {
        Random random = new Random();
        //String CHARACTERS = "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        String CHARACTERS = "";
        for (Usuario i: Keys){
           if(i.getNome() == Remetente || i.getNome() == Destinatario){
               CHARACTERS += i.getKey();
           }
        }
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        S_Key = sb.toString();
        return sb.toString();
    } // Gera a K_S

    private static void SKey_Send(String Destinatario,String Contato) throws Exception{
        for(Usuario i: Keys){
            if(i.getNome() == Destinatario){
                i.addS_Key(AES.Encrypt(i.getKey(),S_Key));
                for(Usuario j: Keys){
                    if(j.getNome() == Contato) i.addS_Key(AES.Encrypt(j.getKey(),S_Key));
                }
            }
        }

    }

    private static void ImprimirSKey(){
        System.out.println("Session Key: " + S_Key);
    }
}
