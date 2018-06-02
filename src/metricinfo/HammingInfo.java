package metricinfo;

import java.util.List;

import structures.ResultSet;

public class HammingInfo<S> implements MetricInfo<S, String> {

	@Override
	public ResultSet<S, String> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		throw new UnsupportedOperationException();
	}

}
