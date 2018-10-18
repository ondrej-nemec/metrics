package distance.info;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import distance.info.JaroWinklerDistanceInfo;
import exception.InvalidOpeationCostException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import structures.DistanceMatrixResultSet;
import structures.DistanceResultSet;
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
		DistanceResultSet<Character, DistanceMatrixResultSet<JaroValues>> res = 
				new JaroWinklerDistanceInfo<>(' ', 0.2).calculate(from, to);
		assertEquals(weightDistance, res.getDistance().doubleValue(), 0.00001);
	}
	
	
	public Object[] parametersForTestWeightDistance() {
		return parameters(3);
	}
	
	@Test
	@Parameters
	public void testCalculateDistance(
			List<Character> from,
			List<Character> to,
			double distance){
		DistanceResultSet<Character, DistanceMatrixResultSet<JaroValues>> res = 
				new JaroWinklerDistanceInfo<>(' ').calculate(from, to);
		assertEquals(distance, res.getDistance().doubleValue(), 0.00001);
	}
	
	public Object[] parametersForTestCalculateDistance() {
		return parameters(2);
	}

	/**************************************************/
	
	@Override
	public List<Object[]> resultSet() {
		return Arrays.asList(
				//equals
				new Object[]{
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					1.0,
					1.0
				},
				//different
				new Object[]{
					Arrays.asList('a', 'a', 'a'),
					Arrays.asList('b', 'b', 'b'),
					0,
					0
				},
				//substitution
				new Object[]{
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					Arrays.asList('s', 't', 'r', 'y', 'n', 'g'),
					0.92222,
					0.95555
				},
				//insertion and deletion
				new Object[]{
						Arrays.asList('s', 't', 'r', 'n', 'g', 'a'),
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						0.92222,
						0.95555
						
				},	
				//transposition
				new Object[]{
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('t', 's', 'r', 'i', 'n', 'g'),
						0.94444,
						0.94444
				},		
				//so long sequence
				new Object[]{
						Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
									  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
									  'a', 't', 'e', 'l', 'n', 'ì', 'j', '', 'í', 'h', 'o'),
						Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
									  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
									  'á', 't', 'e', 'l', 'n', 'ì', 'j', '', 'í', 'h', 'o'),
						0.90303,
						0.92727
				},		
				//kolinn vs kpollim
				new Object[]{
						Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
						Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
						0.76857,
						0.79428
				},
				//koolipan vs kopllisa
				new Object[]{
						Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
						Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
						0.8,
						0.85
				},
				/************/
				//second empty
				new Object[]{
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						new LinkedList<>(),
						0,
						0
				},
				//first empty				
				new Object[]{
						new LinkedList<>(),
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						0,
						0
				},
				//special cases	
				new Object[]{
						Arrays.asList('k', 'o', 'l', 'o'),
						Arrays.asList('o', 'k', 'o'),
						0.80555,
						0.80555
				},
				new Object[]{
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						Arrays.asList('l'),
						0.73333,
						0.73333
				},
				new Object[]{
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						Arrays.asList('s', 'l'),
						0.84,
						0.88
				},
				new Object[]{
						Arrays.asList('s', 'l', 'o', 'v', 'a'),
						Arrays.asList('a'),
						0.0,
						0
				}
			);
	}
}
