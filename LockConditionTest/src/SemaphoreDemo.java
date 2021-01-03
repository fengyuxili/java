import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    static class Car extends Thread {
        private int num;
        private Semaphore semaphore;

        public  Car(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }
        public void run() {
            try {
                semaphore.acquire(); //获取一个令牌，如果拿不到令牌，就会阻塞
                System.out.println("第"+num+" 抢占一个车位");
                Thread.sleep(2000);
                System.out.println("第"+num+" 开走了");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 15; i++) {
            new Car(i, semaphore).start();
        }
    }
}
