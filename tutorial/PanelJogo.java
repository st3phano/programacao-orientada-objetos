import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelJogo extends JPanel implements ActionListener {
   public static final int INTERVALO_ATUALIZACAO_MS = 100;
   public static final int LARGURA_PX = 800;
   public static final int ALTURA_PX = 600;
   public static final int ARESTA_CELULA_PX = 20;
   public static final int NUM_CELULAS_X = LARGURA_PX / ARESTA_CELULA_PX;
   public static final int NUM_CELULAS_Y = ALTURA_PX / ARESTA_CELULA_PX;
   public static final int NUM_CELULAS = NUM_CELULAS_X * NUM_CELULAS_Y;

   private int _xSnake[] = new int[NUM_CELULAS];
   private int _ySnake[] = new int[NUM_CELULAS];
   private int _tamanhoSnake = 6;
   private Direcao _direcao = Direcao.DIREITA;

   private Random _random = new Random();
   private int _xComida;
   private int _yComida;
   private int _pontosAcumulados = 0;

   private Timer _timer = new Timer(INTERVALO_ATUALIZACAO_MS, this);
   private boolean _rodando = false;

   public PanelJogo() {
      this.setPreferredSize(new Dimension(LARGURA_PX, ALTURA_PX));
      this.setBackground(Color.white);
      this.setFocusable(true);
      this.addKeyListener(new Teclado());

      iniciarJogo();
   }

   public void iniciarJogo() {
      posicionarComida();
      _timer.start();
      _rodando = true;
   }

   public void posicionarComida() {
      _xComida = _random.nextInt(NUM_CELULAS_X) * ARESTA_CELULA_PX;
      _yComida = _random.nextInt(NUM_CELULAS_Y) * ARESTA_CELULA_PX;
   }

   public void moverSnake() {
      for (int i = _tamanhoSnake; i > 0; --i) {
         _xSnake[i] = _xSnake[i - 1];
         _ySnake[i] = _ySnake[i - 1];
      }

      switch (_direcao) {
         case BAIXO:
            _ySnake[0] += ARESTA_CELULA_PX;
            break;
         case CIMA:
            _ySnake[0] -= ARESTA_CELULA_PX;
            break;
         case DIREITA:
            _xSnake[0] += ARESTA_CELULA_PX;
            break;
         case ESQUERDA:
            _xSnake[0] -= ARESTA_CELULA_PX;
            break;
      }
   }

   public void verificarColisao() {
      // Colisão com o próprio corpo
      for (int i = _tamanhoSnake; i > 0; --i) {
         if (_xSnake[0] == _xSnake[i]
               && _ySnake[0] == _ySnake[i])
            _rodando = false;
      }

      // Colisão com a borda da janela
      if (_xSnake[0] < 0 || _xSnake[0] > LARGURA_PX
            || _ySnake[0] < 0 || _ySnake[0] > ALTURA_PX) {
         _rodando = false;
      }

      // Colisão com a comida
      if (_xSnake[0] == _xComida
            && _ySnake[0] == _yComida) {
         ++_tamanhoSnake;
         ++_pontosAcumulados;
         posicionarComida();
      }
   }

   public void finalizarJogo(Graphics g) {

   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      desenharGrade(g);
      desenharComida(g);
      desenharSnake(g);
      desenharPontuacao(g);
   }

   public void desenharGrade(Graphics g) {
      g.setColor(Color.black);
      for (int x = 0; x < NUM_CELULAS_X; ++x) {
         g.drawLine(x * ARESTA_CELULA_PX, 0, x * ARESTA_CELULA_PX, ALTURA_PX);
      }
      for (int y = 0; y < NUM_CELULAS_Y; ++y) {
         g.drawLine(0, y * ARESTA_CELULA_PX, LARGURA_PX, y * ARESTA_CELULA_PX);
      }
   }

   public void desenharComida(Graphics g) {
      g.setColor(Color.red);
      g.fillOval(_xComida, _yComida, ARESTA_CELULA_PX, ARESTA_CELULA_PX);
   }

   public void desenharSnake(Graphics g) {
      // Cabeça
      g.setColor(Color.darkGray);
      g.fill3DRect(_xSnake[0], _ySnake[0], ARESTA_CELULA_PX, ARESTA_CELULA_PX, _rodando);

      // Corpo
      g.setColor(Color.gray);
      for (int i = 1; i < _tamanhoSnake; ++i) {
         // g.setColor(new Color(_random.nextInt()));
         g.fill3DRect(_xSnake[i], _ySnake[i], ARESTA_CELULA_PX, ARESTA_CELULA_PX, _rodando);
      }
   }

   public void desenharPontuacao(Graphics g) {
      g.setColor(Color.red);
      g.setFont(new Font("Monospaced", Font.BOLD, ARESTA_CELULA_PX * 2));
      g.drawString("Pontos:" + _pontosAcumulados, ARESTA_CELULA_PX, ARESTA_CELULA_PX * 2);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (_rodando) {
         moverSnake();
         verificarColisao();
      } else {
         _timer.stop();
      }

      repaint();
   }

   public class Teclado extends KeyAdapter {
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

   public enum Direcao {
      BAIXO,
      CIMA,
      DIREITA,
      ESQUERDA
   }
}
