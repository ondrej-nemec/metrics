package distance.info;

import java.util.List;

import structures.DistanceResult;

public interface DistanceInfo<S, T> {

	public DistanceResult<S, T> calculate(List<S> sequenceFrom, List<S> sequenceTo);
	
}
