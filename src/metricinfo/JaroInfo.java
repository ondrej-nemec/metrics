package metricinfo;

import java.util.List;

import structures.ResultSet;
import support.JaroValues;
import support.Matrix;

public class JaroInfo<S> implements MetricInfo<S, Matrix<JaroValues>>{

	@Override
	public ResultSet<S, Matrix<JaroValues>> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		throw new UnsupportedOperationException();
	}

}
