package metricinfo;

import java.util.ArrayList;
import java.util.List;

import structures.MatrixResultSet;
import structures.ResultSet;
import support.Matrix;
import support.Tuple2;

public class LevenshteinInfo<S> implements StructureMatrix<S, Tuple2<Integer, Boolean>>{

	private final S empty;
	private int deletionCost = 0;
	private int substitutionCost = 0;
	private int insertionCost = 0;
	
	public LevenshteinInfo(final S empty) {
		this.empty = empty;
	}
	
	public LevenshteinInfo(final S empty, int deletionCost, int insertionCost, int substitutionCost) {
		this.empty = empty;
		if(deletionCost > 0)
			this.deletionCost = deletionCost;
		//TODO exception else
		if(insertionCost > 0)
			this.insertionCost = insertionCost;
		//TODO exception else
		if(substitutionCost > 0)
			this.substitutionCost = substitutionCost;
		//TODO exception else
	}
	
	//TODO  kontrola
	
	@Override
	public ResultSet<S, MatrixResultSet<Tuple2<Integer, Boolean>>> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		
		Matrix<Tuple2<Integer, Boolean>> matrix = startFill(sequenceFrom, sequenceTo);
		
		List<Tuple2<Integer, Integer>> indexes = new ArrayList<>();
		List<S> finalFrom = new ArrayList<>();
		List<S> finalTo = new ArrayList<>();
		String operations = findOperations(matrix, sequenceFrom, sequenceTo, finalFrom, finalTo, indexes);
		
		return new ResultSet<S, MatrixResultSet<Tuple2<Integer,Boolean>>>(
				finalFrom,
				finalTo,
				"Levenshtein distance",
				operations,
				getDistance(matrix, operations),
				new MatrixResultSet<>(
						matrix,
						1,
						(a)-> a.getFirst() + " (" + (a.getSecond()?1:0) + ")",
						indexes
					)
			);
	}

	private String findOperations(
				Matrix<Tuple2<Integer, Boolean>> matrix,
				List<S> from, List<S> to,
				List<S> finalFrom, List<S> finalTo,
			List<Tuple2<Integer, Integer>> indexes) {
		int row = 0;
		int col = 0;
		String operations = "";
		while(row < matrix.getRowSize() && col < matrix.getColumnSize()){ 
		//while(row < fromSize && col < toSize){	
			if(row < matrix.getRowSize()-1 && col < matrix.getColumnSize()-1) {
				if(matrix.getCell(row+1, col+1).getSecond()) { //equals
					finalFrom.add(from.get(row));
					finalTo.add(to.get(col));
					indexes.add(new Tuple2<>(row, col));
					operations += "E";
					row++;
					col++;
				}else{
					int direct = look(row+1, col+1, matrix);
					if(direct == 0) { //deletion
						finalFrom.add(from.get(row));
						finalTo.add(empty);
						indexes.add(new Tuple2<>(row, col));
						operations += "D";
						row++;
					}else if(direct == 1) { //insertion
						finalFrom.add(empty);
						finalTo.add(to.get(col));
						indexes.add(new Tuple2<>(row, col));
						operations += "I";
						col++;
					}else { //substitution
						finalFrom.add(from.get(row));
						finalTo.add(to.get(col));
						indexes.add(new Tuple2<>(row, col));
						operations += "S";
						row++;
						col++;
					}
				}
			}else if(row >= matrix.getRowSize()-1 && col < matrix.getColumnSize()-1) { // on the bottom of matrix - insertion
				finalFrom.add(empty);
				finalTo.add(to.get(col));
				indexes.add(new Tuple2<>(row, col));
				operations += "I";
				col++;
			}else if(col >= matrix.getColumnSize()-1 && row < matrix.getRowSize()-1) { //on right of matrix - deletion
				finalFrom.add(from.get(row));
				finalTo.add(empty);
				indexes.add(new Tuple2<>(row, col));
				operations += "D";
				row++;
			}else { //end
				indexes.add(new Tuple2<>(row, col));
				row++;
				col++;				
			}
		}
		return operations;
	}

	private int look(final int i,final int j, Matrix<Tuple2<Integer, Boolean>> matrix){
		final int dia = distance(i, j, 2, matrix);
		final int col = distance(i, j, 1, matrix);
		final int row = distance(i, j, 0, matrix);
		
		if(col == -1 && row == -1)
			return -1;
		//deletion
		if(col != -1 && dia == -1)
			return 0;
		if(col != -1 && dia > col)
			return 0;
		//insertion
		if(row != -1 && dia == -1)
			return 1;
		if(row != -1 && dia > row)
			return 1;
		return -1;
	}
	
	//TODO seaching range ??
		private int distance(final int row, final int col, final int mode, Matrix<Tuple2<Integer, Boolean>> matrix){
			if(mode == 0) { //row
				for(int i = col; i < matrix.getColumnSize(); i++) {
					if(matrix.getCell(row, i).getSecond())
						return i - col;
				}
			}else if(mode == 1) { //column
				for(int i = row; i < matrix.getRowSize(); i++) {
					if(matrix.getCell(i, col).getSecond())
						return  i-row;
				}
			}else { //diagonal
				for(int i = 0; i < matrix.getRowSize(); i++) {
					if(row + i < matrix.getRowSize() && col + i < matrix.getColumnSize()
							&& matrix.getCell(row + i, col + i).getSecond())
						return i;
				}
			}
			return -1;
		}

	private int getDistance(Matrix<Tuple2<Integer, Boolean>> matrix, String operations) {
		if(deletionCost > 0 && insertionCost > 0 && substitutionCost > 0) {
			//TODO
			return 0;
		}else{
			return matrix.getCell(
					matrix.getRowSize()-1,
					matrix.getColumnSize()-1
				)
				.getFirst();
		}
	}

	/**
	 * @param from
	 * @param to
	 * @return
	 */
	private Matrix<Tuple2<Integer, Boolean>> startFill(List<S> from, List<S> to) {
		@SuppressWarnings("unchecked")
		Matrix<Tuple2<Integer, Boolean>> matrix = new Matrix<>(new Tuple2[from.size()+1][to.size()+1]);
		for(int row = 0; row <= from.size(); row++){
			for(int col = 0; col <= to.size(); col++){
				if(row == 0) {
					matrix.setCell(row, col, new Tuple2<>(col, false));
				}else if(col==0){
					matrix.setCell(row, col, new Tuple2<>(row, false));
				}else{
					boolean second = from.get(row-1).equals(to.get(col-1));
					matrix.setCell(row, col, 
							new Tuple2<>(
									Math.min(
											Math.min(
													matrix.getCell(row, col-1).getFirst()+1,
													matrix.getCell(row-1, col).getFirst()+1),
											matrix.getCell(row-1, col-1).getFirst() + (second?0:1)
											),
									second));
				}
					
			}
		}
		return matrix;
	}	


}
