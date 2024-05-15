import javax.swing.JFrame;

public class Main {
   private static final int LARGURA_JFRAME = 800;
   private static final int ALTURA_JFRAME = 600;

   public static void main(String[] args) {
      var frame = new JFrame("Snake");

      frame.setSize(LARGURA_JFRAME, ALTURA_JFRAME);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);

      var tabuleiro = new Janela(LARGURA_JFRAME, ALTURA_JFRAME);
      frame.add(tabuleiro);
      frame.pack();

      frame.setVisible(true);
   }
}
