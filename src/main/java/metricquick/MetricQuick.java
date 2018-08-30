package metricquick;

import java.util.List;

public interface MetricQuick<S> {
	
	public Number calculate(List<S> sequenceFrom, List<S> sequenceTo);

}
