import java.awt.*;

public class Main {
   public static void main(String[] args) {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      String[] fonts = ge.getAvailableFontFamilyNames();
      for (String font : fonts) {
         System.out.println(font);
      }
   }
}
