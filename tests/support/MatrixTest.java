package support;

import org.junit.Test;

import support.Matrix;

import static org.junit.Assert.*;

public class MatrixTest{
	
	private Matrix<Integer> matrix;
	
	public MatrixTest() {
		Integer[][] inte = new Integer[1][1];
		inte[0][0] = 1;
		this.matrix = new Matrix<>(inte);
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
		assertSame(1, matrix.getCell(0, 0));
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
