import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class MapaTiles {

    final static int TAMANHO_TILE = 8;
    
    public static void converterPNGparaTMX(Path caminhoPNG, Path caminhoTMX) throws Exception {
        
        BufferedImage imagem = ImageIO.read(caminhoPNG.toFile());
        int larguraImagem = imagem.getWidth();
        int alturaImagem = imagem.getHeight();
        
        int larguraMapa = larguraImagem / TAMANHO_TILE;
        int alturaMapa = alturaImagem / TAMANHO_TILE;
        
        Map<String, Integer> tilesUnicos = new LinkedHashMap<>();
        List<BufferedImage> imagensTilesUnicos = new ArrayList<>();

        int[][] mapaDeTiles = new int[alturaMapa][larguraMapa];

        int proximoIdTile = 1;
        
        for (int linha = 0; linha < alturaMapa; linha++) {

            for (int coluna = 0; coluna < larguraMapa; coluna++) {
                BufferedImage tile = imagem.getSubimage(
                    coluna * TAMANHO_TILE, 
                    linha * TAMANHO_TILE, 
                    TAMANHO_TILE, 
                    TAMANHO_TILE
                );
                
                String identificadorTile = gerarIdentificadorUnicoDoTile(tile);
                
                if (!tilesUnicos.containsKey(identificadorTile)) {
                    tilesUnicos.put(identificadorTile, proximoIdTile);
                    imagensTilesUnicos.add(tile);
                    proximoIdTile++;
                }
                
                mapaDeTiles[linha][coluna] = tilesUnicos.get(identificadorTile);
            }

        }
        
        String nomeTileset = caminhoPNG.getFileName().toString().replace(
            ".png", "_tileset.png"
        );
        Path caminhoTileset = caminhoTMX.getParent().resolve(nomeTileset);
        criarImagemTileset(imagensTilesUnicos, caminhoTileset);
        
        String conteudoTMX = gerarConteudoArquivoTMX(
            nomeTileset, 
            larguraMapa, 
            alturaMapa, 
            mapaDeTiles, 
            tilesUnicos.size()
        );
        
        File arquivoSaida = caminhoTMX.toFile();
        try (FileWriter escritor = new FileWriter(arquivoSaida)) {
            escritor.write(conteudoTMX);
        }
        
        System.out.println("TMX gerado: " + caminhoTMX);
        System.out.println("Tileset gerado: " + caminhoTileset);
    }
    
    public static void criarImagemTileset(
        List<BufferedImage> tilesUnicos, Path caminhoSaida
    ) throws Exception {
        
        if (tilesUnicos.isEmpty()) {
            return;
        }

        int quantidadeTiles = tilesUnicos.size();
        int colunasTileset = (int) Math.ceil(Math.sqrt(quantidadeTiles));
        int linhasTileset = (quantidadeTiles + colunasTileset - 1) / colunasTileset;
        
        BufferedImage tileset = new BufferedImage(
            colunasTileset * TAMANHO_TILE,
            linhasTileset * TAMANHO_TILE,
            BufferedImage.TYPE_INT_ARGB
        );
        
        for (int i = 0; i < quantidadeTiles; i++) {

            BufferedImage tile = tilesUnicos.get(i);

            int x = (i % colunasTileset) * TAMANHO_TILE;
            int y = (i / colunasTileset) * TAMANHO_TILE;
            
            for (int ty = 0; ty < TAMANHO_TILE; ty++) {

                for (int tx = 0; tx < TAMANHO_TILE; tx++) {
                    int pixel = tile.getRGB(tx, ty);
                    tileset.setRGB(x + tx, y + ty, pixel);
                }

            }

        }
        
        ImageIO.write(tileset, "PNG", caminhoSaida.toFile());
    }
    
    public static String gerarIdentificadorUnicoDoTile(BufferedImage tile) {

        StringBuilder identificador = new StringBuilder();
        
        for (int y = 0; y < tile.getHeight(); y++) {

            for (int x = 0; x < tile.getWidth(); x++) {
                identificador.append(tile.getRGB(x, y));
            }

        }
        
        return identificador.toString();

    }
    
    private static String gerarConteudoArquivoTMX(
        String nomeArquivoTileset, 
        int larguraMapa, 
        int alturaMapa, 
        int[][] mapaDeTiles, 
        int quantidadeTilesUnicos
    ) {

        StringBuilder xml = new StringBuilder();
        
        int colunasTileset = (int) Math.ceil(Math.sqrt(quantidadeTilesUnicos));
        int larguraTileset = colunasTileset * TAMANHO_TILE;
        int alturaTileset = (
            (quantidadeTilesUnicos + colunasTileset - 1) / colunasTileset
        ) * TAMANHO_TILE;
        
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<map version=\"1.10\"\n");
        xml.append("     tilewidth=\"").append(TAMANHO_TILE).append("\"\n");
        xml.append("     tileheight=\"").append(TAMANHO_TILE).append("\"\n");
        xml.append("     width=\"").append(larguraMapa).append("\"\n");
        xml.append("     height=\"").append(alturaMapa).append("\">\n");
        
        xml.append("  <tileset firstgid=\"1\"\n");
        xml.append("            tilewidth=\"").append(TAMANHO_TILE).append("\"\n");
        xml.append("            tileheight=\"").append(TAMANHO_TILE).append("\"\n");
        xml.append("            tilecount=\"").append(quantidadeTilesUnicos).append("\"\n");
        xml.append("            columns=\"").append(colunasTileset).append("\">\n");
        
        xml.append("    <image source=\"").append(nomeArquivoTileset).append("\"\n");
        xml.append("           width=\"").append(larguraTileset).append("\"\n");
        xml.append("           height=\"").append(alturaTileset).append("\"/>\n");
        xml.append("  </tileset>\n");
        
        xml.append("  <layer name=\"camada\" width=\"").append(larguraMapa).append("\"\n");
        xml.append("          height=\"").append(alturaMapa).append("\">\n");
        xml.append("    <data encoding=\"csv\">\n");
        
        for (int linha = 0; linha < alturaMapa; linha++) {

            for (int coluna = 0; coluna < larguraMapa; coluna++) {

                xml.append(mapaDeTiles[linha][coluna]);

                if (coluna < larguraMapa - 1) {
                    xml.append(",");
                }

            }

            if (linha < alturaMapa - 1) {
                xml.append("\n");
            }

        }
        
        xml.append("\n    </data>\n");
        xml.append("  </layer>\n");
        xml.append("</map>");
        
        return xml.toString();
    }

}
