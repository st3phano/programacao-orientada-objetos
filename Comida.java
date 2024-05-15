import java.awt.Color;
import java.awt.Graphics;

public class Comida extends Celula {
   private Color _cor;

   public Comida(Color cor, int x, int y) {
      super(x, y);
      _cor = cor;
   }

   public void desenhar(Graphics g) {
      g.setColor(_cor);
      g.fillOval(super.get_x(), super.get_y(),
            Celula.ARESTA, Celula.ARESTA);
   }
}
