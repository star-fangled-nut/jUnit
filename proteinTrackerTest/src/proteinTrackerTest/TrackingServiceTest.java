package proteinTrackerTest;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import com.simpleprogrammer.proteintracker.InvalidGoalException;
import com.simpleprogrammer.proteintracker.TrackingService;

public class TrackingServiceTest {

	private TrackingService service;
	
	@BeforeClass
	public static void before() {
		System.out.println("Before class");
	}
	
	@AfterClass
	public static void after() {
		System.out.println("After class");
	}
	
	@Before
	public void setup() {
		System.out.println("before");
		service = new TrackingService();
	}
	
	@After
	public void teardown() {
		System.out.println("After");
	}
	
	@Test
	@Category({GoodTestsCategory.class, BadTestsCategory.class})
	public void newTrackingServiceTotalIsZero() {
		assertEquals("Tracking service total was not zero", 0, service.getTotal());
	}
	
	@Test
	@Ignore
	public void whenAddingProteinTotalIncreseasesByThatAmount() {
		service.addProtein(10);
		assertEquals("Protein amount was not correct",10,service.getTotal());
		assertThat(service.getTotal(), is(10));
		
		assertThat(service.getTotal(), allOf(is(10), instanceOf(Integer.class)));
	}
	
	@Test
	@Category(GoodTestsCategory.class)
	public void whenRemovingProteinTotalRemainsZero() {
		service.removeProtein(5);
		assertEquals(0, service.getTotal());
	}
	
	@Rule
	public ExpectedException thrownException = ExpectedException.none();
	
	@Test
	//@Test(expected = InvalidGoalException.class)
	public void whenGoalIsSetToLessThanZeroExceptionIsThrown() throws InvalidGoalException {
		thrownException.expect(InvalidGoalException.class);
		thrownException.expectMessage("Goal was less than zero");
		thrownException.expectMessage(containsString("Goal"));
		service.setGoal(-5);
	}
	
	@Rule
	public Timeout timeoutRule = new Timeout(2, TimeUnit.SECONDS);
	
	//@Test(timeout = 200)
	@Test
	public void badTest() {
		for (int i = 0; i < 10000000 ; i++) {
			service.addProtein(1);
		} 
	}
}
