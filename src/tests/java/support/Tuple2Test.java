package support;

import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class Tuple2Test {
	
	@Test
	@Parameters
	public void testEqualsWorks(Object comparedObject, boolean expectedResult) {
		Tuple2<Integer, String> actualObject = new Tuple2<Integer, String>(1, "hello world");
		assertSame(expectedResult, actualObject.equals(comparedObject));
	}
	
	public Object[] parametersForTestEqualsWorks() {
		return new Object[]{
				new Object[]{
					"Not Tuple2", false
				},
				new Object[]{
					new Tuple2<>("text", "text"), false	
				},
				new Object[]{
					new Tuple2<>(2, "hello world"), false	
				},
				new Object[]{
						new Tuple2<Integer, String>(1, "hell.o world"), false	
				},
				new Object[]{
						new Tuple2<Integer, String>(1, "hello world"), true	
				}
		};
	}
}
