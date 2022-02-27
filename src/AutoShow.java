public class AutoShow{
    int currentAuto = 0;

    public synchronized void buyCar() {
        try {
            Thread.sleep(2000);
            String buyer = Thread.currentThread().getName();
            System.out.println(buyer + " зашел в автосалон");
            while (currentAuto <= 0) {
                System.out.println("Машин нет");
                wait();
            }
            Thread.sleep(1000);
            currentAuto--;
            System.out.println(buyer + " прикупил машину");
        } catch (InterruptedException ignored) { }
    }

    public synchronized void getCar() {
        try {
            Thread.sleep(1000);
            currentAuto += 1;
            notify();
        } catch (InterruptedException ignored) { }
    }
}
