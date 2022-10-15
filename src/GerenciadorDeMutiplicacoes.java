import java.util.concurrent.Semaphore;

public class GerenciadorDeMutiplicacoes extends Thread {
    
    private int id;
    private long threadInicial;
    private long threadFinal;
    private long totalDeVetoresMatrizC;
    private double[][] matrizA;
    private double[][] matrizB;
    private Semaphore conclusao;

    public GerenciadorDeMutiplicacoes(int id, long threadInicial, long threadFinal, long totalDeVetoresMatrizC, Semaphore conclusao, double[][] matrizA, double[][] matrizB) {
        this.id = id;
        this.threadInicial = threadInicial;
        this.threadFinal = threadFinal;
        this.conclusao = conclusao;
        this.totalDeVetoresMatrizC = totalDeVetoresMatrizC;
        this.matrizA = matrizA;
        this.matrizB = matrizB;
    }

    public void run() {
        try {

            System.out.println("Thread [" + id + "] iniciada");
            MutiplicacaoMatriz multiplicacao = new MutiplicacaoMatriz(threadInicial, threadFinal, totalDeVetoresMatrizC, matrizA, matrizB);
            double[][] matrizC = multiplicacao.execute();
            ProcessadorDeArquivos.criaAqruivoMatriz(matrizC.length, matrizC[0].length, "MatrizC.txt");
            System.out.println("Thread [" + id + "] finalizada");
            conclusao.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
