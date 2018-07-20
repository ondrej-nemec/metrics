package metricquick;

import java.util.List;

import exception.SequencesMustHaveSameLengthException;

public class HammingQuick<S> implements MetricQuick<S> {

	@Override
	public Number calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		if(sequenceFrom.size() != sequenceTo.size())
			throw new SequencesMustHaveSameLengthException();
		int resutl = 0;
		for(int i = 0; i < sequenceFrom.size(); i++){
			if(!sequenceFrom.get(i).equals(sequenceTo.get(i)))
				resutl++;
		}
		return new Integer(resutl);
	}

}
