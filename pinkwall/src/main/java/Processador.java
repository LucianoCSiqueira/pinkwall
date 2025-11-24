
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
import javasnes.util.types.vars.scalar.data.SnesChar;
import javasnes.util.types.vars.scalar.data.SnesVoid;
import javasnes.util.types.vars.scalar.number.signed.SnesS32;
import javasnes.util.types.vars.scalar.number.signed.SnesS8;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU16;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU8;

public class Processador {

    final static SnesVoid VOID = new SnesVoid();

    public static Processor processador = new Processor();
    public static SnesProcess[] processos;

    public static void configurarProcessador() {

        processos = new SnesProcess[]{
            limparTela(),
            printHiScore(),
            comecarJogo(),
            gameOver(),
            printScoreGameOver(),
            atualizarHiScore(),
            printDadosFase(),
            atualizarLevel(),
            carregarTijolo(),
            atualizarTijolo(),
            ehParaCarregarPink(),
            atualizarPink(),
            mudarBackground()
        };

        for (SnesProcess processo : processos) {

            if (!processo.name.equals("limparTela")) {
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
                        new OperatorEquals(
                                DadosGlobais.atualBG, new SnesU8("4")
                        ).getSourceCode()
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
                2, 26, "HighScore: %d", ", (long long int) HiScore"
        ));

        SnesIf seBGehStart = new SnesIf(
                new OperatorEquals(DadosGlobais.atualBG, new SnesU8("4")),
                seBGehStartInstrucoes
        );

        seBGehStart.generateSourceCode();

        instrucoes[0] = seBGehStart;

        return new SnesProcess("printHiScore", instrucoes, VOID);

    }

    public static SnesProcess printScoreGameOver() {

        SnesInstruction[] instrucoes = new SnesInstruction[2];

        List<SnesInstruction> seBGehGameOverInstrucoes = new ArrayList<>();

        seBGehGameOverInstrucoes.add(SnesOutput.consoleDrawText(
                2, 26, "Score: %d", ", (long long int) Score"
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
                new OperatorGreater(DadosGlobais.Score, DadosGlobais.HiScore),
                new ArrayList<>() {
            {
                add(new OperatorAssign(
                        DadosGlobais.HiScore.name, DadosGlobais.Score.name
                ));
                add(SnesUtilities.consoleCopySram("(u8*)&HiScore", "4"));
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
                new ArrayList<>() {
            {

                add(SnesOutput.consoleDrawText(
                        26, 3, "High", null
                ));

                add(SnesOutput.consoleDrawText(
                        26, 4, "Score:", null
                ));

                add(SnesOutput.consoleDrawText(
                        26, 6, "%06d", ", (long long int) HiScore"
                ));

                add(SnesOutput.consoleDrawText(
                        26, 10, "Score:", null
                ));

                add(SnesOutput.consoleDrawText(
                        26, 12, "%06d", ", (long long int) Score"
                ));

                add(SnesOutput.consoleDrawText(
                        26, 16, "Level:", null
                ));

                add(SnesOutput.consoleDrawText(
                        26, 18, "%d", ", (int) (4 - qtdVidas)"
                ));

                add(SnesOutput.consoleDrawText(
                        26, 22, "Lifes:", null
                ));

                add(SnesOutput.consoleDrawText(
                        26, 24, "%d", ", (int) qtdVidas"
                ));

            }
        }
        );

        seBGehFase.generateSourceCode();

        instrucoes[0] = seBGehFase;

        return new SnesProcess("printDadosFase", instrucoes, VOID);

    }

    public static SnesProcess limparTela() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];
        
        instrucoes[0] = SnesOutput.consoleDrawText(
                0, 0, " ".repeat(900), null
        );

        return new SnesProcess("limparTela", instrucoes, VOID);

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
                        DadosGlobais.mudarBG
                ).getSourceCode()
        );

        instrucoes[1] = new OperatorAssign(
                DadosGlobais.qtdVidas.name,
                new OperatorTernary(
                        caiu,
                        new SnesU8("1"),
                        new SnesU8("0")
                ).getSourceCode(),
                '-'
        );

        return new SnesProcess("atualizarLevel", instrucoes, VOID);

    }

    public static SnesProcess colisao() {
        return null;
    }

    public static SnesProcess carregarTijolo() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        SnesOperator ehParaCarregarTijolo = new OperatorAnd(
                new OperatorEquals(
                        DadosGlobais.ehParaCarregarTijolo, new SnesU8("1")
                ).getSourceCode(),
                new OperatorEquals(
                        DadosGlobais.tijoloEstaNaTela, new SnesU8("0")
                ).getSourceCode()
        );

        List<SnesInstruction> instrucoesParaCarregarTijolo = new ArrayList<>();

        instrucoesParaCarregarTijolo.add(new SnesU16(
                "posXNovoTijolo", "rand() % 23"
        ));

        instrucoesParaCarregarTijolo.add(new OperatorAssign(
                DadosGlobais.yTijolo.name, "0"
        ));

        instrucoesParaCarregarTijolo.add(new OperatorAssign(
                DadosGlobais.xTijolo.name, "posXNovoTijolo"
        ));

        instrucoesParaCarregarTijolo.add(new OperatorAssign(
                DadosGlobais.tijoloEstaNaTela.name, "1"
        ));

        SnesIf seEhParaCarregarTijolo = new SnesIf(
                ehParaCarregarTijolo, instrucoesParaCarregarTijolo
        );

        seEhParaCarregarTijolo.generateSourceCode();

        instrucoes[0] = seEhParaCarregarTijolo;

        return new SnesProcess("carregarTijolo", instrucoes, VOID);
    }

    public static SnesProcess atualizarTijolo() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        final Integer SPEED = 1;

        SnesOperator estaNaTela = new OperatorEquals(
                DadosGlobais.tijoloEstaNaTela.name, "1"
        );

        List<SnesInstruction> instrucoesSeEstaNaTela = new ArrayList<>();

        instrucoesSeEstaNaTela.add(new SnesRawInstruction(
                "consoleDrawText(" + 
                DadosGlobais.xTijolo.name + ", " +
                DadosGlobais.yTijoloAnterior.name + ", " + 
                new OperatorTernary(
                        new OperatorGreaterOrEqual(
                                DadosGlobais.yTijoloAnterior, new SnesU8("0")
                        ),
                        new SnesChar("\"\""), new SnesChar("\" \"")
                ).getSourceCode() + ");"
        ));

        instrucoesSeEstaNaTela.add(new OperatorAssign(
                DadosGlobais.yTijoloAnterior.name, DadosGlobais.yTijolo.name
        ));

        instrucoesSeEstaNaTela.add(new OperatorAssign(
                DadosGlobais.yTijolo.name, SPEED.toString(),
                '+'
        ));

        instrucoesSeEstaNaTela.add(new SnesRawInstruction(
                "consoleDrawText(" + 
                DadosGlobais.xTijolo.name + ", " +
                DadosGlobais.yTijolo.name + ", " + 
                "\"#\"" + ");"
        ));

        SnesIf seEhPraAtualizarTijoloNaTela = new SnesIf(
                estaNaTela, instrucoesSeEstaNaTela
        );

        seEhPraAtualizarTijoloNaTela.generateSourceCode();

        instrucoes[0] = seEhPraAtualizarTijoloNaTela;
        
        return new SnesProcess("atualizarTijolo", instrucoes, VOID);

    }

    public static SnesProcess ehParaCarregarPink() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        instrucoes[0] = new OperatorAssign(DadosGlobais.ehParaCarregarPink.name,
                new OperatorTernary(new OperatorSmallerOrEqual(
                        DadosGlobais.atualBG, new SnesU8("2")
                ), new SnesU8("1"), new SnesU8("0")).getSourceCode()
        );

        return new SnesProcess("EhParaCarregarPink", instrucoes, VOID);

    }

    public static SnesProcess atualizarPink() {

        final SnesU8 MOVE_SPEED = new SnesU8("3");
        final SnesS8 MIN_X = new SnesS8("-2");
        final SnesU8 MAX_X = new SnesU8("178");
        final SnesU8 PINK_Y = new SnesU8("178");

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        SnesOperator ehParaCarregarPinkOperator = new OperatorEquals(
                DadosGlobais.ehParaCarregarPink, new SnesU8("1")
        );

        List<SnesInstruction> instrucoesSeEhParaCarregarPink = new ArrayList<>();

        instrucoesSeEhParaCarregarPink.add(new OperatorAssign(
                DadosGlobais.xPink.name,
                new OperatorTernary(
                        SnesInput.keyLeftPressed(),
                        new SnesU8(new OperatorAdd(
                                DadosGlobais.xPink, new SnesS8("-1")
                        ).getSourceCode()),
                        DadosGlobais.xPink
                ).getSourceCode()
        ));

        instrucoesSeEhParaCarregarPink.add(new OperatorAssign(
                DadosGlobais.xPink.name,
                new OperatorTernary(
                        SnesInput.keyRightPressed(),
                        new SnesU8(new OperatorAdd(
                                DadosGlobais.xPink, MOVE_SPEED
                        ).getSourceCode()),
                        DadosGlobais.xPink
                ).getSourceCode()
        ));

        instrucoesSeEhParaCarregarPink.add(new OperatorAssign(
                DadosGlobais.xPink.name,
                new OperatorTernary(
                        new OperatorSmaller(DadosGlobais.xPink, MIN_X),
                        MIN_X,
                        DadosGlobais.xPink
                ).getSourceCode()
        ));

        instrucoesSeEhParaCarregarPink.add(new OperatorAssign(
                DadosGlobais.xPink.name,
                new OperatorTernary(
                        new OperatorGreater(DadosGlobais.xPink, MAX_X),
                        MAX_X,
                        DadosGlobais.xPink
                ).getSourceCode()
        ));

        instrucoesSeEhParaCarregarPink.add(new OperatorAssign(
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

        instrucoesSeEhParaCarregarPink.add(new OperatorAssign(
                DadosGlobais.animFrame.name,
                new OperatorTernary(
                        new OperatorGreaterOrEqual(
                                DadosGlobais.animFrame, new SnesU8("4")
                        ),
                        new SnesU8("0"),
                        DadosGlobais.animFrame
                ).getSourceCode()
        ));

        instrucoesSeEhParaCarregarPink.add(SnesOutput.oamSetEx(
                0,
                SnesOutput.ObjState.OBJ_SMALL,
                new OperatorTernary(
                        new OperatorEquals("ehParaCarregarPink", "1"),
                        new SnesU8(SnesOutput.ObjState.OBJ_HIDE),
                        new SnesU8(SnesOutput.ObjState.OBJ_SHOW)
                ).getSourceCode()
        ));

        instrucoesSeEhParaCarregarPink.add(SnesOutput.oamSet(
                0,
                DadosGlobais.xPink.name,
                PINK_Y.name,
                "3",
                "0",
                "0",
                "animLeft[animFrame]",
                "2"
        ));

        instrucoesSeEhParaCarregarPink.add(SnesSound.spcPlaySound(1));

        SnesIf seEhParaCarregarPink = new SnesIf(
                ehParaCarregarPinkOperator,
                instrucoesSeEhParaCarregarPink
        );

        seEhParaCarregarPink.generateSourceCode();

        instrucoes[0] = seEhParaCarregarPink;

        return new SnesProcess("atualizarPink",
                instrucoes,
                VOID
        );
    }

    public static SnesProcess mudarBackground() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        List<SnesInstruction> instrucoesSeEhParaMudarBG = new ArrayList<>();

        instrucoesSeEhParaMudarBG.add(new OperatorAssign(
                DadosGlobais.mudarBG.name, "0"
        ));

        instrucoesSeEhParaMudarBG.add(new OperatorAssign(
                DadosGlobais.atualBG.name, new OperatorTernary(
                        new OperatorGreaterOrEqual(
                                DadosGlobais.atualBG, new SnesU8("4")
                        ),
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

        SnesInstruction chamarLimparTela = new SnesRawInstruction(
                "limparTela();"
        );

        mapa0.add(chamarLimparTela);
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

        mapa1.add(chamarLimparTela);
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

        mapa2.add(chamarLimparTela);
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

        mapa3.add(chamarLimparTela);
        mapa3.add(SnesOutput.setScreenOff());
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

        mapa4.add(chamarLimparTela);
        mapa4.add(new OperatorAssign("Score", "0"));
        mapa4.add(new OperatorAssign("qtdVidas", "3"));
        mapa4.add(SnesOutput.setScreenOff());
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
