package length.info;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import structures.LengthMatrixResult;
import structures.LengthResult;
import support.Matrix;

@RunWith(JUnitParamsRunner.class)
public class LongestCommonSubstringLengthInfoTest {
	
	@Test
	@Parameters
	public void testCalculateWorks(
			List<Character> from,
			List<Character> to,
			LengthResult<Character, LengthMatrixResult<Integer>> result) {
		LengthResult<Character, LengthMatrixResult<Integer>> actual = 
				new LongestCommonSubstringLengthInfo<Character>().calculate(from, to);
		assertEquals(result, actual);
	}	

	public List<Object[]> parametersForTestCalculateWorks() {
		return Arrays.asList(
				//equals
				new Object[]{
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					new LengthResult<>(
							6,
							"Longest Common Substring length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
										new Integer[][]{
											{0, 0, 0, 0, 0, 0, 0},
											{0, 1, 0, 0, 0, 0, 0},
											{0, 0, 2, 0, 0, 0, 0},
											{0, 0, 0, 3, 0, 0, 0},
											{0, 0, 0, 0, 4, 0, 0},
											{0, 0, 0, 0, 0, 5, 0},
											{0, 0, 0, 0, 0, 0, 6},
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
							"Longest Common Substring length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
										new Integer[][]{
											{0, 0, 0, 0,},
											{0, 0, 0, 0,},
											{0, 0, 0, 0,},
											{0, 0, 0, 0,},
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
							3,
							"Longest Common Substring length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
											new Integer[][]{
												{0, 0, 0, 0, 0, 0, 0},
												{0, 1, 0, 0, 0, 0, 0},
												{0, 0, 2, 0, 0, 0, 0},
												{0, 0, 0, 3, 0, 0, 0},
												{0, 0, 0, 0, 0, 0, 0},
												{0, 0, 0, 0, 0, 1, 0},
												{0, 0, 0, 0, 0, 0, 2},
											}
									)
								),
							Arrays.asList(
									Arrays.asList('s', 't', 'r')	
							)
					)
				},
				//insertion and deletion
				new Object[]{
						Arrays.asList('s', 't', 'r', 'n', 'g', 'a'),
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						new LengthResult<>(
								3,
								"Longest Common Substring length",
								new LengthMatrixResult<>(
										1,
										null,
										new Matrix<>(
												new Integer[][]{
													{0, 0, 0, 0, 0, 0, 0},
													{0, 1, 0, 0, 0, 0, 0},
													{0, 0, 2, 0, 0, 0, 0},
													{0, 0, 0, 3, 0, 0, 0},
													{0, 0, 0, 0, 0, 1, 0},
													{0, 0, 0, 0, 0, 0, 2},
													{0, 0, 0, 0, 0, 0, 0},
												}
										)
									),
								Arrays.asList(
									Arrays.asList('s', 't', 'r')	
								)
						)
					},
				//transposition
				new Object[]{
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('t', 's', 'r', 'i', 'n', 'g'),
						new LengthResult<>(
								4,
								"Longest Common Substring length",
								new LengthMatrixResult<>(
										1,
										null,
										new Matrix<>(
												new Integer[][]{
													{0, 0, 0, 0, 0, 0, 0},
													{0, 0, 1, 0, 0, 0, 0},
													{0, 1, 0, 0, 0, 0, 0},
													{0, 0, 0, 1, 0, 0, 0},
													{0, 0, 0, 0, 2, 0, 0},
													{0, 0, 0, 0, 0, 3, 0},
													{0, 0, 0, 0, 0, 0, 4},
												}
										)
									),
								Arrays.asList(
									Arrays.asList('r', 'i', 'n', 'g')
								)
						)
					},
				//so long sequence
				new Object[]{
					Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
								  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
								  'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
					Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
								  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
								  'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
					new LengthResult<>(
							10,
							"Longest Common Substring length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
											new Integer[][] {
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, },
												{0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, },
												{0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, },
												{0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, },
												{0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, },
												{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, },
											}
									)
								),
							Arrays.asList(
									Arrays.asList('t', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o')
							)
					)
				},
				//koolipan vs kopllisa
				new Object[]{
					Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
					Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
					new LengthResult<>(
							2,
							"Longest Common Substring length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
											new Integer[][]{
												{0, 0, 0, 0, 0, 0, 0, 0, 0},
												{0, 1, 0, 0, 0, 0, 0, 0, 0},
												{0, 0, 2, 0, 0, 0, 0, 0, 0},
												{0, 0, 1, 0, 0, 0, 0, 0, 0},
												{0, 0, 0, 0, 1, 1, 0, 0, 0},
												{0, 0, 0, 0, 0, 0, 2, 0, 0},
												{0, 0, 0, 1, 0, 0, 0, 0, 0},
												{0, 0, 0, 0, 0, 0, 0, 0, 1},
												{0, 0, 0, 0, 0, 0, 0, 0, 0},
											}
									)
								),
							Arrays.asList(
								Arrays.asList('k', 'o'),
								Arrays.asList('l', 'i')
							)
					)
				},				
				//kolinn vs kpollim
				new Object[]{
						Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
						Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
						new LengthResult<>(
								2,
								"Longest Common Substring length",
								new LengthMatrixResult<>(
										1,
										null,
										new Matrix<>(
												new Integer[][]{
													{0, 0, 0, 0, 0, 0, 0, 0},
													{0, 1, 0, 0, 0, 0, 0, 0},
													{0, 0, 0, 1, 0, 0, 0, 0},
													{0, 0, 1, 0, 0, 0, 0, 0},
													{0, 0, 0, 0, 1, 1, 0, 0},
													{0, 0, 0, 0, 0, 0, 2, 0},
													{0, 0, 0, 0, 0, 0, 0, 0},
													{0, 0, 0, 0, 0, 0, 0, 0},
												}
										)
									),
								Arrays.asList(
									Arrays.asList('l', 'i')
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
							"Longest Common Substring length",
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
							"Longest Common Substring length",
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
							"Longest Common Substring length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
											new Integer[][]{
												{0, 0, 0, 0},
												{0, 0, 1, 0},
												{0, 1, 0, 2},
												{0, 0, 0, 0},
												{0, 1, 0, 1},
											}
									)
								),
							Arrays.asList(
									Arrays.asList('k', 'o')
							)
					)
				}
		);
	}
}
