package metricinfo;

import java.util.List;

import structures.MatrixResultSet;
import structures.ResultSet;
import support.Tuple2;

public class LevenshteinInfo<S> implements StructureMatrix<S, Tuple2<Integer, Boolean>>{

	
	//TODO  kontrola
	
	@Override
	public ResultSet<S, MatrixResultSet<Tuple2<Integer, Boolean>>> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		return null;
	}

}
