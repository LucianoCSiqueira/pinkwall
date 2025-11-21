
import javasnes.data.Data;
import javasnes.util.types.AppData;

public class CarregarDados {

    public static AppData carregarDados() {

        AppData appData = new AppData();

        colocarDadosAppData(appData, dadosFonte(), (byte) 2);
        colocarDadosAppData(appData, dadosPink(), (byte) 2);
        colocarDadosAppData(appData, dadosTijolo(), (byte) 2);

        colocarDadosAppData(appData, dadosBackground1(), (byte) 3);
        colocarDadosAppData(appData, dadosBackground2(), (byte) 4);
        colocarDadosAppData(appData, dadosBackground3(), (byte) 5);
        colocarDadosAppData(appData, dadosBackground4(), (byte) 6);
        colocarDadosAppData(appData, dadosBackground5(), (byte) 7);
        
        return appData;

    }

    public static Data[] dadosFonte() {

        Data[] dados = new Data[2];

        Data tiles = new Data("tilesfont", "fonte.pic", false);
        Data palette = new Data("palfont", "fonte.pal", false);

        dados[0] = tiles;
        dados[1] = palette;

        return dados;

    }

    public static Data[] dadosBackground1() {

        Data[] dados = new Data[3];

        Data bgtiles = new Data("bgtiles0", "BG_1.pic", true);
        Data bgpalette = new Data("bgpalette0", "BG_1.pal", true);
        Data bgmap = new Data("bgmap0", "BG_1.map", true);

        dados[0] = bgtiles;
        dados[1] = bgpalette;
        dados[2] = bgmap;

        return dados;

    }

    public static Data[] dadosBackground2() {

        Data[] dados = new Data[3];

        Data bgtiles = new Data("bgtiles1", "BG_2.pic", true);
        Data bgpalette = new Data("bgpalette1", "BG_2.pal", true);
        Data bgmap = new Data("bgmap1", "BG_2.map", true);

        dados[0] = bgtiles;
        dados[1] = bgpalette;
        dados[2] = bgmap;

        return dados;

    }

    public static Data[] dadosBackground3() {

        Data[] dados = new Data[3];

        Data bgtiles = new Data("bgtiles2", "BG_3.pic", true);
        Data bgpalette = new Data("bgpalette2", "BG_3.pal", true);
        Data bgmap = new Data("bgmap2", "BG_3.map", true);

        dados[0] = bgtiles;
        dados[1] = bgpalette;
        dados[2] = bgmap;

        return dados;

    }

    public static Data[] dadosBackground4() {

        Data[] dados = new Data[3];

        Data bgtiles = new Data("bgtiles3", "BG_4.pic", true);
        Data bgpalette = new Data("bgpalette3", "BG_4.pal", true);
        Data bgmap = new Data("bgmap3", "BG_4.map", true);

        dados[0] = bgtiles;
        dados[1] = bgpalette;
        dados[2] = bgmap;

        return dados;

    }

    public static Data[] dadosBackground5() {

        Data[] dados = new Data[3];

        Data bgtiles = new Data("bgtiles4", "BG_5.pic", true);
        Data bgpalette = new Data("bgpalette4", "BG_5.pal", true);
        Data bgmap = new Data("bgmap4", "BG_5.map", true);

        dados[0] = bgtiles;
        dados[1] = bgpalette;
        dados[2] = bgmap;

        return dados;

    }

    public static Data[] dadosPink() {

        Data[] dados = new Data[2];

        Data tilespink = new Data("tilespink", "sprites.pic", true);
        Data palpink = new Data("palpink", "sprites.pal", true);

        dados[0] = tilespink;
        dados[1] = palpink;

        return dados;

    }

    public static Data[] dadosTijolo() {

        Data[] dados = new Data[2];

        Data tilestijolo = new Data("tilestijolos", "tijolo.pic", true);
        Data paltijolo = new Data("paltijolos", "tijolo.pal", true);

        dados[0] = tilestijolo;
        dados[1] = paltijolo;

        return dados;

    }

    public static void colocarDadosAppData(AppData appData, Data[] dados, byte bank) {

        for (Data dado : dados) {
            appData.registerData(dado, bank);
        }

    }


    
}
