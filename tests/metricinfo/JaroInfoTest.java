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

import structures.ResultSet;
import support.JaroValues;
import support.Matrix;

@RunWith(Parameterized.class)
public class JaroInfoTest {

	private ResultSet<Double, Matrix<JaroValues>> result;
	private List<Character> from;
	private List<Character> to;
	
	public JaroInfoTest(List<Character> from, List<Character> to, ResultSet<Double, Matrix<JaroValues>> result) {
		this.from = from;
		this.to = to;
		this.result = result;
	}
	//TODO exceptions test
	
	@Test
	public void testCalculateWork() {
		JaroInfo<Character> dis = new JaroInfo<>(' ');
		assertEquals(
				result, 
				dis.calculate(from, to)
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
									Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
									Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
									"Jaro distance",
									"EEEEEE",
									1.0,
									null
								)
					},
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							new LinkedList<>(),
							new ResultSet<>(
									Arrays.asList('s', 'l', 'o', 'v', 'o'),
									new LinkedList<>(),
									null,
									null,
									"Jaro distance",
									"",
									0.0,
									null
								)
					},
					new Object[]{
							new LinkedList<>(),
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							new ResultSet<>(
									new LinkedList<>(),
									Arrays.asList('s', 'l', 'o', 'v', 'o'),
									null,
									null,
									"Jaro distance",
									"",
									0.0,
									null
								)
					},
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							Arrays.asList('l'),
							new ResultSet<>(
									Arrays.asList('s', 'l', 'o', 'v', 'o'),
									Arrays.asList('l'),
									null,
									null,
									"Jaro distance",
									"",
									0.7333333333333333,
									null
								)
					},
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'o'),
							Arrays.asList('s', 'l'),
							new ResultSet<>(
									Arrays.asList('s', 'l', 'o', 'v', 'o'),
									Arrays.asList('s', 'l'),
									null,
									null,
									"Jaro distance",
									"",
									0.8,
									null
								)
					},
					new Object[]{
							Arrays.asList('s', 'l', 'o', 'v', 'a'),
							Arrays.asList('a'),
							new ResultSet<>(
									Arrays.asList('s', 'l', 'o', 'v', 'a'),
									Arrays.asList('a'),
									null,
									null,
									"Jaro distance",
									"",
									0.0,
									null
								)
					},
					new Object[]{
							Arrays.asList('o', 'k', 'n', 'o'),
							Arrays.asList('w', 'i', 'n', 'd', 'o', 'w'),
							new ResultSet<>(
									Arrays.asList('o', 'k', 'n', 'o'),
									Arrays.asList('w', 'i', 'n', 'd', 'o', 'w'),
									null,
									null,
									"Jaro distance",
									"",
									0.6111111111111112,
									null
								)
					},
					new Object[]{
							Arrays.asList('k', 'o', 'l', 'o'),
							Arrays.asList('o', 'k', 'o'),
							new ResultSet<>(
									Arrays.asList('k', 'o', 'l', 'o'),
									Arrays.asList('o', 'k', 'o'),
									null,
									null,
									"Jaro distance",
									"",
									0.8055555555555555,
									null
								)
					},
					new Object[]{
							Arrays.asList('n', 'e', 'n', 'í'),
							Arrays.asList('n', 'i', 'e', ' ', 'j', 'e'),
							new ResultSet<>(
									Arrays.asList('n', 'e', 'n', 'í'),
									Arrays.asList('n', 'i', 'e', ' ', 'j', 'e'),
									null,
									null,
									"Jaro distance",
									"",
									0.6111111111111112,
									null
								)
					},
					new Object[]{
							Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
							Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
							new ResultSet<>(
									Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
									Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
									null,
									null,
									"Jaro distance",
									"",
									0.7777777777777777,
									null
								)
					},
					new Object[]{
							Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
							Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
							new ResultSet<>(
									Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
									Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
									null,
									null,
									"Jaro distance",
									"",
									0.75,
									null
								)
					},
					new Object[]{
							Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
							Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
							new ResultSet<>(
									Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
									Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
									null,
									null,
									"Jaro distance",
									"",
									0.7428571428571429,
									null
								)
					},
					new Object[]{
							Arrays.asList('w', 'o', 'r', 'd'),
							Arrays.asList('7', ';', '$', 'Ð'),
							new ResultSet<>(
									Arrays.asList('w', 'o', 'r', 'd'),
									Arrays.asList('7', ';', '$', 'Ð'),
									null,
									null,
									"Jaro distance",
									"",
									0.0,
									null
								)
					},
					new Object[]{
							Arrays.asList('a', 'b', 'b', 'c', 'b'),
							Arrays.asList('a', 'b', 'c', 'a', 'b'),
							new ResultSet<>(
									Arrays.asList('a', 'b', 'b', 'c', 'b'),
									Arrays.asList('a', 'b', 'c', 'a', 'b'),
									null,
									null,
									"Jaro distance",
									"",
									0.8666666666666667,
									null
								)
					},
					new Object[]{
							Arrays.asList('a', 'a', 'h', 'o', 'j'),
							Arrays.asList('a', 'h', 'o', 'j', 'k', 'y'),
							new ResultSet<>(
									Arrays.asList('a', 'a', 'h', 'o', 'j'),
									Arrays.asList('a', 'h', 'o', 'j', 'k', 'y'),
									null,
									null,
									"Jaro distance",
									"",
									0.8222222222222222,
									null
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
									Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
											  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
											  'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
									Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
											  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
											  'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
									null,
									null,
									"Jaro distance",
									"",
									0.8787878787878787,
									null
								)
					}				
				);
	}
}
