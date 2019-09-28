import java.util.ArrayList;
import java.util.Random;

public class Usuario {
    private String nome;
    private String Key;
    private String Clean_Key;
    private int myNounce;
    private int myNew;
    private ArrayList<byte[]> S_Key_Cripto = new ArrayList<byte[]>();

    public Usuario(String nome){
        this.nome = nome;
        this.Key = Key_Gen();
        KDC.addUser(this);
    } // Construtor do Usuario, Atribui Nome, Gera uma CHAVE PRIVADA e adiciona o Usuario no KDC

    private String Key_Gen(){
        Random random = new Random();
        String CHARACTERS = "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        Key = sb.toString();
        return sb.toString();
    } // Gera a CHAVE PRIVADA

    public void addS_Key(byte[] Key){
        S_Key_Cripto.add(Key);
    }

    private void addS_Key_Clean(byte[] Key) throws Exception{
       Clean_Key = AES.Decrypt(this.Key,Key);
    }

    private void sendS_Key(Usuario Destinatario) throws Exception{
        Destinatario.addS_Key(this.S_Key_Cripto.get(1));
        Destinatario.addS_Key_Clean(Destinatario.S_Key_Cripto.get(0));
        this.addS_Key_Clean(this.S_Key_Cripto.get(0));
    }

    private void ImprimirKey(){
        System.out.println("Chave do(a) " + this.nome + ": " +this.Key);
    } // Imprime a CHAVE PRIVADA

    private void ImprimirSKey(){
        System.out.println("Chave Criptografada do " + this.getNome() + " " + this.S_Key_Cripto.get(0));
        System.out.println("Chave Descriptografada do " + this.getNome() + " " + this.Clean_Key);
    }

    public String getNome(){
        return this.nome;
    } // Retorna o Nome

    public String getKey(){
        return this.Key;
    } // Retorna a Key

    private void Nounce(){
        Random random = new Random();
        this.myNounce = random.nextInt();
    }

    private void Func(int Checker){
        switch (Checker){
            case 0:
                this.myNew = 450 + this.myNounce;
                break;
            case 1:
                this.myNew = this.myNounce/5;
            case 2:
                this.myNew = this.myNounce * 3;
            case 3:
                this.myNew = this.myNounce - 457;
        }
    }

    private boolean checkNew(Usuario Destinatario){
        if(Destinatario.myNew == this.myNew) return true;
        return false;
    }

    private void sendNounce(Usuario Destinatario){
        Destinatario.myNounce = this.myNounce;

    }

    public void startConversation(Usuario Destinatario) throws Exception{

        int Checker = new Random().nextInt(3);

        boolean userChekcer = requestKDCuserChecker(Destinatario);

        if(!userChekcer) System.out.println("Usuario ou Destinatario Invalido");

        this.sendS_Key(Destinatario);

        Destinatario.Nounce();

        Destinatario.Func(Checker);

        Destinatario.sendNounce(this);

        this.Func(Checker);

        boolean Nounce = Destinatario.checkNew(this);

        if (Nounce) {
            System.out.println("Mensagem Enviada com sucesso");
        }
        this.ImprimirTudo(Checker);
        Destinatario.ImprimirTudo(Checker);
    }

    private boolean requestKDCuserChecker(Usuario Destinatario) throws Exception{
        return KDC.userChecker(this.getNome(),AES.Encrypt(this.getKey(),this.getNome()),AES.Encrypt(this.getKey(),Destinatario.getNome()));
    }

    private void ImprimirTudo(int Checker) {
        System.out.println(Checker);
        System.out.println(this.getNome() + " chave de sessão: " + this.Clean_Key);
        System.out.println(this.getNome() + " nounce: " + this.myNounce);
        System.out.println(this.getNome() + " new: " + this.myNew);

    }
    }