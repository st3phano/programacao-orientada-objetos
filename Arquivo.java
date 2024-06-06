import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Arquivo {
   public static FrameJogo carregarConfiguracao(String caminhoArquivo)
         throws FileNotFoundException, NumeroNaoPositivoException {
      var arquivo = new File(caminhoArquivo);
      var scanner = new Scanner(arquivo);

      scanner.next();
      int numCelulasLado = scanner.nextInt();
      scanner.next();
      int ladoCelulaPx = scanner.nextInt();
      scanner.next();
      int intervaloAtualizacaoMs = scanner.nextInt();

      scanner.close();

      return new FrameJogo(numCelulasLado, ladoCelulaPx, intervaloAtualizacaoMs);
   }

   public static void salvarJogo(PanelJogo jogo, String caminhoArquivo)
         throws FileNotFoundException, IOException, ClassNotFoundException {
      var file_output_stream = new FileOutputStream(caminhoArquivo);
      var object_output_stream = new ObjectOutputStream(file_output_stream);

      object_output_stream.writeObject(jogo);

      object_output_stream.close();
      file_output_stream.close();
   }

   public static PanelJogo carregarJogo(String caminhoArquivo)
         throws FileNotFoundException, IOException, ClassNotFoundException {
      var file_input_stream = new FileInputStream(caminhoArquivo);
      var object_input_stream = new ObjectInputStream(file_input_stream);

      PanelJogo jogoSalvo = (PanelJogo) object_input_stream.readObject();

      object_input_stream.close();
      file_input_stream.close();

      return jogoSalvo;
   }
}
