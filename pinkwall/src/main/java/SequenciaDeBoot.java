
import javasnes.boot.Boot;
import javasnes.instruction.SnesInstruction;
import javasnes.instruction.SnesRawInstruction;
import javasnes.output.SnesOutput;
import javasnes.sneslib.SnesSound;
import javasnes.sneslib.SnesUtilities;
import javasnes.util.operators.assign.OperatorAssign;
import javasnes.util.operators.logical.OperatorEquals;
import javasnes.util.operators.ternary.OperatorTernary;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU32;
import javasnes.util.types.vars.scalar.sound.SnesBrrSample;

public class SequenciaDeBoot {

    public static Boot sequenciaDeBoot() {

        Boot boot = new Boot();

        Boot.SnesBootCommand[] passosCarregarSPC = new Boot.SnesBootCommand[8];

        carregarMusica(passosCarregarSPC);

        boot.betweenSPCVRAMLoadCommands = passosCarregarSPC;

        Boot.SnesBootCommand[] passosCarregarSRAM = new Boot.SnesBootCommand[2];

        carregarSRAM(passosCarregarSRAM);

        boot.postBootCommands = passosCarregarSRAM;

        Boot.SnesBootCommand[] passosInicializacao = new Boot.SnesBootCommand[18];

        passosInicializacao[0] = new Boot.SnesBootCommand(SnesOutput.dmaClearVram());

        carregarTexto(passosInicializacao);
        carregarMapa(passosInicializacao);
        carregarPink(passosInicializacao);
        carregarTijolos(passosInicializacao);

        boot.postLogoCommands = passosInicializacao;


        // Atualiza o codigo fonte da sequencia de boot
        boot.getSourceCode();

        return boot;
    }

    public static void carregarMusica(Boot.SnesBootCommand[] comandosBoot) {

        SnesInstruction[] comandos = new SnesInstruction[8];

        comandos[0] = SnesSound.spcAllocateSoundRegion(60);
        comandos[1] = SnesSound.spcSetBank("&SOUNDBANK__");
        comandos[2] = SnesSound.spcLoad("MOD_POLLEN8");
        comandos[3] = SnesSound.spcPlay(0);
        comandos[4] = new SnesRawInstruction(
            new SnesBrrSample("losesound").sourceCode + "WaitForVBlank();"
        );
        comandos[5] = new SnesRawInstruction(
            new SnesBrrSample("movesound").sourceCode + "WaitForVBlank();"
        );
        comandos[6] = SnesSound.spcSetSoundEntry(
                15, 15, 2, "(&losebrrsound_end - &losebrrsound)", "&losebrrsound", "&losesound"
        );
        comandos[7] = SnesSound.spcSetSoundEntry(
                15, 15, 5, "(&movebrrsound_end - &movebrrsound)", "&movebrrsound", "&movesound"
        );

        for (int i = 0; i < comandos.length; i++) {
            comandosBoot[i] = new Boot.SnesBootCommand(comandos[i]);
        }

    }

    public static void carregarSRAM(Boot.SnesBootCommand[] comandosBoot) {

        SnesInstruction[] comandos = new SnesInstruction[2];

        comandos[0] = SnesUtilities.consoleLoadSram("(u8* )&HiScore", "4");

        comandos[1] = new OperatorAssign(
                "HiScore",
                new OperatorTernary(
                        new OperatorEquals("HiScore % 100", "0"),
                        new SnesU32("HiScore"), new SnesU32("(100000")
                ).getSourceCode()
        );

        for (int i = 0; i < comandos.length; i++) {
            comandosBoot[i] = new Boot.SnesBootCommand(comandos[i]);
        }

    }

    public static void carregarTexto(Boot.SnesBootCommand[] comandosBoot) {

        SnesInstruction[] comandos = new SnesInstruction[4];

        comandos[0] = SnesOutput.consoleSetTextGfxPtr("0x3000");
        comandos[1] = SnesOutput.consoleSetTextMapPtr("0x6800");
        comandos[2] = SnesOutput.consoleSetTextOffset("0x0100");
        comandos[3] = SnesOutput.consoleInitText(1, 16 * 2, "&tilesfont", "&palfont");

        for (int i = 1; i < comandos.length + 1; i++) {
            comandosBoot[i] = new Boot.SnesBootCommand(comandos[i - 1]);
        }

    }

    public static void carregarMapa(Boot.SnesBootCommand[] comandosBoot) {

        SnesInstruction[] comandos = new SnesInstruction[9];

        comandos[0] = SnesOutput.setScreenOff();

        comandos[1] = SnesOutput.bgInitTileSet(
                1, "&bgtiles4", "&bgpalette4", "0",
                "(&bgtiles4_end - &bgtiles4)",
                "(&bgpalette4_end - &bgpalette4)",
                "BG_16COLORS", "0x4000"
        );
        comandos[2] = SnesOutput.bgInitMapSet(
                1, "&bgmap4", "(&bgmap4_end - &bgmap4)",
                "SC_64x64", "0x1000"
        );

        comandos[3] = SnesOutput.bgSetGfxPtr(0, "0x2000");
        comandos[4] = SnesOutput.bgSetMapPtr(0, "0x6800, SC_32x32");

        comandos[5] = SnesOutput.setMode("BG_MODE1", 0);
        comandos[6] = SnesOutput.bgSetDisable(2);

        comandos[7] = SnesOutput.setScreenOn();
        comandos[8] = SnesOutput.waitForVblank();

        for (int i = 5; i < comandos.length + 5; i++) {
            comandosBoot[i] = new Boot.SnesBootCommand(comandos[i - 5]);
        }

    }

    public static void carregarPink(Boot.SnesBootCommand[] comandosBoot) {

        SnesInstruction[] comandos = new SnesInstruction[2];

        comandos[0] = SnesOutput.oamInitGfxSet(
                "&tilespink", "(&tilespink_end - &tilespink)",
                "&palpink", "(&palpink_end - &palpink)",
                "2", "0x6000", SnesOutput.ObjSize.OBJ_SIZE32_L64
        );
        comandos[1] = SnesOutput.oamSetEx(
                0, SnesOutput.ObjState.OBJ_SMALL, SnesOutput.ObjState.OBJ_HIDE
        );

        for (int i = 14; i < comandos.length + 14; i++) {
            comandosBoot[i] = new Boot.SnesBootCommand(comandos[i - 14]);
        }

    }

    public static void carregarTijolos(Boot.SnesBootCommand[] comandosBoot) {

        SnesInstruction[] comandos = new SnesInstruction[2];

        int enderecoBase = 0x5800;
        int vramOffset = enderecoBase;
        Integer indiceTile = vramOffset >> 5;

        comandos[0] = SnesOutput.oamInitGfxSet(
                "&tilestijolos", "(&tilestijolos_end - &tilestijolos)",
                "&paltijolos", "(&paltijolos_end - &paltijolos)",
                "3", indiceTile.toString(), SnesOutput.ObjSize.OBJ_SIZE8_L16
        );

        comandos[1] = SnesOutput.oamSetEx(
                1, SnesOutput.ObjState.OBJ_SMALL, SnesOutput.ObjState.OBJ_HIDE
        );

        for (int i = 16; i < comandos.length + 16; i++) {
            comandosBoot[i] = new Boot.SnesBootCommand(comandos[i - 16]);
        }

    }

}
