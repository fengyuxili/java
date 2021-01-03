import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionNotify implements Runnable{
    private Lock lock;

    private Condition condition;

    public ConditionNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println("ConditionNotify start");
            condition.signal();
            System.out.println("ConditionNotify end");
        }finally {
            lock.unlock();
        }
    }
}
