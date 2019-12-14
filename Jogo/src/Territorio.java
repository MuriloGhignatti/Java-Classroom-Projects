import javax.swing.*;
import java.awt.*;

public class Territorio extends JPanel {

    private static final int ALTURA_BARRA_TITULO = 20;

    private JFrame frame;

    private int contador = 0;

    private Ser ser;

    private Tubo tubos_1;

    private Tubo tubos_2;

    private Tubo tubos_3;

    private int tube_spawn;

    //private int tube_larg;

    //private int tube_dist_h;

    public Territorio(String nome) {
        ser = new Ser(Color.GREEN,this);
        tubos_1 = new Tubo(this);
        tubos_2 = new Tubo(this);
        tubos_3 = new Tubo(this);
        frame = new JFrame(nome); // cria um frame
        frame.add(this); // insere o território no frame
        frame.setSize(400, 300 + ALTURA_BARRA_TITULO ); // define as dimensões do frame
        frame.setVisible(true); // torna o frame visível
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // define como o frame é fechado
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g; // por motivos de compatibilidade com a AWT
        g2d.setColor(Color.GREEN);
        if (tubos_1 != null) tubos_1.paint(g,tube_spawn,0);
        if (tubos_2 != null) tubos_2.paint(g,tube_spawn, 1);
        if (tubos_3 != null) tubos_3.paint(g,tube_spawn, 2);
    }

    public void jogar(){
        tube_spawn = getWidth() + 10;
        boolean jogando = true;
        while (jogando) {
            tube_spawn -= 10;
            contador++;
            System.out.println("largura: " + getWidth() + ", altura: " + getHeight());
            //Tudo que estiver acima disso é o que acontece durante o jogo

            if (contador == 100) jogando = false; // Condição de parada do jogo
            repaint(); // atualiza a imagem da janela

            try {

                Thread.sleep(500); // dorme por meio segundo (Velocidade do jogo)

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        game_over();
    }

    private void game_over() {
        String mensagem = "Parabéns!";
        JOptionPane.showMessageDialog(this, mensagem, "Game Over", JOptionPane.YES_NO_OPTION);
    }
}