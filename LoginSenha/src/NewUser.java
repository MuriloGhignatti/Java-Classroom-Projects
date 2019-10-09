import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.math.BigInteger;

public class NewUser {
    private String login;
    private String name;
    private String pass;
    private String fileName = "Users.txt";

    public NewUser(String login, String pass) {
        this.name = login;
        this.login = login.substring(0,4);
        this.pass = pass.substring(0,4) + "!)&%";
    }

    public boolean CreateNewUser() throws Exception{
        boolean checker = false;
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);

            FileWriter fileWriter = new FileWriter(fileName,true);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String st;
            while ((st = bufferedReader.readLine()) != null){
                if(st.substring(0,4).equals(this.login)) checker = true;
        }

            if(checker){
                System.out.println("O(a) Usuario(a) " + this.name + " Já Existe");
                bufferedWriter.close();
                bufferedReader.close();
                return false;
            }
            bufferedWriter.write(this.login + ":" + hashPass(this.pass)+"\n");
            bufferedWriter.close();
            bufferedReader.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean AuthUser(String login, String pass) throws Exception{
        String localLogin = login.substring(0,4);
        String localPass = hashPass(pass.substring(0,4) + "!)&%");
        String Login = "";
        String Pass = "";

        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String st;
        while ((st = bufferedReader.readLine()) != null){
            if(st.substring(0,4).equals(localLogin)) Login = st.substring(0,4);
            if(st.substring(5).equals(localPass)) Pass = st.substring(5);
        }
        if(!(localLogin.equals(Login) && localPass.equals(Pass))){
            System.out.println(this.name + ": Login ou Senha Incoreto");
            bufferedReader.close();
            return false;
        }

            System.out.println("Usuario: " + this.name + " Autenticado com sucesso");

            bufferedReader.close();
            return true;
    }

    public String hashPass(String pass) throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");

        m.update(pass.getBytes(),0,pass.length());

        return new BigInteger(1,m.digest()).toString(16);
    }

    public String getName(){
        return this.name;
    }
}