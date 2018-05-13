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
	
	
	@Test
	public void matrixGetThrowsException(){
		fail("Not yet implemented");
	}
	
	@Test
	public void matrixGetWork(){
		assertSame(1, matrix.getCell(0, 0));
	}

}
