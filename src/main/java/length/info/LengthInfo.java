package length.info;

import java.util.List;

import structures.LengthResult;

public interface LengthInfo<S, T> {

	public LengthResult<S, T> calculate(List<S> sequenceFrom, List<S> sequenceTo);

}
