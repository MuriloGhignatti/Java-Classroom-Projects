import javax.swing.text.html.parser.Parser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class Sys {

    private static String fileName = "Users.txt";

    private static void sLogin(String Login, String Password,String key) throws Exception, ExecaoContaExistente{
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
            bufferedWriter.write(Login + ":" + hashPass(Salt(Password)) + ":" + Base64.getEncoder().encodeToString(AES.Encrypt(Salt(Password),key)) + "\n"); //Senha salva em hash SHA-256; "\n" p/ linha seguinte
        }

        catch (ExecaoLoginErrado e){
            System.out.println("Login ou Senha Não Encontrados");
        }

        finally {
            bufferedWriter.close();
            bufferedReader.close();
        }
    }

    public static void saveLogin(String Login, String Password,String key) throws Exception, ExecaoContaExistente{
        Sys.sLogin(Login,Password,key);
    }

    private static String hashPass(String pass) throws Exception {
        MessageDigest m = MessageDigest.getInstance("SHA-256");

        m.update(pass.getBytes(),0,pass.length());

        return new BigInteger(1,m.digest()).toString(16);
    }

    public static void hPass(String pass) throws Exception{
        Sys.hashPass(pass);
    }

    private static boolean LG(String Login, String Password) throws Exception, ExecaoLoginInvalido{
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try{
            String st;
            while ((st = bufferedReader.readLine()) != null){
                String p = st.substring(st.indexOf(":") + 1,st.lastIndexOf(":"));
                String hP = hashPass(Salt(Password));
                if(st.substring(0,st.indexOf(":")).equals(Login)){
                    if(p.equals(hP)) return true;
                    if(!(p.equals(hP))) throw new ExecaoLoginInvalido();
                }
            }
            return false;
        }

        finally {
            bufferedReader.close();
        }

    }

    public static boolean Login(String Login, String Password) throws Exception{
        return LG(Login,Password);
    }

    private static String Salt(String Password){
        if(Password.length() < 16)  Password += "0wefjkewmopfihbu".substring(0,16-Password.length());
        if(Password.length() > 16) Password = Password.substring(0,16);
        return Password;
    }

    public static String getSavePass(String Login,String Password) throws Exception{
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String st;
        while ((st = bufferedReader.readLine()) != null){
            if(st.substring(0,st.indexOf(":")).equals(Login)){
                String PassSalt = Salt(Password);
                String PassWordSaved = st.substring(st.lastIndexOf(":")+1);
                //byte[] bytes = StringToByte(PassWordSaved);
                byte[] bytes = Base64.getDecoder().decode(PassWordSaved);
                String result = AES.Decrypt(PassSalt,bytes);
                return result;
            }
        }
        return "Algo Deu Errado";
    } //Tem Algo Errado Aqui

    private static byte[] StringToByte(String parameter){
        int counter = 0;
        if(!(parameter.charAt(0) == '[' && parameter.charAt(parameter.length()-1) == ']')) return null; //Garante que o parametro é uma lista

        byte[] result = new byte[parameter.replaceAll("[0123456789]","").replaceAll("-","").length()/2];

        String number = "";
        for(int i = 0; i < parameter.length() ; i++){
            if(parameter.charAt(i) != ']' && parameter.charAt(i) != '[' && parameter.charAt(i) != ',' && parameter.charAt(i) != ' '){
                number += parameter.charAt(i);
                if(parameter.charAt(i+1) == ',' || parameter.charAt(i+1) == ']'){//verifica se é o ultimo digito do numero
                   result[counter] = (byte)Integer.parseInt(number);
                   counter ++;
                   number = "";
                }
            }
        }

       return result;
    }
}
