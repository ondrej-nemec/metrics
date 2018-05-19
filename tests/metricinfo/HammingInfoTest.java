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
								null,
								null,
								null
							)
				}
			);
	}

}
