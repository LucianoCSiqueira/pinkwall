
import javasnes.App;

public class PinkWall {

    public static void main(String[] args) {

        App.Builder pinkwall = new App.Builder();

        pinkwall.setAppData(CarregarDados.carregarDados());
        pinkwall.setBoot(SequenciaDeBoot.sequenciaDeBoot());

        DadosGlobais.definirDados();
        pinkwall.setGlobalInstructions(DadosGlobais.definicoesGlobais);

        Processador.configurarProcessador();
        pinkwall.setProcessor(Processador.processor);
        pinkwall.setSnesProcesses(Processador.processes);

        try {

            Compilador.compilarROM(pinkwall);

        } catch (Exception e) {

            System.err.println("Erro ao compilar o ROM: " + e.getMessage());
            throw new RuntimeException(e);

        }

    }
    
}

