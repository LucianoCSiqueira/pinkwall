
import javasnes.App;

public class PinkWall {

    public static void main(String[] args) {

        // Constroi o app pinkwall
        App.Builder pinkwall = new App.Builder();

        // passa os dados do app
        pinkwall.setAppData(CarregarDados.carregarDados());

        // faz a inicialização e a sequencia de inicialização
        pinkwall.setBoot(SequenciaDeBoot.sequenciaDeBoot());

        // define os dados
        DadosGlobais.definirDados();
        pinkwall.setGlobalInstructions(DadosGlobais.definicoesGlobais);

        // configura o processador e os processos
        Processador.configurarProcessador();
        pinkwall.setProcessor(Processador.processador);
        pinkwall.setSnesProcesses(Processador.processos);

        try {

            // tente compilar
            Compilador.compilarROM(pinkwall);

        } catch (Exception e) {

            // se não compilar vai printar o erro
            System.err.println("Erro ao compilar o ROM: " + e.getMessage());
            throw new RuntimeException(e);

        }

    }
    
}

