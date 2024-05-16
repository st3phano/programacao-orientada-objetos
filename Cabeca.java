import java.awt.Color;
import java.awt.Graphics;

public class Cabeca extends Celula {
   public Cabeca(int x, int y, Color cor) {
      super(x, y, cor);
   }

   public void desenhar(Graphics g) {
      g.setColor(super.get_cor());
      g.fillRect(super.get_x(), super.get_y(),
            Celula.ARESTA, Celula.ARESTA);
   }
}
