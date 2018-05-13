package metricinfo;

import structures.ResultSet;

public class HammingInfo<S> implements MetricInfo<S, String> {

	@Override
	public ResultSet<S, String> calculate(Iterable<S> sequenceFrom, Iterable<S> sequenceTo) {
		throw new UnsupportedOperationException();
	}

}
