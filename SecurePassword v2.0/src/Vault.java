import java.io.*;
import java.util.ArrayList;

public class Vault implements Serializable {

    private ArrayList<Login> Sites = new ArrayList<Login>();

    public void AddSite(String Site, String Login, byte[] Password){
        Sites.add(new Login(Site, Login, Password));
    }
}
