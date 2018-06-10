package metricquick;

import java.util.ArrayList;
import java.util.List;

public class LevenshteinQuick<S> implements MetricQuick<S> {

	@Override
	public Number calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		List<Integer> upper = new ArrayList<>();
		for(int i = 0; i <= sequenceTo.size(); i++){
			upper.add(i);
		}
		List<Integer> actual = new ArrayList<>();
		for(int row = 1; row <= sequenceFrom.size(); row++){
			actual.add(row);
			for(int col = 1; col <= sequenceTo.size(); col++){
				int bool = 1;
				if(sequenceFrom.get(row-1).equals(sequenceTo.get(col-1)))
					bool--;
				actual.add(
						Math.min(upper.get(col-1) + bool,
								Math.min(
										upper.get(col) + 1,
										actual.get(col-1) + 1
										)
								)
						);
			}
			upper = actual;
			actual = new ArrayList<>();
		}
		return upper.get(sequenceTo.size());
	}

}
