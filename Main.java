import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class Main {
   public static void main(String[] args) {
      try {
         var frameJogo = Arquivo.carregarConfiguracao("configuracao.txt");
         frameJogo.exibirJogo();
      } catch (FileNotFoundException e) {
         JOptionPane.showMessageDialog(null,
               "Arquivo de configuração não encontrado.",
               "Erro",
               JOptionPane.ERROR_MESSAGE);
      } catch (NumeroNaoPositivoException e) {
         JOptionPane.showMessageDialog(null,
               "Utilize apenas inteiros positivos no arquivo de configuração.",
               "Erro",
               JOptionPane.ERROR_MESSAGE);
      }
   }
}
