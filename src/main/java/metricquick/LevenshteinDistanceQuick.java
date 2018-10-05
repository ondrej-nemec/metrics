package metricquick;

import java.util.List;

public class LevenshteinDistanceQuick<S> implements MetricQuick<S> {

	@Override
	public Number calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		int[] upper = new int[sequenceTo.size()+1];
		for(int i = 0; i <= sequenceTo.size(); i++){
			upper[i] = i;
		}
		int[] actual = new int[sequenceTo.size()+1];
		for(int row = 1; row <= sequenceFrom.size(); row++){
			actual[0] = row;
			for(int col = 1; col <= sequenceTo.size(); col++){
				int bool = 1;
				if(sequenceFrom.get(row-1).equals(sequenceTo.get(col-1)))
					bool--;
				actual[col] = 
						Math.min(upper[col-1] + bool,
								Math.min(
										upper[col] + 1,
										actual[col-1] + 1
										)
								);
			}
			upper = actual;
			actual = new int[sequenceTo.size()+1];
			
		}
		return upper[sequenceTo.size()];
	}

}
