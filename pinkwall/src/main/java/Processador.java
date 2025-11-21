
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javasnes.input.SnesInput;
import javasnes.instruction.SnesInstruction;
import javasnes.instruction.SnesRawInstruction;
import javasnes.output.SnesOutput;
import javasnes.util.keywords.KeyWords;
import javasnes.util.logic.SnesIf;
import javasnes.util.logic.SnesSwitch;
import javasnes.util.operators.assign.OperatorAssign;
import javasnes.util.operators.binary.OperatorBinAnd;
import javasnes.util.operators.logical.OperatorEquals;
import javasnes.util.operators.logical.OperatorGreaterOrEqual;
import javasnes.util.operators.math.OperatorAdd;
import javasnes.util.operators.ternary.OperatorTernary;
import javasnes.util.types.Processor;
import javasnes.util.types.SnesProcess;
import javasnes.util.types.vars.scalar.data.SnesVoid;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU16;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU8;

public class Processador {

    final static SnesVoid VOID = new SnesVoid();

    public static Processor processador = new Processor();
    public static SnesProcess[] processos;

    public static void configurarProcessador() {

        processos = new SnesProcess[4];

        processos[0] = printHiScore();
        processos[1] = limparPrintHiScore();
        processos[2] = ehParaMudarBackground();
        processos[3] = mudarBackground();

        processador.addProcess(printHiScore(), null);
        processador.addProcess(ehParaMudarBackground(), null);
        processador.addProcess(mudarBackground(), null);

    }

    public static SnesProcess printHiScore() {

        SnesInstruction[] instrucoes = new SnesInstruction[1];

        List<SnesInstruction> seBGehStartInstrucoes = new ArrayList<>();

        seBGehStartInstrucoes.add(SnesOutput.consoleDrawText(
            5, 25, "Hi Score: %d", ", (int) HiScore"
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

        instrucoes[0] = SnesOutput.consoleDrawText(4, 25, "                          ", null);

        return new SnesProcess("limparPrintHiScore", instrucoes, VOID);

    }

    public static SnesProcess ehParaMudarBackground() {

        SnesInstruction[] instrucoes = new SnesInstruction[2];

        instrucoes[0] = new OperatorAssign(
            DadosGlobais.pad0.name, SnesInput.padsCurrent((byte) 0).sourceCode
        );

        instrucoes[1] = new OperatorAssign(
            DadosGlobais.mudarBG.name, new OperatorBinAnd(
                DadosGlobais.pad0, new SnesU16(SnesInput.keys.KEY_A.sourceCode)
            ).getSourceCode()
        );

        return new SnesProcess("ehParaMudarBackground", instrucoes, VOID);

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
        mapa0.add(SnesOutput.waitForVblank());
        mapa0.add(SnesOutput.setScreenOn());
        mapa0.add(KeyWords.snesBreak);


        mapa1.add(chamarLimparPrintHiScore);
        mapa1.add(SnesOutput.setScreenOff());
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
        mapa1.add(SnesOutput.waitForVblank());
        mapa1.add(SnesOutput.setScreenOn());
        mapa1.add(KeyWords.snesBreak);


        mapa2.add(chamarLimparPrintHiScore);
        mapa2.add(SnesOutput.setScreenOff());
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
        mapa2.add(SnesOutput.waitForVblank());
        mapa2.add(SnesOutput.setScreenOn());
        mapa2.add(KeyWords.snesBreak);


        mapa3.add(chamarLimparPrintHiScore);
        mapa3.add(SnesOutput.setScreenOff());
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
        mapa3.add(SnesOutput.waitForVblank());
        mapa3.add(SnesOutput.setScreenOn());
        mapa3.add(KeyWords.snesBreak);


        mapa4.add(chamarLimparPrintHiScore);
        mapa4.add(SnesOutput.setScreenOff());
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
        mapa4.add(SnesOutput.waitForVblank());
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
