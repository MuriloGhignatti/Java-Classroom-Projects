import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Territorio extends JPanel {
    private static final int X_MAXIMO_INICIAL = 400;
    private static final int Y_MAXIMO_INICIAL = 300;
    private static int ALTURA_BARRA_TITULO = 20;

    private static int NUMERO_DE_SERES = 15;
    private static int TEMPO_UM_PASSO = 20;

    private int contador = 0;

    private ArrayList<Ser> seres;
    private boolean jogando = true;

    private JFrame frame;

    public Territorio(String nome) {
        seres = new ArrayList<Ser>();

        frame = new JFrame(nome);
        frame.add(this);
        frame.setSize(X_MAXIMO_INICIAL,Y_MAXIMO_INICIAL + ALTURA_BARRA_TITULO );
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 16)); // define a fonte em uso
        g2d.drawString("Contador: " + String.valueOf(contador), 10, 30); // escreve uma string

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); // melhora a qualidade da imagem em movimento

        for (Ser s: seres) s.paint(g);
    }

    private void criar_ser() {
        Ser ser = new Ser(this);
        seres.add(ser);
    }

    private void criar_seres() {
        for (int i = 0; i < NUMERO_DE_SERES; i++) criar_ser();
    }

    public void jogar() {

        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        criar_seres();

        while (jogando) {
            contador++;
            if (contador == 10) jogando = false;
            repaint();
            for (Ser s: seres) s.mover();
            verificar_colisoes_entre_seres();
            repaint();
            try {
                Thread.sleep(TEMPO_UM_PASSO);
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
    }

    private void verificar_colisoes_entre_seres() {
        boolean colisao_detectada = true;
        while (colisao_detectada) {
            colisao_detectada = false;
            for (int i = 0; i < seres.size(); i++) {
                if (seres.get(i).nao_engolido()) {
                    for (int j = i + 1; j < seres.size(); j++) {
                        if (seres.get(j).nao_engolido()) {
                            if (seres.get(i).em_colisao(seres.get(j)))
                            {
                                seres.get(i).fundir(seres.get(j));
                                colisao_detectada = true;
                            }
                        }

                    }
                }
            }
            remover_engolidos();
        }
    }

    private void remover_engolidos() {
        int i = 0;
        while (i < seres.size()) {
            if (seres.get(i).engolido()) {
                seres.remove(i);
            }
            else
            {
                i++;
            }
        }
    }
}
