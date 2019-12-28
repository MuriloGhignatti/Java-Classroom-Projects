import java.io.*;
import java.util.ArrayList;

public class Vault implements Serializable {

    private ArrayList<Login> Sites = new ArrayList<Login>();

    public void AddSite(String Site, String Login, byte[] Password){
        Sites.add(new Login(Site, Login, Password));
    }

    public void RemoveSite(String Site){
        for(Login i: Sites){
            if(i.getSite().equals(Site)) Sites.remove(Sites.indexOf(i));
        }
    }

    public Login SearchSite(String Site){
        for(Login i: Sites){
           if(i.getSite().equals(Site) || i.getSite().contains(Site)) return i;
        }
        return null;
    }

    public void mostrarSites(){
        int counter = 0;
        String sites = "";
        for(Login i: Sites){
            if(counter == 0) sites += i.getSite();
            if(counter != 0) sites += ", " + i.getSite();
            counter++;
        }
        System.out.println(sites);
    }
}
