package metricinfo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import exception.InvalidOpeationCostException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import structures.MatrixResultSet;
import structures.ResultSet;
import support.JaroValues;
import support.AbstractMetricInfoTest;


@RunWith(JUnitParamsRunner.class)
public class JaroWinklerDistanceInfoTest extends AbstractMetricInfoTest {
	
	@Test(expected=InvalidOpeationCostException.class)
	public void testConstructorThrowWhenCostIsNotPositive(){
		new JaroWinklerDistanceInfo<>(' ', 1);
	}
	
	@Test
	@Parameters
	public void testWeightDistance(
			List<Character> from,
			List<Character> to,
			double weightDistance){
		ResultSet<Character, MatrixResultSet<JaroValues>> res = 
				new JaroWinklerDistanceInfo<>(' ', 0.2).calculate(from, to);
		assertEquals(weightDistance, res.getDistance().doubleValue(), 0.00001);
	}
	
	
	public Object[] parametersForTestWeightDistance() {
		return makeParams(weightDistances());
	}
	
	@Test
	@Parameters
	public void testCalculateDistance(
			List<Character> from,
			List<Character> to,
			double distance){
		ResultSet<Character, MatrixResultSet<JaroValues>> res = 
				new JaroWinklerDistanceInfo<>(' ').calculate(from, to);
		assertEquals(distance, res.getDistance());
	}
	
	public Object[] parametersForTestCalculateDistance() {
		return makeParams(resultSets());
	}

	/**************************************************/
	
	public Object[] resultSets() {
		return new Object[]{
				1.0, 0.0, 0.0, 0.7333333333333333, 0.8400000000000001, 0.0, 0.6111111111111112, 
				0.8055555555555555, 0.65, 0.8666666666666666, 0.8, 0.7685714285714286,
				0.0, 0.8933333333333333, 0.84, 0.903030303030303, 
		};
	}
	
	public List<Object[]> sequences() {
		return Arrays.asList(
				new Object[]{
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
				},
				new Object[]{
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						new LinkedList<>(),
				},
				new Object[]{
						new LinkedList<>(),
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
				},
				new Object[]{
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						Arrays.asList('l'),
				},
				new Object[]{
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						Arrays.asList('s', 'l'),
				},
				new Object[]{
						Arrays.asList('s', 'l', 'o', 'v', 'a'),
						Arrays.asList('a'),
				},
				new Object[]{
						Arrays.asList('o', 'k', 'n', 'o'),
						Arrays.asList('w', 'i', 'n', 'd', 'o', 'w'),
				},
				new Object[]{
						Arrays.asList('k', 'o', 'l', 'o'),
						Arrays.asList('o', 'k', 'o'),
				},
				new Object[]{
						Arrays.asList('n', 'e', 'n', 'í'),
						Arrays.asList('n', 'i', 'e', ' ', 'j', 'e'),
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
						Arrays.asList('7', ';', '$', 'í'),
				},
				new Object[]{
						Arrays.asList('a', 'b', 'b', 'c', 'b'),
						Arrays.asList('a', 'b', 'c', 'a', 'b'),
				},
				new Object[]{
						Arrays.asList('a', 'a', 'h', 'o', 'j'),
						Arrays.asList('a', 'h', 'o', 'j', 'k', 'y'),
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
				1.0, 0, 0, 0.7333333333333333, 0.88, 0, 0.61111111111,
				0.80555555555555, 0.68888888888, 0.9555555555, 0.85, 0.7942857142857143,
				0, 0.92, 0.85777777777, 0.927272727, 
		};
	}
}
