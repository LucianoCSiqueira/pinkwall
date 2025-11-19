
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    final static Scanner LER = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Path diretorioResources = Paths.get(
            "formatimg", "src", "main", "resources"
        );

        System.out.println("===== Processador de Imagens =====\n");

        System.out.println("Convertendo BG.png para mapa TMX...");

        Path bgPath = diretorioResources.resolve("BG.png");
        Path tmxPath = diretorioResources.resolve("background.tmx");

        MapaTiles.converterPNGparaTMX(bgPath, tmxPath);
        System.out.println("BG.png convertido para background.tmx");

        String[] imagensParaProcessar = {
            "fonteTxt.png", "sprites.bmp", "tijolo.png"
        };

        for (int i = 0; i < imagensParaProcessar.length; i++) {

            Path caminhoImagem = diretorioResources.resolve(imagensParaProcessar[i]);

            PaletaCores paleta = new PaletaCores(caminhoImagem);
            paleta.imprimirPaleta();

            System.out.print(
                    "Digite o índice da cor para mover para primeira posição (ou -1 para pular): "
            );

            int indice = LER.nextInt();

            if (indice >= 0) {

                Path caminhoSaida = diretorioResources.resolve(
                        imagensParaProcessar[i].replace(".", "_pal_mod.")
                );

                paleta.moverCorPaletaPrimeiraPosicao(indice).save(caminhoSaida);
                System.out.println(
                        "Imagem processada com sucesso: " + caminhoSaida.getFileName()
                );

            } else {

                System.out.println("Não foi feita nenhuma modificação na imagem.");

            }

        }

    }
}
