public class Celula {
   public static final int ARESTA = 20;
   private int _x;
   private int _y;

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
}
