import javax.swing.JFrame;

public class FrameJogo extends JFrame {
   private PanelJogo _panelJogo;

   public FrameJogo(int numCelulasLado, int ladoCelulaPx, int intervaloAtualizacaoMs)
         throws NumeroNaoPositivoException {
      _panelJogo = new PanelJogo(numCelulasLado, ladoCelulaPx, intervaloAtualizacaoMs, this);

      this.add(_panelJogo);
      this.pack();
      this.setTitle("Snake");
      this.setResizable(false);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
   }

   public void iniciarJogo() {
      _panelJogo.iniciarJogo();
   }
}
