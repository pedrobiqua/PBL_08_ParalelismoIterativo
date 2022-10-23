import java.util.concurrent.Semaphore;

public class GerenciadorDeMutiplicacoes extends Thread {
    
    private int id;
    private long threadInicial;
    private long threadFim;
    private double[][] matrizA;
    private double[][] matrizB;
    private double[][] matrizC;
    private Semaphore conclusao;

    /**
     * Construtor da classe
     * @param id identificador da thread
     * @param threadInicial Onde começa a multiplicação das matrizes
     * @param threadFim Onde termina a multiplicação das matrizes
     * @param conclusao Semaforo para controlar a conclusão das threads
     * @param matrizA matriz A
     * @param matrizB matriz B
     * @param matrizC matriz C
     * @author Pedro Bianchini de Quadros
     * @author Lukas Jacon Barboza
     * @author Thiago Krugel
     * @author Lucas Kreutzer de Jesus
     */
    public GerenciadorDeMutiplicacoes(int id, long threadInicial, long threadFim, Semaphore conclusao, double[][] matrizA, double[][] matrizB, double[][] matrizC) {
        this.id = id;
        this.threadInicial = threadInicial;
        this.threadFim = threadFim;
        this.conclusao = conclusao;
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.matrizC = matrizC;
    }

    /**
     * Executa a multiplicação de matrizes e preenche uma matriz C.
     * @author Pedro Bianchini de Quadros
     * @author Lukas Jacon Barboza
     * @author Thiago Krugel
     * @author Lucas Kreutzer de Jesus
     */
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
