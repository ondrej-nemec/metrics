package structures;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

import support.Matrix;
import support.Tuple2;

public class DistanceMatrixResult<R> implements Serializable{
	//TODO maybe test for equals
	private static final long serialVersionUID = 1L;

	private final  int shift;
	
	private final Function<R, String> values;
	
	private final List<Tuple2<Integer, Integer>> indexes;
	
	private final Matrix<R> matrix;
	
	public DistanceMatrixResult(
			Matrix<R> matrix,
			int shift,
			Function<R, String> getValue,
			List<Tuple2<Integer, Integer>> indexes) {
		this.shift = shift;
		this.values = getValue;
		this.indexes = indexes;
		this.matrix = matrix;
	}
	
	public int getShift() {
		return shift;
	}

	public Function<R, String> getValues() {
		return values;
	}

	public List<Tuple2<Integer, Integer>> getIndexes() {
		return indexes;
	}
	
	public Matrix<R> getMatrix(){
		return matrix;
	}

	@Override
	public String toString() {
		String result = matrix.toString() + "\n";
		result += "shift " + shift + "\n";
		for(int i = 0; i < indexes.size(); i++){
			result += "\t" + indexes.get(i).getFirst() + "\t" + indexes.get(i).getSecond() + "\n";
		}
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof DistanceMatrixResult))
			return false;
		@SuppressWarnings("unchecked")
		DistanceMatrixResult<R> aux = (DistanceMatrixResult<R>)o;
		if(!matrix.equals(aux.getMatrix()))
			return false;
		if(shift != aux.getShift())
			return false;
		if(!indexes.equals(aux.getIndexes()))
			return false;
		return true;
	}	
}
