import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelJogo extends JPanel implements ActionListener {
   public final int _CELULAS_LADO, _CELULAS_TOTAL;
   public final int _LADO_CELULA_PX, _LADO_PANEL_PX;
   public final int _INTERVALO_ATUALIZACAO_MS;

   private Snake _snake;
   private Timer _timer;
   private Random _random;
   private Comida _comida;
   private Direcao _direcaoSnake;

   private Color _corCabecaSnake;

   public PanelJogo(int celulasLado, int ladoCelulaPx, int intervaloAtualizacaoMs)
         throws NumeroNaoPositivoException {
      if (celulasLado < 1 || ladoCelulaPx < 1 || intervaloAtualizacaoMs < 1) {
         throw new NumeroNaoPositivoException();
      }

      _CELULAS_LADO = celulasLado;
      _CELULAS_TOTAL = celulasLado * celulasLado;
      _LADO_CELULA_PX = ladoCelulaPx;
      _LADO_PANEL_PX = celulasLado * ladoCelulaPx;
      _INTERVALO_ATUALIZACAO_MS = intervaloAtualizacaoMs;

      _snake = new Snake(_CELULAS_TOTAL);
      _timer = new Timer(_INTERVALO_ATUALIZACAO_MS, this);
      _random = new Random();
      posicionarComida();
      _direcaoSnake = _snake.getDirecao();

      super.setBackground(Color.white);
      super.setPreferredSize(new Dimension(_LADO_PANEL_PX, _LADO_PANEL_PX));
   }

   public void setDirecaoSnake(int keyCode) {
      switch (keyCode) {
         case KeyEvent.VK_DOWN:
            if (_snake.getDirecao() != Direcao.CIMA) {
               _direcaoSnake = Direcao.BAIXO;
               _timer.start();
            }
            break;
         case KeyEvent.VK_UP:
            if (_snake.getDirecao() != Direcao.BAIXO) {
               _direcaoSnake = Direcao.CIMA;
               _timer.start();
            }
            break;
         case KeyEvent.VK_RIGHT:
            if (_snake.getDirecao() != Direcao.ESQUERDA) {
               _direcaoSnake = Direcao.DIREITA;
               _timer.start();
            }
            break;
         case KeyEvent.VK_LEFT:
            if (_snake.getDirecao() != Direcao.DIREITA) {
               _direcaoSnake = Direcao.ESQUERDA;
               _timer.start();
            }
            break;
      }
   }

   public void pausarJogo() {
      _timer.stop();
      _corCabecaSnake = Color.orange;
      super.repaint();
   }

   public void piscarCabecaSnake(Color cor) {
      _corCabecaSnake = cor;
      if (!_timer.isRunning()) {
         super.repaint();
      }
   }

   private void posicionarComida() {
      int xComida, yComida;
      do {
         xComida = _random.nextInt(_CELULAS_LADO) * _LADO_CELULA_PX;
         yComida = _random.nextInt(_CELULAS_LADO) * _LADO_CELULA_PX;
      } while (serPosicaoDaSnake(xComida, yComida));

      final int pontos = _random.nextInt(1, 10);
      _comida = new Comida(xComida, yComida, pontos);
   }

   private boolean serPosicaoDaSnake(int x, int y) {
      for (int i = 0; i < _snake.getTamanho(); ++i) {
         if (x == _snake.getX(i)
               && y == _snake.getY(i)) {
            return true;
         }
      }

      return false;
   }

   private void verificarColisao() {
      final int xCabecaSnake = _snake.getX(0);
      final int yCabecaSnake = _snake.getY(0);

      // Colisão da snake com o próprio corpo
      for (int i = _snake.getTamanho(); i > 0; --i) {
         if (xCabecaSnake == _snake.getX(i) && yCabecaSnake == _snake.getY(i)) {
            _snake.morrer();
            return;
         }
      }

      // Colisão da snake com a borda da janela
      if (xCabecaSnake < 0 || xCabecaSnake >= _LADO_PANEL_PX
            || yCabecaSnake < 0 || yCabecaSnake >= _LADO_PANEL_PX) {
         _snake.morrer();
      }

      // Colisão da snake com a comida
      else if (xCabecaSnake == _comida.getX() && yCabecaSnake == _comida.getY()) {
         _snake.crescer(_comida.getPontos());
         posicionarComida();
      }
   }

   private void desenharGrade(Graphics g) {
      g.setColor(Color.black);
      for (int cel = 0; cel < _CELULAS_LADO; ++cel) {
         g.drawLine((cel * _LADO_CELULA_PX), 0, (cel * _LADO_CELULA_PX), _LADO_PANEL_PX);
         g.drawLine(0, (cel * _LADO_CELULA_PX), _LADO_PANEL_PX, (cel * _LADO_CELULA_PX));
      }
   }

   private void desenharComida(Graphics g) {
      g.setColor(Color.red);
      g.fillOval(_comida.getX(), _comida.getY(), _LADO_CELULA_PX, _LADO_CELULA_PX);

      g.setColor(Color.black);
      Font font = new Font("Arial", Font.BOLD | Font.ITALIC, _LADO_CELULA_PX / 2);
      g.setFont(font);

      String pontos = String.valueOf(_comida.getPontos());
      FontMetrics fontMetrics = getFontMetrics(font);
      int x = _comida.getX() + ((_LADO_CELULA_PX - fontMetrics.stringWidth(pontos)) / 2);
      int y = _comida.getY() + ((_LADO_CELULA_PX + font.getSize()) / 2);
      g.drawString(pontos, x, y);
   }

   private void desenharSnake(Graphics g) {
      // Corpo
      g.setColor(Color.gray);
      for (int i = 1; i < _snake.getTamanho(); ++i) {
         g.fill3DRect(_snake.getX(i), _snake.getY(i), _LADO_CELULA_PX, _LADO_CELULA_PX, _snake.getViva());
      }

      // Cabeça
      g.setColor(_corCabecaSnake);
      _corCabecaSnake = Color.darkGray;
      g.fill3DRect(_snake.getX(0), _snake.getY(0), _LADO_CELULA_PX, _LADO_CELULA_PX, _snake.getViva());
   }

   public void desenharPontuacao(Graphics g) {
      g.setColor(Color.blue);
      Font font = new Font("Arial", Font.BOLD, (_LADO_CELULA_PX * 2));
      g.setFont(font);

      String pontos = String.valueOf(_snake.getTamanho());
      FontMetrics fontMetrics = getFontMetrics(font);
      int x = (_LADO_PANEL_PX - fontMetrics.stringWidth(pontos)) / 2;
      int y = font.getSize();
      g.drawString(pontos, x, y);
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      desenharGrade(g);
      desenharComida(g);
      desenharSnake(g);
      desenharPontuacao(g);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      super.repaint();

      if (_snake.getViva()) {
         _snake.setDirecao(_direcaoSnake);
         _snake.mover(_LADO_CELULA_PX);
         verificarColisao();
      } else {
         _timer.stop();
      }
   }
}
