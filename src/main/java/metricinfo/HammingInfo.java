package metricinfo;

import java.util.List;

import exception.InvalidOpeationCostException;
import exception.SequencesMustHaveSameLengthException;
import structures.ResultSet;

public class HammingInfo<S> implements StructureString<S> {

	private final int costOfSubstitution;
	
	public HammingInfo() {
		costOfSubstitution = 1;
	}
	
	public HammingInfo(final int costOfSubstitution) {
		if(costOfSubstitution < 1)
			throw new InvalidOpeationCostException(costOfSubstitution, "positive");
		this.costOfSubstitution = costOfSubstitution;
	}
	
	
	@Override
	public ResultSet<S, String> calculate(List<S> sequenceFrom, List<S> sequenceTo) {
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
		return new ResultSet<S, String>(
					sequenceFrom,
					sequenceTo,
					"Hamming distance",
					operations,
					distance,
					operations
				);
	}

}
