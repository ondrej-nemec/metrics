package metricinfo;

import structures.ResultSet;

public interface MetricInfo<S, T> {

	public ResultSet<S, T> calculate(Iterable<S> sequenceFrom, Iterable<S> sequenceTo);
	
}
