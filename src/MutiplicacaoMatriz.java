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
            // Inicializa a matriz C com zeros
            for (int i = (int)threadInicial; i < threadFinal; i++) {

                for (int j = (int)threadInicial; j < threadFinal; j++) {
                    
                    matrizC[i/(matrizB[0].length)][j % matrizB[0].length] = 0.0;
                    // Faz a multiplicação de matrizes preenchendo a matriz C
                    for (int k = 0; k < matrizC.length; k++) {
                        matrizC[i/(matrizB[0].length)][j % matrizB[0].length] = 
                            matrizC[i/(matrizB[0].length)][j % matrizB[0].length] + matrizA[i/(matrizB[0].length)][k] * matrizB[k][j % matrizB[0].length];
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
