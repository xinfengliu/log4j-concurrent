/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lxf
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

public class TestLog4j {
  static Logger logger = Logger.getLogger("mylog");
  static int numOfThreads = 20;
  static long numOfMessages = 20000;
  static StringBuilder dumbmsg;

  public static void main (String[] args) 
            throws InterruptedException{
      //PropertyConfigurator.configure(args[0]);
      //logger.info("Hello World!");

      dumbmsg = buildMesg();

      if(args.length > 0){
         numOfThreads = Integer.parseInt(args[0]);
      }
      if(args.length > 1){
         numOfMessages = Long.parseLong(args[1]);
      }

      System.out.println( "log4j test starts." ); 
      System.out.println( "Number of Threads: " + numOfThreads );
      System.out.println( "Total number of Messages: " + numOfMessages );

      long begin = System.currentTimeMillis();

      Thread t[] = new Thread[numOfThreads];
      for ( int i=0; i<numOfThreads; i++) {
        t[i] = new Thread(new LogWorker());
        t[i].start();
      }

      // here you can test forced shutdown before other threads finish to 
      // see if the behavior of RW locks in Category.java is correct.
      //Thread.sleep(200);
      //LogManager.shutdown();

      for ( int i = 0; i<numOfThreads; i++) {
        t[i].join();
      }
      long end = System.currentTimeMillis();
      System.out.println("\nTest completed. Took " + (end - begin) + " millsecs.");
      LogManager.shutdown();
  }

  private static class LogWorker implements Runnable {
    public void run() {
        String msg = "\nHello, I am " + Thread.currentThread().getName() + ".\n";
        //msg += dumbmsg;
        for( int i = 0; i < numOfMessages/numOfThreads ; i++){
             logger.info(dumbmsg);
             logger.info(msg);
/*
             try {
	       Thread.sleep(10);
	     } catch (InterruptedException ie) {
		System.err.println("Interrupted");
		break;
	     }
*/
        }
    }
  }

  private static StringBuilder buildMesg() {
    BufferedReader br = null;
    StringBuilder sb = new StringBuilder(32768);
    sb.append('\n');
		try {
			String s;
			br = new BufferedReader(new FileReader("dumbmsg.txt"));
			while ((s = br.readLine()) != null) {
			         sb.append(s).append('\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
                return sb;
  }
}

