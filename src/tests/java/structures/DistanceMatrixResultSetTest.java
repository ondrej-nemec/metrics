package structures;

import static org.junit.Assert.assertSame;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import support.Matrix;
import support.Tuple2;

@RunWith(JUnitParamsRunner.class)
public class DistanceMatrixResultSetTest {
	
	@Test
	@Parameters
	public void testEqualsWorks(Object comparedObject, boolean expectedResult) {
		DistanceMatrixResult<Boolean> actualObject = new DistanceMatrixResult<>(
				new Matrix<>(
					new Boolean[][]{
						{false, true, true}
					}
				),
				4,
				(a)->a.toString(),
				Arrays.asList(
					new Tuple2<>(1, 1),
					new Tuple2<>(1, 2)	
				)
		);		
		assertSame(expectedResult, actualObject.equals(comparedObject));
	}
	
	public Object[] parametersForTestEqualsWorks() {
		return new Object[]{
				new Object[]{
					"Absolutly not MatrixResultSet", false
				},
				new Object[]{
					new DistanceMatrixResult<Integer>(
							new Matrix<>(new Integer[][]{{1}}), 
							4,
							(a)->a.toString(),
							Arrays.asList(
								new Tuple2<>(1, 1),
								new Tuple2<>(1, 2)	
							)
					),
					false
				},
				new Object[]{
						new DistanceMatrixResult<>(
								new Matrix<>(
									new Boolean[][]{
										{false, true, true, false}
									}
								),
								4,
								(a)->a.toString(),
								Arrays.asList(
									new Tuple2<>(1, 1),
									new Tuple2<>(1, 2)	
								)
						),
						false
				},
				new Object[]{
						new DistanceMatrixResult<>(
								new Matrix<>(
									new Boolean[][]{
										{false, true, true}
									}
								),
								0,
								(a)->a.toString(),
								Arrays.asList(
									new Tuple2<>(1, 1),
									new Tuple2<>(1, 2)	
								)
						),
						false	
				},
				new Object[]{
						new DistanceMatrixResult<>(
								new Matrix<>(
									new Boolean[][]{
										{false, true, true}
									}
								),
								4,
								(a)->a.toString(),
								Arrays.asList(
									new Tuple2<>(1, 1),
									new Tuple2<>(1, 2),
									new Tuple2<>(1, 2)
								)
						),
						false
				}
		};
	}
}
