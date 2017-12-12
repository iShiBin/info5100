package assignment7;

/**
 * 2. Write a program called ReverseHello.java that creates a thread (let's call it
 * Thread 1). Thread 1 creates another thread (Thread 2); Thread 2 creates
 * Thread 3; and so on, up to Thread 50. Each thread should print Hello from
 * Thread num!
 * 
 * but you should structure your program such that the threads print their
 * greetings in reverse order. (Score 2)
 * 
 * @author bin
 *
 */
class ReverseHello implements Runnable { // score 2
  private static final int LIMIT = 50;
  private static int counter = 0;
  private int num = 0;

  @Override
  public void run() {
    if (counter++ < LIMIT) {
      ReverseHello hello = new ReverseHello();
      hello.num = counter;
      Thread t = new Thread(hello);
      t.start();

      // wait until all the thread finish
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Hello from Thread " + hello.num + "!");
    }
  }

  public static void main(String[] args) {
    new Thread(new ReverseHello()).start();
  }
}
