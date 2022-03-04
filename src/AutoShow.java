import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AutoShow{
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    int currentAuto = 0;

    public void buyCar() {
        lock.lock();
        try {
            Thread.sleep(1000);
            String buyer = Thread.currentThread().getName();
            System.out.println(buyer + " зашел в автосалон");
            while (currentAuto <= 0) {
                System.out.println("Машин нет");
                condition.await();
            }
            Thread.sleep(1000);
            currentAuto--;
            System.out.println(buyer + " прикупил машину");
        } catch (InterruptedException ignored) { } finally {
            lock.unlock();
        }
    }

    public void getCar() {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("Производитель " + Thread.currentThread().getName() + " доставил машину в автосалон");
            currentAuto += 1;
            condition.signal();
        } catch (InterruptedException ignored) { } finally {
            lock.unlock();
        }
    }
}
