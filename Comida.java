import java.io.Serializable;

public class Comida implements Serializable {
   private Posicao _posicao;
   protected int _pontos;

   public Comida(int x, int y, int pontos) {
      _posicao = new Posicao(x, y);
      _pontos = pontos;
   }

   public int getX() {
      return _posicao.getX();
   }

   public int getY() {
      return _posicao.getY();
   }

   public int getPontos() {
      return _pontos;
   }
}
