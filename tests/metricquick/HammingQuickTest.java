package metricquick;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import support.Tuple3;

public class HammingQuickTest {

	//TODO exceptions test
	
	@Test
	public void testCalculateWork() {
		HammingQuick<Character> dis = new HammingQuick<>();
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
								Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
								Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
								2
							),
						new Tuple3<Character, Number>(
								Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
								Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
								5
							),
						new Tuple3<Character, Number>(
								Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
								Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
								5
							),
						new Tuple3<Character, Number>(
								Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
											  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
											  'a', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
								Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
											  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
											  'á', 't', 'e', 'l', 'n', 'ì', 'j', 'š', 'í', 'h', 'o'),
								13
							)
				}
			);
	}

}
