import java.awt.Color;
import java.awt.Graphics;

import java.util.Random;

public class Comida extends Celula {
   private Random _random;

   public Comida(int x, int y, Color cor) {
      super(x, y, cor);

      _random = new Random();
   }

   public void sortear_x(int min, int max) {
      super.set_x(_random.nextInt(min, max));
   }

   public void sortear_y(int min, int max) {
      super.set_y(_random.nextInt(min, max));
   }

   public void desenhar(Graphics g) {
      g.setColor(super.get_cor());
      g.fillOval(super.get_x(), super.get_y(),
            Celula.ARESTA, Celula.ARESTA);
   }
}
