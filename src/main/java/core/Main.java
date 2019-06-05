package core;

/**
 * 
 * @author aaaanwz
 *
 */
public class Main {
  /**
   * Program entry point.
   * 
   * @param args not used.
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    try (DrillManiaOperator oper = new DrillManiaOperator()) {
      while (!oper.isStarted()) {
        // Wait for initial countdown
        Thread.sleep(1);
      }

      // Determine drill or not during playtime.
      while (!oper.isTimeOver()) {
        Boolean isDrill = oper.isDrill();
        oper.sendResult(isDrill);
      }
      Thread.sleep(5000); // Show your score!
    }
  }
}
