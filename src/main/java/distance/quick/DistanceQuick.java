package distance.quick;

import java.util.List;

public interface DistanceQuick<S> {
	
	public Number calculate(List<S> sequenceFrom, List<S> sequenceTo);

}
