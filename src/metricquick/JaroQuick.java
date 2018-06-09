package metricquick;

import java.util.List;

import support.Tuple;

public class JaroQuick<S> implements MetricQuick<S> {

	private double w1, w2, wt;
	
	public JaroQuick() {
		this.w1 = 1.0/3.0;
		this.w2 = 1.0/3.0;
		this.wt = 1.0/3.0;
	}
	
	public JaroQuick(double w1, double w2, double  wt) {
		this.w1 = w1;
		this.w2 = w2;
		this.wt = wt;
	}
	
	@Override
	public Number calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		//TODO kontrola
		
		Tuple<Integer, Integer> aux = getCAndT(sequenceFrom, sequenceTo);
		int c = aux.getFirst();
		int t = aux.getSecond();
		if(sequenceFrom.size() == 0)
			return 0.0;
		if(sequenceTo.size() == 0)
			return 0.0;
		if(c == 0)
			return 0.0;
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
	
	/**
	 * calculate range of calculating
	 * @param sequenceFrom
	 * @param sequenceTo
	 * @return
	 */
	private double calculateRange(List<S> sequenceFrom, List<S> sequenceTo){
		if(Math.max(sequenceFrom.size(), sequenceTo.size())/2.0 - 1 > 2)
			return Math.max(sequenceFrom.size(), sequenceTo.size())/2.0 - 1;
		return Math.min(sequenceFrom.size(), sequenceTo.size())/2.0;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @param sequenceFrom
	 * @param sequenceTo
	 * @return
	 */
	private boolean isTransposition(int row, int col, List<S> sequenceFrom, List<S> sequenceTo){
		if(row+2 < sequenceFrom.size() && col+2 < sequenceTo.size()
				&& sequenceFrom.get(row+2).equals(sequenceTo.get(col+1))
				&& sequenceFrom.get(row+1).equals(sequenceTo.get(col+2))
			)
			return true;
		return false;
	}
	
	/**
	 * look if in column/row/diagnal are equals objects
	 * @param rowIndex
	 * @param colIndex
	 * @param sequenceFrom
	 * @param sequenceTo
	 * @return direct to closest equals - 0 column, 1 row, other diagonal
	 */
	private int look(final int rowIndex, final int colIndex, List<S> sequenceFrom, List<S> sequenceTo){
		double range = calculateRange(sequenceFrom, sequenceTo);
		final int dia = distance(rowIndex, colIndex, 2, range, sequenceFrom, sequenceTo);
		final int col = distance(rowIndex, colIndex, 1, range, sequenceFrom, sequenceTo);
		final int row = distance(rowIndex, colIndex, 0, range, sequenceFrom, sequenceTo);
		
		if(col != -1 && check(col, dia, true) && check(col, row, true))//deletion
			return 0;
		if(row != -1 && check(row, dia, true) && check(row, col, false))//substution
			return 1;
		return -1;
	}
	
	/**
	 * 
	 * @param expected
	 * @param actual
	 * @param equal
	 * @return
	 */
	private boolean check(final int expected, final int actual, final boolean equal){
		if(actual == -1)
			return true;
		if(equal)
			return expected <= actual;
		return expected < actual;
	}
	
	/**
	 * calculate distance to equals objects
	 * @param row
	 * @param col
	 * @param direct - 0 row, 1 column, other diagonal
	 * @param range
	 * @param sequenceFrom
	 * @param sequenceTo
	 * @return distance or sequence size
	 */
	private int distance(int row, int col, int direct, double range, List<S> sequenceFrom, List<S> sequenceTo){
		if(direct == 0){ //row
			for(int i = col; i < sequenceTo.size(); i++){
				if(i >= row + range + (row-col))
					return -1;
				if(sequenceFrom.get(row).equals(sequenceTo.get(i)))
					return i - col;
			}
		}
		if(direct == 1){ //column
			for(int i = row; i < sequenceFrom.size(); i++){
				if(i >= col + range + (row-col))
					return -1;
				if(sequenceFrom.get(i).equals(sequenceTo.get(col)))
					return i - row;
			}
		}
		//diagonal
		for(int i = 0; i < sequenceFrom.size(); i++){
			if(i >= row + range + (row - col))
				return -1;
			if(sequenceFrom.get(row + i).equals(sequenceTo.get(col + i)))
				return i;
		}
		return (int)range;
	}
	
}
