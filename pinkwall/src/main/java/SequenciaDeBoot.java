import javasnes.boot.Boot;
import javasnes.instruction.SnesInstruction;
import javasnes.output.SnesOutput;

public class SequenciaDeBoot {

    public static Boot sequenciaDeBoot() {

        Boot boot = new Boot();

        Boot.SnesBootCommand[] passosInicializacao = new Boot.SnesBootCommand[8];
        carregarMapa(passosInicializacao);

        return boot;

    }

    public static void carregarMapa(Boot.SnesBootCommand[] comandosBoot) {

        SnesInstruction[] comandosMapa = new SnesInstruction[8];

        comandosMapa[0] = SnesOutput.setScreenOff();
        comandosMapa[1] = SnesOutput.bgSetDisable(0);
        comandosMapa[2] = SnesOutput.dmaClearVram();
        comandosMapa[3] = SnesOutput.bgInitTileSetLz(
            1, "&bgtiles", "&bgpalette", "0", "(&bgpalette_end - &bgpalette)",
            "BG_16COLORS", "0x4000"
        );
        comandosMapa[4] = SnesOutput.bgInitMapSet(
            1, "&bgmap", "(&bgmap_end - &bgmap)", "SC_32x32", "0x0000"
        );
        comandosMapa[5] = SnesOutput.bgSetEnable(1);
        comandosMapa[6] = SnesOutput.setScreenOn();
        comandosMapa[7] = SnesOutput.waitForVblank();

        for (int i = 0; i < comandosMapa.length; i++) {
            comandosBoot[i] = new Boot.SnesBootCommand(comandosMapa[i]);
        }

    }
    
}
