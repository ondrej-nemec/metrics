package support;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import support.Matrix;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class MatrixTest{
	
	private Matrix<Integer> matrix;
	
	public MatrixTest() {
		Integer[][] inte = new Integer[1][1];
		inte[0][0] = 1;
		this.matrix = new Matrix<>(inte);
	}
	
	@Test
	@Parameters
	public void testEqualsWorks(Object comparedObject, boolean expectedResult) {
		Matrix<Boolean> actualObject = new Matrix<>(new Boolean[][]{
				{true, false, true, false},
				{false, true, false, true},
				{true, false, true, false},
				{false, true, false, true}
			});
		assertSame(expectedResult, actualObject.equals(comparedObject));
	}
	
	public Object[] parametersForTestEqualsWorks() {
		return new Object[]{
				new Object[]{
					"Not matrix", false
				},
				new Object[]{
					new Matrix<>(new String[][]{{"string"}}), false
				},
				new Object[]{
						new Matrix<>(new Boolean[][]{
							{false, true, true, false},
							{false, true, false, true},
							{true, false, true, false},
							{false, true, false, true}
						}),
						false
				},
				new Object[]{
						new Matrix<>(new Boolean[][]{
							{false, true, false, true},
							{true, false, true, false},
							{false, true, false, true}
						}),
						false
				},
				new Object[]{
						new Matrix<>(new Boolean[][]{
							{true, false, true},
							{false, true, false, true},
							{true, false, true, false},
							{false, true, false, true}
						}),
						false
				},
				new Object[]{
						new Matrix<>(new Boolean[][]{
							{true, false, true, false},
							{false, true, false, true},
							{true, false, true, false},
							{false, true, false, true}
						}),
						true
				}
		};
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void matrixGetCellThrowsExceptionLargeX(){
		matrix.getCell(1, 0);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void matrixGetCellThrowsExceptionLowX(){
		matrix.getCell(-1, 0);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void matrixGetCellThrowsExceptionLargeY(){
		matrix.getCell(0, 1);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void matrixGetCellThrowsExceptionLowY(){
		matrix.getCell(0, -1);
	}
	
	@Test
	public void matrixGetCellWork(){
		assertEquals(new Integer(1), matrix.getCell(0, 0));
	}
	
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void matrixSetCellThrowsExceptionLargeX(){
		matrix.setCell(1, 0, 0);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void matrixSetCellThrowsExceptionLowX(){
		matrix.setCell(-1, 0, 0);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void matrixSetCellThrowsExceptionLargeY(){
		matrix.setCell(0, 1, 0);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void matrixSetCellThrowsExceptionLowY(){
		matrix.setCell(0, -1, 0);
	}
	
	@Test
	public void matrixSetCellWork(){
		matrix.setCell(0, 0, 2);
		assertEquals(new Integer(2), matrix.getCell(0, 0));
	}

}
