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

import metricquick.LevenshteinQuick;
import structures.MatrixResultSet;
import structures.ResultSet;
import support.Matrix;
import support.Tuple2;

@RunWith(Parameterized.class)
public class LevenshteinInfoTest {

	private ResultSet<Integer, Matrix<Tuple2<Character, Boolean>>> expected;
	private ResultSet<Integer, Matrix<Tuple2<Character, Boolean>>> actual;
	
	public LevenshteinInfoTest(List<Character> from, List<Character> to,
			ResultSet<Integer, Matrix<Tuple2<Character, Boolean>>> result) {
		this.expected = result;
		this.actual = new LevenshteinInfo().calculate(from, to);
	}
	
	
	//TODO exceptions test
	
	@Test
	public void testCalculateFinalSequence() {
		fail();
	}
	
	@Test
	public void testCalculateOperations(){
		fail();
	}
	
	@Test
	public void testCalculateDistance(){
		fail();
	}
	
	@Test
	public void testCalculateStructureMatrix(){
		fail();
	}
	
	@Test
	public void testCalculateStructureIndexes(){
		fail();
	}

	@Parameters
	public static Collection<Object[]> dataProvider() {
		return Arrays.asList(
					new Object[]{
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							new ResultSet<>(
									null,
									null,
									"Levenshtein distance",
									"",
									0,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
												)
										)
								)
					},
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							new LinkedList<>(),
							new ResultSet<>(
									null,
									null,
									"Levenshtein distance",
									"",
									5,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
												)
										)
								)
					},
					new Object[]{
							new LinkedList<>(),
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							new ResultSet<>(
									null,
									null,
									"Levenshtein distance",
									"",
									5,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
												)
										)
								)
					},
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							Arrays.asList('l'),
							new ResultSet<>(
									null,
									null,
									"Levenshtein distance",
									"",
									4,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
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
									"Levenshtein distance",
									"",
									3,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
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
									"Levenshtein distance",
									"",
									4,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
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
									"Levenshtein distance",
									"",
									4,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
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
									"Levenshtein distance",
									"",
									2,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
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
									"Levenshtein distance",
									"",
									4,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
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
									"Levenshtein distance",
									"",
									2,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
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
									"Levenshtein distance",
									"",
									4,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
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
									"Levenshtein distance",
									"",
									4,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
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
									"Levenshtein distance",
									"",
									4,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
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
									"Levenshtein distance",
									"",
									2,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
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
									"Levenshtein distance",
									"",
									3,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
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
									"Levenshtein distance",
									"",
									7,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2<Integer, Boolean>[][]{{new Tuple2<>(0, false)}}),
											null,
											1,
											null,
											Arrays.asList(
													new Tuple2<Integer, Integer>(0, 0)
												)
										)
								)
					}				
				);
	}
}
