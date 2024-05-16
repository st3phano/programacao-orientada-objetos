import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Jogo extends JPanel implements ActionListener, KeyListener {
   private final int _CELULAS_X;
   private final int _CELULAS_Y;

   private Random _random;
   private Comida _comida;
   private Snake _snake;

   private Timer _loop;

   public Jogo(int largura, int altura) {
      setPreferredSize(new Dimension(largura, altura));
      setBackground(Color.BLACK);
      addKeyListener(this);
      setFocusable(true);

      _CELULAS_X = largura / Celula.ARESTA;
      _CELULAS_Y = altura / Celula.ARESTA;

      _random = new Random();

      _comida = new Comida(_random.nextInt(_CELULAS_X), _random.nextInt(_CELULAS_Y), Color.RED);
      _snake = new Snake(_random.nextInt(_CELULAS_X), _random.nextInt(_CELULAS_Y), Color.GREEN);

      _loop = new Timer(100, this);
      _loop.start();
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      _comida.desenhar(g);
      _snake.desenhar_cabeca(g);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      _snake.mover();
      repaint();
   }

   @Override
   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_UP && _snake.get_velocidade_y() != 1) {
         _snake.set_velocidade_x(0);
         _snake.set_velocidade_y(-1);
      } else if (e.getKeyCode() == KeyEvent.VK_DOWN && _snake.get_velocidade_y() != -1) {
         _snake.set_velocidade_x(0);
         _snake.set_velocidade_y(1);
      } else if (e.getKeyCode() == KeyEvent.VK_LEFT && _snake.get_velocidade_x() != 1) {
         _snake.set_velocidade_x(-1);
         _snake.set_velocidade_y(0);
      } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && _snake.get_velocidade_x() != -1) {
         _snake.set_velocidade_x(1);
         _snake.set_velocidade_y(0);
      }
   }

   @Override
   public void keyTyped(KeyEvent e) {
   }

   @Override
   public void keyReleased(KeyEvent e) {
   }

   private void recolocar_comida() {
      _comida.sortear_x(0, _CELULAS_X);
      _comida.sortear_y(0, _CELULAS_Y);
   }
}
