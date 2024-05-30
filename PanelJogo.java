import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelJogo extends JPanel implements ActionListener {
   public final int _NUM_CELULAS_LADO, _NUM_CELULAS_TOTAL;
   public final int _LADO_PANEL_PX, _LADO_CELULA_PX;
   public final int _INTERVALO_ATUALIZACAO_MS;

   private Snake _snake;
   private int _pontosAcumulados = 0;

   private int _xComida;
   private int _yComida;
   private Random _random = new Random();

   private Timer _timer;
   private boolean _rodando = false;

   private JFrame _frameJogo;

   public PanelJogo(int numCelulasLado, int ladoCelulaPx, int intervaloAtualizacaoMs, JFrame frameJogo)
         throws NumeroNaoPositivoException {
      if (numCelulasLado < 1 || ladoCelulaPx < 1 || intervaloAtualizacaoMs < 1) {
         throw new NumeroNaoPositivoException("Argumento inteiro menor que 1");
      }

      _NUM_CELULAS_LADO = numCelulasLado;
      _NUM_CELULAS_TOTAL = _NUM_CELULAS_LADO * _NUM_CELULAS_LADO;
      _snake = new Snake(_NUM_CELULAS_TOTAL);

      _LADO_CELULA_PX = ladoCelulaPx;
      _LADO_PANEL_PX = _NUM_CELULAS_LADO * _LADO_CELULA_PX;

      _INTERVALO_ATUALIZACAO_MS = intervaloAtualizacaoMs;
      _timer = new Timer(_INTERVALO_ATUALIZACAO_MS, this);

      _frameJogo = frameJogo;

      this.setPreferredSize(new Dimension(_LADO_PANEL_PX, _LADO_PANEL_PX));
      this.setBackground(Color.white);
      this.setFocusable(true);
      this.addKeyListener(new Teclado());
   }

   public void iniciarJogo() {
      posicionarComida();
      _timer.start();
      _rodando = true;
   }

   private void posicionarComida() {
      do {
         _xComida = _random.nextInt(_NUM_CELULAS_LADO) * _LADO_CELULA_PX;
         _yComida = _random.nextInt(_NUM_CELULAS_LADO) * _LADO_CELULA_PX;
      } while (posicaoDaSnake(_xComida, _yComida));
   }

   private boolean posicaoDaSnake(int x, int y) {
      for (int i = 0; i < _tamanhoSnake; ++i) {
         if (x == _xSnake[i] && y == _ySnake[i]) {
            return true;
         }
      }

      return false;
   }

   private void verificarColisao() {
      // Colisão com o próprio corpo
      for (int i = _tamanhoSnake; i > 0; --i) {
         if (_xSnake[0] == _xSnake[i]
               && _ySnake[0] == _ySnake[i])
            _rodando = false;
      }

      // Colisão com a borda da janela
      if (_xSnake[0] < 0 || _xSnake[0] > _LADO_PANEL_PX
            || _ySnake[0] < 0 || _ySnake[0] > _LADO_PANEL_PX) {
         _rodando = false;
      }

      // Colisão com a comida
      if (_xSnake[0] == _xComida
            && _ySnake[0] == _yComida) {
         ++_tamanhoSnake;
         ++_pontosAcumulados;
         posicionarComida();

         _frameJogo.setTitle(String.valueOf(_pontosAcumulados));
      }
   }

   private void desenharGrade(Graphics g) {
      g.setColor(Color.black);
      for (int cel = 0; cel < _NUM_CELULAS_LADO; ++cel) {
         g.drawLine((cel * _LADO_CELULA_PX), 0, (cel * _LADO_CELULA_PX), _LADO_PANEL_PX);
         g.drawLine(0, (cel * _LADO_CELULA_PX), _LADO_PANEL_PX, (cel * _LADO_CELULA_PX));
      }
   }

   private void desenharComida(Graphics g) {
      g.setColor(Color.red);
      g.fillOval(_xComida, _yComida, _LADO_CELULA_PX, _LADO_CELULA_PX);
   }

   private void desenharSnake(Graphics g) {
      // Cabeça
      g.setColor(Color.darkGray);
      g.fill3DRect(_snake.getX(0), _snake.getY(0), _LADO_CELULA_PX, _LADO_CELULA_PX, _rodando);

      // Corpo
      g.setColor(Color.gray);
      for (int i = 1; i < _snake.getTamanho(); ++i) {
         g.fill3DRect(_snake.getX(i), _snake.getY(i), _LADO_CELULA_PX, _LADO_CELULA_PX, _rodando);
      }
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      desenharGrade(g);
      desenharComida(g);
      desenharSnake(g);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (_rodando) {
         _snake.mover(_LADO_CELULA_PX);
         verificarColisao();
         repaint();
      } else {
         _timer.stop();
      }
   }

   private class Teclado extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent e) {
         switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
               if (_direcao != Direcao.CIMA) {
                  _direcao = Direcao.BAIXO;
               }
               break;
            case KeyEvent.VK_UP:
               if (_direcao != Direcao.BAIXO) {
                  _direcao = Direcao.CIMA;
               }
               break;
            case KeyEvent.VK_RIGHT:
               if (_direcao != Direcao.ESQUERDA) {
                  _direcao = Direcao.DIREITA;
               }
               break;
            case KeyEvent.VK_LEFT:
               if (_direcao != Direcao.DIREITA) {
                  _direcao = Direcao.ESQUERDA;
               }
               break;
         }
      }
   }

}
