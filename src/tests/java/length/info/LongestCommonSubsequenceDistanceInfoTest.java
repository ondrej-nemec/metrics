package length.info;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import length.info.LongestCommonSubsequenceDistanceInfo;
import structures.LengthMatrixResult;
import structures.LengthResult;
import support.AbstractMetricInfoTest;
import support.Matrix;
import support.Tuple2;

@RunWith(JUnitParamsRunner.class)
public class LongestCommonSubsequenceDistanceInfoTest extends AbstractMetricInfoTest {
	
	@Test
	@Parameters
	public void testCalculateWorks(
			List<Character> from,
			List<Character> to,
			LengthResult<Character, LengthMatrixResult<Tuple2<Integer, Boolean>>> result) {
		LengthResult<Character, LengthMatrixResult<Tuple2<Integer, Boolean>>> actual = 
				new LongestCommonSubsequenceDistanceInfo<Character>().calculate(from, to);
		assertEquals(result, actual);
	}	
		
	public Object[] parametersForTestCalculateWorks() {
		return parameters(2);
	}
	
	/**********************************/
	
	@Override
	public List<Object[]> resultSet() {
		return Arrays.asList(
				//equals
				new Object[]{
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					new LengthResult<>(
							6,
							"Longest Common Subsequence length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
										new Integer[][]{
											{0, 0, 0, 0, 0, 0, 0},
											{0, 1, 1, 1, 1, 1, 1},
											{0, 1, 2, 2, 2, 2, 2},
											{0, 1, 2, 3, 3, 3, 3},
											{0, 1, 2, 3, 4, 4, 4},
											{0, 1, 2, 3, 4, 5, 5},
											{0, 1, 2, 3, 4, 5, 6},		
										}
									)
								),
							Arrays.asList(
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g')
							)
					)
				},
				//different
				new Object[]{
					Arrays.asList('a', 'a', 'a'),
					Arrays.asList('b', 'b', 'b'),
					new LengthResult<>(
							0,
							"Longest Common Subsequence length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
										new Integer[][]{
											{0, 0, 0, 0},
											{0, 0, 0, 0},
											{0, 0, 0, 0},
											{0, 0, 0, 0},					
										}
									)
								),
							Arrays.asList()
					)
				},
				//substitution
				new Object[]{
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					Arrays.asList('s', 't', 'r', 'y', 'n', 'g'),
					new LengthResult<>(
							5,
							"Longest Common Subsequence length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
										new Integer[][]{
											{0, 0, 0, 0, 0, 0, 0},
											{0, 1, 1, 1, 1, 1, 1},
											{0, 1, 2, 2, 2, 2, 2},
											{0, 1, 2, 3, 3, 3, 3},
											{0, 1, 2, 3, 3, 3, 3},
											{0, 1, 2, 3, 3, 4, 4},
											{0, 1, 2, 3, 3, 4, 5},				
										}
									)
								),
							Arrays.asList(
									Arrays.asList('s', 't', 'r', 'n', 'g')	
							)
					)
				},
				//insertion and deletion
				new Object[]{
						Arrays.asList('s', 't', 'r', 'n', 'g', 'a'),
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						new LengthResult<>(
								5,
								"Longest Common Subsequence length",
								new LengthMatrixResult<>(
										1,
										null,
										new Matrix<>(
											new Integer[][]{
												{0, 0, 0, 0, 0, 0, 0},
												{0, 1, 1, 1, 1, 1, 1},
												{0, 1, 2, 2, 2, 2, 2},
												{0, 1, 2, 3, 3, 3, 3},
												{0, 1, 2, 3, 3, 4, 4},
												{0, 1, 2, 3, 3, 4, 5},
												{0, 1, 2, 3, 3, 4, 5},						
											}
										)
									),
								Arrays.asList(
									Arrays.asList('s', 't', 'r', 'n', 'g')	
								)
						)
					},
				//transposition
				new Object[]{
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('t', 's', 'r', 'i', 'n', 'g'),
						new LengthResult<>(
								5,
								"Longest Common Subsequence length",
								new LengthMatrixResult<>(
										1,
										null,
										new Matrix<>(
											new Integer[][]{
												{0, 0, 0, 0, 0, 0, 0},
												{0, 0, 1, 1, 1, 1, 1},
												{0, 1, 1, 1, 1, 1, 1},
												{0, 1, 1, 2, 2, 2, 2},
												{0, 1, 1, 2, 3, 3, 3},
												{0, 1, 1, 2, 3, 4, 4},
												{0, 1, 1, 2, 3, 4, 5},
											}
										)
									),
								Arrays.asList(
									Arrays.asList('t', 'r', 'i', 'n', 'g'),
									Arrays.asList('s', 'r', 'i', 'n', 'g')
								)
						)
					},
				//so long sequence
				new Object[]{
					Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
								  'ě', 'p', 'o', 'd', 'o', 'b', 'ň', 'o', 'v', 'á', 'v',
								  'a', 't', 'e', 'l', 'n', 'ě', 'j', 'š', 'í', 'h', 'o'),
					Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
								  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
								  'á', 't', 'e', 'l', 'n', 'ě', 'j', 'š', 'í', 'h', 'o'),
					new LengthResult<>(
							27,
							"Longest Common Subsequence length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
										new Integer[][]{
											{},	//TODO
										}
									)
								),
							Arrays.asList(
									Arrays.asList('n', 'e', 'n', 'e', 'p', 'r', 'a', 
											'v', 'd', 'p', 'o', 'd', 'o', 'b', 'o', 'v', 'v',
											't', 'e', 'l', 'n', 'ě', 'j', 'š', 'í', 'h', 'o')
							)
					)
				},
				//koolipan vs kopllisa
				new Object[]{
					Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
					Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
					new LengthResult<>(
							5,
							"Longest Common Subsequence length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
										new Integer[][]{
											{0, 0, 0, 0, 0, 0, 0, 0, 0},
											{0, 1, 1, 1, 1, 1, 1, 1, 1},
											{0, 1, 2, 2, 2, 2, 2, 2, 2},
											{0, 1, 2, 2, 2, 2, 2, 2, 2},
											{0, 1, 2, 2, 3, 3, 3, 3, 3},
											{0, 1, 2, 2, 3, 3, 4, 4, 4},
											{0, 1, 2, 3, 3, 3, 4, 4, 4},
											{0, 1, 2, 3, 3, 3, 4, 4, 5},
											{0, 1, 2, 3, 3, 3, 4, 4, 5},
										}
									)
								),
							Arrays.asList(
								Arrays.asList('k', 'o', 'l', 'i', 'a')
							)
					)
				},				
				//kolinn vs kpollim
				new Object[]{
						Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
						Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
						new LengthResult<>(
								4,
								"Longest Common Subsequence length",
								new LengthMatrixResult<>(
										1,
										null,
										new Matrix<>(
											new Integer[][]{
												{0, 0, 0, 0, 0, 0, 0, 0},
												{0, 1, 1, 1, 1, 1, 1, 1},	
												{0, 1, 1, 2, 2, 2, 2, 2},	
												{0, 1, 2, 2, 2, 2, 2, 2},	
												{0, 1, 2, 2, 3, 3, 3, 3},	
												{0, 1, 2, 2, 3, 3, 4, 4},	
												{0, 1, 2, 2, 3, 3, 4, 4},	
												{0, 1, 2, 2, 3, 3, 4, 4},
											}
										)
									),
								Arrays.asList(
									Arrays.asList('k', 'p', 'l', 'i'),
									Arrays.asList('k', 'o', 'l', 'i')	
								)
						)
				},
				/************/
				//second empty
				new Object[]{
					new LinkedList<>(),
					Arrays.asList('s', 'l', 'o', 'v', 'o'),
					new LengthResult<>(
							0,
							"Longest Common Subsequence length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
										new Integer[][]{
											{0, 0, 0, 0, 0, 0},
										}
									)
								),
							Arrays.asList()
					)
				},				
				//first empty				
				new Object[]{
					Arrays.asList('s', 'l', 'o', 'v', 'o'),
					new LinkedList<>(),
					new LengthResult<>(
							0,
							"Longest Common Subsequence length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
										new Integer[][]{
											{0},
											{0},
											{0},
											{0},
											{0},
											{0},
										}
									)
								),
							Arrays.asList()
					)
				},
				//special case	
				new Object[]{
					Arrays.asList('k', 'o', 'l', 'o'),
					Arrays.asList('o', 'k', 'o'),
					new LengthResult<>(
							2,
							"Longest Common Subsequence length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
										new Tuple2[][]{
											{t(0, false), t(0, false), t(0, false), t(0, false)},
											{t(0, false), t(0, false), t(1, true), t(1, false)},
											{t(0, false), t(1, true), t(1, false), t(2, true)},
											{t(0, false), t(1, false), t(1, false), t(2, false)},
											{t(0, false), t(1, true), t(1, false), t(2, true)},
										}
									)
								),
							Arrays.asList(
									Arrays.asList('o', 'o'),
									Arrays.asList('k', 'o')
							)
					)
				}
		);
	}
	
	private Tuple2<Integer, Boolean> t(int first, boolean second) {
		return new Tuple2<>(first, second);
	}
}
