import java.io.*;
import java.util.ArrayList;

public class Users implements Serializable {
    private static ArrayList<User> Usuarios = new ArrayList<User>();

    public static void AddUser(User Usuario){
        Usuarios.add(Usuario);
    }

    public static User SearchUserByName(String Nome){
        for(User i: Usuarios){
            if(i.getName().equals(Nome)) return i;
        }
        return null;
    }
}
