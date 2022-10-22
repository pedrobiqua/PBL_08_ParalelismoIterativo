import java.util.concurrent.Semaphore;

public class GerenciadorDeMutiplicacoes extends Thread {
    
    private int id;
    private long threadInicial;
    private long threadFim;
    private double[][] matrizA;
    private double[][] matrizB;
    private double[][] matrizC;
    private Semaphore conclusao;

    public GerenciadorDeMutiplicacoes(int id, long threadInicial, long threadFim, Semaphore conclusao, double[][] matrizA, double[][] matrizB, double[][] matrizC) {
        this.id = id;
        this.threadInicial = threadInicial;
        this.threadFim = threadFim;
        this.conclusao = conclusao;
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.matrizC = matrizC;
    }

    public void run() {
        try {

            System.out.println("Thread [" + id + "] iniciada");
            MutiplicacaoMatriz multiplicacao = new MutiplicacaoMatriz(threadInicial, threadFim, matrizA, matrizB, matrizC);
            multiplicacao.execute();
            System.out.println("Thread [" + id + "] finalizada");
            conclusao.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
