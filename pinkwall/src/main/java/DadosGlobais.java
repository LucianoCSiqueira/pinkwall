
import javasnes.instruction.SnesInstruction;
import javasnes.util.structures.SnesLoadExtern;
import javasnes.util.types.vars.scalar.data.SnesChar;

public class DadosGlobais {

    final static SnesChar CHAR = new SnesChar("char");

    public static SnesInstruction[] definicoesGlobais;
    
    public static void definirDados() {

        definicoesGlobais = new SnesInstruction[1];
        definicoesGlobais[0] = carregarExternos();

    }

    public static SnesInstruction carregarExternos() {

        String[] loadExtern = new String[40];

        loadExtern[0] = "tilesfont";
        loadExtern[1] = "palfont";
        loadExtern[2] = "tilespink";
        loadExtern[3] = "palpink";
        loadExtern[4] = "tilespink_end";
        loadExtern[5] = "palpink_end";
        loadExtern[6] = "tilestijolos";
        loadExtern[7] = "paltijolos";
        loadExtern[8] = "tilestijolos_end";
        loadExtern[9] = "paltijolos_end";

        loadExtern[10] = "bgtiles0";
        loadExtern[11] = "bgtiles0_end";
        loadExtern[12] = "bgpalette0";
        loadExtern[13] = "bgpalette0_end";
        loadExtern[14] = "bgmap0";
        loadExtern[15] = "bgmap0_end";

        loadExtern[16] = "bgtiles1";
        loadExtern[17] = "bgtiles1_end";
        loadExtern[18] = "bgpalette1";
        loadExtern[19] = "bgpalette1_end";
        loadExtern[20] = "bgmap1";
        loadExtern[21] = "bgmap1_end";

        loadExtern[22] = "bgtiles2";
        loadExtern[23] = "bgtiles2_end";
        loadExtern[24] = "bgpalette2";
        loadExtern[25] = "bgpalette2_end";
        loadExtern[26] = "bgmap2";
        loadExtern[27] = "bgmap2_end";

        loadExtern[28] = "bgtiles3";
        loadExtern[29] = "bgtiles3_end";
        loadExtern[30] = "bgpalette3";
        loadExtern[31] = "bgpalette3_end";
        loadExtern[32] = "bgmap3";
        loadExtern[33] = "bgmap3_end";

        loadExtern[34] = "bgtiles4";
        loadExtern[35] = "bgtiles4_end";
        loadExtern[36] = "bgpalette4";
        loadExtern[37] = "bgpalette4_end";
        loadExtern[38] = "bgmap4";
        loadExtern[39] = "bgmap4_end";

        return new SnesLoadExtern(loadExtern, CHAR);

    }

}
