package metricinfo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import exception.InvalidOpeationCostException;
import exception.SequencesMustHaveSameLengthException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import structures.ResultSet;
import support.AbstractMetricInfoTest;

@RunWith(JUnitParamsRunner.class)
public class HammingDistanceInfoTest extends AbstractMetricInfoTest {

	@Test(expected=InvalidOpeationCostException.class)
	public void testConstructorThrowWhenCostIsNotPositive(){
		new HammingDistanceInfo<Character>(0);
	}
	
	@Test(expected=SequencesMustHaveSameLengthException.class)
	public void testCalculateThrowsWhenInvalidInput(){
		HammingDistanceInfo<Character> h = new HammingDistanceInfo<Character>();
		h.calculate(Arrays.asList('a', 'b'), Arrays.asList('a'));
	}
	
	@Test
	@Parameters
	public void testWeightDistance(
			List<Character> from,
			List<Character> to,
			int weightDistance){
		ResultSet<Character, String> res = 
				new HammingDistanceInfo<Character>(2).calculate(from, to);
		assertEquals(weightDistance, res.getDistance());
	}
	
	public Object[] parametersForTestWeightDistance() {
		return makeParams(weightDistances());
	}

	@Test
	@Parameters
	public void testCalculateWorks(
			List<Character> from,
			List<Character> to,
			ResultSet<Character, String> result) {
		ResultSet<Character, String> metric = new HammingDistanceInfo<Character>().calculate(from, to);
		assertEquals(result, metric);
	}
	
	public Object[] parametersForTestCalculateWorks() {
		return makeParams(resultSets());
	}	
	
	private ResultSet<Character, String> makeResultSet(
			List<Character> from,
			List<Character> to, 
			String operations,
			int distance) {
		return new ResultSet<>(
				from, 
				to,
				"Hamming distance",
				operations,
				distance,
				operations
			);
	}

	/************************************/
	
	public Object[] resultSets() {
		return new Object[]{
				makeResultSet(
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						"EEEEEE",
						0
					),
				makeResultSet(
						Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
						Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
						"EEEESS",
						2
					),
				makeResultSet(
						Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
						Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
						"EESESSSS",
						5
					),	
				makeResultSet(
						Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
						Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
						"ESSESSS",
						5
					),
				makeResultSet(
						Arrays.asList('w', 'o', 'r', 'd'),
						Arrays.asList('7', ';', '$', 'Ð'),
						"SSSS",
						4
					),
				makeResultSet(
						Arrays.asList('a', 'b', 'b', 'c', 'b'),
						Arrays.asList('a', 'b', 'c', 'a', 'b'),
						"EESSE",
						2
					),
				makeResultSet(
						Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
								  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
								  'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
						Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
								  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
								  'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
						"EESSSSSSSSSSEEEEESEESESEEEEEEEEEE",
						13
					),	
		};
	}
	
	public List<Object[]> sequences() {
		return Arrays.asList(
				new Object[]{
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),						
				},
				new Object[]{
						Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
						Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),					
				},
				new Object[]{
						Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
						Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),						
				},
				new Object[]{
						Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
						Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),						
				},
				new Object[]{
						Arrays.asList('w', 'o', 'r', 'd'),
						Arrays.asList('7', ';', '$', 'Ð'),							
				},
				new Object[]{
						Arrays.asList('a', 'b', 'b', 'c', 'b'),
						Arrays.asList('a', 'b', 'c', 'a', 'b'),						
				},
				new Object[]{
						Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
								  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
								  'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
						Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
								  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
								  'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
				}
			);
	}
	
	public Object[] weightDistances() {
		return new Object[]{
				0, 4, 10, 10, 8, 4, 26,
		};
	}
}
