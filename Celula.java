import java.awt.Color;
import java.awt.Graphics;

public class Celula {
   public static final int ARESTA = 20;

   private int _x, _y;
   private TipoCelula _tipo;

   public Celula(int x, int y) {
      set_x(x);
      set_y(y);
   }

   public int get_x() {
      return _x;
   }

   private void set_x(int x) {
      _x = x * ARESTA;
   }

   public int get_y() {
      return _y;
   }

   private void set_y(int y) {
      _y = y * ARESTA;
   }

   public TipoCelula get_tipo() {
      return _tipo;
   }

   public void set_tipo(TipoCelula tipo) {
      _tipo = tipo;
   }

   public void desenhar(Graphics g) {
      switch (get_tipo()) {
         case VAZIA:
            g.setColor(Color.WHITE);
            g.fillRect(get_x(), get_y(), ARESTA, ARESTA);
            break;
         case COMIDA:
            g.setColor(Color.RED);
            g.fillOval(get_x(), get_y(), ARESTA, ARESTA);
            break;
         case CABECA_SNAKE:
            g.setColor(Color.LIGHT_GRAY);
            g.fill3DRect(get_x(), get_y(), ARESTA, ARESTA, true);
            break;
         case CORPO_SNAKE:
            g.setColor(Color.DARK_GRAY);
            g.fill3DRect(get_x(), get_y(), ARESTA, ARESTA, true);
            break;
      }
   }
}

enum TipoCelula {
   VAZIA,
   COMIDA,
   CABECA_SNAKE,
   CORPO_SNAKE
}
