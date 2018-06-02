package metricinfo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import structures.ResultSet;
import support.Tuple3;

public class HammingInfoTest {

	//TODO exceptions test
	
	@Test
	public void testCalculateWork() {
		HammingInfo<Character> dis = new HammingInfo<>();
		List<Tuple3<Character, ResultSet<Number, String>>> data = dataProvider();
		for (Tuple3<Character, ResultSet<Number, String>> item : data) {
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
	private List<Tuple3<Character, ResultSet<Number, String>>> dataProvider() {
		return Arrays.asList(
				new Tuple3[]{
						new Tuple3<Character, ResultSet<Number, String>>(
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
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
										  	  '�', 'p', 'o', 'd', 'o', 'b', '�', 'o', 'v', '�', 'v',
										  	  'a', 't', 'e', 'l', 'n', '�', 'j', '�', '�', 'h', 'o'),
								Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
											  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
											  '�', 't', 'e', 'l', 'n', '�', 'j', '�', '�', 'h', 'o'),
								null
							)
				}
			);
	}

}
