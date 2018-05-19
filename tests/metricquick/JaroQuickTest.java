package metricquick;

import static org.junit.Assert.*;

import java.util.Arrays;
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
								null,
								null,
								null
							)
				}
			);
	}

}
