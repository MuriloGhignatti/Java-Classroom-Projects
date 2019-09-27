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
    private String pass;
    private String fileName = "Users.txt";

    public NewUser(String login, String pass) {
        this.login = login.substring(0,4);
        this.pass = pass.substring(0,4);
    }

    public void CreateNewUser() throws Exception{
        System.out.println(this.login + ":" + this.pass);
        String line = null;
        try {
            FileWriter fileWriter = new FileWriter(fileName,true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(this.login + ":" + hashPass()+"\n");

            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String hashPass() throws Exception {
        MessageDigest m = MessageDigest.getInstance("MD5");

        m.update(pass.getBytes(),0,pass.length());

        return new BigInteger(1,m.digest()).toString(16);
    }
}