package metricquick;

public interface MetricQuick<S> {
	
	public Number calculate(Iterable<S> sequenceFrom, Iterable<S> sequenceTo);

}
