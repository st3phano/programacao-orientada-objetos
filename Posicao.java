import java.io.Serializable;

public class Posicao implements Serializable {
   private int _x, _y;

   public Posicao(int x, int y) {
      _x = x;
      _y = y;
   }

   public int getX() {
      return _x;
   }

   public int getY() {
      return _y;
   }

   public void set(Posicao posicao) {
      _x = posicao._x;
      _y = posicao._y;
   }

   public void setX(int x) {
      _x = x;
   }

   public void setY(int y) {
      _y = y;
   }
}
