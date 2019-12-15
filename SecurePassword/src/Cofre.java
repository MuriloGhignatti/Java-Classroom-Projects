import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/*
Trabalhar em como salvar a UserKey de forma segura
para que o usuario possa fazer login
 */

public class Cofre {
    private String fileName;
    private Usuario User;

    public Cofre(Usuario User) throws Exception{
        this.User = User;
        this.fileName = hashName(User.getNome()) + " Vault.txt";
    }

    public void saveLoginSite(String Site, String Login, String Password) throws Exception{
        if(fileName.equals(null)) throw new Exception();
        FileWriter fileWriter = new FileWriter(fileName,true);
        FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try{
            String st;
            while ((st = bufferedReader.readLine()) != null){
                if(st.substring(0,st.indexOf(":")).equals(Site)) throw new ExecaoSiteRegistrado();
            }
            bufferedWriter.write(Site + ":" + Login + ":" + User.Encrypt(Password) + "\n");
        }

        finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
    }

    private static String hashName(String name) throws Exception {
        MessageDigest m = MessageDigest.getInstance("SHA-256");

        m.update(name.getBytes(),0,name.length());

        return new BigInteger(1,m.digest()).toString(16);
    }

    public String ShowSite(String site) throws Exception{
        FileReader fileReader = new FileReader(this.fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try{
            String st;
            while ((st = bufferedReader.readLine()) != null){
                if(st.substring(0,st.indexOf(":")).equals(site)){
                    String webSite = st.substring(0,st.indexOf(":"));
                    String login = st.substring(st.indexOf(":") + 1,st.lastIndexOf(":"));
                    String passC = st.substring(st.lastIndexOf(":") + 1);
                    byte[] test = passC.getBytes(StandardCharsets.UTF_8);
                    String savedPass = Sys.getSavePass(User.getNome(),User.getKeyVault());
                    String pass = AES.Decrypt(savedPass,test);
                    return "Site: " + webSite + "\n Login: " + login + "\n Senha: " + pass;
                }
            }
        }

        finally {
            bufferedReader.close();
        }
        return "Something Went Wrong";
    } //Tem Algo Errado Aqui Por Causa do Algo Errado no Sys getSavePass
}
