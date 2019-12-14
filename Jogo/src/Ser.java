import javax.swing.*;
import java.awt.*;
public class Ser extends JComponent {
    private Territorio territorio;
    private Color cor;
    private int x = 50;
    private int y = 40;
    private int altura = 30;
    private int largura = 30;

    public Ser(Color cor, Territorio territorio) {
        this.cor = cor;
        this.territorio = territorio;
    }
    public void paint(Graphics g,int x) {
        Graphics2D g2d = (Graphics2D) g; // compatibilidade com a AWT
        g2d.setColor(cor);
        g2d.fillOval(x, y, largura, altura); // desenha um círculo
    }
}