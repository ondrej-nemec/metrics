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
import support.Tuple2;

@RunWith(Parameterized.class)
public class LevenshteinInfoTest {

	private ResultSet<Character, MatrixResultSet<Tuple2<Integer, Boolean>>> expected;
	private ResultSet<Character, MatrixResultSet<Tuple2<Integer, Boolean>>> actual;
	
	public LevenshteinInfoTest(List<Character> from, List<Character> to,
			ResultSet<Character, MatrixResultSet<Tuple2<Integer, Boolean>>> result) {
		this.expected = result;
		this.actual = new LevenshteinInfo<>(' ').calculate(from, to);
	}
	
	
	//TODO exceptions test
	
	@Test
	public void testCalculateFinalSequence() {
		assertEquals(expected.getFinalSequenceFrom(), actual.getFinalSequenceFrom());
		assertEquals(expected.getFinalSequenceTo(), actual.getFinalSequenceTo());
	}
	
	@Test
	public void testCalculateOperations(){
		assertEquals(expected.getOperations(), actual.getOperations());
	}
	
	@Test
	public void testCalculateDistance(){
		assertEquals(expected.getDistance(), actual.getDistance());
	}
	
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

	@Parameters
	public static Collection<Object[]> dataProvider() {
		return Arrays.asList(
					new Object[]{
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							new ResultSet<>(
									Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
									Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
									"Levenshtein distance",
									"EEEEEE",
									0,
									new MatrixResultSet<>(
										//	new Matrix<>(new Tuple2[][]{{new Tuple2<>(0, false)}}),
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
									Arrays.asList('s', 'l', 'o', 'v', 'o'),
									Arrays.asList(' ', ' ', ' ', ' ', ' '),
									"Levenshtein distance",
									"DDDDD",
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
									Arrays.asList(' ', ' ', ' ', ' ', ' '),
									Arrays.asList('s', 'l', 'o', 'v', 'o'),
									"Levenshtein distance",
									"IIIII",
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
									Arrays.asList('s', 'l', 'o', 'v', 'o'),
									Arrays.asList(' ', 'l', ' ', ' ', ' '),
									"Levenshtein distance",
									"DEDDD",
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
									Arrays.asList('s', 'l', 'o', 'v', 'o'),
									Arrays.asList('s', 'l', ' ', ' ', ' '),
									"Levenshtein distance",
									"EEDDD",
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
									Arrays.asList('s', 'l', 'o', 'v', 'a'),
									Arrays.asList(' ', ' ', ' ', ' ', 'a'),
									"Levenshtein distance",
									"DDDDE",
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
									Arrays.asList('o', 'k', 'n', ' ', 'o', ' '),
									Arrays.asList('w', 'i', 'n', 'd', 'o', 'w'),
									"Levenshtein distance",
									"SSEIEI",
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
									Arrays.asList('k', 'o', 'l', 'o'),
									Arrays.asList(' ', 'o', 'k', 'o'),
									"Levenshtein distance",
									"DESE",
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
							Arrays.asList('n', 'e', 'n', 'í'),
							Arrays.asList('n', 'i', 'e', ' ', 'j', 'e'),
							new ResultSet<>(
									Arrays.asList('n', ' ', 'e', 'n', 'í', ' '),
									Arrays.asList('n', 'i', 'e', ' ', 'j', 'e'),
									"Levenshtein distance",
									"EIESSI",
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
									Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
									Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
									"Levenshtein distance",
									"EEEESS",
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
									Arrays.asList('k', 'o', 'o', 'l', ' ', 'i', 'p', 'a', 'n'),
									Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a', ' '),
									"Levenshtein distance",
									"EESEIESED",
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
									Arrays.asList('k', 'o', 'p', ' ', 'l', ' ', 'i', 'n', 'n'),
									Arrays.asList('k', ' ', 'p', 'o', 'l', 'l', 'i', 'm', ' '),
									"Levenshtein distance",
									"EDEIEIESD",
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
							Arrays.asList('7', ';', '$', 'í'),
							new ResultSet<>(
									Arrays.asList('w', 'o', 'r', 'd'),
									Arrays.asList('7', ';', '$', 'í'),
									"Levenshtein distance",
									"SSSS",
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
									Arrays.asList('a', 'b', 'b', 'c', ' ', 'b'),
									Arrays.asList('a', 'b', ' ', 'c', 'a', 'b'),
									"Levenshtein distance",
									"EEDEIE",
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
									Arrays.asList('a', 'a', 'h', 'o', 'j', ' ', ' '),
									Arrays.asList('a', ' ', 'h', 'o', 'j', 'k', 'y'),
									"Levenshtein distance",
									"EDEEEII",
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
									  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
									  'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
							Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
									  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
									  'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
							new ResultSet<>(
									Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd', 'ì', ' ', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v', 'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
									Arrays.asList('n', 'e', ' ', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j', 'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v', 'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
									"Levenshtein distance",
									"EEDEESEEEEESIEEEEESEESESEEEESESSEE",
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
