package jobs;

import play.Logger;
import play.jobs.*;

/** Filre at every 1min **/
//@OnApplicationStart
//@On("0 0 12 * * ?")
//@On("0 * * * * ?")
//@Every("5s")
//public class MyJob extends Job {
public class MyJob extends Job<String> {

//  public void doJob() {
//    // execute some application logic here ...
//    Logger.info("Maintenance job ...");
//  }

  public String doJobWithResult() {
    // execute some application logic here ...
    Logger.info("returned job ...");
    return "aaa";
  }

}
