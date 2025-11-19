import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public final class PaletaCores {

    public Path caminhoImagem;
    public BufferedImage imagem;
    public IndexColorModel modeloCores;
    public byte[] reds, greens, blues;
    
    public PaletaCores(Path caminhoImagem) throws IOException {
        this.caminhoImagem = caminhoImagem;
        this.carregarImagem();
    }
    
    public void carregarImagem() throws IOException {
        this.imagem = ImageIO.read(caminhoImagem.toFile());

        if (this.imagem.getColorModel() instanceof IndexColorModel) {

            this.modeloCores = (IndexColorModel) this.imagem.getColorModel();
            int tamanhoMapaCores = this.modeloCores.getMapSize();
            
            this.reds = new byte[tamanhoMapaCores];
            this.greens = new byte[tamanhoMapaCores];
            this.blues = new byte[tamanhoMapaCores];

            this.modeloCores.getReds(reds);
            this.modeloCores.getGreens(greens);
            this.modeloCores.getBlues(blues);

        } else {

            System.out.println(
                "Imagem não é indexada. Convertendo automaticamente para PNG indexado..."
            );
            this.imagem = converterParaIndexada(this.imagem);
            this.modeloCores = (IndexColorModel) this.imagem.getColorModel();
            
            int tamanhoMapaCores = this.modeloCores.getMapSize();
            this.reds = new byte[tamanhoMapaCores];
            this.greens = new byte[tamanhoMapaCores];
            this.blues = new byte[tamanhoMapaCores];

            this.modeloCores.getReds(reds);
            this.modeloCores.getGreens(greens);
            this.modeloCores.getBlues(blues);
            
            System.out.println(
                "Conversão automática concluída! Paleta com " + tamanhoMapaCores + " cores."
            );

        }
    }
    

    public BufferedImage converterParaIndexada(BufferedImage imagemOriginal) {

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        
        Map<Integer, Integer> coresUnicas = new HashMap<>();
        int[][] pixels = new int[altura][largura];
        
        for (int y = 0; y < altura; y++) {

            for (int x = 0; x < largura; x++) {

                int rgb = imagemOriginal.getRGB(x, y);
                pixels[y][x] = rgb;

                coresUnicas.put(
                    rgb, coresUnicas.getOrDefault(
                        rgb, 0
                    ) + 1
                );

            }

        }

        int numeroCores = Math.min(coresUnicas.size(), 256);

        byte[] r = new byte[numeroCores];
        byte[] g = new byte[numeroCores];
        byte[] b = new byte[numeroCores];

        int index = 0;

        for (int cor : coresUnicas.keySet()) {

            if (index >= numeroCores) {
                break;
            }

            r[index] = (byte) ((cor >> 16) & 0xFF);
            g[index] = (byte) ((cor >> 8) & 0xFF);
            b[index] = (byte) (cor & 0xFF);

            index++;

        }

        while (index < numeroCores) {
            r[index] = 0;
            g[index] = 0;
            b[index] = 0;
            index++;
        }

        IndexColorModel novoModeloCor = new IndexColorModel(8, numeroCores, r, g, b);

        BufferedImage imagemIndexada = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_INDEXED, novoModeloCor);

        for (int y = 0; y < altura; y++) {

            for (int x = 0; x < largura; x++) {

                int rgbOriginal = pixels[y][x];
                int melhorIndice = encontrarMelhorIndiceCor(
                    rgbOriginal, r, g, b, numeroCores
                );
                imagemIndexada.setRGB(x, y, melhorIndice);

            }

        }
        
        return imagemIndexada;
    }
    

    public int encontrarMelhorIndiceCor(
        int rgb, byte[] r, byte[] g, byte[] b, int numeroCores
    ) {

        int rOrig = (rgb >> 16) & 0xFF;
        int gOrig = (rgb >> 8) & 0xFF;
        int bOrig = rgb & 0xFF;
        
        int melhorIndice = 0;
        int menorDistancia = Integer.MAX_VALUE;
        
        for (int i = 0; i < numeroCores; i++) {

            int distancia = calcularDistanciaCor(
                rOrig, gOrig, bOrig,
                r[i] & 0xFF, g[i] & 0xFF, b[i] & 0xFF
            );
            
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                melhorIndice = i;
            }

        }
        
        return melhorIndice;

    }

    public int calcularDistanciaCor(int r1, int g1, int b1, int r2, int g2, int b2) {

        int dr = r1 - r2;
        int dg = g1 - g2;
        int db = b1 - b2;

        return dr * dr + dg * dg + db * db;

    }
    
    public PaletaCores moverCorPaletaPrimeiraPosicao(int indiceDaCor) {

        if (indiceDaCor < 0 || indiceDaCor >= reds.length) {

            throw new IllegalArgumentException("Índice inválido: " + indiceDaCor);

        }
        
        if (indiceDaCor == 0) {

            return this;

        }
        
        byte vermelhoNaPosicao = reds[indiceDaCor];
        byte verdeNaPosicao = greens[indiceDaCor];
        byte azulNaPosicao = blues[indiceDaCor];
        
        for (int i = indiceDaCor; i > 0; i--) {
            reds[i] = reds[i - 1];
            greens[i] = greens[i - 1];
            blues[i] = blues[i - 1];
        }
        
        reds[0] = vermelhoNaPosicao;
        greens[0] = verdeNaPosicao;
        blues[0] = azulNaPosicao;
        
        return this;

    }
    
    public PaletaCores moverCorPaletaPrimeiraPosicao(int r, int g, int b) {

        int indiceDaCor = procurarIndiceDaCor(r, g, b);

        if (indiceDaCor != -1) {

            return moverCorPaletaPrimeiraPosicao(indiceDaCor);

        } else {

            System.out.println(
                "Cor RGB(" + r + "," + g + "," + b + ") não encontrada na paleta"
            );

            return this;
        }

    }
    
    public int procurarIndiceDaCor(int r, int g, int b) {

        for (int i = 0; i < reds.length; i++) {

            if ((reds[i] & 0xFF) == r && 
                (greens[i] & 0xFF) == g && 
                (blues[i] & 0xFF) == b) {
                return i;
            }

        }

        return -1;

    }
    
    public PaletaCores imprimirPaleta() {

        System.out.println("Paleta de cores (" + reds.length + " cores):");

        for (int i = 0; i < reds.length; i++) {

            System.out.printf("Índice %3d: RGB(%3d, %3d, %3d)%n", 
                i, reds[i] & 0xFF, greens[i] & 0xFF, blues[i] & 0xFF);

        }

        return this;

    }
    
    public void salvar(Path saida) throws IOException {

        IndexColorModel novoModeloCor = new IndexColorModel(
            8, reds.length, this.reds, this.greens, this.blues
        );
        
        BufferedImage novaImagem = new BufferedImage(
            novoModeloCor,
            this.imagem.getRaster(),
            false,
            null
        );
        
        String formatoArquivo = obterExtensaoArquivo(saida);
        ImageIO.write(novaImagem, formatoArquivo, saida.toFile());
        
        System.out.println("Imagem salva: " + saida);

    }
    
    public void save(Path saida) throws IOException {

        salvar(saida);

    }
    
    public String obterExtensaoArquivo(Path caminho) {

        String nomeArquivo = caminho.getFileName().toString();
        int ultimoPonto = nomeArquivo.lastIndexOf('.');
        
        if (ultimoPonto > 0 && ultimoPonto < nomeArquivo.length() - 1) {

            return nomeArquivo.substring(ultimoPonto + 1).toLowerCase();

        }
        
        return "png";

    }
    
    public String pegarTipoArquivo(Path path) {

        return obterExtensaoArquivo(path);

    }
    
    public static boolean ehImagemIndexada(Path caminhoImagem) throws IOException {

        BufferedImage img = ImageIO.read(caminhoImagem.toFile());
        return img.getColorModel() instanceof IndexColorModel;

    }
    
    public static void mostrarInfoPaleta(Path caminhoImagem) throws IOException {
        BufferedImage img = ImageIO.read(caminhoImagem.toFile());
        
        if (img.getColorModel() instanceof IndexColorModel) {

            IndexColorModel modelo = (IndexColorModel) img.getColorModel();
            int tamanhoPaleta = modelo.getMapSize();
            System.out.println("Imagem: " + caminhoImagem.getFileName());
            System.out.println("Tamanho da paleta: " + tamanhoPaleta + " cores");
            System.out.println("Modo de cor: Indexado (P)");

        } else {

            System.out.println("Imagem: " + caminhoImagem.getFileName());
            System.out.println("Modo de cor: " + img.getColorModel().getClass().getSimpleName());
            System.out.println("Esta imagem NÃO é indexada! Será convertida automaticamente.");

        }

    }
    

    public static void converterESalvarComoIndexada(
        Path entrada, Path saida
    ) throws IOException {

        BufferedImage imagemOriginal = ImageIO.read(entrada.toFile());
        
        if (imagemOriginal.getColorModel() instanceof IndexColorModel) {

            ImageIO.write(imagemOriginal, "PNG", saida.toFile());
            System.out.println("Imagem já é indexada, copiada: " + saida);

        } else {

            PaletaCores conversor = new PaletaCores(entrada);
            conversor.salvar(saida);
            System.out.println("Imagem convertida para indexada: " + saida);

        }

    }

}
