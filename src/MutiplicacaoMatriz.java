public class MutiplicacaoMatriz {

    private long threadInicial;
    private long threadFim;
    private double[][] matrizA;
    private double[][] matrizB;
    private double[][] matrizC;

    public MutiplicacaoMatriz(long threadInicial, long threadFim, double[][] matrizA, double[][] matrizB, double[][] matrizC) {
        this.threadInicial = threadInicial;
        this.threadFim = threadFim;
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.matrizC = matrizC;
    }

    public void execute() {

        try {
            // Linha
            for (int h = (int)threadInicial; h <= (int)threadFim; h++) {
                // Coluna
                int i = h / matrizC[0].length;
                int j = h % matrizC[0].length;
                // Faz a multiplicação de matrizes preenchendo a matriz C
                if (matrizC[i][j] == 0) {
                    for (int k = 0; k < matrizA[0].length; k++) {
                        matrizC[i][j] = matrizC[i][j] + matrizA[i][k] * matrizB[k][j];
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
