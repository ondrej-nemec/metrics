package structures;

import java.util.List;
import java.util.function.Function;

import support.Matrix;

public class MatrixResultSet<S, T> {

	private final Matrix<T> matrix;
	
	private final  int shift;
	
	private final Function<T, String> values;
	
	private final List<S> indexes;
	
	public MatrixResultSet(Matrix<T> matrix, int shift, Function<T, String> getValue, List<S> indexes) {
		this.matrix = matrix;
		this.shift = shift;
		this.values = getValue;
		this.indexes = indexes;
	}
	
	public Matrix<T> getMatrix() {
		return matrix;
	}

	public int getShift() {
		return shift;
	}

	public Function<T, String> getValues() {
		return values;
	}

	public List<S> getIndexes() {
		return indexes;
	}

	
	
}
