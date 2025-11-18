import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javasnes.App;
import javasnes.boot.Boot;
import javasnes.data.Data;
import javasnes.hdr.MemoryMapping;
import javasnes.instruction.SnesInstruction;
import javasnes.makefile.Make;
import javasnes.output.SnesOutput;
import javasnes.util.structures.SnesLoadExtern;
import javasnes.util.types.AppData;
import javasnes.util.types.Processor;
import javasnes.util.types.SnesProcess;
import javasnes.util.types.vars.scalar.data.SnesChar;
import javasnes.util.types.vars.scalar.data.SnesVoid;

public class Main {

    final static SnesVoid VOID = new SnesVoid();
    final static SnesChar CHAR = new SnesChar("char");

    public static void main(String[] args) throws Exception {

        App.Builder main = Config.generateApp();

        HashMap<String, String> memMapConfig = new HashMap<>();
        memMapConfig.put("name", "Javasnes template    ");
        MemoryMapping memMap = Config.generateMemoryMapping(memMapConfig);
        
        main.setMemoryMapping(memMap);

        AppData appData = Config.generateAppData();
        appData.registerData(new Data("tilfont", "pvsneslibfont.pic", false), (byte) 2);
        appData.registerData(new Data("palfont", "pvsneslibfont.pal", false), (byte) 2);

        main.setAppData(appData);

        Boot boot = Config.generateBoot();

        main.setBoot(boot);

        SnesInstruction[] globalDefs = new SnesInstruction[1];
        String[] loadExtern = {"tilfont", "palfont"};
        globalDefs[0] = new SnesLoadExtern(loadExtern, CHAR);

        main.setGlobalInstructions(globalDefs);

        Processor processor = new Processor();
        SnesProcess[] processes = new SnesProcess[1];
        processes[0] = printHelloWorld();
        processor.addProcess(processes[0], null);
        
        main.setProcessor(processor);
        main.setSnesProcesses(processes);

        Make makefile = Config.generateMakefile();
        makefile.setRomName("javasnes_main");
        Config.addMakeRules(makefile);

        main.setMakefile(makefile);

        Config.build(main);

    }

    public static SnesProcess printHelloWorld() {

        SnesInstruction[] comands = new SnesInstruction[1];

        comands[0] = SnesOutput.consoleDrawText(3, 10, "Hello World from JavaSnes!", null);

        return new SnesProcess(
            "print_hello_world",
            (byte) 0, comands, VOID
        );

    }
    
}

class Config {

    public static App.Builder generateApp() {
        return new App.Builder();
    }

    public static MemoryMapping generateMemoryMapping(Map<String, String> config) {

        MemoryMapping memMap = new MemoryMapping(config);
        return memMap;

    }

    public static AppData generateAppData() {
        return new AppData();
    }

    public static Boot generateBoot() {

        Boot boot = new Boot(postLogoCommands());
        return boot;
        
    }

    private static Map<String, Map<String, String[]>> postLogoCommands() {

        Map<String, Map<String, String[]>> boot = new HashMap<>();

        boot.put("postLogoCommands", new LinkedHashMap<>());

        boot.get("postLogoCommands")
            .put("setScreenOff", null);

        boot.get("postLogoCommands")
            .put("consoleSetTextMapPtr", new String[] { "0x6800" });

        boot.get("postLogoCommands")
            .put("consoleSetTextGfxPtr", new String[] { "0x3000" });

        boot.get("postLogoCommands")
            .put("consoleSetTextOffset", new String[] { "0x0100" });

        boot.get("postLogoCommands")
            .put("consoleInitText", new String[] { 
                "0", "16 * 2", "&tilfont", "&palfont" 
            });
        
        boot.get("postLogoCommands")
            .put("bgSetGfxPtr", new String[] { 
                "0", "0x2000"
            });
        
        boot.get("postLogoCommands")
            .put("bgSetMapPtr", new String[] { 
                "0", "0x6800", "SC_32x32"
            });

        boot.get("postLogoCommands")
            .put("setScreenOn", null);

        return boot;

    }

    public static Make generateMakefile() {

        return new Make();

    }

    public static void addMakeRules(Make makefile) {

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

    }

    public static void build(App.Builder app) throws Exception {

        Path actualPath = Paths.get(
            Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()
        ).normalize().toAbsolutePath().getParent();

        Path dataPath = actualPath.resolve("data").resolve("pvsneslibfont.png");
        Path ouptutPath = actualPath.resolve("output");
        
        cleanBuild(ouptutPath);
        
        app.addDataToCopy(dataPath.toString());
        app.setDestination(ouptutPath.toString());

        app.build();

    }

    private static void cleanBuild(Path directory) throws IOException {

        if (Files.exists(directory)) {
            
            Files.walk(directory)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
            
            Files.createDirectories(directory);

        }

    }

}
