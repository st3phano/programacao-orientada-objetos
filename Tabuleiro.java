import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.util.Random;

import javax.swing.JPanel;

public class Tabuleiro extends JPanel {
   private final int _CELULAS_X;
   private final int _CELULAS_Y;

   private Random _random;
   private Snake _snake;
   private Comida _comida;

   public Tabuleiro(int largura, int altura) {
      setPreferredSize(new Dimension(largura, altura));
      setBackground(Color.BLACK);

      _CELULAS_X = largura / Celula.ARESTA;
      _CELULAS_Y = altura / Celula.ARESTA;

      _random = new Random();

      _snake = new Snake(Color.GREEN,
            _random.nextInt(_CELULAS_X),
            _random.nextInt(_CELULAS_Y));
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      _snake.desenhar_cabeca(g);

      gerar_comida();
      _comida.desenhar(g);
   }

   private void gerar_comida() {
      _comida = new Comida(Color.RED,
            _random.nextInt(_CELULAS_X),
            _random.nextInt(_CELULAS_Y));
   }
}
