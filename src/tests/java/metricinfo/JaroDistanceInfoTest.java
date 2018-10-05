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
import support.Matrix;
import support.Tuple2;

@RunWith(JUnitParamsRunner.class)
public class JaroDistanceInfoTest {
	
	@Test(expected=InvalidOpeationCostException.class)
	public void testConstructorThrowWhenCostIsNotPositive(){
		new JaroDistanceInfo<>(' ', 0, 1, 0);
	}
	
	
	@Test
	@Parameters
	public void testWeightDistance(
			List<Character> from, List<Character> to, ResultSet<Character, MatrixResultSet<JaroValues>> result, double weightDistance){
		ResultSet<Character, MatrixResultSet<JaroValues>> res = 
				new JaroDistanceInfo<>(' ', 1/5.0, 2/5.0, 2/5.0).calculate(from, to);
		assertEquals(weightDistance, res.getDistance().doubleValue(), 0.00001);
	}
	
	public Object[] parametersForTestWeightDistance() {
		return dataProvider();
	}

	@Test
	@Parameters
	public void testCalculateDistance(
			List<Character> from, List<Character> to, ResultSet<Character, MatrixResultSet<JaroValues>> expected, double weightDistance){
		ResultSet<Character, MatrixResultSet<JaroValues>> actual = new JaroDistanceInfo<>(' ').calculate(from, to);
		assertEquals(expected.getDistance(), actual.getDistance());
	}
	
	public Object[] parametersForTestCalculateDistance() {
		return dataProvider();
	}
	
	@Test
	@Parameters
	public void testCalculateFinalSequence(
			List<Character> from, List<Character> to, ResultSet<Character, MatrixResultSet<JaroValues>> expected, double weightDistance) {
		ResultSet<Character, MatrixResultSet<JaroValues>> actual = new JaroDistanceInfo<>(' ').calculate(from, to);
		assertEquals(
				expected.getFinalSequenceFrom(), 
				actual.getFinalSequenceFrom()
			);		
		assertEquals(
				expected.getFinalSequenceTo(),
				actual.getFinalSequenceTo()
			);
	}
	
	public Object[] parametersForTestCalculateFinalSequence() {
		return dataProvider();
	}
		
	@Test
	@Parameters
	public void testCalculateOperations(
			List<Character> from, List<Character> to, ResultSet<Character, MatrixResultSet<JaroValues>> expected, double weightDistance){
		ResultSet<Character, MatrixResultSet<JaroValues>> actual = new JaroDistanceInfo<>(' ').calculate(from, to);
		assertEquals(expected.getOperations(), actual.getOperations());
	}
	
	public Object[] parametersForTestCalculateOperations() {
		return dataProvider();
	}	
	
	@Test
	@Parameters
	public void testCalculateStructureMatrix(
			List<Character> from, List<Character> to, ResultSet<Character, MatrixResultSet<JaroValues>> expected, double weightDistance){
		ResultSet<Character, MatrixResultSet<JaroValues>> actual = new JaroDistanceInfo<>(' ').calculate(from, to);
		assertEquals(
				expected.getStructure().getMatrix(),
				actual.getStructure().getMatrix()
			);
	}
	
	public Object[] parametersForTestCalculateStructureMatrix() {
		return dataProvider();
	}
	
	@Test
	@Parameters
	public void testCalculateStructureIndexes(
			List<Character> from, List<Character> to, ResultSet<Character, MatrixResultSet<JaroValues>> expected, double weightDistance){
		ResultSet<Character, MatrixResultSet<JaroValues>> actual = new JaroDistanceInfo<>(' ').calculate(from, to);
		assertEquals(
				expected.getStructure().getIndexes(),
				actual.getStructure().getIndexes()
			);		
	}
	
	public Object[] parametersForTestCalculateStructureIndexes() {
		return dataProvider();
	}
	
	public Object[] dataProvider() {
		return new Object[]{
					new Object[]{
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							new ResultSet<>(
									Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
									Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
									"Jaro distance",
									"EEEEEE",
									1.0,
									new MatrixResultSet<>(
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
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							new LinkedList<>(),
							new ResultSet<>(
									Arrays.asList('s', 'l', 'o', 'v', 'o'),
									Arrays.asList(' ', ' ', ' ', ' ', ' '),
									"Jaro distance",
									"DDDDD",
									0.0,
									new MatrixResultSet<>(
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
					new Object[]{
							new LinkedList<>(),
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							new ResultSet<>(
									Arrays.asList(' ', ' ', ' ', ' ', ' '),
									Arrays.asList('s', 'l', 'o', 'v', 'o'),
									"Jaro distance",
									"IIIII",
									0.0,
									new MatrixResultSet<>(
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
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							Arrays.asList('l'),
							new ResultSet<>(
									Arrays.asList('s', 'l', 'o', 'v', 'o'),
									Arrays.asList(' ', 'l', ' ', ' ', ' '),
									"Jaro distance",
									"DEDDD",
									0.7333333333333333,
									new MatrixResultSet<>(
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
							new ResultSet<>(
									Arrays.asList('s', 'l', 'o', 'v', 'o'),
									Arrays.asList('s', 'l', ' ', ' ', ' '),
									"Jaro distance",
									"EEDDD",
									0.8,
									new MatrixResultSet<>(
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
							new ResultSet<>(
									Arrays.asList(' ', 's', 'l', 'o', 'v', 'a'),
									Arrays.asList('a', ' ', ' ', ' ', ' ', ' '),
									"Jaro distance",
									"IDDDDD",
									0.0,
									new MatrixResultSet<>(
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
					},
					new Object[]{
							Arrays.asList('o', 'k', 'n', 'o'),
							Arrays.asList('w', 'i', 'n', 'd', 'o', 'w'),
							new ResultSet<>(
									Arrays.asList(' ', 'o', ' ', 'k', 'n', ' ', 'o', ' '),
									Arrays.asList('w', ' ', 'i', ' ', 'n', 'd', 'o', 'w'),
									"Jaro distance",
									"IDIDEIEI",
									0.6111111111111112,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{
												{JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.NULL}												
											}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(-1, 0),
													new Tuple2<>(0, 0),
													new Tuple2<>(0, 1),
													new Tuple2<>(1, 1),
													new Tuple2<>(2, 2),
													new Tuple2<>(2, 3),
													new Tuple2<>(3, 4),
													new Tuple2<>(3, 5)
													)
										)
								),
							0.63333333333
					},
					new Object[]{
							Arrays.asList('k', 'o', 'l', 'o'),
							Arrays.asList('o', 'k', 'o'),
							new ResultSet<>(
									Arrays.asList('k', 'o', 'l', 'o'),
									Arrays.asList('o', 'k', ' ', 'o'),
									"Jaro distance",
									"TTDE",
									0.8055555555555555,
									new MatrixResultSet<>(
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
							0.816666666666
					},
					new Object[]{
							Arrays.asList('n', 'e', 'n', 'í'),
							Arrays.asList('n', 'i', 'e', ' ', 'j', 'e'),
							new ResultSet<>(
									Arrays.asList('n', ' ', 'e', ' ', 'n', ' ', 'í', ' '),
									Arrays.asList('n', 'i', 'e', ' ', ' ', 'j', ' ', 'e'),
									"Jaro distance",
									"EIEIDIDI",
									0.6111111111111112,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{
												{JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.FALSE, JaroValues.FALSE, JaroValues.TRUE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL},												
											}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0),
													new Tuple2<>(0, 1),
													new Tuple2<>(1, 2),
													new Tuple2<>(1, 3),
													new Tuple2<>(2, 3),
													new Tuple2<>(2, 4),
													new Tuple2<>(3, 4),
													new Tuple2<>(3, 5)
													)
										)
								),
							0.633333333333333
					},
					new Object[]{
							Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
							Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
							new ResultSet<>(
									Arrays.asList('h', 'o', 'u', 's', ' ', 'k', ' ', 'a'),
									Arrays.asList('h', 'o', 'u', 's', 'l', ' ', 'e', ' '),
									"Jaro distance",
									"EEEEIDID",
									0.7777777777777777,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{
												{JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE},
												{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE},
											}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0),
													new Tuple2<>(1, 1),
													new Tuple2<>(2, 2),
													new Tuple2<>(3, 3),
													new Tuple2<>(3, 4),
													new Tuple2<>(4, 4),
													new Tuple2<>(4, 5),
													new Tuple2<>(5, 5)
													)
										)
								),
							0.8
					},
					new Object[]{
							Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
							Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
							new ResultSet<>(
									Arrays.asList('k', 'o', ' ', 'o', 'l', ' ', 'i', ' ', 'p', 'a', 'n'),
									Arrays.asList('k', 'o', 'p', ' ', 'l', 'l', 'i', 's', ' ', 'a', ' '),
									"Jaro distance",
									"EEIDEIEIDED",
									0.75,
									new MatrixResultSet<>(
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
					new Object[]{
							Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
							Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
							new ResultSet<>(
									Arrays.asList('k', 'o', 'p', 'l', ' ', 'i', ' ', 'n', 'n'),
									Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm', ' ', ' '),
									"Jaro distance",
									"ETTEIEIDD",
									0.7428571428571429,
									new MatrixResultSet<>(
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
					new Object[]{
							Arrays.asList('w', 'o', 'r', 'd'),
							Arrays.asList('7', ';', '$', 'í'),
							new ResultSet<>(
									Arrays.asList(' ', 'w', ' ', 'o', ' ', 'r', ' ', 'd'),
									Arrays.asList('7', ' ', ';', ' ', '$', ' ', 'í', ' '),
									"Jaro distance",
									"IDIDIDID",
									0.0,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{
												{JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE, JaroValues.FALSE},
												{JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.FALSE},												
											}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(-1, 0),
													new Tuple2<>(0, 0),
													new Tuple2<>(0, 1),
													new Tuple2<>(1, 1),
													new Tuple2<>(1, 2),
													new Tuple2<>(2, 2),
													new Tuple2<>(2, 3),
													new Tuple2<>(3, 3)
													)
										)
								),
							0
					},
					new Object[]{
							Arrays.asList('a', 'b', 'b', 'c', 'b'),
							Arrays.asList('a', 'b', 'c', 'a', 'b'),
							new ResultSet<>(
									Arrays.asList('a', 'b', 'b', 'c', ' ', 'b'),
									Arrays.asList('a', 'b', ' ', 'c', 'a', 'b'),
									"Jaro distance",
									"EEDEIE",
									0.8666666666666667,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{
												{JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.FALSE, JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.NULL, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE},
												{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.FALSE, JaroValues.TRUE},												
											}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0),
													new Tuple2<>(1, 1),
													new Tuple2<>(2, 1),
													new Tuple2<>(3, 2),
													new Tuple2<>(3, 3),
													new Tuple2<>(4, 4)
													)
										)
								),
							0.88
					},
					new Object[]{
							Arrays.asList('a', 'a', 'h', 'o', 'j'),
							Arrays.asList('a', 'h', 'o', 'j', 'k', 'y'),
							new ResultSet<>(
									Arrays.asList('a', 'a', 'h', 'o', 'j', ' ', ' '),
									Arrays.asList('a', ' ', 'h', 'o', 'j', 'k', 'y'),
									"Jaro distance",
									"EDEEEII",
									0.8222222222222222,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{
												{JaroValues.TRUE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.NULL, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE, JaroValues.NULL},
												{JaroValues.NULL, JaroValues.NULL, JaroValues.NULL, JaroValues.TRUE, JaroValues.FALSE, JaroValues.FALSE}												
											}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0),
													new Tuple2<>(1, 0),
													new Tuple2<>(2, 1),
													new Tuple2<>(3, 2),
													new Tuple2<>(4, 3),
													new Tuple2<>(4, 4),
													new Tuple2<>(4, 5)
													)
										)
								),
							0.826666666666
					},
					new Object[]{
							Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
										  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
										  'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
							Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
										  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
										  'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
							new ResultSet<>(
									Arrays.asList('n', 'e', 'j', 'n', 'e', ' ', 'z', 'p', 'r', 'a', 'v', 'd', ' ', 'ì', ' ', 'p', 'o', 'd', 'o', 'b', ' ', 'ò', 'o', 'v', ' ', 'á', 'v', ' ', 'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
									Arrays.asList('n', 'e', ' ', 'n', 'e', 's', ' ', 'p', 'r', 'a', 'v', 'd', 'j', ' ', 'e', 'p', 'o', 'd', 'o', 'b', 'n', ' ', 'o', 'v', 'a', ' ', 'v', 'á', ' ', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
									"Jaro distance",
								     "EEDEEIDEEEEEIDIEEEEEIDEEIDEIDEEEEEEEEEE",                             
									0.8787878787878787,
									new MatrixResultSet<>(
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
					}				
		};
	}
}
