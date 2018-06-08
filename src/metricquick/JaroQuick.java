package metricquick;

import java.util.List;

import support.Tuple;

public class JaroQuick<S> implements MetricQuick<S> {

	private double w1, w2, wt;
	
	@Override
	public Number calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		//TODO kontrola
		
		Tuple<Integer, Integer> aux = getCAndT(sequenceFrom, sequenceTo);
		int c = aux.getFirst();
		int t = aux.getSecond();
		if(sequenceFrom.size() == 0)
			return 0;
		if(sequenceTo.size() == 0)
			return 0;
		if(c == 0)
			return 0;
		return (w1 * c / sequenceFrom.size())
				+ (w2 * c / sequenceTo.size())
				+ (wt * (c - t) / c);
	}
	
	
	private Tuple<Integer, Integer> getCAndT(List<S> sequenceFrom, List<S> sequenceTo){
		return getCAndT(sequenceFrom, sequenceTo, new Tuple<>(0, 0), -1, -1);
	}
	
	private Tuple<Integer, Integer> getCAndT(
			List<S> sequenceFrom, List<S> sequenceTo,
			Tuple<Integer, Integer> result,
			int row, int col){
	//	double range = calculateRange(sequenceFrom, sequenceTo);
		
		//calculating's end
		if(row+1 >= sequenceFrom.size() || col+1 >= sequenceTo.size())
			return result;
		//same
		if(sequenceFrom.get(row+1).equals(sequenceTo.get(col+1)) )
			return getCAndT(sequenceFrom, sequenceTo, result.withFirst(result.getFirst() + 1), row+1, col+1);
		//transposition
		if(isTransposition(row, col, sequenceFrom, sequenceTo))
			return getCAndT(sequenceFrom, sequenceTo, 
								result.withFirst(
									result.getFirst()+2).withSecond(result.getSecond()+1
								),
							row+2, col+2);
		final int direct = look(row+1, col+1, sequenceFrom, sequenceTo);
		int match = 0;
		//deletion
		if(direct == 0){
			if(row >= 0 && col >= 0)
				if(!sequenceFrom.get(row).equals(sequenceTo.get(col))
						&& sequenceFrom.get(row+1).equals(sequenceTo.get(col))
						)
					match++;
			return getCAndT(sequenceFrom, sequenceTo, result.withFirst(result.getFirst() + match), row+1, col);
		}	
		//insertion
		if(direct == 1){
			if(!sequenceFrom.get(row).equals(sequenceTo.get(col))
					&& sequenceFrom.get(row).equals(sequenceTo.get(col+1))
					)
				match++;
			return getCAndT(sequenceFrom, sequenceTo, result.withFirst(result.getFirst() + match), row, col+1);
		}	
		//substitution - insertion and deletion
		return getCAndT(sequenceFrom, sequenceTo, result, row+1, col+1);
	}
	
	private double calculateRange(List<S> sequenceFrom, List<S> sequenceTo){
		if(Math.max(sequenceFrom.size(), sequenceTo.size())/2.0 - 1 > 2)
			return Math.max(sequenceFrom.size(), sequenceTo.size())/2.0 - 1;
		return Math.min(sequenceFrom.size(), sequenceTo.size())/2.0;
	}
	
	private boolean isTransposition(int row, int col, List<S> sequenceFrom, List<S> sequenceTo){
		if(row+2 < sequenceFrom.size() && col+2 < sequenceTo.size()
				&& sequenceFrom.get(row+2).equals(sequenceTo.get(col+1))
				&& sequenceFrom.get(row+1).equals(sequenceTo.get(col+2))
			)
			return true;
		return false;
	}
	
	//TODO
	private int look(final int row, final int col, List<S> sequenceFrom, List<S> sequenceTo){
		
		return -1;
	}
}
