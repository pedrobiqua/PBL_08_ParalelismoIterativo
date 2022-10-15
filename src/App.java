import java.util.concurrent.Semaphore;

public class App {

    /**
     * @param args the command line arguments
     */
    public static long execute_MutiplicacaoMatriz_Sequencial(long threadInicial, long threadFinal, long totalDeVetoresMatrizC, double[][] matrizA, double[][] matrizB) {
        MutiplicacaoMatriz mutiplicacaoMatriz = new MutiplicacaoMatriz(threadInicial, threadFinal, totalDeVetoresMatrizC, matrizA, matrizB);
		long Tempo_inicio = System.currentTimeMillis();
		double[][] matrizC = mutiplicacaoMatriz.execute();
        ProcessadorDeArquivos.criaAqruivoMatriz(matrizC.length, matrizC[0].length, "MatrizC.txt");
		long Tempo_fim = System.currentTimeMillis();
		long Tempo_execucao = Tempo_fim - Tempo_inicio;
		return Tempo_execucao;
    }

    /**
     * @param args the command line arguments
     */
    public static long execute_MutiplicacaoMatriz_Paralela(long totalDeVetoresMatrizC, int numeroDeTarefas, double[][]matrizA, double[][]matrizB) throws InterruptedException {
        
        // Determinando a quantidades de tarefas por cada thread
        long tamSequenciaPorTarefa = totalDeVetoresMatrizC / numeroDeTarefas;
        long totalVetores = tamSequenciaPorTarefa;
        Semaphore conclusao = new Semaphore(1 - numeroDeTarefas);
		
        // Criando as threads
		GerenciadorDeMutiplicacoes[] tarefa = new GerenciadorDeMutiplicacoes[numeroDeTarefas];
        long threadInicial = 0;
		for (int i = 0; i < numeroDeTarefas; i++) {
            tarefa[i] = new GerenciadorDeMutiplicacoes(i, threadInicial, tamSequenciaPorTarefa, totalDeVetoresMatrizC, conclusao, matrizA, matrizB);
            threadInicial = tamSequenciaPorTarefa;
            tamSequenciaPorTarefa += totalVetores;
        }
		
		//ExecutorService exec = Executors.newFixedThreadPool(numeroDeTarefas);
		
		long Tempo_inicio = System.currentTimeMillis();
		for (int i = 0; i < numeroDeTarefas; i++)
			//exec.execute(tarefa[i]);
			tarefa[i].start();
		conclusao.acquire();
		long Tempo_fim = System.currentTimeMillis();
		//exec.shutdown();
		long Tempo_execucao = Tempo_fim - Tempo_inicio;
		return Tempo_execucao;
    }

    public static void main(String[] args) throws Exception {
        
        // Já está montando as matrizes de forma correta
        System.out.println("PBL_08");

        final int tarefasPorProcessador = 1;
		final int totalProcessadores = Runtime.getRuntime().availableProcessors();
		final int numTarefas = (tarefasPorProcessador) * totalProcessadores;

        System.out.println("Total de processadores: " + totalProcessadores);
		System.out.println("Número de tarefas: " + numTarefas);
		System.out.println("Tarefas por processador: " + tarefasPorProcessador);

        String SEP = ", ";
		System.out.println("N" + SEP + "Tamanho da Sequência" + SEP + "Tempo Sequencial" + SEP + "Tempo Paralelo" + SEP + "Razão entre os Tempos");

        for (int n = 0; n <= 1; n++) {
            ProcessadorDeArquivos.criaAqruivoMatriz(3, 3, "MatrizA.txt");
            ProcessadorDeArquivos.criaAqruivoMatriz(3, 3, "MatrizB.txt");

            double[][] matrizA = ProcessadorDeArquivos.inicializaMatrizApartirDoArquivo("MatrizA.txt");
            double[][] matrizB = ProcessadorDeArquivos.inicializaMatrizApartirDoArquivo("MatrizB.txt");

            long totalDeVetoresMatrizC = (matrizA.length) * (matrizB[0].length);

            long tempoSequencial = execute_MutiplicacaoMatriz_Sequencial(0, matrizB[0].length, totalDeVetoresMatrizC, matrizA, matrizB);
            long tempoParalelo = execute_MutiplicacaoMatriz_Paralela(totalDeVetoresMatrizC, numTarefas, matrizA, matrizB);
            double razao = (double) tempoSequencial / tempoParalelo;

            System.out.println(n + SEP + (totalDeVetoresMatrizC/numTarefas) + SEP + tempoSequencial + SEP + tempoParalelo + SEP + razao);
        }

        System.out.println("Fim do programa");
    }
}
