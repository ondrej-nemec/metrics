package metricquick;

import java.util.List;

public class HammingQuick<S> implements MetricQuick<S> {

	@Override
	public Number calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		//TODO podminky
		int resutl = 0;
		for(int i = 0; i < sequenceFrom.size(); i++){
			if(!sequenceFrom.get(i).equals(sequenceTo.get(i)))
				resutl++;
		}
		return new Integer(resutl);
	}

}
