package structures;

import java.io.Serializable;
import java.util.function.Function;

import support.Matrix;

public class LengthMatrixResult<R> implements Serializable{
//TODO maybe test for equals
	private static final long serialVersionUID = 1L;
	
	private final  int shift;
	
	private final Function<R, String> values;
	
	private final Matrix<R> matrix;

	public LengthMatrixResult(int shift, Function<R, String> values, Matrix<R> matrix) {
		this.shift = shift;
		this.values = values;
		this.matrix = matrix;
	}

	public int getShift() {
		return shift;
	}

	public Function<R, String> getValues() {
		return values;
	}

	public Matrix<R> getMatrix() {
		return matrix;
	}
	
	@Override
	public String toString() {
		String result = matrix.toString() + "\n";
		result += "shift " + shift + "\n";
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof LengthMatrixResult))
			return false;
		@SuppressWarnings("unchecked")
		LengthMatrixResult<R> aux = (LengthMatrixResult<R>)o;
		if(!matrix.equals(aux.getMatrix()))
			return false;
		if(shift != aux.getShift())
			return false;
		return true;
	}
}
