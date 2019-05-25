package drillmania;

import drillmania.DrillManiaOperator.DrillType;

public class Main {
	public static void main(String[] args) throws Exception {
		try(DrillManiaOperator oper=new DrillManiaOperator()){
			while (!oper.isTimeOver()) {
				DrillType drillType = oper.getDrillType();
				if (drillType.equals(DrillType.ANIMEDRILL) || drillType.equals(DrillType.REALDRILL)) {
					oper.sendResult(true);
				} else {
					oper.sendResult(false);
				}
			}
			Thread.sleep(10000);//enjoy your sroce!
		}
	}
}
