package support;

/**
 * This class represent a NxM matrix
 * @author Ondøej Nìmec
 *
 * @param <T> - content of cells
 */
public class Matrix<T> {

	private T[][] matrix;
	
	public Matrix(final T[][] matrix) {
		this.matrix = matrix;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return value at position x,y
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public T getCell(final int x, final int y){
		if(matrix.length <= x || x < 0)
			throw new ArrayIndexOutOfBoundsException("Row index: " + x);
		if(matrix[x].length <= y || y < 0)
			throw new ArrayIndexOutOfBoundsException("Column index: " + y);
		return matrix[x][y];
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param value
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public void setCell(final int x, final int y, final T value){
		if(matrix.length >= x|| x < 0)
			throw new ArrayIndexOutOfBoundsException("Row index: " + x);
		if(matrix[x].length >= y || y < 0)
			throw new ArrayIndexOutOfBoundsException("Column index: " + y);
		matrix[x][y] = value;
	}
	
	/**
	 * 
	 * @return count of rows
	 */
	public int getRowSize(){
		return matrix.length;
	}
	
	/**
	 * 
	 * @return count of columns in first row
	 * @throws NullPointerException if matrix has no rows
	 */
	public int getColumnSize(){
		if(matrix.length == 0)
			throw new NullPointerException();
		return matrix[0].length;
	}
	
}
