
import javasnes.instruction.SnesInstruction;
import javasnes.util.macros.SnesInclude;
import javasnes.util.structures.SnesLoadExtern;
import javasnes.util.types.vars.array.data.SnesCharArray;
import javasnes.util.types.vars.scalar.data.SnesChar;
import javasnes.util.types.vars.scalar.number.signed.SnesS16;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU16;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU32;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU8;

public class DadosGlobais {

    final static SnesChar CHAR = new SnesChar("char");

    public static SnesInstruction[] definicoesGlobais;

    @SuppressWarnings("ManualArrayToCollectionCopy")
    public static void definirDados() {

        SnesInstruction[] vars = variaveisGlobais();

        definicoesGlobais = new SnesInstruction[vars.length + 2];

        definicoesGlobais[0] = carregarExternos();

        for (int i = 0; i < vars.length; i++) {
            definicoesGlobais[i + 1] = vars[i];
        }

        definicoesGlobais[vars.length + 1] = include();
        
    }

    public static SnesInstruction carregarExternos() {

        String[] carregarExterno = new String[]{

            "tilesfont", "palfont", "tilespink", "palpink",
            "tilespink_end", "palpink_end",

            "bgtiles0", "bgtiles0_end",
            "bgpalette0", "bgpalette0_end",
            "bgmap0", "bgmap0_end",

            "bgtiles1", "bgtiles1_end",
            "bgpalette1", "bgpalette1_end",
            "bgmap1", "bgmap1_end",

            "bgtiles2", "bgtiles2_end",
            "bgpalette2", "bgpalette2_end",
            "bgmap2", "bgmap2_end",

            "bgtiles3", "bgtiles3_end",
            "bgpalette3", "bgpalette3_end",
            "bgmap3", "bgmap3_end",

            "bgtiles4", "bgtiles4_end",
            "bgpalette4", "bgpalette4_end",
            "bgmap4", "bgmap4_end",

            "SOUNDBANK__"

        };

        return new SnesLoadExtern(carregarExterno, CHAR);
    }

    public static SnesU8 mudarBG = new SnesU8("mudarBG", "0");
    public static SnesU8 atualBG = new SnesU8("atualBG", "4");

    public static SnesU32 HiScore = new SnesU32("HiScore", "10000");
    public static SnesU32 Score = new SnesU32("Score", "0");

    public static SnesU16 pad0 = new SnesU16("pad0", "0");

    public static SnesS16 yTijoloAnterior = new SnesS16("yTijoloAnterior", "-1");

    public static SnesS16 yTijolo = new SnesS16("yTijolo", "-1");
    public static SnesS16 xTijolo = new SnesS16("xTijolo", "-1");

    public static SnesU8 tijoloEstaNaTela = new SnesU8("tijoloEstaNaTela", "0");

    public static SnesU8 ehParaReiniciarTijolo = new SnesU8("ehParaReiniciarTijolo", "0");

    public static SnesS16 xPink = new SnesS16("xPink", "0");

    public static SnesU8 qtdVidas = new SnesU8("qtdVidas", "3");

    public static SnesU8 ehParaCarregarPink = new SnesU8("ehParaCarregarPink", "0");
    public static SnesU8 ehParaCarregarTijolo = new SnesU8("ehParaCarregarTijolo", "0");

    public static SnesCharArray animLeft = new SnesCharArray(
        "animLeft", (short) 4, "{0, 4, 0, 8}"
    );

    public static SnesU8 animFrame = new SnesU8("animFrame", "0");
    public static SnesU8 noChao = new SnesU8("noChao", "0");

    public static SnesInstruction[] variaveisGlobais() {

        return new SnesInstruction[]{
            mudarBG,
            pad0,
            atualBG,
            HiScore,
            Score,
            yTijoloAnterior,
            yTijolo,
            xTijolo,
            xPink,
            qtdVidas,
            ehParaCarregarPink,
            ehParaCarregarTijolo,
            tijoloEstaNaTela,
            animLeft,
            animFrame,
            noChao
        };
    }

    public static SnesInstruction include() {

        return new SnesInclude("\"soundbank.h\"");

    }

}
