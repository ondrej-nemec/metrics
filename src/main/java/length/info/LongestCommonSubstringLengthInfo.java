package length.info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import structures.LengthMatrixResult;
import structures.LengthResult;
import support.Matrix;
import support.Tuple3;

public class LongestCommonSubstringLengthInfo<S> implements StructureMatrixLength<S, Integer>{

	@Override
	public LengthResult<S, LengthMatrixResult<Integer>> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		Tuple3<Matrix<Integer>, Integer, List<Integer>> result = startFill(sequenceFrom, sequenceTo);
		Collection<List<S>> subs = getSubsequences(result.getFirst(), result.getThird(), sequenceFrom, result.getSecond());
		
		return new LengthResult<>(
				result.getSecond(),
				"Longest Common Substring length", 
				new LengthMatrixResult<>(
						1,
						(a)->a.toString(),
						result.getFirst()
				),
				subs
		);
	}	

	private Collection<List<S>> getSubsequences(Matrix<Integer> first, List<Integer> lastIndexes, List<S> sequenceFrom, int length) {
		Collection<List<S>> result = new ArrayList<>();
		for (Integer index : lastIndexes) {
			List<S> seq = new ArrayList<>();
			for (int i = 0; i < length; i++) {
				seq.add(sequenceFrom.get(index - i - 1));
			}
			result.add(revertSeq(seq));
		}
		return result;
	}

	private List<S> revertSeq(List<S> seq) {
		List<S> result = new ArrayList<>();
		for (int i = seq.size() - 1; i >= 0; i--) {
			result.add(seq.get(i));
		}
		return result;
	}

	private Tuple3<Matrix<Integer>, Integer, List<Integer>> startFill(List<S> sequenceFrom, List<S> sequenceTo) {
		int length = 0;
		List<Integer> lastIndexes = new ArrayList<>();
		Matrix<Integer> matrix = new Matrix<>(new Integer[sequenceFrom.size() + 1][sequenceTo.size() + 1]);
		for (int row = 0; row < matrix.getRowSize(); row++) {
			for (int col = 0; col < matrix.getColumnSize(); col++) {
				if (col == 0 || row == 0) {
					matrix.setCell(row, col, 0);
				} else if (sequenceFrom.get(row - 1).equals(sequenceTo.get(col - 1))) {
					int l = matrix.getCell(row-1, col-1) + 1;
					matrix.setCell(row, col, l);
					if (matrix.getCell(row, col) == length) {
						lastIndexes.add(row);
					} else if (matrix.getCell(row, col) > length) {
						lastIndexes = new ArrayList<>();
						length = l;
						lastIndexes.add(row);
					}						
				} else {
					matrix.setCell(
						row,
						col,
						0
					);
				}										
			}
		}
		return new Tuple3<>(matrix, length, lastIndexes);
	}

}
