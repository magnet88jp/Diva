import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

  @Before
  public void setup() {
    Fixtures.deleteAll();
  }
    
  @Test
  public void aVeryImportantThingToTest() {
    assertEquals(2, 1 + 1);
  }

  @Test
  public void createUser() {
    User bob = new User("bob@gmail.com", "secret", "Bob").save();
  }

  @Test
  public void fullTest() {
    Fixtures.loadModels("data.yml");
  }

}
