
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
import javasnes.util.logic.SnesElse;
import javasnes.util.logic.SnesIf;
import javasnes.util.logic.SnesSwitch;
import javasnes.util.operators.SnesOperator;
import javasnes.util.operators.assign.OperatorAssign;
import javasnes.util.operators.binary.OperatorBinAnd;
import javasnes.util.operators.binary.OperatorBinC2;
import javasnes.util.operators.binary.OperatorBinSHR;
import javasnes.util.operators.logical.OperatorAnd;
import javasnes.util.operators.logical.OperatorEquals;
import javasnes.util.operators.logical.OperatorGreater;
import javasnes.util.operators.logical.OperatorGreaterOrEqual;
import javasnes.util.operators.logical.OperatorOr;
import javasnes.util.operators.logical.OperatorSmaller;
import javasnes.util.operators.logical.OperatorSmallerOrEqual;
import javasnes.util.operators.math.OperatorAdd;
import javasnes.util.operators.math.OperatorMod;
import javasnes.util.operators.ternary.OperatorTernary;
import javasnes.util.types.Processor;
import javasnes.util.types.SnesProcess;
import javasnes.util.types.vars.scalar.data.SnesVoid;
import javasnes.util.types.vars.scalar.number.signed.SnesS16;
import javasnes.util.types.vars.scalar.number.signed.SnesS32;
import javasnes.util.types.vars.scalar.number.signed.SnesS8;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU16;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU32;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU8;

public class Processador {

    final static SnesVoid VOID = new SnesVoid();

    public static Processor processador = new Processor();
    public static SnesProcess[] processos;

    /**
     * Primeiro limpe qualquer tela se necessário(chamado por mudarBackground
     * no final do processo anterior, por isso não é rodado a cada
     * frame(processador)). Depois imprime as informações do jogo necessárias
     * pro BG especifico.
     *
     * Depois roda o metodo de iniciar o jogo que le o controle para que quando
     * pressione start inicie o jogo.
     *
     * Depois roda um metodo que verifica se precisa carregar o pinkwall, e
     * outro metodo que carrega se necessário o tijolo.
     *
     * Após isso roda os metodos que atualizam os dados do jogo, e verifica se
     * houve colisão, e por fim roda um metodo que atualiza o background se
     * necessário.
     *
     */
    public static void configurarProcessador() {

        processos = new SnesProcess[]{
            limparTela(),
            printHiScore(),
            printDadosFase(),
            printScoreGameOver(),
            iniciarJogoComStart(),
            ehParaCarregarPink(),
            carregarTijolo(),
            atualizarHiScore(),
            atualizarLevel(),
            atualizarTijolo(),
            atualizarPink(),
            colisaoTijoloPink(),
            mudarBackground()

        };

        for (SnesProcess processo : processos) {

            if (!processo.name.equals("limparTela")) {
                processador.addProcess(processo, null);
            }

        }

    }

    /**
     * Inicia o jogo se o background atual for igual a 3 e se a tecla start for
     * pressionada.
     *
     * @return SnesProcess que representa a inicializa o do jogo.
     */
    public static SnesProcess iniciarJogoComStart() {

        SnesInstruction[] instrucoes = new SnesInstruction[2];

        instrucoes[0] = SnesSound.spcProcess();

        SnesIf seStart = new SnesIf(
                new OperatorAnd(
                        SnesInput.keyStartPressed().getSourceCode(),
                        new OperatorGreaterOrEqual(
                                DadosGlobais.atualBG, new SnesU8("3")
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

        return new SnesProcess("iniciarJogoComStart", instrucoes, VOID);

    }

    /**
     * Imprime na tela o valor atual da variavel HiScore se o background atual
     * for igual a 4.
     *
     * @return SnesProcess que representa a impress o do valor atual da variavel
     * HiScore.
     */
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

    /**
     * Imprime na tela o valor atual da variavel Score se o background atual for
     * igual a 3.
     *
     * @return SnesProcess que representa a impress o do valor atual da variavel
     * Score.
     */
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

    /**
     * Atualiza o valor da variavel HiScore se o valor da variavel Score for
     * maior.
     *
     * @return SnesProcess que representa a atualiza o do valor da variavel
     * HiScore.
     */
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

    /**
     * Imprime na tela o valor atual da variavel HiScore se o background atual
     * for igual a 2.
     *
     * @return SnesProcess que representa a impress o do valor atual da variavel
     * HiScore.
     */
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

    /**
     * Imprime na tela a string de espaços em branco, formando uma linha
     * horizontal em branco. Isso é útil para limpar a tela.
     *
     * @return SnesProcess que representa a impress o de uma linha horizontal em
     * branco.
     */
    public static SnesProcess limparTela() {

        SnesInstruction[] instrucoes = new SnesInstruction[30];

        for (int i = 0; i < 30; i++) {

            instrucoes[i] = SnesOutput.consoleDrawText(
                    0, i, " ".repeat(34), null
            );

        }

        return new SnesProcess("limparTela", instrucoes, VOID);

    }

    /**
     * Atualiza o nivel atual do jogo, mudando o background (mudarBG) e
     * decrementando a quantidade de vidas (qtdVidas) caso o personagem tenha
     * ultrapassado o limite superior da tela (yTijolo > 27).
     *
     * @return SnesProcess que representa a atualiza o do nivel do jogo.
     */
    public static SnesProcess atualizarLevel() {

        SnesInstruction[] instrucoes = new SnesInstruction[2];

        SnesU8 limiteY = new SnesU8("27");

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

    /**
     * Verifica se o personagem colidiu com o pinkwall. Caso sim, decrementa a
     * quantidade de vidas (qtdVidas) e muda o background (mudarBG) para o
     * pinkwall.
     *
     * @return SnesProcess que representa a colis o entre o personagem e o
     * pinkwall.
     */
    public static SnesProcess colisaoTijoloPink() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        SnesOperator tijoloNaTela = new OperatorEquals(
                DadosGlobais.tijoloEstaNaTela, new SnesU8("1")
        );

        List<SnesInstruction> instrucoesSeTijoloNaTela = new ArrayList<>();

        SnesOperator seColidiuXEsquerdo = new OperatorSmallerOrEqual(
                new OperatorBinSHR(
                        new SnesS16("(" + DadosGlobais.xPink.name + "+8)"),
                        3
                ).getSourceCode(), DadosGlobais.xTijolo.name
        );

        SnesOperator seColidiuXDireito = new OperatorGreaterOrEqual(
                new OperatorBinSHR(
                        new SnesS16("(" + DadosGlobais.xPink.name + "+32+8)"),
                        3
                ).getSourceCode(), DadosGlobais.xTijolo.name
        );

        SnesOperator seColidiuX = new OperatorAnd(
                seColidiuXEsquerdo.getSourceCode(), seColidiuXDireito.getSourceCode()
        );

        SnesOperator seColidiuY = new OperatorGreaterOrEqual(
                DadosGlobais.yTijolo.name,
                new OperatorBinSHR(
                        new SnesS16("(" + 192 + "+8)"),
                        3
                ).getSourceCode()
        );

        SnesOperator seColidiu = new OperatorAnd(
                seColidiuX.getSourceCode(), seColidiuY.getSourceCode()
        );

        List<SnesInstruction> seColidiuInstrucoes = new ArrayList<>();

        seColidiuInstrucoes.add(new OperatorAssign(
                DadosGlobais.tijoloEstaNaTela.name, "0"
        ));

        seColidiuInstrucoes.add(new OperatorAssign(
                DadosGlobais.ehParaCarregarTijolo.name, "1"
        ));

        seColidiuInstrucoes.add(new OperatorAssign(
                DadosGlobais.yTijoloAnterior.name, "-1"
        ));

        seColidiuInstrucoes.add(new OperatorAssign(
                DadosGlobais.Score.name, "100", '+'
        ));

        seColidiuInstrucoes.add(new SnesRawInstruction(
                "limparTela();"
        ));

        seColidiuInstrucoes.add(new SnesRawInstruction(
                "printDadosFase();"
        ));

        SnesIf seColidiuTijolo = new SnesIf(
                seColidiu,
                seColidiuInstrucoes
        );

        seColidiuTijolo.generateSourceCode();

        instrucoesSeTijoloNaTela.add(seColidiuTijolo);

        SnesIf seTijoloNaTela = new SnesIf(
                tijoloNaTela,
                instrucoesSeTijoloNaTela
        );

        seTijoloNaTela.generateSourceCode();

        instrucoes[0] = seTijoloNaTela;

        return new SnesProcess("colisaoTijoloPink", instrucoes, VOID);

    }

    /**
     * Carrega as informacoes necessarias para o SPC processar a cria o de um
     * novo tijolo na tela.
     *
     * A primeira instru o define que o tijolo s criado se a variavel
     * ehParaCarregarTijolo for igual a 1 e a variavel tijoloEstaNaTela for
     * igual a 0. A segunda define que, se a condicao for verdadeira, a variavel
     * ehParaCarregarTijolo ser atualizada com o valor 0, a variavel
     * yTijoloAnterior ser atualizada com o valor -1, a variavel posXNovoTijolo
     * ser atualizada com o valor (rand() % 22) + 1, a variavel yTijolo ser
     * atualizada com o valor 0, a variavel xTijolo ser atualizada com o valor
     * posXNovoTijolo e a variavel tijoloEstaNaTela ser atualizada com o valor
     * 1.
     *
     * @return Um SnesProcess que representa o carregamento do tijolo na tela.
     */
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

        instrucoesParaCarregarTijolo.add(new OperatorAssign(
                DadosGlobais.ehParaCarregarTijolo.name, "0"
        ));

        instrucoesParaCarregarTijolo.add(new OperatorAssign(
                DadosGlobais.yTijoloAnterior.name, "-1"
        ));

        instrucoesParaCarregarTijolo.add(new SnesU16(
                "posXNovoTijolo", "(rand() % 22) + 1"
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

    /**
     * Atualiza a posicao do tijolo na tela.
     *
     * Primeiramente, verifica se o tijolo esta na tela. Caso esteja, atualiza a
     * posicao do tijolo na tela. Em seguida, atualiza a posicao do tijolo na
     * memoria de SRAM.
     *
     * @return SnesProcess que representa a atualizacao da posicao do tijolo na
     * tela.
     */
    public static SnesProcess atualizarTijolo() {

        SnesInstruction[] instrucoes = new SnesInstruction[2];

        final Integer VELOCIDADE = 1;

        instrucoes[0] = new SnesRawInstruction(
                "consoleDrawText("
                + DadosGlobais.xTijolo.name + ", "
                + DadosGlobais.yTijoloAnterior.name + ", "
                + "\" \"" + ");"
        );

        SnesOperator estaNaTelaEPrecisaAtualizar = new OperatorAnd(
                new OperatorEquals(
                        DadosGlobais.tijoloEstaNaTela.name, "1"
                ).getSourceCode(),
                new OperatorEquals(
                        new OperatorMod(
                                new SnesU32("snes_vblank_count"),
                                new SnesU8("1")
                        ).getSourceCode(),
                        "0"
                ).getSourceCode()
        );

        List<SnesInstruction> instrucoesSeEstaNaTela = new ArrayList<>();

        instrucoesSeEstaNaTela.add(new SnesRawInstruction(
                "consoleDrawText("
                + DadosGlobais.xTijolo.name + ", "
                + DadosGlobais.yTijoloAnterior.name + ", "
                + "\" \"" + ");"
        ));

        instrucoesSeEstaNaTela.add(new OperatorAssign(
                DadosGlobais.yTijoloAnterior.name, DadosGlobais.yTijolo.name
        ));

        instrucoesSeEstaNaTela.add(new OperatorAssign(
                DadosGlobais.yTijolo.name, new OperatorTernary(
                        new OperatorSmaller(
                                DadosGlobais.yTijolo, new SnesU8("29")
                        ),
                        VELOCIDADE.toString(),
                        "0"
                ).getSourceCode(),
                '+'
        ));

        instrucoesSeEstaNaTela.add(new SnesRawInstruction(
                "consoleDrawText("
                + DadosGlobais.xTijolo.name + ", "
                + DadosGlobais.yTijolo.name + ", "
                + "\"#\"" + ");"
        ));

        SnesIf seEhPraAtualizarTijoloNaTela = new SnesIf(
                estaNaTelaEPrecisaAtualizar, instrucoesSeEstaNaTela
        );

        seEhPraAtualizarTijoloNaTela.generateSourceCode();

        instrucoes[1] = seEhPraAtualizarTijoloNaTela;

        return new SnesProcess("atualizarTijolo", instrucoes, VOID);

    }

    /**
     * EhParaCarregarPink (EhParaCarregarPink)
     *
     * This SnesProcess sets ehParaCarregarPink to 1 if the current background
     * is 2, otherwise, it sets ehParaCarregarPink to 0.
     *
     * @return A SnesProcess object containing the instructions.
     */
    public static SnesProcess ehParaCarregarPink() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        instrucoes[0] = new OperatorAssign(DadosGlobais.ehParaCarregarPink.name,
                new OperatorTernary(new OperatorSmallerOrEqual(
                        DadosGlobais.atualBG, new SnesU8("2")
                ), new SnesU8("1"), new SnesU8("0")).getSourceCode()
        );

        return new SnesProcess("EhParaCarregarPink", instrucoes, VOID);

    }

    /**
     * atualizarPink (AtualizarPink)
     *
     * This SnesProcess updates pink's position and animation frame. It checks
     * if ehParaCarregarPink is 1, if it is, it updates pink's position and
     * animation frame, otherwise, it resets pink's animation frame and
     * visibility.
     *
     * @return A SnesProcess object containing the instructions.
     */
    public static SnesProcess atualizarPink() {

        final SnesU8 VELOCIDADE = new SnesU8("7");
        final SnesS8 MIN_X = new SnesS8("0");
        final SnesU8 MAX_X = new SnesU8("178");
        final SnesU8 PINK_Y = new SnesU8("192");

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
                                DadosGlobais.xPink, new SnesS8(
                                        new OperatorBinC2(VELOCIDADE).getSourceCode()
                                )
                        ).getSourceCode()),
                        DadosGlobais.xPink
                ).getSourceCode()
        ));

        instrucoesSeEhParaCarregarPink.add(new OperatorAssign(
                DadosGlobais.xPink.name,
                new OperatorTernary(
                        SnesInput.keyRightPressed(),
                        new SnesU8(new OperatorAdd(
                                DadosGlobais.xPink, VELOCIDADE
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

        instrucoesSeEhParaCarregarPink.add(SnesOutput.oamSetEx(
                0,
                SnesOutput.ObjState.OBJ_SMALL,
                new OperatorTernary(
                        new OperatorEquals("ehParaCarregarPink", "1"),
                        new SnesU8(SnesOutput.ObjState.OBJ_SHOW),
                        new SnesU8(SnesOutput.ObjState.OBJ_HIDE)
                ).getSourceCode()
        ));

        instrucoesSeEhParaCarregarPink.add(SnesOutput.oamSetVisible(
                0, SnesOutput.ObjState.OBJ_SHOW
        ));

        List<SnesInstruction> instrucoesSeNaoEhParaCarregarPink = new ArrayList<>();

        instrucoesSeNaoEhParaCarregarPink.add(new OperatorAssign(
                DadosGlobais.animFrame.name, "0"
        ));

        instrucoesSeNaoEhParaCarregarPink.add(SnesOutput.oamSet(
                0,
                DadosGlobais.xPink.name,
                PINK_Y.name,
                "3",
                "0",
                "0",
                "animLeft[animFrame]",
                "2"
        ));

        instrucoesSeNaoEhParaCarregarPink.add(SnesOutput.oamSetEx(
                0,
                SnesOutput.ObjState.OBJ_SMALL,
                SnesOutput.ObjState.OBJ_HIDE
        ));

        instrucoesSeNaoEhParaCarregarPink.add(SnesOutput.oamSetVisible(
                0, SnesOutput.ObjState.OBJ_HIDE
        ));

        SnesElse seNaoEhParaCarregarPink = new SnesElse(
                instrucoesSeNaoEhParaCarregarPink
        );

        SnesIf seEhParaCarregarPink = new SnesIf(
                ehParaCarregarPinkOperator,
                instrucoesSeEhParaCarregarPink,
                seNaoEhParaCarregarPink
        );

        seEhParaCarregarPink.generateSourceCode();

        instrucoes[0] = seEhParaCarregarPink;

        return new SnesProcess("atualizarPink",
                instrucoes,
                VOID
        );
    }

    /**
     * Verifica se precisa mudar o background e o muda caso necess ario.
     *
     * Primeiramente, verifica se precisa mudar o background (mudarBG) e muda o
     * atual background (atualBG) para o valor correto. Em seguida, carrega o
     * mapa correto para o background.
     *
     * @return SnesProcess que representa a mudan a do background.
     */
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

    /**
     * Carrega as informa es necessarias para o SPC processar a mapa do
     * PinkWall. O mapa carregado depende do valor de atualBG.
     *
     * @return Um Map com as chaves sendo 0, 1, 2, 3 e 4, e os valores sendo
     * listas de SnesInstructions que devem ser executadas para carregar o mapa.
     */
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
        mapa0.add(new OperatorAssign(DadosGlobais.tijoloEstaNaTela.name, "0"));
        mapa0.add(new OperatorAssign(DadosGlobais.yTijoloAnterior.name, "-1"));
        mapa0.add(new OperatorAssign(DadosGlobais.yTijolo.name, "-1"));
        mapa0.add(new OperatorAssign(DadosGlobais.xTijolo.name, "-1"));
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
        mapa1.add(new OperatorAssign(DadosGlobais.tijoloEstaNaTela.name, "0"));
        mapa1.add(new OperatorAssign(DadosGlobais.yTijoloAnterior.name, "-1"));
        mapa1.add(new OperatorAssign(DadosGlobais.yTijolo.name, "-1"));
        mapa1.add(new OperatorAssign(DadosGlobais.xTijolo.name, "-1"));
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
        mapa2.add(new OperatorAssign(DadosGlobais.tijoloEstaNaTela.name, "0"));
        mapa2.add(new OperatorAssign(DadosGlobais.yTijoloAnterior.name, "-1"));
        mapa2.add(new OperatorAssign(DadosGlobais.yTijolo.name, "-1"));
        mapa2.add(new OperatorAssign(DadosGlobais.xTijolo.name, "-1"));
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
        mapa3.add(new OperatorAssign(DadosGlobais.tijoloEstaNaTela.name, "0"));
        mapa3.add(new OperatorAssign(DadosGlobais.yTijoloAnterior.name, "-1"));
        mapa3.add(new OperatorAssign(DadosGlobais.yTijolo.name, "-1"));
        mapa3.add(new OperatorAssign(DadosGlobais.xTijolo.name, "-1"));
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
        mapa4.add(new OperatorAssign(DadosGlobais.tijoloEstaNaTela.name, "0"));
        mapa4.add(new OperatorAssign(DadosGlobais.yTijoloAnterior.name, "-1"));
        mapa4.add(new OperatorAssign(DadosGlobais.yTijolo.name, "-1"));
        mapa4.add(new OperatorAssign(DadosGlobais.xTijolo.name, "-1"));
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
