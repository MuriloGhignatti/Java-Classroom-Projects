import java.util.ArrayList;

public class Vault {

    private User Usuario;
    private ArrayList<Login> Sites = new ArrayList<Login>();

    public Vault(User Usuario){
        this.Usuario = Usuario;
    }

    public void AddSite(Login Login){
        Sites.add(Login);
    }
}
