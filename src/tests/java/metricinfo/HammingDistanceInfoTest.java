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

@RunWith(JUnitParamsRunner.class)
public class HammingDistanceInfoTest {

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
			List<Character> from, List<Character> to, ResultSet<Character, String> result, int weightDistance){
		ResultSet<Character, String> res = 
				new HammingDistanceInfo<Character>(2).calculate(from, to);
		assertEquals(weightDistance, res.getDistance());
	}
	
	public Object[] parametersForTestWeightDistance() {
		return dataProvider();
	}

	@Test
	@Parameters
	public void testCalculateFinalSequence(
			List<Character> from, List<Character> to, ResultSet<Character, String> result, int weightDistance) {
		ResultSet<Character, String> metric = new HammingDistanceInfo<Character>().calculate(from, to);
		assertEquals(
				result.getFinalSequenceFrom(), 
				metric.getFinalSequenceFrom()
			);		
		assertEquals(
				result.getFinalSequenceTo(),
				metric.getFinalSequenceTo()
			);
	}
	
	public Object[] parametersForTestCalculateFinalSequence() {
		return dataProvider();
	}
	
	@Test
	@Parameters
	public void testCalculateOperations(
			List<Character> from, List<Character> to, ResultSet<Character, String> result, int weightDistance){
		ResultSet<Character, String> metric = new HammingDistanceInfo<Character>().calculate(from, to);
		assertEquals(result.getOperations(), metric.getOperations());
	}
	
	public Object[] parametersForTestCalculateOperations() {
		return dataProvider();
	}
	
	@Test
	@Parameters
	public void testCalculateDistance(
			List<Character> from, List<Character> to, ResultSet<Character, String> result, int weightDistance){
		ResultSet<Character, String> metric = new HammingDistanceInfo<Character>().calculate(from, to);
		assertEquals(result.getDistance(), metric.getDistance());
	}
	
	public Object[] parametersForTestCalculateDistance() {
		return dataProvider();
	}
	
	@Test
	@Parameters
	public void testCalculateStructure(
			List<Character> from, List<Character> to, ResultSet<Character, String> result, int weightDistance){
		ResultSet<Character, String> metric = new HammingDistanceInfo<Character>().calculate(from, to);
		assertEquals(result.getStructure(), metric.getStructure());
	}
	
	public Object[] parametersForTestCalculateStructure() {
		return dataProvider();
	}

	private Object[] dataProvider() {
		return new Object[] {
					new Object[]{
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							makeResultSet(
									Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
									Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
									"EEEEEE",
									0
								),
							0
					},
					new Object[]{
							Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
							Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
							makeResultSet(
									Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
									Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
									"EEEESS",
									2
								),
							4
					},
					new Object[]{
							Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
							Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
							makeResultSet(
									Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
									Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
									"EESESSSS",
									5
								),
							10
					},
					new Object[]{
							Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
							Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
							makeResultSet(
									Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
									Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
									"ESSESSS",
									5
								),
							10
					},
					new Object[]{
							Arrays.asList('w', 'o', 'r', 'd'),
							Arrays.asList('7', ';', '$', 'Ð'),
							makeResultSet(
									Arrays.asList('w', 'o', 'r', 'd'),
									Arrays.asList('7', ';', '$', 'Ð'),
									"SSSS",
									4
								),
							8
					},
					new Object[]{
							Arrays.asList('a', 'b', 'b', 'c', 'b'),
							Arrays.asList('a', 'b', 'c', 'a', 'b'),
							makeResultSet(
									Arrays.asList('a', 'b', 'b', 'c', 'b'),
									Arrays.asList('a', 'b', 'c', 'a', 'b'),
									"EESSE",
									2
								),
							4
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
								),
							26
					}				
		};
	}


	private ResultSet<Character, String> makeResultSet(List<Character> from, List<Character> to, String operations, int distance) {
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
