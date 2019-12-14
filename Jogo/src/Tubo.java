import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Tubo extends JComponent {

    private int tube_larg;
    private int tube_dist_h;
    private Territorio territorio;
    private int random;
    protected int pontox1;
    protected int pontox2;
    protected int pontox3;
    protected int pontox4;
    protected int pontoy1;
    protected int pontoy2;
    protected int pontoy3;
    protected int pontoy4;

    public Tubo(Territorio territorio){
        Random gerador = new Random();
        this.territorio = territorio;
        this.random = gerador.nextInt(80) + 120;
    }

    public int CalcularLargura(Territorio territorio){
        return Math.round((16 * territorio.getWidth())/192);
    }

    public int CalcularDistanciaBTT(Territorio territorio){
        return Math.round((352 * territorio.getWidth())/1920);
    }

    public int CalcularAlturaDT(Territorio territorio){
        return Math.round((274.5f * territorio.getHeight())/1080);
    }

    public void paint(Graphics g,int tube_spawn, int tube_seq) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g; // por motivos de compatibilidade com a AWT
        g2d.setColor(Color.GREEN);
        g2d.fillRect(tube_spawn + CalcularDistanciaBTT(territorio)*tube_seq, 0, CalcularLargura(territorio), random);
        g2d.fillRect(tube_spawn + CalcularDistanciaBTT(territorio)*tube_seq, territorio.getHeight() - 1, CalcularLargura(territorio),  20-CalcularAlturaDT(territorio));

        pontox1 = tube_spawn + CalcularDistanciaBTT(territorio)*tube_seq-CalcularLargura(territorio)/2;
        pontoy1 = 0;
        pontox2 = tube_spawn + CalcularDistanciaBTT(territorio)*tube_seq+CalcularLargura(territorio)/2;
        pontoy2 = pontoy1 + random;
        pontox3 = pontox1;
        pontoy3 = territorio.getHeight() - 1;
        pontox4 = pontox2;
        pontoy4 = pontoy3 + 20-CalcularAlturaDT(territorio);
    }
}