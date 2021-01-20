package de.itout.test.log4j_jdbc_test;

import java.util.concurrent.*;
import org.apache.logging.log4j.*;
import org.junit.*;

/**
 *
 * @author swendelmann
 */
public class LoggerTest
{

  private static final org.apache.logging.log4j.Logger logg = LogManager.getLogger();

  @Test
  public void testProfiling() throws InterruptedException
  {
    logg.traceEntry();
    int runs = 1_000;
    int logs = 10;
    int threadPoolSize = 8;
    logg.debug("Start Test mit " + runs + " durchläufen & " + logs + " logs & " + threadPoolSize + " ThreadPool");
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolSize);

    for (int i = 0; i < runs; i++)
    {
      String laufid = "LD0" + i;
      Task t = new Task(laufid, logs, i);
      executor.execute(t);
    }
    executor.shutdown();
    while (!executor.awaitTermination(100L, TimeUnit.MINUTES))
    {
//      logg.info("Not Ready yet ");
    }
    logg.traceExit();
    Thread.sleep(10_000L);
  }

  public class Task implements Runnable
  {

    String allSicherungsVerzeichnis = "target/sicherungsverzeichnis/";

    public int i;
    public String laufId;
    public int logs;
    
    private  org.apache.logging.log4j.Logger log = LogManager.getLogger("IIPLAUFLOG");

    public Task(String laufId, int logs, int i)
    {
      this.laufId = laufId;
      this.logs = logs;
      this.i = i;
    }

    @Override
    public void run()
    {
//      try
//      {
//        System.out.println("Thread " + i + " started");
//        Thread.sleep(5_000L);
//        System.out.println("Thread " + i + " finished");
//      }
//      catch (Exception e)
//      {
//        e.printStackTrace();
//      }

      if (i % 2 == 0)
      {
        ThreadContext.put("myLogLvl", "WARN");
      }
      else
      {
        ThreadContext.put("myLogLvl", "INFO");
      }

      ThreadContext.put("schnittstelle", "LISA4711");
      ThreadContext.put("version", "L01");
      ThreadContext.put("laufid", laufId);
      ThreadContext.put("user", "SPADMIN");

      for (int j = 0; j < logs; j++)
      {
        log.info("Ich bin nur eine Info und soll nur in das FullFile logging!");
        log.warn("Ich bin ein böser warning und soll in das FullFile und in die DB");
      }

      // Logger säubern
//      ThreadContext.clearAll();
    }

  }

}
