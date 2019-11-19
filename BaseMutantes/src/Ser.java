import java.awt.*;
import java.util.Random;
import javax.swing.JComponent;

public class Ser extends JComponent {
    private static final int RAIO_INICIAL = 5;

    private static final Random gerador_aleatorio = new Random();

    private int x;
    private int y;
    private int raio;
    private Color cor;
    private Territorio territorio;
    private boolean movendo_para_direita;
    private boolean movendo_para_baixo;
    private boolean engolido;

    public Ser(Territorio territorio) {
        this.territorio = territorio;

        cor = Color.GREEN;
        raio = RAIO_INICIAL;
        engolido = false;

        int max_x = territorio.getWidth();
        int max_y = territorio.getHeight();
        int largura = raio * 2;
        int altura = largura;

        x = gerador_aleatorio.nextInt(max_x - largura + 1);
        y = gerador_aleatorio.nextInt(max_y - altura + 1);

        double borda = Math.random();
        if (borda < 0.25)
        {
            // borda superior
            y = 0;
            movendo_para_baixo = true;
            movendo_para_direita = (Math.random() < 0.5);
        }
        else
        if (borda < 0.5)
        {
            // borda inferior
            y = max_y - altura;
            movendo_para_baixo = false;
            movendo_para_direita = (Math.random() < 0.5);
        }
        else
        if (borda < 0.75)
        {
            // borda esquerda
            x = 0;
            movendo_para_direita = true;
            movendo_para_baixo = (Math.random() < 0.5);
        }
        else
        {
            // borda direita
            x = max_x - largura;
            movendo_para_direita = false;
            movendo_para_baixo = (Math.random() < 0.5);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g; // compatibilidade com a AWT

        g2d.setColor(Color.GREEN);
        int largura = raio * 2;
        int altura = largura;
        g2d.fillOval( x, y, largura, altura);
    }

    public void mover()
    {
        int max_x = territorio.getWidth();
        int max_y = territorio.getHeight();
        int largura = raio * 2;
        int altura = largura;

        if (movendo_para_direita)
        {
            x++;
            if (x > max_x - largura)
            {
                x = max_x - largura;
                movendo_para_direita = false;
            }
        }
        else // movendo para a esquerda
        {
            x--;
            if (x < 0)
            {
                x = 0;
                movendo_para_direita = true;
            }
        }
        if (movendo_para_baixo)
        {
            y++;
            if (y > max_y - altura)
            {
                y = max_y - altura;
                movendo_para_baixo = false;
            }
        }
        else // movendo para cima
        {
            y--;
            if (y < 0)
            {
                y = 0;
                movendo_para_baixo = true;
            }
        }
    }

    public boolean engolido() {
        return engolido;
    }

    public boolean nao_engolido() {
        return !engolido;
    }

    public void marcar_engolido() {
        engolido = true;
    }

    public boolean em_colisao(Ser outro_ser) {
        boolean colisao = false;
        int x1 = this.x + this.raio;
        int y1 = this.y + this.raio;
        int x2 = outro_ser.x + outro_ser.raio;
        int y2 = outro_ser.y + outro_ser.raio;
        double distancia_entre_centros = Math.sqrt( Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2) );
        if (distancia_entre_centros <= this.raio + outro_ser.raio)
            colisao = true;
        return colisao;
    }

    public void fundir(Ser outro_ser) {
        boolean este_engole = false;

        if (this.raio > outro_ser.raio)
            este_engole = true;
        else
        if (this.raio < outro_ser.raio)
            este_engole = false;
        else // raios iguais
            este_engole = (Math.random() < 0.5);

        if (este_engole)
            this.engolir(outro_ser);
        else
            outro_ser.engolir(this);
    }

    public void engolir(Ser outro_ser) {
        this.raio = this.raio + outro_ser.raio;
        outro_ser.marcar_engolido();
    }

    public Rectangle getBounds() {
        int diametro = raio * 2;
        return new Rectangle(x, y, diametro, diametro);
    }
}
