import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {

  /**
   * @param iStream  - InputStream da imagem
   * @param fileName - nome do arquivo de saída
   * @throws IOException - caso não consiga ler a imagem
   */
  public void cria(InputStream iStream, String fileName) throws IOException {
    System.out.printf("Criando figurinha");

    BufferedImage original = ImageIO.read(iStream);
    System.out.printf(".");

    int width = original.getWidth();
    int height = original.getHeight();
    int newHeight = height + (height / 5);

    BufferedImage figurinha = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
    Graphics2D graphics = (Graphics2D) figurinha.getGraphics();
    graphics.drawImage(original, 0, 0, null);
    System.out.printf(".");

    // configurar a fonte
    var font = new Font("Impact", Font.BOLD, 96);
    graphics.setFont(font);
    graphics.setColor(Color.yellow);

    // escrever o texto
    String texto = "TOPPERSON!";
    int fontHeight = graphics.getFontMetrics().getHeight();
    int fontAscent = graphics.getFontMetrics().getAscent();
    int x = (width - graphics.getFontMetrics().stringWidth(texto)) / 2;
    int y = ((((newHeight) - fontHeight) / 2) + fontAscent) + height / 2;
    graphics.drawString(texto, x, y);
    System.out.printf(".");

    FontRenderContext fontRenderContext = graphics.getFontRenderContext();
    TextLayout textLayout = new TextLayout(texto, font, fontRenderContext);

    Shape outline = textLayout.getOutline(null);
    AffineTransform transform = graphics.getTransform();
    transform.translate(x, y);
    graphics.setTransform(transform);

    BasicStroke outlinStroke = new BasicStroke(width * 0.004f);
    graphics.setStroke(outlinStroke);

    graphics.setColor(Color.BLACK);
    graphics.draw(outline);
    graphics.setClip(outline);

    // garantir que o diretório de saída existe
    new File("../saida/").mkdir();

    ImageIO.write(figurinha, "png", new File("../saida/" + fileName));

    System.out.printf("\u001b[32;1m \u2714 Pronto!\n\n");
  }
}
