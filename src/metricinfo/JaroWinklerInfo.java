package metricinfo;

import java.util.List;

import structures.MatrixResultSet;
import structures.ResultSet;
import support.JaroValues;

public class JaroWinklerInfo<S> implements StructureMatrix<S, JaroValues>{

	private double p;
	private final S empty;
	
	public JaroWinklerInfo(final S empty) {
		this.p = 0.1;
		this.empty = empty;
	}
	
	public JaroWinklerInfo(final S empty, final double p){
		this.p = p;
		this.empty = empty;
	}
	

		
	@Override
	public ResultSet<S, MatrixResultSet<S, JaroValues>> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		//TODO kontrola
		
		ResultSet<S, MatrixResultSet<S, JaroValues>> jaro = new JaroInfo<S>(empty).calculate(sequenceFrom, sequenceTo);
		double distance = jaro.getDistance().doubleValue() +
				(getAlfa(sequenceFrom, sequenceTo) * p *
						(1 - jaro.getDistance().doubleValue())
						);
		return new ResultSet<>(
					sequenceFrom,
					sequenceTo,
					jaro.getFinalSequenceFrom(),
					jaro.getFinalSequenceTo(), 
					"Jaro-Winkler distance", //TODO dodat vzorec
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
