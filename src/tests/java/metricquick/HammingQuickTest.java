package metricquick;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import exception.SequencesMustHaveSameLengthException;

@RunWith(Parameterized.class)
public class HammingQuickTest {

	private Number distance;
	private List<Character> from;
	private List<Character> to;
	
	public HammingQuickTest(List<Character> from, List<Character> to, Number distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}
	
	@Test(expected=SequencesMustHaveSameLengthException.class)
	public void testCalculateThrowsWhenInvalidInput(){
		HammingQuick<Character> h = new HammingQuick<Character>();
		h.calculate(Arrays.asList('a', 'b'), Arrays.asList('a'));
	}
	
	@Test
	public void testCalculateWork() {
		HammingQuick<Character> dis = new HammingQuick<>();
		assertEquals(
				distance,
				dis.calculate(from, to)
			);
	}

	@Parameters
	public static Collection<Object[]> dataProvider() {
		return Arrays.asList(
					new Object[]{
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
							0
					},
					new Object[]{
							Arrays.asList('h', 'o', 'u', 's', 'k', 'a'),
							Arrays.asList('h', 'o', 'u', 's', 'l', 'e'),
							2
					},
					new Object[]{
							Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
							Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
							5
					},
					new Object[]{
							Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
							Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
							5
					},
					new Object[]{
							Arrays.asList('w', 'o', 'r', 'd'),
							Arrays.asList('7', ';', '$', '�'),
							4
					},
					new Object[]{
							Arrays.asList('a', 'b', 'b', 'c', 'b'),
							Arrays.asList('a', 'b', 'c', 'a', 'b'),
							2
					},
					new Object[]{
							Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
										  '�', 'p', 'o', 'd', 'o', 'b', '�', 'o', 'v', '�', 'v',
										  'a', 't', 'e', 'l', 'n', '�', 'j', '�', '�', 'h', 'o'),
							Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
										  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
										  '�', 't', 'e', 'l', 'n', '�', 'j', '�', '�', 'h', 'o'),
							13
					}				
				);
	}

}
