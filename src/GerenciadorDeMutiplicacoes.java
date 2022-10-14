import java.util.concurrent.Semaphore;

public class GerenciadorDeMutiplicacoes extends Thread {
    
    private int id;
    private Semaphore conclusao;

    public GerenciadorDeMutiplicacoes(int id, long tamSequencia, Semaphore conclusao) {
        this.id = id;
        this.conclusao = conclusao;
    }

    public void run() {
        try {
            
            System.out.println("Thread " + id + " iniciada");
            MutiplicacaoMatriz multiplicacao = new MutiplicacaoMatriz();
            multiplicacao.execute();
            System.out.println("Thread " + id + " finalizada");
            conclusao.release();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
