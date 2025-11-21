
import javasnes.instruction.SnesInstruction;
import javasnes.output.SnesOutput;
import javasnes.util.types.Processor;
import javasnes.util.types.SnesProcess;
import javasnes.util.types.vars.scalar.data.SnesVoid;

public class Processador {

    final static SnesVoid VOID = new SnesVoid();

    public static Processor processor = new Processor();
    public static SnesProcess[] processes;

    public static void configurarProcessador() {

        processes = new SnesProcess[1];
        processes[0] = printHelloWorld();

        processor.addProcess(processes[0], null);

    }

    public static SnesProcess printHelloWorld() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        instrucoes[0] = SnesOutput.consoleDrawText(8, 7, "Hello World!", null);

        return new SnesProcess("PrintHelloWorld", instrucoes, VOID);

    }
    
}
