package support;

import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class Tuple3Test {
	
	@Test
	@Parameters
	public void testEqualsWorks(Object comparedObject, boolean expectedResult) {
		Tuple3<Boolean, Integer, String> actualObject = new Tuple3<Boolean, Integer, String>(false, 2, "farewal world");
		assertSame(expectedResult, actualObject.equals(comparedObject));
	}
	
	public Object[] parametersForTestEqualsWorks() {
		return new Object[]{
				new Object[]{
					"Not Tuple3", false	
				},
				new Object[]{
					new Tuple3<String, String, String>("text", "text", "text"), false	
				},
				new Object[]{
						new Tuple3<Boolean, Integer, String>(true, 2, "farewal world"), false
				},
				new Object[]{
						new Tuple3<Boolean, Integer, String>(false, 0, "farewal world"), false
				},
				new Object[]{
						new Tuple3<Boolean, Integer, String>(false, 2, "far ewal world"), false	
				},
				new Object[]{
						new Tuple3<Boolean, Integer, String>(false, 2, "farewal world"), true	
				},
		};
	}
}
