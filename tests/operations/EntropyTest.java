package operations;

import java.util.Arrays;
import static org.junit.Assert.*;

import org.junit.Test;

import support.Tuple2;
import support.Tuple3;

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
		assertEquals(
				Arrays.asList(
						new Tuple2<>('a', 2),
						new Tuple2<>('h', 1),
						new Tuple2<>('o', 2),
						new Tuple2<>('j', 3),
						new Tuple2<>('k', 1),
						new Tuple2<>('s', 1),
						new Tuple2<>('e', 2),
						new Tuple2<>('m', 1),
						new Tuple2<>('á', 1),
						new Tuple2<>('š', 1),
						new Tuple2<>('d', 1),
						new Tuple2<>('t', 1)
					), 
				entropy.getFonemsFromWithCount()
			);
	}
	
	@Test
	public void testGetFonemsToWorks(){
		assertEquals(
				Arrays.asList(
						new Tuple2<>('c', 1),
						new Tuple2<>('a', 4),
						new Tuple2<>('u', 1),
						new Tuple2<>(' ', 1),
						new Tuple2<>('k', 1),
						new Tuple2<>('o', 2),
						new Tuple2<>('s', 2),
						new Tuple2<>('m', 1),
						new Tuple2<>('i', 1),
						new Tuple2<>('d', 1),
						new Tuple2<>('e', 1),
						new Tuple2<>('t', 1)
					), 
				entropy.getFonemsToWithCount()
			);
	}
	
	@Test
	public void testGetFonemsTwinsWorks(){
		assertEquals(
				Arrays.asList(
						new Tuple3<>('a', 'c', 1),
						new Tuple3<>('h', 'a', 1),
						new Tuple3<>('o', 'u', 1),
						new Tuple3<>('j', ' ', 1),
						new Tuple3<>('j', 'a', 1),
						new Tuple3<>('a', 'k', 1),
						new Tuple3<>('k', 'o', 1),
						new Tuple3<>('s', 's', 1),
						new Tuple3<>('e', 'a', 1),
						new Tuple3<>('m', 'm', 1),
						new Tuple3<>('á', 'a', 1),
						new Tuple3<>('š', 's', 1),
						new Tuple3<>('j', 'i', 1),
						new Tuple3<>('d', 'd', 1),
						new Tuple3<>('e', 'e', 1),
						new Tuple3<>('t', 't', 1),
						new Tuple3<>('o', 'o', 1)
					), 
				entropy.getFonemsTwinsWithCounts()
			);
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
