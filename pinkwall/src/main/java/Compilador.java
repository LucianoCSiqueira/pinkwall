import java.nio.file.Path;
import java.nio.file.Paths;

import javasnes.App;
import javasnes.makefile.Make;

public class Compilador {

    public static Make passosCompilacao() {

        Make makefile = new Make();

        Make.MakeRule textFont = new Make.MakeRule(
            "pvsneslibfont.pic",
            "pvsneslibfont.png",
            "$(GFXCONV) -s 8 -o 16 -u 16 -p -e 0 -i $<"
        );

        Make.MakeRule bitmaps = new Make.MakeRule(
            "bitmaps",
            "pvsneslibfont.pic pvsneslibfont.pal",
            ""
        );

        makefile.addRule(textFont);
        makefile.addRule(bitmaps);
        makefile.addPhonyTarget("bitmaps");

        makefile.getRule("all").setPrerequisites(
            makefile.getRule("all").getPrerequisites() + " bitmaps $(ROMNAME).sfc"
        );

        return makefile;

    }

    public static void compilarROM(App.Builder app) throws Exception {

        app.setMakefile(passosCompilacao());

        Path pastaJAR = Paths.get(
            PinkWall.class.getProtectionDomain().getCodeSource().getLocation().toURI()
        ).normalize().toAbsolutePath().getParent();

        Path fonte = pastaJAR.resolve("data").resolve("fonte.png");
        Path background = pastaJAR.resolve("data").resolve("BG.png");
        Path sprites = pastaJAR.resolve("data").resolve("sprites.png");
        Path tijolo = pastaJAR.resolve("data").resolve("tijolo.png");

        app.addDataToCopy(fonte.toString());
        app.addDataToCopy(background.toString());
        app.addDataToCopy(sprites.toString());
        app.addDataToCopy(tijolo.toString());

        Path pastaROM = pastaJAR.resolve("output");
        app.setDestination(pastaROM.toString());

        app.build();

    }

}
