package metricinfo;

import java.util.List;

import structures.MatrixResultSet;
import structures.ResultSet;
import support.Tuple2;

public class LevenshteinInfo<S> implements StructureMatrix<S, Tuple2<S, Boolean>>{

	@Override
	public ResultSet<S, MatrixResultSet<S,Tuple2<S, Boolean>>> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		throw new UnsupportedOperationException();
	}

}
