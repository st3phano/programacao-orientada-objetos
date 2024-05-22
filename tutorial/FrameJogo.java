import javax.swing.JFrame;

public class FrameJogo extends JFrame {
   public FrameJogo() {
      this.add(new PanelJogo());
      this.pack();
      this.setTitle("Snake");
      this.setResizable(false);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
   }
}
