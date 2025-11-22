
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javasnes.input.SnesInput;
import javasnes.instruction.SnesInstruction;
import javasnes.instruction.SnesRawInstruction;
import javasnes.output.SnesOutput;
import javasnes.sneslib.SnesSound;
import javasnes.sneslib.SnesUtilities;
import javasnes.util.keywords.KeyWords;
import javasnes.util.logic.SnesElseIf;
import javasnes.util.logic.SnesIf;
import javasnes.util.logic.SnesSwitch;
import javasnes.util.operators.SnesOperator;
import javasnes.util.operators.assign.OperatorAssign;
import javasnes.util.operators.binary.OperatorBinAnd;
import javasnes.util.operators.logical.OperatorAnd;
import javasnes.util.operators.logical.OperatorEquals;
import javasnes.util.operators.logical.OperatorGreater;
import javasnes.util.operators.logical.OperatorGreaterOrEqual;
import javasnes.util.operators.logical.OperatorOr;
import javasnes.util.operators.logical.OperatorSmaller;
import javasnes.util.operators.logical.OperatorSmallerOrEqual;
import javasnes.util.operators.math.OperatorAdd;
import javasnes.util.operators.ternary.OperatorTernary;
import javasnes.util.types.Processor;
import javasnes.util.types.SnesProcess;
import javasnes.util.types.vars.scalar.data.SnesVoid;
import javasnes.util.types.vars.scalar.number.signed.SnesS16;
import javasnes.util.types.vars.scalar.number.signed.SnesS32;
import javasnes.util.types.vars.scalar.number.signed.SnesS8;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU16;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU8;

public class Processador {

    final static SnesVoid VOID = new SnesVoid();

    public static Processor processador = new Processor();
    public static SnesProcess[] processos;

    public static void configurarProcessador() {

        processos = new SnesProcess[] {
            printHiScore(),
            limparPrintHiScore(),
            comecarJogo(),
            gameOver(),
            printScoreGameOver(),
            atualizarHiScore(),
            printDadosFase(),
            atualizarLevel(),
            atualizarScore(),
            carregarTijolo(),
            atualizarTijolo(),
            ehParaCarregarPink(),
            carregarPink(),
            atualizarPink(),
            mudarBackground()
        };

        for (SnesProcess processo : processos) {

            if (!processo.name.equals("limparPrintHiScore")) {
                processador.addProcess(processo, null);
            }

        }

    }

    public static SnesProcess comecarJogo() {

        SnesInstruction[] instrucoes = new SnesInstruction[2];

        instrucoes[0] = SnesSound.spcProcess();

        SnesIf seStart = new SnesIf(
                new OperatorAnd(
                        SnesInput.keyStartPressed().getSourceCode(),
                        new OperatorEquals(DadosGlobais.atualBG, new SnesU8("4")).getSourceCode()
                ),
                new ArrayList<>() {
            {
                add(new OperatorAssign(DadosGlobais.mudarBG.name, "1"));
            }
        }
        );

        seStart.generateSourceCode();

        instrucoes[1] = seStart;

        return new SnesProcess("comecarJogo", instrucoes, VOID);

    }

    public static SnesProcess gameOver() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        SnesIf seBGehGameOver = new SnesIf(
                new OperatorEquals(DadosGlobais.atualBG, new SnesU8("3")),
                new ArrayList<>() {
            {
                add(SnesSound.spcPlaySound((byte) 0));
                for (int i = 0; i < 500; i++) {
                    add(SnesOutput.waitForVblank());
                }
                add(new OperatorAssign(DadosGlobais.mudarBG.name, "1"));
            }
        }
        );

        seBGehGameOver.generateSourceCode();

        instrucoes[0] = seBGehGameOver;

        return new SnesProcess("gameOver", instrucoes, VOID);

    }

    public static SnesProcess printHiScore() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        List<SnesInstruction> seBGehStartInstrucoes = new ArrayList<>();

        seBGehStartInstrucoes.add(SnesOutput.consoleDrawText(
                2, 26, "HighScore: %d", ", (int) HiScore"
        ));

        SnesIf seBGehStart = new SnesIf(
                new OperatorEquals(DadosGlobais.atualBG, new SnesU8("4")),
                seBGehStartInstrucoes
        );

        seBGehStart.generateSourceCode();

        instrucoes[0] = seBGehStart;

        return new SnesProcess("printHiScore", instrucoes, VOID);

    }

    public static SnesProcess limparPrintHiScore() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        instrucoes[0] = SnesOutput.consoleDrawText(
                1, 26, "                          ", null
        );

        return new SnesProcess("limparPrintHiScore", instrucoes, VOID);

    }

    public static SnesProcess printScoreGameOver() {

        SnesInstruction[] instrucoes = new SnesInstruction[2];

        List<SnesInstruction> seBGehGameOverInstrucoes = new ArrayList<>();

        seBGehGameOverInstrucoes.add(SnesOutput.consoleDrawText(
                2, 26, "Score: %d", ", (int) Score"
        ));

        SnesIf seBGehGameOver = new SnesIf(
                new OperatorEquals(DadosGlobais.atualBG, new SnesU8("3")),
                seBGehGameOverInstrucoes
        );

        seBGehGameOver.generateSourceCode();

        instrucoes[0] = seBGehGameOver;

        instrucoes[1] = SnesSound.spcPlaySound(0);

        return new SnesProcess("printScoreGameOver", instrucoes, VOID);

    }

    public static SnesProcess atualizarHiScore() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        SnesIf ifMudouHiScore = new SnesIf(
                new OperatorGreaterOrEqual(DadosGlobais.Score, DadosGlobais.HiScore),
                new ArrayList<>() {
            {
                add(new OperatorAssign(DadosGlobais.HiScore.name, DadosGlobais.Score.name));
                add(SnesUtilities.consoleCopySram("(u8*)&Score", "4"));
            }
        }
        );

        ifMudouHiScore.generateSourceCode();

        instrucoes[0] = ifMudouHiScore;

        return new SnesProcess("atualizarHiScore", instrucoes, VOID);

    }

    public static SnesProcess printDadosFase() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        SnesIf seBGehFase = new SnesIf(
                new OperatorSmallerOrEqual(DadosGlobais.atualBG, new SnesU8("2")),
                new ArrayList<>() {{

                    add(SnesOutput.consoleDrawText(
                            20, 4, "Hi: %d", ", (int) HiScore"
                    ));

                    add(SnesOutput.consoleDrawText(
                            20, 6, "Sc: %d", ", (int) Score"
                    ));

                    add(SnesOutput.consoleDrawText(
                            20, 8, "Lv: %d", ", (int) (4 - qtdVidas)"
                    ));

                    add(SnesOutput.consoleDrawText(
                            20, 10, "Lf: %d", ", (int) qtdVidas"
                    ));

                }}
        );

        seBGehFase.generateSourceCode();

        instrucoes[0] = seBGehFase;

        return new SnesProcess("printDadosFase", instrucoes, VOID);

    }

    public static SnesProcess atualizarLevel() {

        SnesInstruction[] instrucoes = new SnesInstruction[2];

        SnesU8 limiteY = new SnesU8("215");

        SnesOperator caiu = new OperatorGreater(
                new SnesS32("yTijolo"), limiteY
        );

        instrucoes[0] = new OperatorAssign(
                DadosGlobais.mudarBG.name,
                new OperatorTernary(
                        caiu,
                        new SnesU8("1"),
                        new SnesU8("0")
                ).getSourceCode()
        );

        instrucoes[1] = new OperatorAssign(
                DadosGlobais.qtdVidas.name,
                new OperatorTernary(
                        caiu,
                        new SnesU8("1"),
                        DadosGlobais.qtdVidas
                ).getSourceCode(),
                '-'
        );

        return new SnesProcess("atualizarLevel", instrucoes, VOID);

    }

    public static SnesProcess atualizarScore() {

        SnesInstruction[] instrucoes = new SnesInstruction[2];

        SnesU8 xPink = new SnesU8("xPink");

        SnesU8 pinkW = new SnesU8("32");
        SnesU8 pinkH = new SnesU8("32");
        SnesU8 brickW = new SnesU8("8");
        SnesU8 brickH = new SnesU8("8");

        SnesS32 xT = new SnesS32("xTijolo");
        SnesS32 yT = new SnesS32("yTijolo");

        String xOverlap = new OperatorAnd(
                new OperatorSmaller(
                        xT, new SnesU8(new OperatorAdd(xPink, pinkW).getSourceCode())
                ).getSourceCode(),
                new OperatorGreater(
                        new SnesU8(new OperatorAdd(xT, brickW).getSourceCode()), xPink
                ).getSourceCode()
        ).getSourceCode();

        String yOverlap = new OperatorAnd(
                new OperatorSmaller(
                        yT, new SnesU8(
                                new OperatorAdd(new SnesU8("192"), pinkH).getSourceCode()
                        )
                ).getSourceCode(),
                new OperatorGreater(
                        new SnesU8(
                                new OperatorAdd(yT, brickH).getSourceCode()
                        ), new SnesU8("192")
                ).getSourceCode()
        ).getSourceCode();

        SnesOperator colisao = new OperatorAnd(xOverlap, yOverlap);

        instrucoes[0] = new OperatorAssign(
                DadosGlobais.Score.name,
                new OperatorTernary(
                        colisao,
                        new SnesU16("1000"),
                        DadosGlobais.Score
                ).getSourceCode(),
                '+'
        );

        instrucoes[1] = new OperatorAssign(
                DadosGlobais.colidiuTijolo.name,
                new OperatorTernary(
                        colisao,
                        new SnesU8("1"),
                        new SnesU8("0")
                ).getSourceCode()
        );

        return new SnesProcess("atualizarScore", instrucoes, VOID);

    }

    public static SnesProcess carregarTijolo() {

        SnesInstruction[] instrucoes = new SnesInstruction[3];

        instrucoes[0] = new OperatorAssign(
                DadosGlobais.yTijolo.name,
                "-16"
        );

        instrucoes[1] = new OperatorAssign(
                DadosGlobais.xTijolo.name,
                "rand() & 200"
        );

        instrucoes[2] = SnesOutput.oamSetEx(
                1,
                SnesOutput.ObjState.OBJ_SMALL,
                new OperatorTernary(
                        new OperatorSmaller(DadosGlobais.atualBG, new SnesU8("3")),
                        SnesOutput.ObjState.OBJ_SHOW,
                        SnesOutput.ObjState.OBJ_HIDE
                ).getSourceCode()
        );

        return new SnesProcess("carregarTijolo", instrucoes, VOID);
    }

    public static SnesProcess atualizarTijolo() {

        SnesInstruction[] instrucoes = new SnesInstruction[4];

        SnesU8 limiteY = new SnesU8("215");

        String caiu = new OperatorGreater(
                new SnesS32("yTijolo"), limiteY
        ).getSourceCode();

        instrucoes[0] = new OperatorAssign(
                DadosGlobais.yTijolo.name,
                new OperatorTernary(
                        new OperatorEquals(
                                new OperatorAnd("snes_vblank_count", "3").getSourceCode(),
                                "0"
                        ),
                        new SnesS16(
                                new OperatorAdd(
                                        DadosGlobais.yTijolo,
                                        new SnesS8("1")
                                ).getSourceCode()
                        ),
                        DadosGlobais.yTijolo
                ).getSourceCode()
        );

        SnesOperator precisaRespawn = new OperatorOr(
                caiu,
                DadosGlobais.colidiuTijolo.name
        );

        instrucoes[1] = new OperatorAssign(
                DadosGlobais.yTijolo.name,
                new OperatorTernary(
                        precisaRespawn,
                        new SnesS8("-16"),
                        DadosGlobais.yTijolo
                ).getSourceCode()
        );

        instrucoes[2] = new OperatorAssign(
                DadosGlobais.xTijolo.name,
                new OperatorTernary(
                        precisaRespawn,
                        new SnesU8(
                                "rand() & 200"
                        ),
                        DadosGlobais.xTijolo
                ).getSourceCode()
        );

        instrucoes[3] = SnesOutput.oamSetEx(
                1,
                SnesOutput.ObjState.OBJ_SMALL,
                new OperatorTernary(
                        new OperatorSmaller(
                                DadosGlobais.atualBG, new SnesU8("3")
                        ),
                        SnesOutput.ObjState.OBJ_SHOW,
                        SnesOutput.ObjState.OBJ_HIDE
                ).getSourceCode()
        );

        return new SnesProcess("atualizarTijolo", instrucoes, VOID);

    }

    public static SnesProcess ehParaCarregarPink() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        instrucoes[0] = new OperatorAssign(DadosGlobais.ehParaCarregarPink.name,
                new OperatorTernary(new OperatorEquals(
                        DadosGlobais.atualBG, new SnesU8("0")
                ), new SnesU8("1"), new SnesU8("0")).getSourceCode()
        );

        return new SnesProcess("EhParaCarregarPink", instrucoes, VOID);

    }

    public static SnesProcess carregarPink() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        SnesElseIf seBGNaoPink = new SnesElseIf(
                new OperatorOr(
                        new OperatorEquals(DadosGlobais.atualBG, new SnesU8("3")).getSourceCode(),
                        new OperatorEquals(DadosGlobais.atualBG, new SnesU8("4")).getSourceCode()
                ), new ArrayList<>() {
            {
                add(SnesOutput.oamSet(
                        0, "100", "192", "3", "0", "0", DadosGlobais.animFrame.name, "2"
                ));
                add(SnesOutput.oamSetEx(
                        0, SnesOutput.ObjState.OBJ_SMALL, SnesOutput.ObjState.OBJ_HIDE
                ));
                add(SnesOutput.oamSetVisible(0, SnesOutput.ObjState.OBJ_HIDE));
            }
        }
        );

        SnesIf seBGehPink = new SnesIf(
                new OperatorEquals(DadosGlobais.atualBG, new SnesU8("1")),
                new ArrayList<>() {
            {
                add(SnesOutput.oamSet(
                        0, "100", "192", "3", "0", "0", DadosGlobais.animFrame.name, "2"
                ));
                add(SnesOutput.oamSetEx(
                        0, SnesOutput.ObjState.OBJ_SMALL, SnesOutput.ObjState.OBJ_SHOW
                ));
                add(SnesOutput.oamSetVisible(0, SnesOutput.ObjState.OBJ_SHOW));
            }
        },
                new ArrayList<>() {
            {
                add(seBGNaoPink);
            }
        }
        );

        seBGehPink.generateSourceCode();

        instrucoes[0] = seBGehPink;

        return new SnesProcess("carregarPink", instrucoes, VOID);

    }

    public static SnesProcess atualizarPink() {

        final SnesU8 MOVE_SPEED = new SnesU8("1");
        final SnesU8 MIN_X = new SnesU8("0");
        final SnesU8 MAX_X = new SnesU8("192");
        final SnesU8 PINK_Y = new SnesU8("192");

        List<SnesInstruction> instrucoes = new ArrayList<>();

        instrucoes.add(new OperatorAssign(
                DadosGlobais.xPink.name,
                new OperatorTernary(
                        SnesInput.keyLeftPressed(),
                        new SnesU8(new OperatorAdd(
                                DadosGlobais.xPink, new SnesS8("-1")
                        ).getSourceCode()),
                        DadosGlobais.xPink
                ).getSourceCode()
        ));

        instrucoes.add(new OperatorAssign(
                DadosGlobais.xPink.name,
                new OperatorTernary(
                        SnesInput.keyRightPressed(),
                        new SnesU8(new OperatorAdd(
                                DadosGlobais.xPink, MOVE_SPEED
                        ).getSourceCode()),
                        DadosGlobais.xPink
                ).getSourceCode()
        ));

        instrucoes.add(new OperatorAssign(
                DadosGlobais.xPink.name,
                new OperatorTernary(
                        new OperatorSmaller(DadosGlobais.xPink, MIN_X),
                        MIN_X,
                        DadosGlobais.xPink
                ).getSourceCode()
        ));

        instrucoes.add(new OperatorAssign(
                DadosGlobais.xPink.name,
                new OperatorTernary(
                        new OperatorGreater(DadosGlobais.xPink, MAX_X),
                        MAX_X,
                        DadosGlobais.xPink
                ).getSourceCode()
        ));

        instrucoes.add(new OperatorAssign(
                DadosGlobais.animFrame.name,
                new OperatorTernary(
                        new OperatorOr(
                                SnesInput.keyLeftPressed().getSourceCode(),
                                SnesInput.keyRightPressed().getSourceCode()
                        ),
                        new SnesU8(
                                new OperatorAdd(
                                        DadosGlobais.animFrame, new SnesS8("1")
                                ).getSourceCode()
                        ),
                        DadosGlobais.animFrame
                ).getSourceCode()
        ));

        instrucoes.add(new OperatorAssign(
                DadosGlobais.animFrame.name,
                new OperatorTernary(
                        new OperatorGreaterOrEqual(DadosGlobais.animFrame, new SnesU8("4")),
                        new SnesU8("0"),
                        DadosGlobais.animFrame
                ).getSourceCode()
        ));

        instrucoes.add(SnesOutput.oamSetEx(
                0,
                SnesOutput.ObjState.OBJ_SMALL,
                new OperatorTernary(
                        SnesInput.keyLeftPressed(),
                        new SnesU8("0"),
                        new SnesU8(SnesOutput.ObjState.OBJ_SHOW)
                ).getSourceCode()
        ));

        instrucoes.add(SnesOutput.oamSet(
                0,
                DadosGlobais.xPink.name,
                PINK_Y.name,
                "3",
                new OperatorTernary(
                        SnesInput.keyLeftPressed(),
                        new SnesU8("animLeft[animFrame]"),
                        new SnesU8("animRight[animFrame]")
                ).getSourceCode(),
                "0",
                DadosGlobais.animFrame.name,
                "2"
        ));

        instrucoes.add(SnesSound.spcPlaySound(1));

        return new SnesProcess("atualizarPink",
                instrucoes,
                VOID
        );
    }

    public static SnesProcess mudarBackground() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        List<SnesInstruction> instrucoesSeEhParaMudarBG = new ArrayList<>();

        instrucoesSeEhParaMudarBG.add(new OperatorAssign(
                DadosGlobais.atualBG.name, new OperatorTernary(
                        new OperatorGreaterOrEqual(DadosGlobais.atualBG, new SnesU8("4")),
                        new SnesU8("0"),
                        new SnesU8(
                                new OperatorAdd(
                                        DadosGlobais.atualBG, new SnesU8("1")
                                ).getSourceCode()
                        )
                ).getSourceCode()
        ));

        SnesSwitch switchBG = new SnesSwitch(
                DadosGlobais.atualBG,
                carregarMapas()
        );

        switchBG.generateSourceCode();

        instrucoesSeEhParaMudarBG.add(switchBG);

        SnesIf ifMudarBG = new SnesIf(
                new OperatorBinAnd(DadosGlobais.mudarBG, DadosGlobais.mudarBG),
                instrucoesSeEhParaMudarBG
        );

        ifMudarBG.generateSourceCode();

        instrucoes[0] = ifMudarBG;

        return new SnesProcess("mudarBackground", instrucoes, VOID);

    }

    public static Map<String, List<SnesInstruction>> carregarMapas() {

        Map<String, List<SnesInstruction>> mapas = new HashMap<>();

        List<SnesInstruction> mapa0 = new ArrayList<>();
        List<SnesInstruction> mapa1 = new ArrayList<>();
        List<SnesInstruction> mapa2 = new ArrayList<>();
        List<SnesInstruction> mapa3 = new ArrayList<>();
        List<SnesInstruction> mapa4 = new ArrayList<>();

        SnesInstruction chamarLimparPrintHiScore = new SnesRawInstruction(
                "limparPrintHiScore();"
        );

        mapa0.add(chamarLimparPrintHiScore);
        mapa0.add(SnesOutput.setScreenOff());
        mapa0.add(new OperatorAssign(DadosGlobais.ehParaCarregarTijolo.name, "1"));
        mapa0.add(SnesOutput.waitForVblank());
        mapa0.add(SnesOutput.bgInitTileSet(
                1, "&bgtiles0", "&bgpalette0", "0",
                "(&bgtiles0_end - &bgtiles0)",
                "(&bgpalette0_end - &bgpalette0)",
                "BG_16COLORS", "0x4000"
        ));
        mapa0.add(SnesOutput.bgInitMapSet(
                1, "&bgmap0", "(&bgmap0_end - &bgmap0)",
                "SC_64x64", "0x1000"
        ));
        for (int i = 0; i < 20; i++) {
            mapa0.add(SnesOutput.waitForVblank());
        }
        mapa0.add(SnesOutput.setScreenOn());
        mapa0.add(KeyWords.snesBreak);

        mapa1.add(chamarLimparPrintHiScore);
        mapa1.add(SnesOutput.setScreenOff());
        mapa1.add(new OperatorAssign(DadosGlobais.ehParaCarregarTijolo.name, "1"));
        mapa1.add(SnesOutput.waitForVblank());
        mapa1.add(SnesOutput.bgInitTileSet(
                1, "&bgtiles1", "&bgpalette1", "0",
                "(&bgtiles1_end - &bgtiles1)",
                "(&bgpalette1_end - &bgpalette1)",
                "BG_16COLORS", "0x4000"
        ));
        mapa1.add(SnesOutput.bgInitMapSet(
                1, "&bgmap1", "(&bgmap1_end - &bgmap1)",
                "SC_64x64", "0x1000"
        ));
        for (int i = 0; i < 20; i++) {
            mapa1.add(SnesOutput.waitForVblank());
        }
        mapa1.add(SnesOutput.setScreenOn());
        mapa1.add(KeyWords.snesBreak);

        mapa2.add(chamarLimparPrintHiScore);
        mapa2.add(SnesOutput.setScreenOff());
        mapa2.add(new OperatorAssign(DadosGlobais.ehParaCarregarTijolo.name, "1"));
        mapa2.add(SnesOutput.waitForVblank());
        mapa2.add(SnesOutput.bgInitTileSet(
                1, "&bgtiles2", "&bgpalette2", "0",
                "(&bgtiles2_end - &bgtiles2)",
                "(&bgpalette2_end - &bgpalette2)",
                "BG_16COLORS", "0x4000"
        ));
        mapa2.add(SnesOutput.bgInitMapSet(
                1, "&bgmap2", "(&bgmap2_end - &bgmap2)",
                "SC_64x64", "0x1000"
        ));
        for (int i = 0; i < 20; i++) {
            mapa2.add(SnesOutput.waitForVblank());
        }
        mapa2.add(SnesOutput.setScreenOn());
        mapa2.add(KeyWords.snesBreak);

        mapa3.add(chamarLimparPrintHiScore);
        mapa3.add(SnesOutput.setScreenOff());
        mapa3.add(new OperatorAssign(DadosGlobais.ehParaCarregarPink.name, "0"));
        mapa3.add(new OperatorAssign(DadosGlobais.ehParaCarregarTijolo.name, "0"));
        mapa3.add(SnesOutput.waitForVblank());
        mapa3.add(SnesOutput.bgInitTileSet(
                1, "&bgtiles3", "&bgpalette3", "0",
                "(&bgtiles3_end - &bgtiles3)",
                "(&bgpalette3_end - &bgpalette3)",
                "BG_16COLORS", "0x4000"
        ));
        mapa3.add(SnesOutput.bgInitMapSet(
                1, "&bgmap3", "(&bgmap3_end - &bgmap3)",
                "SC_64x64", "0x1000"
        ));
        for (int i = 0; i < 20; i++) {
            mapa3.add(SnesOutput.waitForVblank());
        }
        mapa3.add(SnesOutput.setScreenOn());
        mapa3.add(KeyWords.snesBreak);

        mapa4.add(chamarLimparPrintHiScore);
        mapa4.add(SnesOutput.setScreenOff());
        mapa4.add(new OperatorAssign(DadosGlobais.ehParaCarregarPink.name, "0"));
        mapa4.add(new OperatorAssign(DadosGlobais.ehParaCarregarTijolo.name, "0"));
        mapa4.add(SnesOutput.waitForVblank());
        mapa4.add(SnesOutput.bgInitTileSet(
                1, "&bgtiles4", "&bgpalette4", "0",
                "(&bgtiles4_end - &bgtiles4)",
                "(&bgpalette4_end - &bgpalette4)",
                "BG_16COLORS", "0x4000"
        ));
        mapa4.add(SnesOutput.bgInitMapSet(
                1, "&bgmap4", "(&bgmap4_end - &bgmap4)",
                "SC_64x64", "0x1000"
        ));
        for (int i = 0; i < 20; i++) {
            mapa4.add(SnesOutput.waitForVblank());
        }
        mapa4.add(SnesOutput.setScreenOn());
        mapa4.add(KeyWords.snesBreak);

        mapas.put("0", mapa0);
        mapas.put("1", mapa1);
        mapas.put("2", mapa2);
        mapas.put("3", mapa3);
        mapas.put("4", mapa4);

        return mapas;

    }

}
