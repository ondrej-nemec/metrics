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


@RunWith(JUnitParamsRunner.class)
public class JaroWinklerInfoTest {
	
	@Test(expected=InvalidOpeationCostException.class)
	public void testConstructorThrowWhenCostIsNotPositive(){
		new JaroWinklerInfo<>(' ', 1);
	}
	
	@Test
	@Parameters
	public void testWeightDistance(
			List<Character> from, List<Character> to, double distance, double weightDistance){
		ResultSet<Character, MatrixResultSet<JaroValues>> res = 
				new JaroWinklerInfo<>(' ', 0.2).calculate(from, to);
		assertEquals(weightDistance, res.getDistance().doubleValue(), 0.00001);
	}
	
	
	public Object[] parametersForTestWeightDistance() {
		return dataProvider();
	}
	
	@Test
	@Parameters
	public void testCalculateDistance(
			List<Character> from, List<Character> to, double distance, double weightDistance){
		ResultSet<Character, MatrixResultSet<JaroValues>> res = 
				new JaroWinklerInfo<>(' ').calculate(from, to);
		assertEquals(distance, res.getDistance());
	}
	
	public Object[] parametersForTestCalculateDistance() {
		return dataProvider();
	}
	
	public Object[] dataProvider() {
		return new Object[]{
					new Object[]{
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							1.0,
							1.0
					},
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							new LinkedList<>(),
							0.0,
							0
					},
					new Object[]{
							new LinkedList<>(),
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							0.0,
							0
					},
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							Arrays.asList('l'),
							0.7333333333333333,
							0.7333333333333333
					},
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							Arrays.asList('s', 'l'),
							0.8400000000000001,
							0.88
					},
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'a'),
							Arrays.asList('a'),
							0.0,
							0
					},
					new Object[]{
							Arrays.asList('o', 'k', 'n', 'o'),
							Arrays.asList('w', 'i', 'n', 'd', 'o', 'w'),
							0.6111111111111112,
							0.61111111111
					},
					new Object[]{
							Arrays.asList('k', 'o', 'l', 'o'),
							Arrays.asList('o', 'k', 'o'),
							0.8055555555555555,
							0.80555555555555
					},
					new Object[]{
							Arrays.asList('n', 'e', 'n', 'í'),
							Arrays.asList('n', 'i', 'e', ' ', 'j', 'e'),
							0.65,
							0.68888888888
					},
					new Object[]{
							Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
							Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
							0.8666666666666666,
							0.9555555555
					},
					new Object[]{
							Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
							Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
							0.8,
							0.85
					},
					new Object[]{
							Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
							Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
							0.7685714285714286,
							0.7942857142857143
					},
					new Object[]{
							Arrays.asList('w', 'o', 'r', 'd'),
							Arrays.asList('7', ';', '$', 'í'),
							0.0,
							0
					},
					new Object[]{
							Arrays.asList('a', 'b', 'b', 'c', 'b'),
							Arrays.asList('a', 'b', 'c', 'a', 'b'),
							0.8933333333333333,
							0.92
					},
					new Object[]{
							Arrays.asList('a', 'a', 'h', 'o', 'j'),
							Arrays.asList('a', 'h', 'o', 'j', 'k', 'y'),
							0.84,
							0.85777777777
					},
					new Object[]{
							Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
									  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
									  'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
							Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
									  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
									  'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
							0.903030303030303,
							0.927272727
					}				
		};
	}
}
