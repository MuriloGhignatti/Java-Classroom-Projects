import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
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
}
