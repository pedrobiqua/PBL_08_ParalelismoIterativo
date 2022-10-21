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
            SalvaMatriz(matrizC);
            System.out.println("Thread [" + id + "] finalizada");
            conclusao.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SalvaMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] != 0) {
                    App.MatrizResultante[i][j] = matriz[i][j];
                }
            }
        }
    }
}
