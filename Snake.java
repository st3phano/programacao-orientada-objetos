import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Snake {
   private Celula _cabeca;
   private LinkedList<Celula> _corpo;

   public Snake(Celula ) {
      _cabeca = new Cabeca(x, y, cor_cabeca);

      _velocidade_x = 0;
      _velocidade_y = 0;
   }

   public int get_velocidade_x() {
      return _velocidade_x;
   }

   public void set_velocidade_x(int velocidade_x) {
      _velocidade_x = velocidade_x;
   }

   public int get_velocidade_y() {
      return _velocidade_y;
   }

   public void set_velocidade_y(int velocidade_y) {
      _velocidade_y = velocidade_y;
   }

   public void mover() {
      _cabeca.somar_em_x(_velocidade_x);
      _cabeca.somar_em_y(_velocidade_y);
   }

   public void desenhar_cabeca(Graphics g) {
      _cabeca.desenhar(g);
   }
}
