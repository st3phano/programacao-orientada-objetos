public class Comida {
   private Posicao _posicao;

   public Comida(int x, int y) {
      _posicao = new Posicao(x, y);
   }

   public Direcao getDirecao() {
      return _direcao;
   }

   public void setDirecao(Direcao direcao) {
      _direcao = direcao;
   }

   public void mover(int quantidade) {
      for (int i = _tamanho; i > 0; --i) {
         _corpo[i].setPosicao(_corpo[i - 1]);
      }

      switch (_direcao) {
         case Direcao.BAIXO:
            _corpo[0].moverY(quantidade);
            break;
         case Direcao.CIMA:
            _corpo[0].moverY(-quantidade);
            break;
         case Direcao.DIREITA:
            _corpo[0].moverX(quantidade);
            break;
         case Direcao.ESQUERDA:
            _corpo[0].moverX(-quantidade);
            break;
      }
   }
}
