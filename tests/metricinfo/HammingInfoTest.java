package metricinfo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import structures.ResultSet;

@RunWith(Parameterized.class)
public class HammingInfoTest {

	private ResultSet<Character, String> expected;
	private ResultSet<Character, String> actual;
	
	public HammingInfoTest(List<Character> from, List<Character> to, ResultSet<Character, String> result) {
		this.expected = result;
		this.actual = new HammingInfo<Character>().calculate(from, to);
	}
	//TODO exceptions test
	

	@Test
	public void testCalculateFinalSequence() {
		assertEquals(
				expected.getFinalSequenceFrom(), 
				actual.getFinalSequenceFrom()
			);		
		assertEquals(
				expected.getFinalSequenceTo(),
				actual.getFinalSequenceTo()
			);
	}
	
	@Test
	public void testCalculateOperations(){
		assertEquals(expected.getOperations(), actual.getOperations());
	}
	
	@Test
	public void testCalculateDistance(){
		assertEquals(expected.getDistance(), actual.getDistance());
	}
	
	@Test
	public void testCalculateStructure(){
		assertEquals(expected.getStructure(), actual.getStructure());
	}

	@Parameters
	public static Collection<Object[]> dataProvider() {
		return Arrays.asList(
					new Object[]{
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							makeResultSet(
									Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
									Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
									"EEEEEE",
									0)
					},
					new Object[]{
							Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
							Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
							makeResultSet(
									Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
									Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
									"EEEESS",
									2
								)
					},
					new Object[]{
							Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
							Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
							makeResultSet(
									Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
									Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
									"EESESSSS",
									5
								)
					},
					new Object[]{
							Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
							Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
							makeResultSet(
									Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
									Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
									"ESSESSS",
									5
								)
					},
					new Object[]{
							Arrays.asList('w', 'o', 'r', 'd'),
							Arrays.asList('7', ';', '$', 'Ð'),
							makeResultSet(
									Arrays.asList('w', 'o', 'r', 'd'),
									Arrays.asList('7', ';', '$', 'Ð'),
									"SSSS",
									4
								)
					},
					new Object[]{
							Arrays.asList('a', 'b', 'b', 'c', 'b'),
							Arrays.asList('a', 'b', 'c', 'a', 'b'),
							makeResultSet(
									Arrays.asList('a', 'b', 'b', 'c', 'b'),
									Arrays.asList('a', 'b', 'c', 'a', 'b'),
									"EESSE",
									2
								)
					},
					new Object[]{
							Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
										  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
										  'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
							Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
										  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
										  'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
							makeResultSet(
									Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
											  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
											  'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
								Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
											  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
											  'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
									"EESSSSSSSSSSEEEEESEESESEEEEEEEEEE",
									13
								)
					}				
				);
	}


	private static Object makeResultSet(List<Character> from, List<Character> to, String operations, int distance) {
		return new ResultSet<>(
				from, 
				to,
				"Hamming distance",
				operations,
				distance,
				operations
			);
	}

}
