package distance.info;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import distance.info.JaroDistanceInfo;
import exception.InvalidOpeationCostException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import structures.DistanceMatrixResult;
import structures.DistanceResult;
import support.JaroValues;
import support.Matrix;
import support.AbstractMetricInfoTest;
import support.Tuple2;

@RunWith(JUnitParamsRunner.class)
public class JaroDistanceInfoTest extends AbstractMetricInfoTest {
	
	@Test(expected=InvalidOpeationCostException.class)
	public void testConstructorThrowWhenCostIsNotPositive(){
		new JaroDistanceInfo<>(' ', 0, 1, 0);
	}
		
	@Test
	@Parameters
	public void testWeightDistance(
			List<Character> from,
			List<Character> to,
			double weightDistance){
		DistanceResult<Character, DistanceMatrixResult<JaroValues>> res = 
				new JaroDistanceInfo<>(' ', 1/5.0, 2/5.0, 2/5.0).calculate(from, to);
		assertEquals(weightDistance, res.getDistance().doubleValue(), 0.00001);
	}
	
	public Object[] parametersForTestWeightDistance() {
		return parameters(3);
	}

	@Test
	@Parameters
	public void testCalculateDistanceWorks(
			List<Character> from,
			List<Character> to,
			DistanceResult<Character, DistanceMatrixResult<JaroValues>> expected){
		DistanceResult<Character, DistanceMatrixResult<JaroValues>> actual = new JaroDistanceInfo<>(' ').calculate(from, to);
		assertEquals(expected, actual);
	}
	
	/********************************************/
	
	public Object[] parametersForTestCalculateDistanceWorks() {
		return parameters(2);
	}
	
	/*******************************************/
	
	@Override
	public List<Object[]> resultSet() {
		return Arrays.asList(
				//equals
				new Object[]{
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					new DistanceResult<>(
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						"Jaro distance",
						"EEEEEE",
						1.0,
						new DistanceMatrixResult<>(
									new Matrix<>(new JaroValues[][]{
										{JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
										{JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
										{JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL},
										{JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL},
										{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE},
										{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE},													
									}),
									0,
									null,
									Arrays.asList(
											new Tuple2<>(0, 0),
											new Tuple2<>(1, 1),
											new Tuple2<>(2, 2),
											new Tuple2<>(3, 3),
											new Tuple2<>(4, 4),
											new Tuple2<>(5, 5)
											)
								)
					),
					1.0
				},
				//different
				new Object[]{
					Arrays.asList('a', 'a', 'a'),
					Arrays.asList('b', 'b', 'b'),
					new DistanceResult<>(
							Arrays.asList(' ', 'a', ' ', 'a', ' ', 'a'),
							Arrays.asList('b', ' ', 'b', ' ', 'b', ' '),
							"Jaro distance",
							"IDIDID",
							0.0,
							new DistanceMatrixResult<>(
									new Matrix<>(new JaroValues[][]{
										{JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL,},
										{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE},
										{JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE},										
									}),
									0,
									null,
									Arrays.asList(
											new Tuple2<>(-1, 0),
											new Tuple2<>(0, 0),
											new Tuple2<>(0, 1),
											new Tuple2<>(1, 1),
											new Tuple2<>(1, 2),
											new Tuple2<>(2, 2)
									)
							)
					),
					0
				},
				//substitution
				new Object[]{
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					Arrays.asList('s', 't', 'r', 'y', 'n', 'g'),
					new DistanceResult<>(
						Arrays.asList('s', 't', 'r', ' ', 'i', 'n', 'g'),
						Arrays.asList('s', 't', 'r', 'y', ' ', 'n', 'g'),
						"Jaro distance",
						"EEEIDEE",
						0.88888, 
						new DistanceMatrixResult<>(
							new Matrix<>(
								new JaroValues[][]{
									{JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
									{JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
									{JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, },
									{JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, },
									{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, },
									{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, },								
								}
							),
							0,
							null,
							Arrays.asList(
								new Tuple2<Integer, Integer>(0, 0),
								new Tuple2<Integer, Integer>(1, 1),
								new Tuple2<Integer, Integer>(2, 2),
								new Tuple2<Integer, Integer>(2, 3),
								new Tuple2<Integer, Integer>(3, 3),								
								new Tuple2<Integer, Integer>(4, 4),
								new Tuple2<Integer, Integer>(5, 5)
							)
						)
					),
					0.9
				},
				//insertion and deletion
				new Object[]{
						Arrays.asList('s', 't', 'r', 'n', 'g', 'a'),
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						new DistanceResult<>(
							Arrays.asList('s', 't', 'r', ' ', 'n', 'g', 'a'),
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g', ' '),
							"Jaro distance",
							"EEEIEED",
							0.88888,
							new DistanceMatrixResult<>(
								new Matrix<>(
									new JaroValues[][]{
										{JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
										{JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
										{JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL},
										{JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.NULL},
										{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE},
										{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE}
									}
								),
								0,
								null,
								Arrays.asList(
									new Tuple2<Integer, Integer>(0, 0),
									new Tuple2<Integer, Integer>(1, 1),
									new Tuple2<Integer, Integer>(2, 2),
									new Tuple2<Integer, Integer>(2, 3),
									new Tuple2<Integer, Integer>(3, 4),
									new Tuple2<Integer, Integer>(4, 5),
									new Tuple2<Integer, Integer>(5, 5)
								)
							)
					),
					0.9
				},	
				//transposition
				new Object[]{
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('t', 's', 'r', 'i', 'n', 'g'),
						new DistanceResult<>(
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							Arrays.asList('t', 's', 'r', 'i', 'n', 'g'),
							"Jaro distance",
							"TTEEEE",
							0.94444,
							new DistanceMatrixResult<>(
								new Matrix<>(
									new JaroValues[][]{
										{JaroValues.FALSE, JaroValues.TRUE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
										{JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
										{JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, },
										{JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, },
										{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, },
										{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, },
									}
								),
								0,
								null,
								Arrays.asList(
									new Tuple2<Integer, Integer>(0, 1),
									new Tuple2<Integer, Integer>(1, 0),									
									new Tuple2<Integer, Integer>(2, 2),
									new Tuple2<Integer, Integer>(3, 3),
									new Tuple2<Integer, Integer>(4, 4),
									new Tuple2<Integer, Integer>(5, 5)
								)
							)
						),
						0.93333
				},		
				//so long sequence
				new Object[]{
						Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
									  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
									  'a', 't', 'e', 'l', 'n', 'ì', 'j', '', 'í', 'h', 'o'),
						Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
									  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
									  'á', 't', 'e', 'l', 'n', 'ì', 'j', '', 'í', 'h', 'o'),
						new DistanceResult<>(
								Arrays.asList('n', 'e', 'j', 'n', 'e', ' ', 'z', 'p', 'r', 'a', 'v', 'd', ' ', 'ì', ' ', 'p', 'o', 'd', 'o', 'b', ' ', 'ò', 'o', 'v', ' ', 'á', 'v', ' ', 'a', 't', 'e', 'l', 'n', 'ì', 'j', '', 'í', 'h', 'o'),
								Arrays.asList('n', 'e', ' ', 'n', 'e', 's', ' ', 'p', 'r', 'a', 'v', 'd', 'j', ' ', 'e', 'p', 'o', 'd', 'o', 'b', 'n', ' ', 'o', 'v', 'a', ' ', 'v', 'á', ' ', 't', 'e', 'l', 'n', 'ì', 'j', '', 'í', 'h', 'o'),
								"Jaro distance",
							     "EEDEEIDEEEEEIDIEEEEEIDEEIDEIDEEEEEEEEEE",                             
								0.87878,
								new DistanceMatrixResult<>(
										new Matrix<>(new JaroValues[][]{
											{JaroValues.TRUE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.TRUE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, },
											{JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, },
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, },
										}),
										0,
										null,
										Arrays.asList(
												new Tuple2<>(0, 0),
												new Tuple2<>(1, 1),
												new Tuple2<>(2, 1),
												new Tuple2<>(3, 2),
												new Tuple2<>(4, 3),
												new Tuple2<>(4, 4),
												new Tuple2<>(5, 4),
												new Tuple2<>(6, 5),
												new Tuple2<>(7, 6),
												new Tuple2<>(8, 7),
												new Tuple2<>(9, 8),
												new Tuple2<>(10, 9),
												new Tuple2<>(10, 10),
												new Tuple2<>(11, 10),
												new Tuple2<>(11, 11),
												new Tuple2<>(12, 12),
												new Tuple2<>(13, 13),
												new Tuple2<>(14, 14),
												new Tuple2<>(15, 15),
												new Tuple2<>(16, 16),								
												new Tuple2<>(16, 17),
												new Tuple2<>(17, 17),
												new Tuple2<>(18, 18),
												new Tuple2<>(19, 19),
												new Tuple2<>(19, 20),													
												new Tuple2<>(20, 20),
												new Tuple2<>(21, 21),
												new Tuple2<>(21, 22),
												new Tuple2<>(22, 22),
												new Tuple2<>(23, 23),
												new Tuple2<>(24, 24),
												new Tuple2<>(25, 25),
												new Tuple2<>(26, 26),
												new Tuple2<>(27, 27),
												new Tuple2<>(28, 28),
												new Tuple2<>(29, 29),
												new Tuple2<>(30, 30),
												new Tuple2<>(31, 31),
												new Tuple2<>(32, 32)
											)
									)
							),
						0.89090
				},		
				//kolinn vs kpollim
				new Object[]{
						Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
						Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
						new DistanceResult<>(
								Arrays.asList('k', 'o', 'p', 'l', ' ', 'i', ' ', 'n', 'n'),
								Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm', ' ', ' '),
								"Jaro distance",
								"ETTEIEIDD",
								0.74285,
								new DistanceMatrixResult<>(
										new Matrix<>(new JaroValues[][]{
											{JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
											{JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
											{JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL},
											{JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL},
											{JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE},
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE},
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE},												
										}),
										0,
										null,
										Arrays.asList(
												new Tuple2<>(0, 0),
												new Tuple2<>(1, 2),
												new Tuple2<>(2, 1),
												new Tuple2<>(3, 3),
												new Tuple2<>(3, 4),
												new Tuple2<>(4, 5),
												new Tuple2<>(4, 6),
												new Tuple2<>(5, 6),
												new Tuple2<>(6, 6)
												)
									)
						),
						0.748571
				},
				//koolipan vs kopllisa
				new Object[]{
						Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
						Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
						new DistanceResult<>(
								Arrays.asList('k', 'o', ' ', 'o', 'l', ' ', 'i', ' ', 'p', 'a', 'n'),
								Arrays.asList('k', 'o', 'p', ' ', 'l', 'l', 'i', 's', ' ', 'a', ' '),
								"Jaro distance",
								"EEIDEIEIDED",
								0.75,
								new DistanceMatrixResult<>(
										new Matrix<>(new JaroValues[][]{
											{JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
											{JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
											{JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
											{JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL},
											{JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL},
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE},
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE},
											{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE},												
										}),
										0,
										null,
										Arrays.asList(
												new Tuple2<>(0, 0),
												new Tuple2<>(1, 1),
												new Tuple2<>(1, 2),
												new Tuple2<>(2, 2),
												new Tuple2<>(3, 3),
												new Tuple2<>(3, 4),
												new Tuple2<>(4, 5),
												new Tuple2<>(4, 6),
												new Tuple2<>(5, 6),
												new Tuple2<>(6, 7),
												new Tuple2<>(7, 7)
												)
									)
							),
						0.775
				},
				/************/
				//second empty
				new Object[]{
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						new LinkedList<>(),
						new DistanceResult<>(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList(' ', ' ', ' ', ' ', ' '),
								"Jaro distance",
								"DDDDD",
								0.0,
								new DistanceMatrixResult<>(
										new Matrix<>(new JaroValues[][]{
											{},
											{},
											{},
											{},
											{}
										}),
										0,
										null,
										Arrays.asList(
												new Tuple2<>(0, -1),
												new Tuple2<>(1, -1),
												new Tuple2<>(2, -1),
												new Tuple2<>(3, -1),
												new Tuple2<>(4, -1)
											)
									)
						),
						0
				},
				//first empty				
				new Object[]{
						new LinkedList<>(),
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						new DistanceResult<>(
								Arrays.asList(' ', ' ', ' ', ' ', ' '),
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								"Jaro distance",
								"IIIII",
								0.0,
								new DistanceMatrixResult<>(
										new Matrix<>(new JaroValues[][]{}),
										0,
										null,
										Arrays.asList(
												new Tuple2<>(-1, 0),
												new Tuple2<>(-1, 1),
												new Tuple2<>(-1, 2),
												new Tuple2<>(-1, 3),
												new Tuple2<>(-1, 4)
											)
									)
						),
						0
				},
				//special cases	
				new Object[]{
						Arrays.asList('k', 'o', 'l', 'o'),
						Arrays.asList('o', 'k', 'o'),
						new DistanceResult<>(
								Arrays.asList('k', 'o', 'l', 'o'),
								Arrays.asList('o', 'k', ' ', 'o'),
								"Jaro distance",
								"TTDE",
								0.80555,
								new DistanceMatrixResult<>(
										new Matrix<>(new JaroValues[][]{
											{JaroValues.FALSE, JaroValues.TRUE, JaroValues.NULL},
											{JaroValues.TRUE, JaroValues.FALSE, JaroValues.TRUE},
											{JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE},
											{JaroValues.NULL, JaroValues.NULL, JaroValues.TRUE}												
										}),
										0,
										null,
										Arrays.asList(
												new Tuple2<>(0, 1),
												new Tuple2<>(1, 0),
												new Tuple2<>(2, 1),
												new Tuple2<>(3, 2)
												)
									)
						),
						0.81666
				},
				new Object[]{
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						Arrays.asList('l'),
						new DistanceResult<>(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList(' ', 'l', ' ', ' ', ' '),
								"Jaro distance",
								"DEDDD",
								0.73333,
								new DistanceMatrixResult<>(
										new Matrix<>(new JaroValues[][]{
											{JaroValues.FALSE},
											{JaroValues.TRUE},
											{JaroValues.NULL},
											{JaroValues.NULL},
											{JaroValues.NULL},
										}),
										0,
										null,
										Arrays.asList(
												new Tuple2<>(0, -1),
												new Tuple2<>(1, 0),
												new Tuple2<>(2, 0),
												new Tuple2<>(3, 0),
												new Tuple2<>(4, 0)
												)
									)
						),
						0.84
				},
				new Object[]{
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						Arrays.asList('s', 'l'),
						new DistanceResult<>(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList('s', 'l', ' ', ' ', ' '),
								"Jaro distance",
								"EEDDD",
								0.8,
								new DistanceMatrixResult<>(
										new Matrix<>(new JaroValues[][]{
											{JaroValues.TRUE, JaroValues.FALSE},
											{JaroValues.FALSE, JaroValues.TRUE},
											{JaroValues.NULL, JaroValues.FALSE},
											{JaroValues.NULL, JaroValues.NULL},
											{JaroValues.NULL, JaroValues.NULL}
										}),
										0,
										null,
										Arrays.asList(
												new Tuple2<>(0, 0),
												new Tuple2<>(1, 1),
												new Tuple2<>(2, 1),
												new Tuple2<>(3, 1),
												new Tuple2<>(4, 1)
												)
									)
						),
						0.88
				},
				new Object[]{
						Arrays.asList('s', 'l', 'o', 'v', 'a'),
						Arrays.asList('a'),
						new DistanceResult<>(
								Arrays.asList(' ', 's', 'l', 'o', 'v', 'a'),
								Arrays.asList('a', ' ', ' ', ' ', ' ', ' '),
								"Jaro distance",
								"IDDDDD",
								0.0,
								new DistanceMatrixResult<>(
										new Matrix<>(new JaroValues[][]{
											{JaroValues.FALSE},
											{JaroValues.FALSE},
											{JaroValues.NULL},
											{JaroValues.NULL},
											{JaroValues.NULL},												
										}),
										0,
										null,
										Arrays.asList(
												new Tuple2<>(-1, 0),
												new Tuple2<>(0, 0),
												new Tuple2<>(1, 0),
												new Tuple2<>(2, 0),
												new Tuple2<>(3, 0),
												new Tuple2<>(4, 0)
												)
									)
						),
						0
				}
			);
	}
}
