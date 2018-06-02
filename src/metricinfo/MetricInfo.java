package metricinfo;

import java.util.List;

import structures.ResultSet;

public interface MetricInfo<S, T> {

	public ResultSet<S, T> calculate(List<S> sequenceFrom, List<S> sequenceTo);
	
}
