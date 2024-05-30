public class Posicao {
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

   public void setPosicao(Posicao posicao) {
      _x = posicao._x;
      _y = posicao._y;
   }

   public void moverX(int quantidade) {
      _x += quantidade;
   }

   public void moverY(int quantidade) {
      _y += quantidade;
   }
}
