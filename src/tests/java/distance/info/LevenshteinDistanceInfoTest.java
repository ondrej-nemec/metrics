package distance.info;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import distance.info.LevenshteinDistanceInfo;
import exception.InvalidOpeationCostException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import structures.DistanceMatrixResult;
import structures.DistanceResult;
import support.Matrix;
import support.AbstractMetricInfoTest;
import support.Tuple2;

@RunWith(JUnitParamsRunner.class)
public class LevenshteinDistanceInfoTest extends AbstractMetricInfoTest {

	@Test(expected=InvalidOpeationCostException.class)
	public void testConstructorThrowWhenCostIsNotPositive(){
		new LevenshteinDistanceInfo<>(' ', 0, -1, 0);
	}
	
	@Test
	@Parameters
	public void testWeightDistance(
			List<Character> from, List<Character> to,
			int weightDistance){
		DistanceResult<Character, DistanceMatrixResult<Tuple2<Integer, Boolean>>> res = 
				new LevenshteinDistanceInfo<>(' ', 2, 2, 2).calculate(from, to);
		assertEquals(weightDistance, res.getDistance());
	}
	
	public Object[] parametersForTestWeightDistance() {
		return parameters(3);
	}
	
	@Test
	@Parameters
	public void testCalculateWorks(
			List<Character> from, List<Character> to,
			DistanceResult<Character, DistanceMatrixResult<Tuple2<Integer, Boolean>>> result) {
		DistanceResult<Character, DistanceMatrixResult<Tuple2<Integer, Boolean>>> actual =
				new LevenshteinDistanceInfo<>(' ').calculate(from, to);
		assertEquals(result, actual);
	}
	
	public Object[] parametersForTestCalculateWorks() {
		return parameters(2);
	}

	
	/*******************************************/
			
	private Tuple2<Integer, Boolean> t(int i, boolean b){
		return new Tuple2<>(i, b);
	}
	
	/***********************************************/
	
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
						"Levenshtein distance",
						"EEEEEE",
						0,
						new DistanceMatrixResult<>(
							new Matrix<>(
								new Tuple2[][]{
									{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false)},
									{t(1, false), t(0, true), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false)},
									{t(2, false), t(1, false), t(0, true), t(1, false), t(2, false), t(3, false), t(4, false),},
									{t(3, false), t(2, false), t(1, false), t(0, true), t(1, false), t(2, false), t(3, false),},
									{t(4, false), t(3, false), t(2, false), t(1, false), t(0, true), t(1, false), t(2, false),},
									{t(5, false), t(4, false), t(3, false), t(2, false), t(1, false), t(0, true), t(1, false),},
									{t(6, false),t(5, false), t(4, false), t(3, false), t(2, false), t(1, false), t(0, true),},
								}
							),
							1,
							null,
							Arrays.asList(
								new Tuple2<Integer, Integer>(0, 0),
								new Tuple2<Integer, Integer>(1, 1),
								new Tuple2<Integer, Integer>(2, 2),
								new Tuple2<Integer, Integer>(3, 3),
								new Tuple2<Integer, Integer>(4, 4),
								new Tuple2<Integer, Integer>(5, 5),
								new Tuple2<Integer, Integer>(6, 6)
							)
						)
					),
					0
				},
				//different
				new Object[]{
					Arrays.asList('a', 'a', 'a'),
					Arrays.asList('b', 'b', 'b'),
					new DistanceResult<>(
						Arrays.asList('a', 'a', 'a'),
						Arrays.asList('b', 'b', 'b'),
						"Levenshtein distance",
						"SSS",
						3,
						new DistanceMatrixResult<>(
							new Matrix<>(
								new Tuple2[][]{
									{t(0, false), t(1, false), t(2, false), t(3, false)},
									{t(1, false), t(1, false), t(2, false), t(3, false)},
									{t(2, false), t(2, false), t(2, false), t(3, false)},
									{t(3, false), t(3, false), t(3, false), t(3, false)},
								}
							),
							1,
							null,
							Arrays.asList(
								new Tuple2<Integer, Integer>(0, 0),
								new Tuple2<Integer, Integer>(1, 1),
								new Tuple2<Integer, Integer>(2, 2),
								new Tuple2<Integer, Integer>(3, 3)
							)
						)
					),
					6
				},
				//substitution
				new Object[]{
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					Arrays.asList('s', 't', 'r', 'y', 'n', 'g'),
					new DistanceResult<>(
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('s', 't', 'r', 'y', 'n', 'g'),
						"Levenshtein distance",
						"EEESEE",
						1,
						new DistanceMatrixResult<>(
							new Matrix<>(
								new Tuple2[][]{
									{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false), },
									{t(1, false), t(0, true), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), },
									{t(2, false), t(1, false), t(0, true), t(1, false), t(2, false), t(3, false), t(4, false), },
									{t(3, false), t(2, false), t(1, false), t(0, true), t(1, false), t(2, false), t(3, false), },
									{t(4, false), t(3, false), t(2, false), t(1, false), t(1, false), t(2, false), t(3, false), },
									{t(5, false), t(4, false), t(3, false), t(2, false), t(2, false), t(1, true), t(2, false), },
									{t(6, false), t(5, false), t(4, false), t(3, false), t(3, false), t(2, false), t(1, true), },
								}
							),
							1,
							null,
							Arrays.asList(
								new Tuple2<Integer, Integer>(0, 0),
								new Tuple2<Integer, Integer>(1, 1),
								new Tuple2<Integer, Integer>(2, 2),
								new Tuple2<Integer, Integer>(3, 3),
								new Tuple2<Integer, Integer>(4, 4),
								new Tuple2<Integer, Integer>(5, 5),
								new Tuple2<Integer, Integer>(6, 6)
							)
						)
					),
					2
				},
				//insertion and deletion
				new Object[]{
						Arrays.asList('s', 't', 'r', 'n', 'g', 'a'),
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						new DistanceResult<>(
							Arrays.asList('s', 't', 'r', ' ', 'n', 'g', 'a'),
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g', ' '),
							"Levenshtein distance",
							"EEEIEED",
							2,
							new DistanceMatrixResult<>(
								new Matrix<>(
									new Tuple2[][]{
										{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false), },
										{t(1, false), t(0, true), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), },
										{t(2, false), t(1, false), t(0, true), t(1, false), t(2, false), t(3, false), t(4, false), },
										{t(3, false), t(2, false), t(1, false), t(0, true), t(1, false), t(2, false), t(3, false), },										
										{t(4, false), t(3, false), t(2, false), t(1, false), t(1, false), t(1, true), t(2, false), },
										{t(5, false), t(4, false), t(3, false), t(2, false), t(2, false), t(2, false), t(1, true), },
										{t(6, false), t(5, false), t(4, false), t(3, false), t(3, false), t(3, false), t(2, false), },
									}
								),
								1,
								null,
								Arrays.asList(
									new Tuple2<Integer, Integer>(0, 0),
									new Tuple2<Integer, Integer>(1, 1),
									new Tuple2<Integer, Integer>(2, 2),
									new Tuple2<Integer, Integer>(3, 3),
									new Tuple2<>(3, 4),
									new Tuple2<Integer, Integer>(4, 5),
									new Tuple2<>(5, 6),
									new Tuple2<Integer, Integer>(6, 6)
								)
							)
						),
						4
					},		
				//transposition
				new Object[]{
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('t', 's', 'r', 'i', 'n', 'g'),
						new DistanceResult<>(
							Arrays.asList('s', 't', ' ', 'r', 'i', 'n', 'g'),
							Arrays.asList(' ', 't', 's', 'r', 'i', 'n', 'g'),
							"Levenshtein distance",
							"DEIEEEE",
							2,
							new DistanceMatrixResult<>(
								new Matrix<>(
									new Tuple2[][]{
										{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false), },
										{t(1, false), t(1, false), t(1, true), t(2, false), t(3, false), t(4, false), t(5, false), },
										{t(2, false), t(1, true), t(2, false), t(2, false), t(3, false), t(4, false), t(5, false), },
										{t(3, false), t(2, false), t(2, false), t(2, true), t(3, false), t(4, false), t(5, false), },
										{t(4, false), t(3, false), t(3, false), t(3, false), t(2, true), t(3, false), t(4, false), },
										{t(5, false), t(4, false), t(4, false), t(4, false), t(3, false), t(2, true), t(3, false), },
										{t(6, false), t(5, false), t(5, false), t(5, false), t(4, false), t(3, false), t(2, true), }
									}
								),
								1,
								null,
								Arrays.asList(
									new Tuple2<Integer, Integer>(0, 0),
									new Tuple2<Integer, Integer>(1, 0),
									new Tuple2<Integer, Integer>(2, 1),
									new Tuple2<Integer, Integer>(2, 2),
									new Tuple2<Integer, Integer>(3, 3),
									new Tuple2<Integer, Integer>(4, 4),
									new Tuple2<Integer, Integer>(5, 5),
									new Tuple2<Integer, Integer>(6, 6)
								)
							)
						),
						4
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
						Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd', 'ì', ' ', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v', 'a', 't', 'e', 'l', 'n', 'ì', 'j', '', 'í', 'h', 'o'),
						Arrays.asList('n', 'e', ' ', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j', 'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v', 'á', 't', 'e', 'l', 'n', 'ì', 'j', '', 'í', 'h', 'o'),
						"Levenshtein distance",
					    "EEDEESEEEEESIEEEEESEESESEEEEEEEEEE", 
						7,
						new DistanceMatrixResult<>(
							new Matrix<>(new Tuple2[][]{
								{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, false), t(24, false), t(25, false), t(26, false), t(27, false), t(28, false), t(29, false), t(30, false), t(31, false), t(32, false), t(33, false), },
								{t(1, false), t(0, true), t(1, false), t(2, true), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, true), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, false), t(24, false), t(25, false), t(26, true), t(27, false), t(28, false), t(29, false), t(30, false), t(31, false), t(32, false), },
								{t(2, false), t(1, false), t(0, true), t(1, false), t(2, true), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, true), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, true), t(24, false), t(25, false), t(26, false), t(27, false), t(28, false), t(29, false), t(30, false), t(31, false), },
								{t(3, false), t(2, false), t(1, false), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(8, true), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, false), t(24, false), t(25, false), t(26, true), t(27, false), t(28, false), t(29, false), t(30, false), },
								{t(4, false), t(3, true), t(2, false), t(1, true), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(14, true), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, true), t(24, false), t(25, false), t(26, false), t(27, false), t(28, false), t(29, false), },
								{t(5, false), t(4, false), t(3, true), t(2, false), t(1, true), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, true), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(20, true), t(21, false), t(22, false), t(23, false), t(24, false), t(25, false), t(26, false), t(27, false), t(28, false), },
								{t(6, false), t(5, false), t(4, false), t(3, false), t(2, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(21, false), t(22, false), t(23, false), t(24, false), t(25, false), t(26, false), t(27, false), t(28, false), },
								{t(7, false), t(6, false), t(5, false), t(4, false), t(3, false), t(3, false), t(2, true), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, true), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(22, false), t(23, false), t(24, false), t(25, false), t(26, false), t(27, false), t(28, false), },
								{t(8, false), t(7, false), t(6, false), t(5, false), t(4, false), t(4, false), t(3, false), t(2, true), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, false), t(24, false), t(25, false), t(26, false), t(27, false), t(28, false), },
								{t(9, false), t(8, false), t(7, false), t(6, false), t(5, false), t(5, false), t(4, false), t(3, false), t(2, true), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, true), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, false), t(24, false), t(25, false), t(26, false), t(27, false), },
								{t(10, false), t(9, false), t(8, false), t(7, false), t(6, false), t(6, false), t(5, false), t(4, false), t(3, false), t(2, true), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, true), t(14, false), t(15, true), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, false), t(24, false), t(25, false), t(26, false), },
								{t(11, false), t(10, false), t(9, false), t(8, false), t(7, false), t(7, false), t(6, false), t(5, false), t(4, false), t(3, false), t(2, true), t(3, false), t(4, false), t(5, false), t(6, false), t(7, true), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, false), t(24, false), t(25, false), },
								{t(12, false), t(11, false), t(10, false), t(9, false), t(8, false), t(8, false), t(7, false), t(6, false), t(5, false), t(4, false), t(3, false), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(19, true), t(20, false), t(21, false), t(22, false), t(23, false), t(24, false), },
								{t(13, false), t(12, false), t(11, false), t(10, false), t(9, false), t(9, false), t(8, true), t(7, false), t(6, false), t(5, false), t(4, false), t(4, false), t(4, false), t(4, true), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, false), t(24, false), },
								{t(14, false), t(13, false), t(12, false), t(11, false), t(10, false), t(10, false), t(9, false), t(8, false), t(7, false), t(6, false), t(5, false), t(5, false), t(5, false), t(5, false), t(4, true), t(5, false), t(6, true), t(7, false), t(8, false), t(9, true), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, true), },
								{t(15, false), t(14, false), t(13, false), t(12, false), t(11, false), t(11, false), t(10, false), t(9, false), t(8, false), t(7, false), t(6, true), t(6, false), t(6, false), t(6, false), t(5, false), t(4, true), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), },
								{t(16, false), t(15, false), t(14, false), t(13, false), t(12, false), t(12, false), t(11, false), t(10, false), t(9, false), t(8, false), t(7, false), t(7, false), t(7, false), t(7, false), t(6, true), t(5, false), t(4, true), t(5, false), t(6, false), t(7, true), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, true), },
								{t(17, false), t(16, false), t(15, false), t(14, false), t(13, false), t(13, false), t(12, false), t(11, false), t(10, false), t(9, false), t(8, false), t(8, false), t(8, false), t(8, false), t(7, false), t(6, false), t(5, false), t(4, true), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), },
								{t(18, false), t(17, false), t(16, false), t(15, false), t(14, false), t(14, false), t(13, false), t(12, false), t(11, false), t(10, false), t(9, false), t(9, false), t(9, false), t(9, false), t(8, false), t(7, false), t(6, false), t(5, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, false), t(20, false), },
								{t(19, false), t(18, false), t(17, false), t(16, false), t(15, false), t(15, false), t(14, false), t(13, false), t(12, false), t(11, false), t(10, false), t(10, false), t(10, false), t(10, false), t(9, true), t(8, false), t(7, true), t(6, false), t(6, false), t(5, true), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), t(19, true), },
								{t(20, false), t(19, false), t(18, false), t(17, false), t(16, false), t(16, false), t(15, false), t(14, false), t(13, false), t(12, true), t(11, false), t(11, false), t(11, false), t(11, false), t(10, false), t(9, false), t(8, false), t(7, false), t(7, false), t(6, false), t(5, true), t(6, false), t(7, true), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), t(18, false), },
								{t(21, false), t(20, false), t(19, false), t(18, false), t(17, false), t(17, false), t(16, false), t(15, false), t(14, false), t(13, false), t(12, false), t(12, false), t(12, false), t(12, false), t(11, false), t(10, false), t(9, false), t(8, false), t(8, false), t(7, false), t(6, false), t(6, false), t(7, false), t(7, true), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), },
								{t(22, false), t(21, false), t(20, false), t(19, false), t(18, false), t(18, false), t(17, false), t(16, false), t(15, false), t(14, true), t(13, false), t(13, false), t(13, false), t(13, false), t(12, false), t(11, false), t(10, false), t(9, false), t(9, false), t(8, false), t(7, true), t(7, false), t(6, true), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), },
								{t(23, false), t(22, false), t(21, false), t(20, false), t(19, false), t(19, false), t(18, false), t(17, false), t(16, true), t(15, false), t(14, false), t(14, false), t(14, false), t(14, false), t(13, false), t(12, false), t(11, false), t(10, false), t(10, false), t(9, false), t(8, false), t(7, true), t(7, false), t(7, false), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), t(17, false), },
								{t(24, false), t(23, false), t(22, false), t(21, false), t(20, false), t(20, false), t(19, false), t(18, false), t(17, false), t(16, false), t(15, false), t(15, false), t(15, false), t(15, false), t(14, false), t(13, false), t(12, false), t(11, false), t(11, false), t(10, false), t(9, false), t(8, false), t(8, false), t(8, false), t(7, true), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(16, false), },
								{t(25, false), t(24, false), t(23, true), t(22, false), t(21, true), t(21, false), t(20, false), t(19, false), t(18, false), t(17, false), t(16, false), t(16, false), t(15, true), t(16, false), t(15, false), t(14, false), t(13, false), t(12, false), t(12, false), t(11, false), t(10, false), t(9, false), t(9, false), t(9, false), t(8, false), t(7, true), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), },
								{t(26, false), t(25, false), t(24, false), t(23, false), t(22, false), t(22, false), t(21, false), t(20, false), t(19, false), t(18, false), t(17, false), t(17, false), t(16, false), t(16, false), t(16, false), t(15, false), t(14, false), t(13, false), t(13, false), t(12, false), t(11, false), t(10, false), t(10, false), t(10, false), t(9, false), t(8, false), t(7, true), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), },
								{t(27, false), t(26, true), t(25, false), t(24, true), t(23, false), t(23, false), t(22, false), t(21, false), t(20, false), t(19, false), t(18, false), t(18, false), t(17, false), t(17, false), t(17, false), t(16, false), t(15, false), t(14, false), t(13, true), t(13, false), t(12, false), t(11, false), t(11, false), t(11, false), t(10, false), t(9, false), t(8, false), t(7, true), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), },
								{t(28, false), t(27, false), t(26, false), t(25, false), t(24, false), t(24, false), t(23, false), t(22, false), t(21, false), t(20, false), t(19, false), t(19, false), t(18, false), t(18, false), t(18, false), t(17, false), t(16, false), t(15, false), t(14, false), t(14, false), t(13, false), t(12, false), t(12, false), t(12, false), t(11, false), t(10, false), t(9, false), t(8, false), t(7, true), t(8, false), t(9, false), t(10, false), t(11, false), t(12, false), },
								{t(29, false), t(28, false), t(27, false), t(26, false), t(25, false), t(25, false), t(24, false), t(23, false), t(22, false), t(21, false), t(20, false), t(19, true), t(19, false), t(19, false), t(19, false), t(18, false), t(17, false), t(16, false), t(15, false), t(15, false), t(14, false), t(13, false), t(13, false), t(13, false), t(12, false), t(11, false), t(10, false), t(9, false), t(8, false), t(7, true), t(8, false), t(9, false), t(10, false), t(11, false), },
								{t(30, false), t(29, false), t(28, false), t(27, false), t(26, false), t(26, false), t(25, false), t(24, false), t(23, false), t(22, false), t(21, false), t(20, false), t(20, false), t(20, false), t(20, false), t(19, false), t(18, false), t(17, false), t(16, false), t(16, false), t(15, false), t(14, false), t(14, false), t(14, false), t(13, false), t(12, false), t(11, false), t(10, false), t(9, false), t(8, false), t(7, true), t(8, false), t(9, false), t(10, false), },
								{t(31, false), t(30, false), t(29, false), t(28, false), t(27, false), t(27, false), t(26, false), t(25, false), t(24, false), t(23, false), t(22, false), t(21, false), t(21, false), t(21, false), t(21, false), t(20, false), t(19, false), t(18, false), t(17, false), t(17, false), t(16, false), t(15, false), t(15, false), t(15, false), t(14, false), t(13, false), t(12, false), t(11, false), t(10, false), t(9, false), t(8, false), t(7, true), t(8, false), t(9, false), },
								{t(32, false), t(31, false), t(30, false), t(29, false), t(28, false), t(28, false), t(27, false), t(26, false), t(25, false), t(24, false), t(23, false), t(22, false), t(22, false), t(22, false), t(22, false), t(21, false), t(20, false), t(19, false), t(18, false), t(18, false), t(17, false), t(16, false), t(16, false), t(16, false), t(15, false), t(14, false), t(13, false), t(12, false), t(11, false), t(10, false), t(9, false), t(8, false), t(7, true), t(8, false), },
								{t(33, false), t(32, false), t(31, false), t(30, false), t(29, false), t(29, false), t(28, false), t(27, false), t(26, false), t(25, false), t(24, false), t(23, false), t(23, false), t(23, false), t(22, true), t(22, false), t(21, true), t(20, false), t(19, false), t(18, true), t(18, false), t(17, false), t(17, false), t(17, false), t(16, false), t(15, false), t(14, false), t(13, false), t(12, false), t(11, false), t(10, false), t(9, false), t(8, false), t(7, true), },
							}),
							1,
							null,
							Arrays.asList(
								new Tuple2<>(0, 0),
								new Tuple2<>(1, 1),
								new Tuple2<>(2, 2),
								new Tuple2<>(3, 2),
								new Tuple2<>(4, 3),
								new Tuple2<>(5, 4),
								new Tuple2<>(6, 5),
								new Tuple2<>(7, 6),
								new Tuple2<>(8, 7),
								new Tuple2<>(9, 8),
								new Tuple2<>(10, 9),
								new Tuple2<>(11, 10),
								new Tuple2<>(12, 11),
								new Tuple2<>(12, 12),
								new Tuple2<>(13, 13),
								new Tuple2<>(14, 14),
								new Tuple2<>(15, 15),
								new Tuple2<>(16, 16),
								new Tuple2<>(17, 17),
								new Tuple2<>(18, 18),
								new Tuple2<>(19, 19),
								new Tuple2<>(20, 20),
								new Tuple2<>(21, 21),
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
								new Tuple2<>(32, 32),
								new Tuple2<>(33, 33)
							)
						)
					),
					14
				},
				//koolipan vs kopllisa
				new Object[]{
					Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
					Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
					new DistanceResult<>(
						Arrays.asList('k', 'o', 'o', 'l', ' ', 'i', 'p', 'a', 'n'),
						Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a', ' '),
						"Levenshtein distance",
						"EESEIESED",
						4,
						new DistanceMatrixResult<>(
							new Matrix<>(new Tuple2[][]{
								{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false)},
								{t(1, false), t(0, true), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false)},
								{t(2, false), t(1, false), t(0, true), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false)},
								{t(3, false), t(2, false), t(1, true), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false)},
								{t(4, false), t(3, false), t(2, false), t(2, false), t(1, true), t(2, true), t(3, false), t(4, false), t(5, false)},
								{t(5, false), t(4, false), t(3, false), t(3, false), t(2, false), t(2, false), t(2, true), t(3, false), t(4, false)},
								{t(6, false), t(5, false), t(4, false), t(3, true), t(3, false), t(3, false), t(3, false), t(3, false), t(4, false)},
								{t(7, false), t(6, false), t(5, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(3, true)},
								{t(8, false), t(7, false), t(6, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(4, false)},
							}),
							1,
							null,
							Arrays.asList(
								new Tuple2<Integer, Integer>(0, 0),
								new Tuple2<Integer, Integer>(1, 1),
								new Tuple2<Integer, Integer>(2, 2),
								new Tuple2<Integer, Integer>(3, 3),
								new Tuple2<Integer, Integer>(4, 4),
								new Tuple2<Integer, Integer>(4, 5),
								new Tuple2<Integer, Integer>(5, 6),
								new Tuple2<Integer, Integer>(6, 7),
								new Tuple2<Integer, Integer>(7, 8),
								new Tuple2<Integer, Integer>(8, 8)
							)
						)
					),
					8
				},				
				//kolinn vs kpollim
				new Object[]{
						Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
						Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
						new DistanceResult<>(
								Arrays.asList('k', 'o', 'p', ' ', 'l', ' ', 'i', 'n', 'n'),
								Arrays.asList('k', ' ', 'p', 'o', 'l', 'l', 'i', 'm', ' '),
								"Levenshtein distance",
								"EDEIEIESD",
								4,
								new DistanceMatrixResult<>(
										new Matrix<>(new Tuple2[][]{
												{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false), t(7, false)},
												{t(1, false), t(0, true), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, false)},
												{t(2, false), t(1, false), t(1, false), t(1, true), t(2, false), t(3, false), t(4, false), t(5, false)},
												{t(3, false), t(2, false), t(1, true), t(2, false), t(2, false), t(3, false), t(4, false), t(5, false)},
												{t(4, false), t(3, false), t(2, false), t(2, false), t(2, true), t(2, true), t(3, false), t(4, false)},
												{t(5, false), t(4, false), t(3, false), t(3, false), t(3, false), t(3, false), t(2, true), t(3, false)},
												{t(6, false), t(5, false), t(4, false), t(4, false), t(4, false), t(4, false), t(3, false), t(3, false)},
												{t(7, false), t(6, false), t(5, false), t(5, false), t(5, false), t(5, false), t(4, false), t(4, false)}
											}),
										1,
										null,
										Arrays.asList(
												new Tuple2<Integer, Integer>(0, 0),
												new Tuple2<Integer, Integer>(1, 1),
												new Tuple2<Integer, Integer>(2, 1),
												new Tuple2<Integer, Integer>(3, 2),
												new Tuple2<Integer, Integer>(3, 3),
												new Tuple2<Integer, Integer>(4, 4),
												new Tuple2<Integer, Integer>(4, 5),
												new Tuple2<Integer, Integer>(5, 6),
												new Tuple2<Integer, Integer>(6, 7),
												new Tuple2<Integer, Integer>(7, 7)
										)
							)
						),
						8 //TODO fix: not responce with distance
				},
				/************/
				//second empty
				new Object[]{
					new LinkedList<>(),
					Arrays.asList('s', 'l', 'o', 'v', 'o'),
					new DistanceResult<>(
						Arrays.asList(' ', ' ', ' ', ' ', ' '),
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						"Levenshtein distance",
						"IIIII",
						5,
						new DistanceMatrixResult<>(
							new Matrix<>(
								new Tuple2[][]{
									{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false)}
								}),
							1,
							null,
							Arrays.asList(
								new Tuple2<Integer, Integer>(0, 0),
								new Tuple2<Integer, Integer>(0, 1),
								new Tuple2<Integer, Integer>(0, 2),
								new Tuple2<Integer, Integer>(0, 3),
								new Tuple2<Integer, Integer>(0, 4),
								new Tuple2<Integer, Integer>(0, 5)
							)
						)
					),
					10
				},				
				//first empty				
				new Object[]{
					Arrays.asList('s', 'l', 'o', 'v', 'o'),
					new LinkedList<>(),
					new DistanceResult<>(
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						Arrays.asList(' ', ' ', ' ', ' ', ' '),
						"Levenshtein distance",
						"DDDDD",
						5,
						new DistanceMatrixResult<>(
							new Matrix<>(
								new Tuple2[][]{
									{t(0, false)},
									{t(1, false)},
									{t(2, false)},
									{t(3, false)},
									{t(4, false)},
									{t(5, false)},
							}),
							1,
							null,
							Arrays.asList(
								new Tuple2<Integer, Integer>(0, 0),
								new Tuple2<Integer, Integer>(1, 0),
								new Tuple2<Integer, Integer>(2, 0),
								new Tuple2<Integer, Integer>(3, 0),
								new Tuple2<Integer, Integer>(4, 0),
								new Tuple2<Integer, Integer>(5, 0)
							)
						)
					),
					10
				},
				//special case	
				new Object[]{
					Arrays.asList('k', 'o', 'l', 'o'),
					Arrays.asList('o', 'k', 'o'),
					new DistanceResult<>(
						Arrays.asList('k', 'o', 'l', 'o'),
						Arrays.asList(' ', 'o', 'k', 'o'),
						"Levenshtein distance",
						"DESE",
						2,
						new DistanceMatrixResult<>(
							new Matrix<>(
								new Tuple2[][]{
									{t(0, false), t(1, false), t(2, false), t(3, false)},
									{t(1, false), t(1, false), t(1, true), t(2, false)},
									{t(2, false), t(1, true), t(2, false), t(1, true)},
									{t(3, false), t(2, false), t(2, false), t(2, false)},
									{t(4, false), t(3, true), t(3, false), t(2, true)}
								}
							),
							1,
							null,
							Arrays.asList(
								new Tuple2<Integer, Integer>(0, 0),
								new Tuple2<Integer, Integer>(1, 0),
								new Tuple2<Integer, Integer>(2, 1),
								new Tuple2<Integer, Integer>(3, 2),
								new Tuple2<Integer, Integer>(4, 3)
							)
						)
					),
					4
				}					
		);
	}
}
