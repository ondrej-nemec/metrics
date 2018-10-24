package length.info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import structures.LengthMatrixResult;
import structures.LengthResult;
import support.Matrix;
import support.Tuple2;

public class LongestCommonSubsequenceLengthInfo<S> implements StructureMatrixLength<S, Tuple2<Integer, Boolean>> {

	@Override
	public LengthResult<S, LengthMatrixResult<Tuple2<Integer, Boolean>>> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		Matrix<Tuple2<Integer, Boolean>> matrix = startFill(sequenceFrom, sequenceTo);		
		Collection<List<S>> subs = getSubsequences(matrix, sequenceFrom, sequenceTo);

		return new LengthResult<>(
				matrix.getCell(sequenceFrom.size(), sequenceTo.size()).getFirst(),
				"Longest Common Subsequence length", 
				new LengthMatrixResult<>(
						1,
						(a)->a.toString(),
						matrix
				),
				subs
		);
	}

	private Matrix<Tuple2<Integer, Boolean>> startFill(List<S> sequenceFrom, List<S> sequenceTo) {
		@SuppressWarnings("unchecked")
		Matrix<Tuple2<Integer, Boolean>> matrix = new Matrix<>(new Tuple2[sequenceFrom.size() + 1][sequenceTo.size() + 1]);
		for (int row = 0; row < matrix.getRowSize(); row++) {
			for (int col = 0; col < matrix.getColumnSize(); col++) {
				if (col == 0 || row == 0)
					matrix.setCell(row, col, new Tuple2<>(0, false));
				else if (sequenceFrom.get(row - 1).equals(sequenceTo.get(col - 1)))
					matrix.setCell(
						row,
						col,
						new Tuple2<>(
								matrix.getCell(row-1, col-1).getFirst() + 1,
								true
						)						
					);
				else
					matrix.setCell(
						row,
						col,
						new Tuple2<>(
							Math.max(
								matrix.getCell(row-1, col).getFirst(),
								matrix.getCell(row, col-1).getFirst()
								),
							false
						)
					);					
			}
		}
		return matrix;
	}
	
	private Collection<List<S>> getSubsequences(Matrix<Tuple2<Integer, Boolean>> matrix , List<S> sequenceFrom, List<S> sequenceTo) {
		return getSubsequences(matrix, sequenceFrom, sequenceTo, 0, 0);
	}
	
	private Collection<List<S>> getSubsequences(
			Matrix<Tuple2<Integer, Boolean>> matrix , List<S> sequenceFrom, List<S> sequenceTo, int rowIndex, int colIndex) {
		int row = rowIndex;
		int col = colIndex;
		List<S> prepend = new ArrayList<>();
		while(row < matrix.getRowSize() && col < matrix.getColumnSize()) {
			if(row < matrix.getRowSize()-1 && col < matrix.getColumnSize()-1) {
				if (matrix.getCell(row+1, col+1).getSecond()) {
					prepend.add(sequenceFrom.get(row));
					row++;
					col++;
				} else {
					int look = look(row + 1, col + 1, matrix);
					if (look == 2) {
						Collection<List<S>> result = new ArrayList<>();						
						mergeLists(
							result,
							prepend, 
							getSubsequences(matrix, sequenceFrom, sequenceTo, row + 1, col)
						);
						mergeLists(
							result,
							prepend,
							getSubsequences(matrix, sequenceFrom, sequenceTo, row, col + 1)
						);						
						return result;
					} else if (look == 0) {
						row++;
					} else if (look == 1) {
						col++;
					} else if (look == -1) {
						row++;
						col++;						
					}
				}
			}else { //end
				row++;
				col++;				
			}
		}
		if (prepend.size() == 0)
			return new ArrayList<>();
		return Arrays.asList(prepend);
	}
	
	private void mergeLists(Collection<List<S>> result, final List<S> prepend, Collection<List<S>> append) {
		for (List<S> list : append) {
			List<S> aux = new ArrayList<>();
			aux.addAll(prepend);
			aux.addAll(list);
			result.add(aux);
		}
	}
	
	private int look(final int i, final int j, Matrix<Tuple2<Integer, Boolean>> matrix){
		final int dia = distanceDia(i, j, matrix);
		final int col = distanceCol(i, j, matrix);
		final int row = distanceRow(i, j, matrix);

		if (col == row && col != -1 && (col <= dia || dia == -1))
			return 2;
		if(col == -1 && row == -1)
			return -1;
		//down
		if(col != -1 && dia == -1)
			return 0;
		if(col != -1 && dia >= col)
			return 0;
		//right
		if(row != -1 && dia == -1)
			return 1;
		if(row != -1 && dia >= row)
			return 1;
		return -1;
	}
	
	private int distanceRow(final int row, final int col, Matrix<Tuple2<Integer, Boolean>> matrix){
		for(int i = col; i < getBounds(matrix.getColumnSize()); i++) {
			if(matrix.getCell(row, i).getSecond())
				return i - col;
		}
		return -1;
	}
	
	private int distanceCol(final int row, final int col, Matrix<Tuple2<Integer, Boolean>> matrix){
		for(int i = row; i < getBounds(matrix.getRowSize()); i++) {
			if(matrix.getCell(i, col).getSecond())
				return  i-row;
		}
		return -1;	
	}
	
	private int distanceDia(final int row, final int col, Matrix<Tuple2<Integer, Boolean>> matrix){
		for(int i = 0; i < getBounds(matrix.getRowSize()); i++) {
			if(row + i < matrix.getRowSize() && col + i < matrix.getColumnSize()
					&& matrix.getCell(row + i, col + i).getSecond())
				return i;
		}
		return -1;
	}

	private double getBounds(int actualBound){
		if(actualBound > 12) // my heuristic for long sequences
			return actualBound / 2.0;
		return actualBound;
	}
	
}
