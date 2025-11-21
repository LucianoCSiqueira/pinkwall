
import javasnes.instruction.SnesInstruction;
import javasnes.util.structures.SnesLoadExtern;
import javasnes.util.types.vars.scalar.data.SnesChar;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU16;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU32;
import javasnes.util.types.vars.scalar.number.unsigned.SnesU8;

public class DadosGlobais {

    final static SnesChar CHAR = new SnesChar("char");

    public static SnesInstruction[] definicoesGlobais;
    
    @SuppressWarnings("ManualArrayToCollectionCopy")
    public static void definirDados() {

        definicoesGlobais = new SnesInstruction[5];

        definicoesGlobais[0] = carregarExternos();

        for (int i = 0; i < variaveisGlobais().length; i++) {
            definicoesGlobais[i + 1] = variaveisGlobais()[i];
        }

    }

    public static SnesInstruction carregarExternos() {

        String[] carregarExterno = new String[40];

        carregarExterno[0] = "tilesfont";
        carregarExterno[1] = "palfont";
        carregarExterno[2] = "tilespink";
        carregarExterno[3] = "palpink";
        carregarExterno[4] = "tilespink_end";
        carregarExterno[5] = "palpink_end";
        carregarExterno[6] = "tilestijolos";
        carregarExterno[7] = "paltijolos";
        carregarExterno[8] = "tilestijolos_end";
        carregarExterno[9] = "paltijolos_end";

        carregarExterno[10] = "bgtiles0";
        carregarExterno[11] = "bgtiles0_end";
        carregarExterno[12] = "bgpalette0";
        carregarExterno[13] = "bgpalette0_end";
        carregarExterno[14] = "bgmap0";
        carregarExterno[15] = "bgmap0_end";

        carregarExterno[16] = "bgtiles1";
        carregarExterno[17] = "bgtiles1_end";
        carregarExterno[18] = "bgpalette1";
        carregarExterno[19] = "bgpalette1_end";
        carregarExterno[20] = "bgmap1";
        carregarExterno[21] = "bgmap1_end";

        carregarExterno[22] = "bgtiles2";
        carregarExterno[23] = "bgtiles2_end";
        carregarExterno[24] = "bgpalette2";
        carregarExterno[25] = "bgpalette2_end";
        carregarExterno[26] = "bgmap2";
        carregarExterno[27] = "bgmap2_end";

        carregarExterno[28] = "bgtiles3";
        carregarExterno[29] = "bgtiles3_end";
        carregarExterno[30] = "bgpalette3";
        carregarExterno[31] = "bgpalette3_end";
        carregarExterno[32] = "bgmap3";
        carregarExterno[33] = "bgmap3_end";

        carregarExterno[34] = "bgtiles4";
        carregarExterno[35] = "bgtiles4_end";
        carregarExterno[36] = "bgpalette4";
        carregarExterno[37] = "bgpalette4_end";
        carregarExterno[38] = "bgmap4";
        carregarExterno[39] = "bgmap4_end";

        return new SnesLoadExtern(carregarExterno, CHAR);

    }

    public static SnesU8 mudarBG = new SnesU8("mudarBG", "0");
    public static SnesU8 atualBG = new SnesU8("atualBG", "4");
    public static SnesU32 HiScore = new SnesU32("HiScore", "10000");
    public static SnesU16 pad0 = new SnesU16("pad0", "0");

    public static SnesInstruction[] variaveisGlobais() {

        SnesInstruction[] variaveis = new SnesInstruction[4];

        variaveis[0] = mudarBG;
        variaveis[1] = pad0;
        variaveis[2] = atualBG;
        variaveis[3] = HiScore;

        return variaveis;

    }

}
