package length.quick;

import java.util.List;

public class LongestCommonSubsequenceLengthQuick<S> implements LengthQuick<S> {

	@Override
	public Number calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		int[] upper = new int[sequenceTo.size()+1];
		for(int i = 0; i <= sequenceTo.size(); i++){
			upper[i] = 0;
		}
		int[] actual = new int[sequenceTo.size()+1];
		for(int row = 1; row <= sequenceFrom.size(); row++){
			actual[0] = 0;
			for(int col = 1; col <= sequenceTo.size(); col++){
				if(sequenceFrom.get(row-1).equals(sequenceTo.get(col-1)))
					actual[col] = upper[col-1] + 1;
				else
					actual[col] = Math.max(upper[col], actual[col-1]);
			}
			upper = actual;
			actual = new int[sequenceTo.size()+1];
			
		}
		return upper[sequenceTo.size()];
	}
	
}
