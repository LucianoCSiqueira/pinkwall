import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import javasnes.App;
import javasnes.hdr.MemoryMapping;
import javasnes.makefile.Make;

public class Compilador {

    public static MemoryMapping configurarMapaMemoria() {

        HashMap<String, String> memMapConfig = new HashMap<>();
        memMapConfig.put("name", "Pink Wall - The Game ");
        MemoryMapping memMap = new MemoryMapping(memMapConfig);
        
        return memMap;

    }

    public static Make passosCompilacao() {

        Make makefile = new Make();

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

        Make.MakeRule tijolo = new Make.MakeRule(
            "tijolo.pic",
            "tijolo.bmp",
            "$(GFXCONV) -s 8 -o 16 -u 16 -p -t bmp -i $<\n"
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
            "fonte.pic BG_1.pic BG_2.pic BG_3.pic BG_4.pic BG_5.pic sprites.pic tijolo.pic",
            ""
        );

        makefile.addRule(textFont);
        makefile.addRule(sprites);
        makefile.addRule(tijolo);

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

        makefile.setRomName("PinkWall");

        return makefile;

    }

    public static void compilarROM(App.Builder app) throws Exception {

        app.setMemoryMapping(configurarMapaMemoria());
        app.setMakefile(passosCompilacao());

        Path pastaJAR = Paths.get(
            PinkWall.class.getProtectionDomain().getCodeSource().getLocation().toURI()
        ).normalize().toAbsolutePath().getParent();

        Path fonte = pastaJAR.resolve("data").resolve("fonte.bmp");
        Path sprites = pastaJAR.resolve("data").resolve("sprites.bmp");
        Path tijolo = pastaJAR.resolve("data").resolve("tijolo.bmp");

        app.addDataToCopy(fonte.toString());
        app.addDataToCopy(sprites.toString());
        app.addDataToCopy(tijolo.toString());

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

        Path pastaROM = pastaJAR.resolve("output");
        app.setDestination(pastaROM.toString());

        app.build();

    }

}
