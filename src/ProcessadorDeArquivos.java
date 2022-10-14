import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ProcessadorDeArquivos
 */
public class ProcessadorDeArquivos {
    
    /**
     * Cria matriz apartir do arquivo passado como parametro.
     * @param nomeArquivo Nome do arquivo que contem a matriz.
     * @return Matriz criada apartir do arquivo.
     * @throws IOException Caso o arquivo nao exista.
     */
    public static Double[][] inicializaMatrizApartirDoArquivo(String nomeArquivo) throws IOException {

        // Está apenas lendo o arquivo e imprimindo na tela.
        File arquivo = new File(nomeArquivo);
        FileReader fileReader = new FileReader(arquivo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linha = bufferedReader.readLine();

        // Cria uma matriz de acordo com as colunas e linhas do arquivo.
        Double[][] matriz = criaMatrizApartirDoArquivo(nomeArquivo);
        int i = 0;

        // Percorre o arquivo linha a linha
        while (linha != null) {

            // System.out.println(linha);
            String [] valores = linha.split(" ");
            for (int j = 0; j < valores.length; j++) {
                matriz[i][j] = Double.parseDouble(valores[j]);
            }

            i++;
            linha = bufferedReader.readLine();
        }
        bufferedReader.close();

        return matriz;
    }

    /**
     * Cria uma matriz de acordo com as colunas e linhas do arquivo.
     * @param nomeArquivo Nome do arquivo.
     * @return Matriz de acordo com as colunas e linhas do arquivo.
     * @throws IOException Exceção de entrada e saída.
     */
    public static Double[][] criaMatrizApartirDoArquivo(String nomeArquivo) throws IOException {

        try {
            File arquivo = new File(nomeArquivo);
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha = bufferedReader.readLine();

            int quantidadeDeLinha = 0;
            int tamanhoColuna = linha.split(" ").length;

            // Percorre o arquivo linha a linha
            while (linha != null) {

                quantidadeDeLinha++;
                linha = bufferedReader.readLine();
            }
            bufferedReader.close();
            return new Double[quantidadeDeLinha][tamanhoColuna];

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Salva a matriz no arquivo passado como parametro.
     * @param tamanhoLinha
     * @param tamanhoColuna
     * @param nomeArquivo
     */
    public static void criaAqruivoMatriz(int tamanhoLinha, int tamanhoColuna, String nomeArquivo) {

        Double[][] matriz = new Double[tamanhoLinha][tamanhoColuna];

        for (int i = 0; i < tamanhoLinha; i++) {
            for (int j = 0; j < tamanhoColuna; j++) {
                matriz[i][j] = Math.random();
            }
        }

        // Salva a matriz em um arquivo.
        SalvaMatriz(matriz, nomeArquivo);

    }
    
    /**
     * Salva a matriz em um arquivo.
     * @param matriz  Matriz a ser salva.
     * @param nomeArquivo Nome do arquivo.
     */
    public static void SalvaMatriz(Double[][] matriz, String nomeArquivo) {

        try {
            FileWriter fileWriter = new FileWriter(nomeArquivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    bufferedWriter.write(matriz[i][j] + " ");
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}