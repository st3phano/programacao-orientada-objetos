import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class FrameJogo extends JFrame {
   private final String _CAMINHO_ARQUIVO_JOGO = "salvo.snake";
   private PanelJogo _panelJogo;

   public FrameJogo(int numCelulasLado, int ladoCelulaPx, int intervaloAtualizacaoMs)
         throws NumeroNaoPositivoException {
      _panelJogo = new PanelJogo(numCelulasLado, ladoCelulaPx, intervaloAtualizacaoMs);

      super.setTitle("Snake");
      super.setVisible(true);
      super.setResizable(false);
      super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      super.addKeyListener(new Teclado());
   }

   public void exibirJogo() {
      super.add(_panelJogo);
      super.pack();
      super.setLocationRelativeTo(null);
   }

   private void salvarJogo() {
      try {
         Arquivo.salvarJogo(_panelJogo, _CAMINHO_ARQUIVO_JOGO);
      } catch (Exception e) {
         e.printStackTrace();
      }

      _panelJogo.piscarCabecaSnake(Color.green);
   }

   private void carregarJogo() {
      remove(_panelJogo);

      try {
         _panelJogo = Arquivo.carregarJogo(_CAMINHO_ARQUIVO_JOGO);
      } catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
      }

      exibirJogo();
      _panelJogo.piscarCabecaSnake(Color.blue);
   }

   private class Teclado extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent e) {
         final int keyCode = e.getKeyCode();

         switch (keyCode) {
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
               _panelJogo.setDirecaoSnake(keyCode);
               break;
            case KeyEvent.VK_SPACE:
               _panelJogo.pausarJogo();
               break;
            case KeyEvent.VK_S:
               salvarJogo();
               break;
            case KeyEvent.VK_C:
               carregarJogo();
               break;
         }
      }
   }
}
