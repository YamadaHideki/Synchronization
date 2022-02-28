import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        tpe.prestartAllCoreThreads();

        AutoShow autoShow = new AutoShow();

        for (int i = 1; i < 10; i++) {
            final int buyer = i;
            tpe.execute(() -> {
                Thread.currentThread().setName("Покупатель " + buyer);
                autoShow.buyCar();
            });
        }

        for (int i = 0; i < 10; i++) {
            tpe.execute(() -> {
                Thread.currentThread().setName("Toyota");
                autoShow.getCar();
            });
        }

        tpe.shutdown();
    }
}
