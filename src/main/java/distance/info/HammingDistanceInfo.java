package distance.info;

import java.util.List;

import exception.InvalidOpeationCostException;
import exception.SequencesMustHaveSameLengthException;
import structures.DistanceResult;

public class HammingDistanceInfo<S> implements StructureStringDistance<S> {

	private final int costOfSubstitution;
	
	public HammingDistanceInfo() {
		costOfSubstitution = 1;
	}
	
	public HammingDistanceInfo(final int costOfSubstitution) {
		if(costOfSubstitution < 1)
			throw new InvalidOpeationCostException(costOfSubstitution, "positive");
		this.costOfSubstitution = costOfSubstitution;
	}
	
	
	@Override
	public DistanceResult<S, String> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		if(sequenceFrom.size() != sequenceTo.size())
			throw new SequencesMustHaveSameLengthException();
		String operations = "";
		int distance = 0;
		for(int i = 0; i < sequenceFrom.size(); i++){
			if(sequenceFrom.get(i).equals(sequenceTo.get(i))) {
				operations += "E";
			}else {
				operations += "S";
				distance += costOfSubstitution;
			}
		}
		return new DistanceResult<S, String>(
					sequenceFrom,
					sequenceTo,
					"Hamming distance",
					operations,
					distance,
					operations
				);
	}

}
