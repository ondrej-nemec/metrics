package metricinfo;

import static org.junit.Assert.*;

import java.util.Arrays;
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
						new Tuple3<Character, ResultSet<Number, Matrix<JaroValues>>>(
								Arrays.asList(' '),
								Arrays.asList(' '),
								null
							)
				}
			);
	}
}
