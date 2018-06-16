package structures;

import java.util.List;
import java.util.function.Function;

import support.Matrix;
import support.Tuple2;

public class MatrixResultSet<S, R> extends ResultSet<S, Matrix<R>>{

	private static final long serialVersionUID = 1L;

	private final  int shift;
	
	private final Function<R, String> values;
	
	private final List<Tuple2<Integer, Integer>> indexes;
	
	public MatrixResultSet(
			final List<S> sequenceFrom,
			final List<S> sequenceTo,
			final List<S> finalSequenceFrom,
			final List<S> finalSequenceTo,
			final String description,
			final String operations,
			final Number distance,
			Matrix<R> matrix,
			int shift,
			Function<R, String> getValue,
			List<Tuple2<Integer, Integer>> indexes) {
		super(
				sequenceFrom,
				sequenceTo,
				finalSequenceFrom,
				finalSequenceTo,
				description,
				operations,
				distance,
				matrix);
		this.shift = shift;
		this.values = getValue;
		this.indexes = indexes;
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

	@Override
	public String toString() {
		String result = super.toString();
		result += "shift " + shift + "\n";
		for(int i = 0; i < indexes.size(); i++){
			result += "\t" + indexes.get(i).getFirst() + "\t" + indexes.get(i).getSecond() + "\n";
		}
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof MatrixResultSet))
			return false;
		@SuppressWarnings("unchecked")
		MatrixResultSet<S, R> aux = (MatrixResultSet<S, R>)o;
		if(shift != aux.getShift())
			return false;
		if(!indexes.equals(aux.getIndexes()))
			return false;
		return super.equals(o);
	}
	
}
