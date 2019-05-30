package drillmania;

public class Main {
  public static void main(String[] args) throws Exception {
    try (DrillManiaOperator oper = new DrillManiaOperator()) {
      while (!oper.isStarted()) {
        // wait
      }

      while (!oper.isTimeOver()) {
        Boolean isDrill = oper.isDrill();
        oper.sendResult(isDrill);
      }
      Thread.sleep(10000); // enjoy your score!
    }
  }
}
