import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Sys {

    private static String fileName = "Users.txt";

    private static void sLogin(String Login, String Password) throws Exception, ExecaoContaExistente{
        FileWriter fileWriter = new FileWriter(fileName,true); //Append para adicionar as senhas
        FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try{
            String st;
            while ((st = bufferedReader.readLine()) != null){
                if(st.substring(0,st.indexOf(":")).equals(Login) && !(st.substring(0,st.indexOf(":")).equals(null)))
                    throw new ExecaoContaExistente(); //Verifica se o Login não existe e se ele não é nulo, caso o usuario tente colocar ':' como login
            }
            bufferedWriter.write(Login + ":" + hashPass(Password) + "\n"); //Senha salva em hash SHA-256; "\n" p/ linha seguinte
        }

        catch (ExecaoLoginErrado e){
            System.out.println("Login ou Senha Não Encontrados");
        }

        finally {
            bufferedWriter.close();
            bufferedReader.close();
        }
    }

    public static void saveLogin(String Login, String Password) throws Exception, ExecaoContaExistente{
        Sys.sLogin(Login,Password);
    }

    private static String hashPass(String pass) throws Exception {
        MessageDigest m = MessageDigest.getInstance("SHA-256");

        m.update(pass.getBytes(),0,pass.length());

        return new BigInteger(1,m.digest()).toString(16);
    }

    public static void hPass(String pass) throws Exception{
        Sys.hashPass(pass);
    }

    private static void LG(String Login, String Password) throws Exception, ExecaoLoginInvalido{
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try{
            String st;
            while ((st = bufferedReader.readLine()) != null){
                String p = st.substring(st.indexOf(":") + 1);
                String hP = hashPass(Password);
                if(!(st.substring(0,st.indexOf(":")).equals(Login) && p.equals(hP))) throw new ExecaoLoginInvalido();
            }
            System.out.println("Login Efetuado Com Sucesso");
        }

        finally {
            bufferedReader.close();
        }

    }

    public static void Login(String Login, String Password) throws Exception{
        LG(Login,Password);
    }

}
