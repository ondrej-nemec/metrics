package metricinfo;

import java.util.List;

import structures.ResultSet;
import support.Matrix;
import support.Tuple;

public class LevenshteinInfo<S> implements MetricInfo<S, Matrix<Tuple<S, Boolean>>>{

	@Override
	public ResultSet<S, Matrix<Tuple<S, Boolean>>> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		throw new UnsupportedOperationException();
	}

}
