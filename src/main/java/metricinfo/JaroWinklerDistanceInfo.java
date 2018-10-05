package metricinfo;

import java.util.List;

import exception.InvalidOpeationCostException;
import structures.MatrixResultSet;
import structures.ResultSet;
import support.JaroValues;

public class JaroWinklerDistanceInfo<S> implements StructureMatrix<S, JaroValues>{

	private double p;
	private final S empty;
	
	public JaroWinklerDistanceInfo(final S empty) {
		this.p = 0.1;
		this.empty = empty;
	}
	
	public JaroWinklerDistanceInfo(final S empty, final double p){
		if(p <= 0 || p >= 1)
			throw new InvalidOpeationCostException(p, "(0, 1)");
		this.p = p;
		this.empty = empty;
	}
	

		
	@Override
	public ResultSet<S, MatrixResultSet<JaroValues>> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		ResultSet<S, MatrixResultSet<JaroValues>> jaro = new JaroDistanceInfo<S>(empty).calculate(sequenceFrom, sequenceTo);
		double distance = jaro.getDistance().doubleValue() +
				(getAlfa(sequenceFrom, sequenceTo) * p *
						(1 - jaro.getDistance().doubleValue())
						);
		return new ResultSet<>(
					jaro.getFinalSequenceFrom(),
					jaro.getFinalSequenceTo(), 
					"Jaro-Winkler distance", //TODO add formula
					jaro.getOperations(),
					distance,
					jaro.getStructure()
				);
	}
	
	private int getAlfa(List<S> from, List<S> to){
		int result = 0;
		int border = Math.min(4, Math.min(from.size(), to.size()));
		int i = 0;
		while(i<border && from.get(i).equals(to.get(i))){
			result++;
			i++;
		}
		return result;
	}

}
