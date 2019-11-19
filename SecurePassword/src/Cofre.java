import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Cofre {
    private static String fileName;

    public Cofre(String Nome){
        fileName = Nome + " Vault.txt";
    }

    public void saveLogin(String Login, String Password, String Site) throws Exception{
        FileWriter fileWriter = new FileWriter(fileName,true);
        FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try{
            String st;
            while ((st = bufferedReader.readLine()) != null){
                if(st.substring(0,st.indexOf(":")).equals(Site)) throw new ExecaoSiteRegistrado();
            }
        }

        finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
