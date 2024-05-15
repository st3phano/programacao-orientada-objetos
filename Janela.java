import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Janela extends JPanel {
   private final int _LARGURA;
   private final int _ALTURA;

   public Janela(int larguraJanela, int alturaJanela) {
      _LARGURA = larguraJanela;
      _ALTURA = alturaJanela;

      setPreferredSize(new Dimension(_LARGURA, _ALTURA));
      setBackground(Color.BLACK);
   }
}
