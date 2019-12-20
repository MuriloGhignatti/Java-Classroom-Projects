import java.io.*;
import java.util.ArrayList;

public class Users implements Serializable {
    private ArrayList<User> Users;

    public Users() throws Exception{
        try{
           Users = abrir();
        }
        catch (ClassNotFoundException e){
            Users = new ArrayList<User>();
            e.printStackTrace();
        }
        catch (FileNotFoundException e){
            Users = new ArrayList<User>();
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void AddUser(User User){
        Users.add(User);
    }

    public User SearchUserByName(String Name){
        for(User i: Users){
            if(i.getName().equals(Name)) return i;
        }
        return null;
    }

    public boolean IsUserRegistred(String Name){
        if(SearchUserByName(Name) != null) return true;
        return false;
    }

    static ArrayList<User> abrir() throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream("Users.obj");
             ObjectInputStream ois = new ObjectInputStream(fis))
        {
            return (ArrayList<User>) ois.readObject();
        }
    }

     void salvar() throws IOException {
        try (FileOutputStream fos = new FileOutputStream("Users.obj");
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(this.Users);
        }
    }

    //DEBUG ONLY
    public ArrayList<User> getUsers(){
        return Users;
    }
}
