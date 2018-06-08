package support;

import java.io.Serializable;

/**
 * This class represent a NxM matrix
 * @author Ondøej Nìmec
 *
 * @param <T> - content of cells
 */
public class Matrix<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
		if(matrix.length <= x|| x < 0)
			throw new ArrayIndexOutOfBoundsException("Row index: " + x);
		if(matrix[x].length <= y || y < 0)
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
	
	public int getColumnSize(final int i){
		if(getRowSize() > i)
			throw new NullPointerException();
		return matrix[i].length;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Matrix<?>))
			return false;
		@SuppressWarnings("unchecked")
		Matrix<T> aux = (Matrix<T>)o;
		if(getRowSize() != aux.getRowSize())
			return false;
		for(int i = 0; i < getRowSize(); i++){
			if(getColumnSize(i) != aux.getColumnSize(i))
				return false;
			for(int j = 0; j < getColumnSize(i); j++){
				if(!getCell(i, j).equals(aux.getCell(i, j)))
					return false;
			}
		}
		return super.equals(o);
	}
	
	@Override
	public String toString() {
		String result= "";
		for(int row = 0; row < matrix.length; row++){
			for(int col = 0; col < matrix[row].length; col++){
				result += matrix[row][col] + "\t";
			}
			result += "\n";
		}
		return result;
	}
	
}
