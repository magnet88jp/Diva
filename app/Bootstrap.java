import play.*;
import play.jobs.*;
import play.test.*;

import models.*;

@OnApplicationStart
public class Bootstrap extends Job {

  public void doJob() {
    if(User.count() == 0) {
      Fixtures.loadModels("initial-data/master.yml");
      Fixtures.loadModels("initial-data/function.yml");
      Fixtures.loadModels("initial-data/setting.yml");
      Fixtures.loadModels("initial-data/majormessage.yml");
      Fixtures.loadModels("initial-data/action.yml");
    }
  }
}
