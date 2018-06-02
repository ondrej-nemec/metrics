package metricinfo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import structures.ResultSet;
import support.Matrix;
import support.Tuple;
import support.Tuple3;

public class LevenshteinInfokTest {

	//TODO exceptions test
	
	@Test
	public void testCalculateWork() {
		LevenshteinInfo<Character> dis = new LevenshteinInfo<>();
		List<Tuple3<Character, ResultSet<Number, Matrix<Tuple<Character, Boolean>>>>> data = dataProvider();
		for (Tuple3<Character, ResultSet<Number, Matrix<Tuple<Character, Boolean>>>> item : data) {
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
	private List<Tuple3<Character, ResultSet<Number, Matrix<Tuple<Character, Boolean>>>>> dataProvider() {
		return Arrays.asList(
				new Tuple3[]{
						new Tuple3<Character, ResultSet<Number, Matrix<Tuple<Character, Boolean>>>>(
								Arrays.asList(' '),
								Arrays.asList(' '),
								null
							)
				}
			);
	}
}
