import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * created test suite
 * @author Bronwyn Skelley
 *
 */

@RunWith(Suite.class)
@SuiteClasses({ ConsumerTest.class, DocumentTest.class, ProducerTest.class,
		SimulationTest.class, UserTest.class })
public class AllTests {

}
