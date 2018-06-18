package metricinfo;

import java.util.ArrayList;
import java.util.List;

import structures.MatrixResultSet;
import structures.ResultSet;
import support.JaroValues;
import support.Matrix;
import support.Tuple2;

public class JaroInfo<S> implements StructureMatrix<S, JaroValues>{

	private double w1;
	private double w2;
	private double wt;
	private final S empty;
	
	private String operations = "";
	
	public JaroInfo(final S empty) {
		this.w1 = this.w2 = this.wt = 1.0/3.0;
		this.empty = empty;
	}
	
	public JaroInfo(final S empty, final double w1, final double w2, final double wt) {
		this.w1 = w1;
		this.w2 = w2;
		this.wt = wt;
		this.empty = empty;
	}
	

	
	@Override
	public ResultSet<S, MatrixResultSet<S, JaroValues>> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		//TODO kontrola
		
		Matrix<JaroValues> matrix = fillMatrix(sequenceFrom, sequenceTo);
		List<S> finalSequenceFrom = new ArrayList<>();
		List<S> finalSequenceTo = new ArrayList<>();
		List<Tuple2<Integer, Integer>> indexes = new ArrayList<>();
		
		Tuple2<Integer, Integer> aux = getCAndT(
				matrix, sequenceFrom, sequenceTo, finalSequenceFrom, finalSequenceTo, indexes);
		double distance;
		int c = aux.getFirst();
		int t = aux.getSecond();
		if(sequenceFrom.size() == 0)
			distance = 0.0;
		else if(sequenceTo.size() == 0)
			distance = 0.0;
		else if(c == 0)
			distance = 0.0;
		else
			distance = (w1 * c / sequenceFrom.size())
					+ (w2 * c / sequenceTo.size())
					+ (wt * (c - t) / c);
		return new ResultSet<>(
					finalSequenceFrom,
					finalSequenceTo,
					"Jaro distance", //TODO dodat vzorec
					operations,
					distance,
					new MatrixResultSet<>(
							matrix,
							0,
							(JaroValues value)->{
									switch(value){
										case TRUE:
											return "1";
										case FALSE:
											return "0";
										default: return "-";
									}
								},
							indexes
							)
				);
	}
	
	private Tuple2<Integer, Integer> getCAndT(
				Matrix<JaroValues> matrix,
				List<S> from,
				List<S> to,
				List<S> finalFrom,
				List<S> finalTo,
				List<Tuple2<Integer, Integer>> indexes
			){
		int c = 0;
		int t = 0;;
		int row = -1;
		int col = -1;
		operations = "";
		
		
		while(row < matrix.getRowSize() && col < matrix.getColumnSize()){ 
			if(row+1 >= from.size() && col+1 >= to.size()){ //end
				row++;
				col++;
			}else if(row+1 >= matrix.getRowSize()){ //edge insertion
				operations += "I";
				finalFrom.add(empty);
				finalTo.add(to.get(col+1));
				indexes.add(new Tuple2<Integer, Integer>(row, col+1));
				col++;
				
			}else if(col+1 >= matrix.getColumnSize()){ //edge deletion
				operations += "D";
				finalFrom.add(from.get(row+1));
				finalTo.add(empty);
				row++;
				
			}else if(matrix.getCell(row+1, col+1) == JaroValues.TRUE){//same
				operations += "E";
				finalFrom.add(from.get(row+1));
				finalTo.add(to.get(col+1));
				indexes.add(new Tuple2<>(row+1, col+1));
				c++;
				row++;
				col++;
			}else if(isTransposition(row, col, matrix)){//transposition
				operations += "TT";
				finalFrom.add(from.get(row+1));
				finalFrom.add(from.get(row+2));
				finalTo.add(to.get(col+1));
				finalTo.add(to.get(col+2));
				indexes.add(new Tuple2<>(row+1, col+2));
				indexes.add(new Tuple2<>(row+2, col+1));
				c += 2;
				t++;
				row += 2;
				col += 2;
			}else{
				final int direct = look(row+1, col+1, matrix);
				if(direct == 0){//deletion
					if(row >= 0 && col >= 0
						&& matrix.getCell(row, col) != JaroValues.TRUE
						&& matrix.getCell(row+1, col) == JaroValues.TRUE)
							c++;
					operations += "D";
					finalFrom.add(from.get(row+1));
					finalTo.add(empty);
					indexes.add(new Tuple2<>(row+1, col));
					row++;
				}else if(direct == 1){//insertion
					if(row >= 0 && col >= col 
						&& matrix.getCell(row, col) != JaroValues.TRUE
						&& matrix.getCell(row, col+1) == JaroValues.TRUE)
						c++;
					operations += "I";
					finalFrom.add(empty);
					finalTo.add(to.get(col+1));
					indexes.add(new Tuple2<>(row, col+1));
					col++;
				}else{//substitution - insertion and deletion
					operations += "ID";
					finalFrom.add(empty);
					finalFrom.add(from.get(row+1));
					finalTo.add(to.get(col+1));
					finalTo.add(empty);
					indexes.add(new Tuple2<>(row, col+1));
					indexes.add(new Tuple2<>(row+1, col));
					row++;
					col++;
				}
			}
		}
		return new Tuple2<Integer, Integer>(c, t);
	}
	
	
	/**
	 * prepare matrix and fill with JaroValues
	 * @param from
	 * @param to
	 * @return
	 */
	private Matrix<JaroValues> fillMatrix(List<S> from, List<S> to){
		Matrix<JaroValues> matrix = new Matrix<>(new JaroValues[from.size()][to.size()]);
		double range = calculateRange(from, to);
		for(int row = 0; row<from.size(); row++){
			for(int col = 0; col<to.size(); col++){
				if(Math.abs(row-col) < range && from.get(row).equals( to.get(col) ) )
					matrix.setCell(row, col, JaroValues.TRUE);
				else if(Math.abs(row-col) < range )
					matrix.setCell(row, col, JaroValues.FALSE);
				else
					matrix.setCell(row, col, JaroValues.NULL);
			}
		}
		return matrix;
	}
	
	/**
	 * calculate range of calculating
	 * @param sequenceFrom
	 * @param sequenceTo
	 * @return
	 */
	private double calculateRange(List<S> sequenceFrom, List<S> sequenceTo){
		if(Math.max(sequenceFrom.size(), sequenceTo.size())/2.0 - 1 > 1)
			return Math.max(sequenceFrom.size(), sequenceTo.size())/2.0 - 1;
		return Math.min(sequenceFrom.size(), sequenceTo.size())/2.0;
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @param matrix
	 * @return
	 */
	private boolean isTransposition(int row, int col, Matrix<JaroValues> matrix){
		if(row+2 < matrix.getRowSize() && col+2 < matrix.getColumnSize()
				&& matrix.getCell(row+2, col+1) == JaroValues.TRUE
				&& matrix.getCell(row+1, col+2) == JaroValues.TRUE
			)
			return true;
		return false;
	}
	
	/**
	 * look if in column/row/diagnal are equals objects
	 * @param rowIndex
	 * @param colIndex
	 * @param matrix
	 * @return direct to closest equals - 0 column, 1 row, other diagonal
	 */
	private int look(final int rowIndex, final int colIndex, Matrix<JaroValues> matrix){
		final int dia = distance(rowIndex, colIndex, 2, matrix);
		final int col = distance(rowIndex, colIndex, 1, matrix);
		final int row = distance(rowIndex, colIndex, 0, matrix);
		
		if(col != -1 && check(col, dia, true) && check(col, row, true))//deletion
			return 0;
		if(row != -1 && check(row, dia, true) && check(row, col, false))//insertion
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
	 * @param matrix
	 * @return distance or sequence size
	 */
	private int distance(int row, int col, int direct,  Matrix<JaroValues> matrix){
		if(direct == 0){ //row
			for(int i = col; i < matrix.getColumnSize(); i++){
				if(matrix.getCell(row, i) == JaroValues.NULL)
					return -1;
				if(matrix.getCell(row, i) == JaroValues.TRUE)
					return i - col;
			}
			return -1;
		}
		if(direct == 1){ //column
			for(int i = row; i < matrix.getRowSize(); i++){
				if(matrix.getCell(i, col) == JaroValues.NULL)
					return -1;
				if(matrix.getCell(i, col) == JaroValues.TRUE)
					return i - row;
			}
			return -1;
		}
		//diagonal
		for(int i = 0; i < matrix.getRowSize(); i++){
			if(row + i >= matrix.getRowSize() || col + i >= matrix.getColumnSize())
				return -1;
			if(matrix.getCell(row+i, col+i) == JaroValues.NULL)
				return -1;
			if(matrix.getCell(row+i, col+i) == JaroValues.TRUE)
				return i;
		}
		//return Math.max(matrix.getRowSize(), matrix.getColumnSize());
		return -1;
	}

}
