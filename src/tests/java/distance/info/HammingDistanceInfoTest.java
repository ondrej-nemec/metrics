package distance.info;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import distance.info.HammingDistanceInfo;
import exception.InvalidOpeationCostException;
import exception.SequencesMustHaveSameLengthException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import structures.DistanceResult;
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
		DistanceResult<Character, String> res = 
				new HammingDistanceInfo<Character>(2).calculate(from, to);
		assertEquals(weightDistance, res.getDistance());
	}
	
	public Object[] parametersForTestWeightDistance() {
		return parameters(3);
	}

	@Test
	@Parameters
	public void testCalculateWorks(
			List<Character> from,
			List<Character> to,
			DistanceResult<Character, String> result) {
		DistanceResult<Character, String> metric = new HammingDistanceInfo<Character>().calculate(from, to);
		assertEquals(result, metric);
	}
	
	public Object[] parametersForTestCalculateWorks() {
		return parameters(2);
	}	
	
	/********************************/
	
	private DistanceResult<Character, String> makeResultSet(
			List<Character> from,
			List<Character> to, 
			String operations,
			int distance) {
		return new DistanceResult<>(
				from, 
				to,
				"Hamming distance",
				operations,
				distance,
				operations
			);
	}

	/************************************/
		
	@Override
	public List<Object[]> resultSet() {
		return Arrays.asList(
				//equals
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
				//different
				new Object[]{
					Arrays.asList('a', 'a', 'a'),
					Arrays.asList('b', 'b', 'b'),
					makeResultSet(
							Arrays.asList('a', 'a', 'a'),
							Arrays.asList('b', 'b', 'b'),
							"SSS",
							3
						),
					6
				},
				//substitution
				new Object[]{
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					Arrays.asList('s', 't', 'r', 'y', 'n', 'g'),
					makeResultSet(
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							Arrays.asList('s', 't', 'r', 'y', 'n', 'g'),
							"EEESEE",
							1
						),
					2
				},
				//insertion and deletion
				new Object[]{
						Arrays.asList('s', 't', 'r', 'n', 'g', 'a'),
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						makeResultSet(
								Arrays.asList('s', 't', 'r', 'n', 'g', 'a'),
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
								"EEESSS",
								3
							),
						6
					},		
				//transposition
				new Object[]{
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('t', 's', 'r', 'i', 'n', 'g'),
						makeResultSet(
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
								Arrays.asList('t', 's', 'r', 'i', 'n', 'g'),
								"SSEEEE",
								2
							),
						4
					},				
				//so long sequence
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
				},
				//koolipan vs kopllisa
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
				//kolinn vs kpollim
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
				}
			);
	}
}
