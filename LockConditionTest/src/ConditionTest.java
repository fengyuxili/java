import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        //condition.await();->放入condition队列阻塞
        //condition.signal();->唤醒线程放入AQS队列，进行锁的竞争
        new Thread(new ConditionWait(lock, condition)).start();
        new Thread(new ConditionNotify(lock, condition)).start();
    }
}
