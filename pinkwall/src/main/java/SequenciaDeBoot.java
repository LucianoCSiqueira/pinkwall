
import javasnes.boot.Boot;
import javasnes.instruction.SnesInstruction;
import javasnes.instruction.SnesRawInstruction;
import javasnes.output.SnesOutput;
import javasnes.sneslib.SnesSound;
import javasnes.sneslib.SnesUtilities;
import javasnes.util.operators.assign.OperatorAssign;
import javasnes.util.operators.logical.OperatorAnd;
import javasnes.util.operators.logical.OperatorEquals;
import javasnes.util.operators.logical.OperatorNot;
import javasnes.util.operators.logical.OperatorSmaller;
import javasnes.util.operators.ternary.OperatorTernary;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU32;

public class SequenciaDeBoot {

    /**
     * Define a sequencia de boot do jogo.
     *
     * A sequencia de boot e responsavel por carregar a memoria do jogo, definir
     * os passos que serao executados na inicializacao do jogo, carregar a
     * musica de fundo, carregar o texto que sera exibido na tela, carregar o
     * mapa do jogo e carregar o personagem principal do jogo.
     *
     * @return O objeto Boot que representa a sequencia de boot do jogo.
     */
    public static Boot sequenciaDeBoot() {

        Boot boot = new Boot();

        Boot.SnesBootCommand[] passosCarregarSPC = new Boot.SnesBootCommand[5];

        carregarMusica(passosCarregarSPC);

        boot.betweenSPCVRAMLoadCommands = passosCarregarSPC;

        Boot.SnesBootCommand[] passosCarregarSRAM = new Boot.SnesBootCommand[2];

        carregarSRAM(passosCarregarSRAM);

        boot.postBootCommands = passosCarregarSRAM;

        Boot.SnesBootCommand[] passosInicializacao = new Boot.SnesBootCommand[17];

        passosInicializacao[0] = new Boot.SnesBootCommand(SnesOutput.dmaClearVram());

        carregarTexto(passosInicializacao);
        carregarMapa(passosInicializacao);
        carregarPink(passosInicializacao);

        boot.postLogoCommands = passosInicializacao;

        // Atualiza o codigo fonte da sequencia de boot
        boot.getSourceCode();

        return boot;
    }

    /**
     * Carrega as instrucoes necessarias para tocar a musica durante a sequencia
     * de boot do jogo.
     *
     * As instrucoes sao as seguintes:
     *
     * Reserva 60 blocos de 256 bytes de memoria de VRAM para a musica Define o
     * banco de sons como sendo o arquivo SOUNDBANK__ Carrega a musica
     * MOD_POLLEN8 Toca a musica Define o volume da musica como sendo 50
     *
     * @param comandosBoot Os comandos a serem executados pelo SPC.
     */
    public static void carregarMusica(Boot.SnesBootCommand[] comandosBoot) {

        SnesInstruction[] comandos = new SnesInstruction[5];

        comandos[0] = SnesSound.spcAllocateSoundRegion(60);
        comandos[1] = SnesSound.spcSetBank("&SOUNDBANK__");
        comandos[2] = SnesSound.spcLoad("MOD_POLLEN8");
        comandos[3] = SnesSound.spcPlay(0);
        comandos[4] = new SnesRawInstruction("spcSetModuleVolume(50);");

        for (int i = 0; i < comandos.length; i++) {
            comandosBoot[i] = new Boot.SnesBootCommand(comandos[i]);
        }

    }

    /**
     * Carrega as informacoes necessarias para o SPC processar a memoria de SRAM
     * do jogo.
     *
     * A primeira instrucao carrega a variavel HiScore na memoria de SRAM a
     * partir da posicao 0x1000. A segunda define que HiScore sera atualizado
     * somente se a variavel for menor ou igual a 10000.
     *
     * @param comandosBoot Os comandos a serem executados pelo SPC.
     */
    public static void carregarSRAM(Boot.SnesBootCommand[] comandosBoot) {

        SnesInstruction[] comandos = new SnesInstruction[2];

        comandos[0] = SnesUtilities.consoleLoadSram("(u8* )&HiScore", "4");

        comandos[1] = new OperatorAssign(
                "HiScore",
                new OperatorTernary(
                        new OperatorAnd(
                                new OperatorEquals("HiScore % 100", "0").getSourceCode(),
                                new OperatorNot(
                                        new OperatorSmaller("HiScore", "10000").getSourceCode()
                                ).getSourceCode()
                        ),
                        new SnesU32("HiScore"), new SnesU32("(100000")
                ).getSourceCode()
        );

        for (int i = 0; i < comandos.length; i++) {
            comandosBoot[i] = new Boot.SnesBootCommand(comandos[i]);
        }

    }

    /**
     * Carrega as informa es necessarias para o SPC processar o texto do jogo.
     *
     * A primeira instrucao define a posicao da memoria de VRAM onde sera
     * carregado o tileset do texto. A segunda define a posicao da memoria de
     * VRAM onde sera carregado o mapa do texto. A terceira define a posicao da
     * memoria de VRAM onde sera carregado o offset do texto. A quarta instrucao
     * inicializa o texto com as caracteristicas definidas.
     *
     * @param comandosBoot Os comandos a serem executados pelo SPC.
     */
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

    /**
     * Carrega as informa es necessarias para o SPC processar a mapa do
     * PinkWall.
     *
     * A primeira instrucao desliga a tela, a segunda define as caracteristicas
     * do tileset e a terceira define as caracteristicas da mapa. A quarta
     * define a posicao inicial da mapa no OAM, a quinta define a posicao
     * inicial da mapa no OAM, a sexta define a cor de fundo da mapa e a setima
     * define a visibilidade da mapa.
     *
     * @param comandosBoot Os comandos a serem executados pelo SPC.
     */
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

    /**
     * Carrega as informa es necessarias para o SPC processar as imagens de
     * Pink.
     *
     * A primeira instrucao carrega as imagens no OAM, a segunda define a
     * posicao inicial de Pink e a terceira define a visibilidade de Pink.
     *
     * @param comandosBoot Os comandos a serem executados pelo SPC.
     */
    public static void carregarPink(Boot.SnesBootCommand[] comandosBoot) {

        SnesInstruction[] comandos = new SnesInstruction[3];

        comandos[0] = SnesOutput.oamInitGfxSet(
                "&tilespink", "(&tilespink_end - &tilespink)",
                "&palpink", "(&palpink_end - &palpink)",
                "2", "0x6000", SnesOutput.ObjSize.OBJ_SIZE32_L64
        );

        comandos[1] = SnesOutput.oamSet(0, "0", "-16", "0", "0", "0", "0", "0");

        comandos[2] = SnesOutput.oamSetEx(
                0, SnesOutput.ObjState.OBJ_SMALL, SnesOutput.ObjState.OBJ_HIDE
        );

        for (int i = 14; i < comandos.length + 14; i++) {
            comandosBoot[i] = new Boot.SnesBootCommand(comandos[i - 14]);
        }

    }

}
