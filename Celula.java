import java.awt.Color;
import java.awt.Graphics;

public abstract class Celula {
   public static final int ARESTA = 20;

   private int _x;
   private int _y;
   private Color _cor;

   public Celula(int x, int y, Color cor) {
      set_x(x);
      set_y(y);
      _cor = cor;
   }

   protected int get_x() {
      return _x;
   }

   protected void set_x(int x) {
      _x = x * ARESTA;
   }

   protected void somar_em_x(int valor) {
      _x += valor * ARESTA;
   }

   protected int get_y() {
      return _y;
   }

   protected void set_y(int y) {
      _y = y * ARESTA;
   }

   protected void somar_em_y(int valor) {
      _y += valor * ARESTA;
   }

   protected Color get_cor() {
      return _cor;
   }

   protected void set_cor(Color cor) {
      _cor = cor;
   }

   public abstract void desenhar(Graphics g);
}
