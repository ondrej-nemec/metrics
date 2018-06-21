package operations;

import java.util.Arrays;
import static org.junit.Assert.*;

import org.junit.Test;

public class EntropyTest {
	
	private Entropy<Character> entropy;
	
	public EntropyTest() {
		this.entropy = new Entropy<>(
				Arrays.asList(
						Arrays.asList('a', 'h', 'o', 'j'),
						Arrays.asList('j', 'a', 'k'),
						Arrays.asList('s', 'e'),
						Arrays.asList('m', 'á', 'š'),
						Arrays.asList('j', 'd', 'e'),
						Arrays.asList('t', 'o')
					),
				Arrays.asList(
						Arrays.asList('c', 'a', 'u', ' '),
						Arrays.asList('a', 'k', 'o'),
						Arrays.asList('s', 'a'),
						Arrays.asList('m', 'a', 's'),
						Arrays.asList('i', 'd', 'e'),
						Arrays.asList('t', 'o')
					)
				);
	}
	
	
	@Test
	public void testGetFonemsFromWorks(){
		
	}
	
	@Test
	public void testGetFonemsToWorks(){
		
	}
	
	@Test
	public void testGetFonemsTwinsWorks(){
		
	}
	
	@Test
	public void testGetEntropyFromWorks(){
		assertEquals(
				new Double(0.6326404413037335), 
				entropy.getEntropyFrom()
			);
	}
	
	@Test
	public void testGetEntropyToWorks(){
		assertEquals(
				new Double(0.7058823529411765), 
				entropy.getEntropyTo()
			);
	}
	
}
