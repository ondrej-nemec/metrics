package metricquick;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import support.Tuple3;

public class JaroQuickTest {

	//TODO exceptions test
	
	@Test
	public void testCalculateWork() {
		JaroQuick<Character> dis = new JaroQuick<>();
		List<Tuple3<Character, Number>> data = dataProvider();
		for (Tuple3<Character, Number> item : data) {
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
	private List<Tuple3<Character, Number>> dataProvider() {
		return Arrays.asList(
				new Tuple3[]{
						new Tuple3<Character, Number>(
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
								1.0
							),
						new Tuple3<Character, Number>(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								new LinkedList<>(),
								0.0
							),
						new Tuple3<Character, Number>(
								new LinkedList<>(),
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								0.0
							),
						new Tuple3<Character, Number>(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList('l'),
								0.7333333333333333
							),
						new Tuple3<Character, Number>(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList('s', 'l'),
								0.8
							),
						new Tuple3<Character, Number>(
								Arrays.asList('s', 'l', 'o', 'v', 'a'),
								Arrays.asList('a'),
								0.0
							),
						new Tuple3<Character, Number>(
								Arrays.asList('o', 'k', 'n', 'o'),
								Arrays.asList('w', 'i', 'n', 'd', 'o', 'w'),
								0.6111111111111112
							),
						new Tuple3<Character, Number>(
								Arrays.asList('n', 'e', 'n', 'í'),
								Arrays.asList('n', 'i', 'e', ' ', 'j', 'e'),
								0.6111111111111112
							),
						new Tuple3<Character, Number>(
								Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
								Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
								0.7777777777777777
							),
						new Tuple3<Character, Number>(
								Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
								Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
								0.75
							),
						new Tuple3<Character, Number>(
								Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
								Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
								0.7428571428571429
							),
						new Tuple3<Character, Number>(
								Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
											  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
											  'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
								Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
											  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
											  'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
								0.8787878787878787
							)
				}
			);
	}

}
