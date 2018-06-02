package metricinfo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import structures.ResultSet;
import support.JaroValues;
import support.Matrix;
import support.Tuple3;

public class JaroInfoTest {

	//TODO exceptions test
	
	@Test
	public void testCalculateWork() {
		JaroInfo<Character> dis = new JaroInfo<>();
		List<Tuple3<Character, ResultSet<Number, Matrix<JaroValues>>>> data = dataProvider();
		for (Tuple3<Character, ResultSet<Number, Matrix<JaroValues>>> item : data) {
			assertEquals(
					item.getResult(), 
					dis.calculate(
							item.getFirst(),
							item.getSecond()
							)
					);
		}		
	}

	@SuppressWarnings("unchecked")
	private List<Tuple3<Character, ResultSet<Number, Matrix<JaroValues>>>> dataProvider() {
		return Arrays.asList(
				new Tuple3[]{
						new Tuple3<Character, ResultSet<Number, String>>(
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
								null
							),
						new Tuple3<Character, ResultSet<Number, String>>(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								new LinkedList<>(),
								null
							),
						new Tuple3<Character, ResultSet<Number, String>>(
								new LinkedList<>(),
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								null
							),
						new Tuple3<Character, ResultSet<Number, String>>(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList('l'),
								null
							),
						new Tuple3<Character, ResultSet<Number, String>>(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList('s', 'l'),
								null
							),
						new Tuple3<Character, ResultSet<Number, String>>(
								Arrays.asList('s', 'l', 'o', 'v', 'a'),
								Arrays.asList('a'),
								null
							),
						new Tuple3<Character, ResultSet<Number, String>>(
								Arrays.asList('o', 'k', 'n', 'o'),
								Arrays.asList('w', 'i', 'n', 'd', 'o', 'w'),
								null
							),
						new Tuple3<Character, ResultSet<Number, String>>(
								Arrays.asList('n', 'e', 'n', 'í'),
								Arrays.asList('n', 'i', 'e', ' ', 'j', 'e'),
								null
							),
						new Tuple3<Character, ResultSet<Number, String>>(
								Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
								Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
								null
							),
						new Tuple3<Character, ResultSet<Number, String>>(
								Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
								Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
								null
							),
						new Tuple3<Character, ResultSet<Number, String>>(
								Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
								Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
								null
							),
						new Tuple3<Character, ResultSet<Number, String>>(
								Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
											  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
											  'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
								Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
											  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
											  'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
								null
							)
				}
			);
	}
}
