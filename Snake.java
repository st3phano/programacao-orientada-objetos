import java.awt.Color;
import java.awt.Graphics;

public class Snake {
   private Color _cor;
   private Celula _cabeca;

   public Snake(Color cor, int x, int y) {
      _cor = cor;
      _cabeca = new Celula(x, y);
   }

   public void desenhar_cabeca(Graphics g) {
      g.setColor(_cor);
      g.fillRect(_cabeca.get_x(), _cabeca.get_y(),
            Celula.ARESTA, Celula.ARESTA);
   }
}
