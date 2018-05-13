package metricinfo;

import structures.ResultSet;
import support.JaroValues;
import support.Matrix;

public class JaroWinklerInfo<S> implements MetricInfo<S, Matrix<JaroValues>>{

	@Override
	public ResultSet<S, Matrix<JaroValues>> calculate(Iterable<S> sequenceFrom, Iterable<S> sequenceTo) {
		throw new UnsupportedOperationException();
	}

}
