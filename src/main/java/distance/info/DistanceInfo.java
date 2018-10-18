package distance.info;

import java.util.List;

import structures.DistanceResultSet;

public interface DistanceInfo<S, T> {

	public DistanceResultSet<S, T> calculate(List<S> sequenceFrom, List<S> sequenceTo);
	
}
