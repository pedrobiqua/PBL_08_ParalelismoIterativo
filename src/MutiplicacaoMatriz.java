public class MutiplicacaoMatriz {

    private long threadInicial;
    private long threadFinal;
    private long totalDeVetoresMatrizC;
    private double[][] matrizA;
    private double[][] matrizB;
    private double[][] matrizC;

    public MutiplicacaoMatriz(long threadInicial, long threadFinal, long totalDeVetoresMatrizC, double[][] matrizA, double[][] matrizB) {
        this.threadInicial = threadInicial;
        this.threadFinal = threadFinal;
        this.totalDeVetoresMatrizC = totalDeVetoresMatrizC;
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.matrizC = new double[matrizA.length][matrizB[0].length];
    }

    public double[][] execute() {

        try {
            // Linha
            for (int i = ( (int)threadInicial/matrizC.length ); i < matrizC.length; i++) {
                // Coluna
                for (int j = ((int)threadInicial % matrizC[0].length ); j < matrizC[0].length; j++) {

                    // Faz a multiplicação de matrizes preenchendo a matriz C
                    for (int k = 0; k < matrizC[0].length; k++) {
                        matrizC[i][j] = matrizC[i][j] + matrizA[i][k] * matrizB[k][j];
                    }
                }
            }
            return matrizC;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
