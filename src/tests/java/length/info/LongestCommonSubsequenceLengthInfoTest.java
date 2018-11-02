package length.info;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import length.info.LongestCommonSubsequenceLengthInfo;
import structures.LengthMatrixResult;
import structures.LengthResult;
import support.Matrix;
import support.Tuple2;

@RunWith(JUnitParamsRunner.class)
public class LongestCommonSubsequenceLengthInfoTest {
	
	@Test
	@Parameters
	public void testCalculateWorks(
			List<Character> from,
			List<Character> to,
			LengthResult<Character, LengthMatrixResult<Tuple2<Integer, Boolean>>> result) {
		LengthResult<Character, LengthMatrixResult<Tuple2<Integer, Boolean>>> actual = 
				new LongestCommonSubsequenceLengthInfo<Character>().calculate(from, to);
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
							"Longest Common Subsequence length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
										new Tuple2[][]{
											{t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false)},
											{t(0, false), t(1, true), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false)},
											{t(0, false), t(1, false), t(2, true), t(2, false), t(2, false), t(2, false), t(2, false)},
											{t(0, false), t(1, false), t(2, false), t(3, true), t(3, false), t(3, false), t(3, false)},
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, true), t(4, false), t(4, false)},
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(5, true), t(5, false)},
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(5, false), t(6, true)},		
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
										new Tuple2[][]{
											{t(0, false), t(0, false), t(0, false), t(0, false)},
											{t(0, false), t(0, false), t(0, false), t(0, false)},
											{t(0, false), t(0, false), t(0, false), t(0, false)},
											{t(0, false), t(0, false), t(0, false), t(0, false)},					
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
										new Tuple2[][]{
											{t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false)},
											{t(0, false), t(1, true), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false)},
											{t(0, false), t(1, false), t(2, true), t(2, false), t(2, false), t(2, false), t(2, false)},
											{t(0, false), t(1, false), t(2, false), t(3, true), t(3, false), t(3, false), t(3, false)},
											{t(0, false), t(1, false), t(2, false), t(3, false), t(3, false), t(3, false), t(3, false)},
											{t(0, false), t(1, false), t(2, false), t(3, false), t(3, false), t(4, true), t(4, false)},
											{t(0, false), t(1, false), t(2, false), t(3, false), t(3, false), t(4, false), t(5, true)},				
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
											new Tuple2[][]{
												{t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false)},
												{t(0, false), t(1, true), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false)},
												{t(0, false), t(1, false), t(2, true), t(2, false), t(2, false), t(2, false), t(2, false)},
												{t(0, false), t(1, false), t(2, false), t(3, true), t(3, false), t(3, false), t(3, false)},
												{t(0, false), t(1, false), t(2, false), t(3, false), t(3, false), t(4, true), t(4, false)},
												{t(0, false), t(1, false), t(2, false), t(3, false), t(3, false), t(4, false), t(5, true)},
												{t(0, false), t(1, false), t(2, false), t(3, false), t(3, false), t(4, false), t(5, false)},						
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
											new Tuple2[][]{
												{t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false)},
												{t(0, false), t(0, false), t(1, true), t(1, false), t(1, false), t(1, false), t(1, false)},
												{t(0, false), t(1, true), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false)},
												{t(0, false), t(1, false), t(1, false), t(2, true), t(2, false), t(2, false), t(2, false)},
												{t(0, false), t(1, false), t(1, false), t(2, false), t(3, true), t(3, false), t(3, false)},
												{t(0, false), t(1, false), t(1, false), t(2, false), t(3, false), t(4, true), t(4, false)},
												{t(0, false), t(1, false), t(1, false), t(2, false), t(3, false), t(4, false), t(5, true)},
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
								  '�', 'p', 'o', 'd', 'o', 'b', '�', 'o', 'v', '�', 'v',
								  'a', 't', 'e', 'l', 'n', '�', 'j', '�', '�', 'h', 'o'),
						Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
								  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
								  '�', 't', 'e', 'l', 'n', '�', 'j', '�', '�', 'h', 'o'),
					new LengthResult<>(
							27,
							"Longest Common Subsequence length",
							new LengthMatrixResult<>(
									1,
									null,
									new Matrix<>(
										new Tuple2[][]{
											{t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), },
											{t(0, false), t(1, true), t(1, false), t(1, true), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, true), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, true), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), },
											{t(0, false), t(1, false), t(2, true), t(2, false), t(2, true), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, true), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, true), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), },
											{t(0, false), t(1, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(3, true), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, true), t(3, false), t(3, false), t(3, false), t(3, false), },
											{t(0, false), t(1, true), t(2, false), t(3, true), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(3, false), t(4, true), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, true), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), },
											{t(0, false), t(1, false), t(2, true), t(3, false), t(4, true), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, true), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(5, true), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(4, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, true), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, true), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), t(5, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, true), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), t(6, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, true), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, true), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), t(7, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, true), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), t(8, true), t(8, false), t(8, true), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), t(8, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, true), t(9, false), t(9, false), t(9, false), t(9, false), t(9, true), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(9, false), t(10, true), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, true), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(9, false), t(10, true), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), t(10, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(9, false), t(10, false), t(11, true), t(11, false), t(11, true), t(11, false), t(11, false), t(11, true), t(11, false), t(11, false), t(11, false), t(11, false), t(11, false), t(11, false), t(11, false), t(11, false), t(11, false), t(11, false), t(11, false), t(11, false), t(11, false), t(11, true), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, true), t(9, false), t(9, false), t(10, false), t(11, false), t(12, true), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), t(12, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(9, false), t(10, false), t(11, true), t(12, false), t(13, true), t(13, false), t(13, false), t(13, true), t(13, false), t(13, false), t(13, false), t(13, false), t(13, false), t(13, false), t(13, false), t(13, false), t(13, false), t(13, false), t(13, false), t(13, false), t(13, false), t(13, true), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, true), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), t(14, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(9, false), t(10, false), t(11, true), t(12, false), t(13, true), t(14, false), t(14, false), t(15, true), t(15, false), t(15, false), t(15, false), t(15, false), t(15, false), t(15, false), t(15, false), t(15, false), t(15, false), t(15, false), t(15, false), t(15, false), t(15, false), t(15, true), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, true), t(9, false), t(9, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(14, false), t(15, false), t(16, true), t(16, false), t(16, true), t(16, false), t(16, false), t(16, false), t(16, false), t(16, false), t(16, false), t(16, false), t(16, false), t(16, false), t(16, false), t(16, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(14, false), t(15, false), t(16, false), t(16, false), t(16, false), t(17, true), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, true), t(9, false), t(9, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(14, false), t(15, false), t(16, true), t(16, false), t(17, true), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, true), t(8, false), t(9, false), t(9, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(14, false), t(15, false), t(16, false), t(17, true), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), t(17, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(9, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(14, false), t(15, false), t(16, false), t(17, false), t(17, false), t(17, false), t(18, true), t(18, false), t(18, false), t(18, false), t(18, false), t(18, false), t(18, false), t(18, false), t(18, false), t(18, false), },
											{t(0, false), t(1, false), t(2, true), t(3, false), t(4, true), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(10, true), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(14, false), t(15, false), t(16, false), t(17, false), t(17, false), t(17, false), t(18, false), t(19, true), t(19, false), t(19, false), t(19, false), t(19, false), t(19, false), t(19, false), t(19, false), t(19, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(10, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(14, false), t(15, false), t(16, false), t(17, false), t(17, false), t(17, false), t(18, false), t(19, false), t(20, true), t(20, false), t(20, false), t(20, false), t(20, false), t(20, false), t(20, false), t(20, false), },
											{t(0, false), t(1, true), t(2, false), t(3, true), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(10, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, true), t(15, false), t(16, false), t(17, false), t(17, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, true), t(21, false), t(21, false), t(21, false), t(21, false), t(21, false), t(21, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(9, false), t(10, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(15, false), t(16, false), t(17, false), t(17, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, true), t(22, false), t(22, false), t(22, false), t(22, false), t(22, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, true), t(10, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(15, false), t(16, false), t(17, false), t(17, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, true), t(23, false), t(23, false), t(23, false), t(23, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(10, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(15, false), t(16, false), t(17, false), t(17, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, false), t(24, true), t(24, false), t(24, false), t(24, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(10, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(15, false), t(16, false), t(17, false), t(17, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, false), t(24, false), t(25, true), t(25, false), t(25, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(10, false), t(10, false), t(11, false), t(12, false), t(13, false), t(14, false), t(15, false), t(15, false), t(16, false), t(17, false), t(17, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, false), t(24, false), t(25, false), t(26, true), t(26, false), },
											{t(0, false), t(1, false), t(2, false), t(3, false), t(4, false), t(4, false), t(5, false), t(6, false), t(7, false), t(8, false), t(9, false), t(10, false), t(10, false), t(10, false), t(11, true), t(12, false), t(13, true), t(14, false), t(15, false), t(16, true), t(16, false), t(17, false), t(17, false), t(17, false), t(18, false), t(19, false), t(20, false), t(21, false), t(22, false), t(23, false), t(24, false), t(25, false), t(26, false), t(27, true), },
										}
									)
								),
							Arrays.asList(
									Arrays.asList('n', 'e', 'n', 'e', 'p', 'r', 'a', 
											'v', 'd', 'p', 'o', 'd', 'o', 'b', 'o', 'v', 'v',
											't', 'e', 'l', 'n', '�', 'j', '�', '�', 'h', 'o')
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
										new Tuple2[][]{
											{t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false)},
											{t(0, false), t(1, true), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false)},
											{t(0, false), t(1, false), t(2, true), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false)},
											{t(0, false), t(1, false), t(2, true), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false)},
											{t(0, false), t(1, false), t(2, false), t(2, false), t(3, true), t(3, true), t(3, false), t(3, false), t(3, false)},
											{t(0, false), t(1, false), t(2, false), t(2, false), t(3, false), t(3, false), t(4, true), t(4, false), t(4, false)},
											{t(0, false), t(1, false), t(2, false), t(3, true), t(3, false), t(3, false), t(4, false), t(4, false), t(4, false)},
											{t(0, false), t(1, false), t(2, false), t(3, false), t(3, false), t(3, false), t(4, false), t(4, false), t(5, true)},
											{t(0, false), t(1, false), t(2, false), t(3, false), t(3, false), t(3, false), t(4, false), t(4, false), t(5, false)},
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
											new Tuple2[][]{
												{t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false)},
												{t(0, false), t(1, true), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false), t(1, false)},	
												{t(0, false), t(1, false), t(1, false), t(2, true), t(2, false), t(2, false), t(2, false), t(2, false)},	
												{t(0, false), t(1, false), t(2, true), t(2, false), t(2, false), t(2, false), t(2, false), t(2, false)},	
												{t(0, false), t(1, false), t(2, false), t(2, false), t(3, true), t(3, true), t(3, false), t(3, false)},	
												{t(0, false), t(1, false), t(2, false), t(2, false), t(3, false), t(3, false), t(4, true), t(4, false)},	
												{t(0, false), t(1, false), t(2, false), t(2, false), t(3, false), t(3, false), t(4, false), t(4, false)},	
												{t(0, false), t(1, false), t(2, false), t(2, false), t(3, false), t(3, false), t(4, false), t(4, false)},
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
										new Tuple2[][]{
											{t(0, false), t(0, false), t(0, false), t(0, false), t(0, false), t(0, false)},
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
										new Tuple2[][]{
											{t(0, false)},
											{t(0, false)},
											{t(0, false)},
											{t(0, false)},
											{t(0, false)},
											{t(0, false)},
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
