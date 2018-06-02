package metricquick;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import support.Tuple3;

public class LevenshteinQuickTest {

	//TODO exceptions test
	
	@Test
	public void testCalculateWork() {
		LevenshteinQuick<Character> dis = new LevenshteinQuick<>();
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
								0
							),
						new Tuple3<Character, Number>(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								new LinkedList<>(),
								5
							),
						new Tuple3<Character, Number>(
								new LinkedList<>(),
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								5
							),
						new Tuple3<Character, Number>(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList('l'),
								4
							),
						new Tuple3<Character, Number>(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList('s', 'l'),
								3
							),
						new Tuple3<Character, Number>(
								Arrays.asList('s', 'l', 'o', 'v', 'a'),
								Arrays.asList('a'),
								4
							),
						new Tuple3<Character, Number>(
								Arrays.asList('o', 'k', 'n', 'o'),
								Arrays.asList('w', 'i', 'n', 'd', 'o', 'w'),
								4
							),
						new Tuple3<Character, Number>(
								Arrays.asList('n', 'e', 'n', '�'),
								Arrays.asList('n', 'i', 'e', ' ', 'j', 'e'),
								4
							),
						new Tuple3<Character, Number>(
								Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
								Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
								2
							),
						new Tuple3<Character, Number>(
								Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
								Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
								4
							),
						new Tuple3<Character, Number>(
								Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
								Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
								4
							),
						new Tuple3<Character, Number>(
								Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
											  '�', 'p', 'o', 'd', 'o', 'b', '�', 'o', 'v', '�', 'v',
											  'a', 't', 'e', 'l', 'n', '�', 'j', '�', '�', 'h', 'o'),
								Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
											  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
											  '�', 't', 'e', 'l', 'n', '�', 'j', '�', '�', 'h', 'o'),
								7
							)
				}
			);
	}

}
