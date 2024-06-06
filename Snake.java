import java.io.Serializable;

public class Snake implements Serializable {
   public static final int TAMANHO_INICIAL = 5;

   private int _tamanho;
   private boolean _viva;
   private Direcao _direcao;
   private Posicao[] _corpo;

   public Snake(int tamanhoMaximo) {
      _tamanho = TAMANHO_INICIAL;
      _viva = true;
      _direcao = Direcao.DIREITA;
      _corpo = new Posicao[tamanhoMaximo];

      // Cabeça
      _corpo[0] = new Posicao(0, 0);

      // Corpo
      for (int i = 1; i < tamanhoMaximo; ++i) {
         _corpo[i] = new Posicao(-1, 0);
      }
   }

   public int getTamanho() {
      return _tamanho;
   }

   public boolean getViva() {
      return _viva;
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

   public void crescer(int celulas) {
      for (int i = 0; i < celulas; ++i) {
         _corpo[_tamanho].set(_corpo[_tamanho - 1]);
         ++_tamanho;
      }
   }

   public void morrer() {
      _viva = false;
   }

   public void mover(int pixels) {
      for (int i = _tamanho; i > 0; --i) {
         _corpo[i].set(_corpo[i - 1]);
      }

      final int xCabecaSnake = _corpo[0].getX();
      final int yCabecaSnake = _corpo[0].getY();
      switch (_direcao) {
         case Direcao.BAIXO:
            _corpo[0].setY(yCabecaSnake + pixels);
            break;
         case Direcao.CIMA:
            _corpo[0].setY(yCabecaSnake - pixels);
            break;
         case Direcao.DIREITA:
            _corpo[0].setX(xCabecaSnake + pixels);
            break;
         case Direcao.ESQUERDA:
            _corpo[0].setX(xCabecaSnake - pixels);
            break;
      }
   }
}
