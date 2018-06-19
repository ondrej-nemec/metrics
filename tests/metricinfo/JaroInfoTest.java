package metricinfo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import structures.MatrixResultSet;
import structures.ResultSet;
import support.JaroValues;
import support.Matrix;
import support.Tuple2;

@RunWith(Parameterized.class)
public class JaroInfoTest {

	private ResultSet<Character, MatrixResultSet<Character, JaroValues>> expected;
	private ResultSet<Character, MatrixResultSet<Character, JaroValues>> actual;
	
	public JaroInfoTest(
			List<Character> from, List<Character> to, ResultSet<Character, MatrixResultSet<Character, JaroValues>> result) {
		this.expected = result;
		this.actual = new JaroInfo<>(' ').calculate(from, to);
	}
	//TODO exceptions test
	/*
	@Test
	public void testCalculateDistance(){
		assertEquals(expected.getDistance(), actual.getDistance());
	}
	
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
	*/
	@Test
	public void testCalculateOperations(){
		assertEquals(expected.getOperations(), actual.getOperations());
	}
	
	/*
	@Test
	public void testCalculateStructureMatrix(){
		assertEquals(
				expected.getStructure().getMatrix(),
				actual.getStructure().getMatrix()
			);
	}
	
	@Test
	public void testCalculateStructureIndexes(){
		assertEquals(
				expected.getStructure().getIndexes(),
				actual.getStructure().getIndexes()
			);
	}
*/
	@Parameters
	public static Collection<Object[]> dataProvider() {
		return Arrays.asList(
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
												new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
												0,
												null,
												Arrays.asList(
														new Tuple2<>(0, 0)
														)
											)
								)
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
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
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
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
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
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					},
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							Arrays.asList('s', 'l'),
							new ResultSet<>(
									null,
									null,
									"Jaro distance",
									"EEDDD",
									0.8,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					},
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'a'),
							Arrays.asList('a'),
							new ResultSet<>(
									null,
									null,
									"Jaro distance",
									"",
									0.0,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					},
					new Object[]{
							Arrays.asList('o', 'k', 'n', 'o'),
							Arrays.asList('w', 'i', 'n', 'd', 'o', 'w'),
							new ResultSet<>(
									null,
									null,
									"Jaro distance",
									"",
									0.6111111111111112,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					},
					new Object[]{
							Arrays.asList('k', 'o', 'l', 'o'),
							Arrays.asList('o', 'k', 'o'),
							new ResultSet<>(
									null,
									null,
									"Jaro distance",
									"TTDE",
									0.8055555555555555,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					},
					new Object[]{
							Arrays.asList('n', 'e', 'n', '�'),
							Arrays.asList('n', 'i', 'e', ' ', 'j', 'e'),
							new ResultSet<>(
									null,
									null,
									"Jaro distance",
									"E",
									0.6111111111111112,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					},
					new Object[]{
							Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
							Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
							new ResultSet<>(
									null,
									null,
									"Jaro distance",
									"EEEEIDID",
									0.7777777777777777,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					},
					new Object[]{
							Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
							Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
							new ResultSet<>(
									null,
									null,
									"Jaro distance",
									"",
									0.75,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					},
					new Object[]{
							Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
							Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
							new ResultSet<>(
									null,
									null,
									"Jaro distance",
									"",
									0.7428571428571429,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					},
					new Object[]{
							Arrays.asList('w', 'o', 'r', 'd'),
							Arrays.asList('7', ';', '$', '�'),
							new ResultSet<>(
									null,
									null,
									"Jaro distance",
									"",
									0.0,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					},
					new Object[]{
							Arrays.asList('a', 'b', 'b', 'c', 'b'),
							Arrays.asList('a', 'b', 'c', 'a', 'b'),
							new ResultSet<>(
									null,
									null,
									"Jaro distance",
									"",
									0.8666666666666667,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					},
					new Object[]{
							Arrays.asList('a', 'a', 'h', 'o', 'j'),
							Arrays.asList('a', 'h', 'o', 'j', 'k', 'y'),
							new ResultSet<>(
									null,
									null,
									"Jaro distance",
									"",
									0.8222222222222222,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					},
					new Object[]{
							Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
										  '�', 'p', 'o', 'd', 'o', 'b', '�', 'o', 'v', '�', 'v',
										  'a', 't', 'e', 'l', 'n', '�', 'j', '�', '�', 'h', 'o'),
							Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
										  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
										  '�', 't', 'e', 'l', 'n', '�', 'j', '�', '�', 'h', 'o'),
							new ResultSet<>(
									null,
									null,
									"Jaro distance",
									"",
									0.8787878787878787,
									new MatrixResultSet<>(
											new Matrix<>(new JaroValues[][]{{JaroValues.NULL}}),
											0,
											null,
											Arrays.asList(
													new Tuple2<>(0, 0)
													)
										)
								)
					}				
				);
	}
}
