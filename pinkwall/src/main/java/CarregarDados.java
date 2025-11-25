
import javasnes.data.Data;
import javasnes.util.types.AppData;

public class CarregarDados {

    /**
     * Carrega os dados do app.
     *
     * @return AppData com os dados do app carregados.
     */
    public static AppData carregarDados() {

        // dados do app
        AppData appData = new AppData();

        // Passa a fonte pro app
        colocarDadosAppData(appData, dadosFonte(), (byte) 2);

        // Passa o sprite pro app
        colocarDadosAppData(appData, dadosPink(), (byte) 2);

        // Passa os background pro app
        colocarDadosAppData(appData, dadosBackground1(), (byte) 3);
        colocarDadosAppData(appData, dadosBackground2(), (byte) 4);
        colocarDadosAppData(appData, dadosBackground3(), (byte) 5);
        colocarDadosAppData(appData, dadosBackground4(), (byte) 6);
        colocarDadosAppData(appData, dadosBackground5(), (byte) 7);

        return appData;

    }

    /**
     * Retorna os dados da fonte.
     *
     * @return Vetor de dados da fonte.
     */
    public static Data[] dadosFonte() {

        // configura os dados da fonte
        Data[] dados = new Data[2];

        Data tiles = new Data("tilesfont", "fonte.pic", false);
        Data palette = new Data("palfont", "fonte.pal", false);

        dados[0] = tiles;
        dados[1] = palette;

        return dados;

    }

    /**
     * Retorna os dados do background 1.
     *
     * @return Vetor de dados do background 1.
     */
    public static Data[] dadosBackground1() {

        // configura os dados do background 1
        Data[] dados = new Data[3];

        Data bgtiles = new Data("bgtiles0", "BG_1.pic", true);
        Data bgpalette = new Data("bgpalette0", "BG_1.pal", true);
        Data bgmap = new Data("bgmap0", "BG_1.map", true);

        dados[0] = bgtiles;
        dados[1] = bgpalette;
        dados[2] = bgmap;

        return dados;

    }

    /**
     * Retorna os dados do background 2.
     *
     * @return Vetor de dados do background 2.
     */
    public static Data[] dadosBackground2() {

        // configura os dados do background 2
        Data[] dados = new Data[3];

        Data bgtiles = new Data("bgtiles1", "BG_2.pic", true);
        Data bgpalette = new Data("bgpalette1", "BG_2.pal", true);
        Data bgmap = new Data("bgmap1", "BG_2.map", true);

        dados[0] = bgtiles;
        dados[1] = bgpalette;
        dados[2] = bgmap;

        return dados;

    }

    /**
     * Retorna os dados do background 3.
     *
     * @return Vetor de dados do background 3.
     */
    public static Data[] dadosBackground3() {

        // Configura os dados do background 3
        Data[] dados = new Data[3];

        Data bgtiles = new Data("bgtiles2", "BG_3.pic", true);
        Data bgpalette = new Data("bgpalette2", "BG_3.pal", true);
        Data bgmap = new Data("bgmap2", "BG_3.map", true);

        dados[0] = bgtiles;
        dados[1] = bgpalette;
        dados[2] = bgmap;

        return dados;

    }

    /**
     * Configura os dados do background 4.
     *
     * @return Vetor de dados do background 4.
     */
    public static Data[] dadosBackground4() {

        // configura os dados do background 4
        Data[] dados = new Data[3];

        Data bgtiles = new Data("bgtiles3", "BG_4.pic", true);
        Data bgpalette = new Data("bgpalette3", "BG_4.pal", true);
        Data bgmap = new Data("bgmap3", "BG_4.map", true);

        dados[0] = bgtiles;
        dados[1] = bgpalette;
        dados[2] = bgmap;

        return dados;

    }

    /**
     * Configura os dados do background 5.
     *
     * @return Vetor de dados do background 5.
     */
    public static Data[] dadosBackground5() {

        // configura os dados do background 4
        Data[] dados = new Data[3];

        Data bgtiles = new Data("bgtiles4", "BG_5.pic", true);
        Data bgpalette = new Data("bgpalette4", "BG_5.pal", true);
        Data bgmap = new Data("bgmap4", "BG_5.map", true);

        dados[0] = bgtiles;
        dados[1] = bgpalette;
        dados[2] = bgmap;

        return dados;

    }

    /**
     * Configura os dados do sprite
     *
     * @return Vetor com os dados do sprite
     */
    public static Data[] dadosPink() {

        // Configura os dados do sprite
        Data[] dados = new Data[2];

        Data tilespink = new Data("tilespink", "sprites.pic", true);
        Data palpink = new Data("palpink", "sprites.pal", true);

        dados[0] = tilespink;
        dados[1] = palpink;

        return dados;

    }

    /**
     * Coloca os dados do vetor dados no app data, com o banco especificado
     *
     * @param appData AppData que sera modificado
     * @param dados Vetor com os dados a serem colocados
     * @param bank Banco onde os dados serao colocados
     */
    public static void colocarDadosAppData(AppData appData, Data[] dados, byte bank) {

        // coloca os Dados no app data
        for (Data dado : dados) {
            appData.registerData(dado, bank);
        }

    }

}
