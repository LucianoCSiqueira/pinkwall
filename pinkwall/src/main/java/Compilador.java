
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import javasnes.App;
import javasnes.hdr.MemoryMapping;
import javasnes.makefile.Make;

public class Compilador {

    /**
     * Configura o mapa de memoria do jogo.
     *
     * @return O mapa de memoria configurado.
     */
    public static MemoryMapping configurarMapaMemoria() {

        // define o mapa da memoria
        HashMap<String, String> memMapConfig = new HashMap<>();

        // Define o nome do jogo (tem que ter 21 caracteres)
        memMapConfig.put("name", "Pink Wall - The Game ");

        // configura o mapa da memoria
        MemoryMapping memMap = new MemoryMapping(memMapConfig);

        return memMap;

    }

    /**
     * Define os passos do make file.
     *
     * @return Os passos do make file.
     */
    public static Make passosCompilacao() {

        Make makefile = new Make();

        //compila os dados
        Make.MakeRule textFont = new Make.MakeRule(
                "fonte.pic",
                "fonte.bmp",
                "$(GFXCONV) -s 8 -u 16 -o 16 -p -R -t bmp -i $<"
        );

        Make.MakeRule sprites = new Make.MakeRule(
                "sprites.pic",
                "sprites.bmp",
                "$(GFXCONV) -s 32 -u 16 -o 16 -p -t bmp -i $<"
        );

        Make.MakeRule bg1 = new Make.MakeRule(
                "BG_1.pic",
                "BG_1.bmp",
                "$(GFXCONV) -s 8 -u 16 -o 16 -p -m -t bmp -i $<"
        );

        Make.MakeRule bg2 = new Make.MakeRule(
                "BG_2.pic",
                "BG_2.bmp",
                "$(GFXCONV) -s 8 -u 16 -o 16 -p -m -t bmp -i $<"
        );

        Make.MakeRule bg3 = new Make.MakeRule(
                "BG_3.pic",
                "BG_3.bmp",
                "$(GFXCONV) -s 8 -u 16 -o 16 -p -m -t bmp -i $<"
        );

        Make.MakeRule bg4 = new Make.MakeRule(
                "BG_4.pic",
                "BG_4.bmp",
                "$(GFXCONV) -s 8 -u 16 -o 16 -p -m -t bmp -i $<"
        );

        Make.MakeRule bg5 = new Make.MakeRule(
                "BG_5.pic",
                "BG_5.bmp",
                "$(GFXCONV) -s 8 -u 16 -o 16 -p -m -t bmp -i $<"
        );

        Make.MakeRule bitmaps = new Make.MakeRule(
                "bitmaps",
                "fonte.pic BG_1.pic BG_2.pic BG_3.pic BG_4.pic BG_5.pic sprites.pic",
                ""
        );

        // adiciona as regras do make file
        makefile.addRule(textFont);
        makefile.addRule(sprites);

        makefile.addRule(bg1);
        makefile.addRule(bg2);
        makefile.addRule(bg3);
        makefile.addRule(bg4);
        makefile.addRule(bg5);

        makefile.addRule(bitmaps);
        makefile.addPhonyTarget("bitmaps");

        makefile.getRule("all").setPrerequisites(
                makefile.getRule("all").getPrerequisites() + " bitmaps $(ROMNAME).sfc"
        );

        // Define o nome da Rom no makefile
        makefile.setRomName("PinkWall");

        makefile.addHeaderLine("AUDIOFILES := pollen8.it");
        makefile.addHeaderLine("export SOUNDBANK := soundbank");
        makefile.addVariable("SMCONVFLAGS", "-s -o $(SOUNDBANK) -V -b 5");

        return makefile;

    }

    /**
     * Compila a ROM com base no projeto atual.
     *
     * A compilacao da ROM copia os arquivos necessarios para a pasta de saida
     * especificada e compila a ROM com base nesses arquivos.
     *
     * @param app O builder da aplicacao.
     * @throws Exception Caso ocorra um erro durante a compilacao.
     */
    public static void compilarROM(App.Builder app) throws Exception {

        // configura o mapa da memoria
        app.setMemoryMapping(configurarMapaMemoria());

        // define os passos do make file
        app.setMakefile(passosCompilacao());

        // pasta do arquivo apos compilar em jar
        Path pastaJAR = Paths.get(
                PinkWall.class.getProtectionDomain().getCodeSource().getLocation().toURI()
        ).normalize().toAbsolutePath().getParent();

        // arquivos em Path pra copiar pra ROM
        Path fonte = pastaJAR.resolve("data").resolve("fonte.bmp");
        Path sprites = pastaJAR.resolve("data").resolve("sprites.bmp");

        app.addDataToCopy(fonte.toString());
        app.addDataToCopy(sprites.toString());

        Path bg1 = pastaJAR.resolve("data").resolve("BG_1.bmp");
        Path bg2 = pastaJAR.resolve("data").resolve("BG_2.bmp");
        Path bg3 = pastaJAR.resolve("data").resolve("BG_3.bmp");
        Path bg4 = pastaJAR.resolve("data").resolve("BG_4.bmp");
        Path bg5 = pastaJAR.resolve("data").resolve("BG_5.bmp");

        app.addDataToCopy(bg1.toString());
        app.addDataToCopy(bg2.toString());
        app.addDataToCopy(bg3.toString());
        app.addDataToCopy(bg4.toString());
        app.addDataToCopy(bg5.toString());

        Path pollen8 = pastaJAR.resolve("data").resolve("pollen8.it");

        app.addDataToCopy(pollen8.toString());

        // Pasta de saida
        Path pastaROM = pastaJAR.resolve("output");
        app.setDestination(pastaROM.toString());

        // Compilar ROM
        app.build();

    }

}
