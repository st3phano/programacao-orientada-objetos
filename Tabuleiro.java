import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Tabuleiro extends JPanel {
   private final int _LARGURA;
   private final int _ALTURA;

   public Tabuleiro(int largura, int altura) {
      _LARGURA = largura;
      _ALTURA = altura;

      setPreferredSize(new Dimension(_LARGURA, _ALTURA));
      setBackground(Color.BLACK);
   }
}
