package core;

import java.util.HashMap;
import java.util.Map;

public class Constants {
  public final static String url = "https://drillmania.work";
  
  private final static String puctureFilePath = "/_nuxt/img/";

  public final static Map<String, Boolean> drillPictureMap = new HashMap<String, Boolean>();
  static {
    drillPictureMap.put(puctureFilePath+"a565676.png", true);// REALDRILL
    drillPictureMap.put(puctureFilePath+"cdc53ee.png", true);// ANIMEDRILL
    drillPictureMap.put(puctureFilePath+"87ce995.png", false);// TAKENOKO
    drillPictureMap.put(puctureFilePath+"60911dc.png", false);// ASPARAGUS
  }
}
