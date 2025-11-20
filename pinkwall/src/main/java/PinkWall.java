import java.util.HashMap;

import javasnes.App;
import javasnes.data.Data;
import javasnes.hdr.MemoryMapping;
import javasnes.instruction.SnesInstruction;
import javasnes.output.SnesOutput;
import javasnes.util.structures.SnesLoadExtern;
import javasnes.util.types.AppData;
import javasnes.util.types.Processor;
import javasnes.util.types.SnesProcess;
import javasnes.util.types.vars.scalar.data.SnesChar;
import javasnes.util.types.vars.scalar.data.SnesVoid;

public class PinkWall {

    final static SnesVoid VOID = new SnesVoid();
    final static SnesChar CHAR = new SnesChar("char");

    public static void main(String[] args) throws Exception {

        App.Builder main = new App.Builder();

        HashMap<String, String> memMapConfig = new HashMap<>();
        memMapConfig.put("name", "Javasnes template    ");
        MemoryMapping memMap = new MemoryMapping(memMapConfig);
        
        main.setMemoryMapping(memMap);

        AppData appData = new AppData();
        appData.registerData(new Data("tilfont", "pvsneslibfont.pic", false), (byte) 2);
        appData.registerData(new Data("palfont", "pvsneslibfont.pal", false), (byte) 2);

        main.setAppData(appData);

        //Boot boot = sequenciaDeBoot();

        //main.setBoot(boot);

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

        Compilador.compilarROM(main);

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

