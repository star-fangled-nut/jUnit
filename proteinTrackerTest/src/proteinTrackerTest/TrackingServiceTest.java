package proteinTrackerTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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
	}
	
	@Test
	@Category(GoodTestsCategory.class)
	public void whenRemovingProteinTotalRemainsZero() {
		service.removeProtein(5);
		assertEquals(0, service.getTotal());
	}
	
	@Test(expected = InvalidGoalException.class)
	public void whenGoalIsSetToLessThanZeroExceptionIsThrown() throws InvalidGoalException {
		service.setGoal(-5);
	}
	
	@Test(timeout = 200)
	public void badTest() {
		for (int i = 0; i < 10000000 ; i++) {
			service.addProtein(1);
		} 
	}
}
