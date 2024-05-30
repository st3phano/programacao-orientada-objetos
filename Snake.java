public class Snake {
   public static final int TAMANHO_INICIAL = 5;

   private int _tamanho = TAMANHO_INICIAL;
   private Direcao _direcao = Direcao.DIREITA;

   private final Posicao[] _corpo;

   public Snake(int tamanhoMaximo) {
      _corpo = new Posicao[tamanhoMaximo];
   }

   public int getTamanho() {
      return _tamanho;
   }

   public Direcao getDirecao() {
      return _direcao;
   }

   public int getX(int i) {
      return _corpo[i].getX();
   }

   public int getY(int i) {
      return _corpo[i].getY();
   }

   public void setDirecao(Direcao direcao) {
      _direcao = direcao;
   }

   public void crescer(int quantidade) {
      _tamanho += quantidade;
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
