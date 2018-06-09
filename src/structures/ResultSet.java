package structures;

import java.io.Serializable;
import java.util.List;

/**
 * Result of MetricInfo calculation
 * Box for data
 * @author Ondøej Nìmec
 *
 * @param <S>
 */
public class ResultSet<S, T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final List<S> sequenceFrom;
	private final List<S> sequenceTo;
	private final List<S> finalSequenceFrom;
	private final List<S> finalSequenceTo;
	private final String description;
	private final String operations;
	private final Number distance;
	private final Number weightDistance;
	private final T structure;
	
	public ResultSet(
			final List<S> sequenceFrom,
			final List<S> sequenceTo,
			final List<S> finalSequenceFrom,
			final List<S> finalSequenceTo,
			final String description,
			final String operations,
			final Number distance,
			final Number weightDistance,
			final T structure
			) {
		this.sequenceFrom = sequenceFrom;
		this.sequenceTo = sequenceTo;
		this.finalSequenceFrom = finalSequenceFrom;
		this.finalSequenceTo = finalSequenceTo;
		this.description = description;
		this.operations = operations;
		this.distance = distance;
		this.weightDistance = weightDistance;
		this.structure = structure;
	}
	
	
	public List<S> getSequenceFrom() {
		return sequenceFrom;
	}
	public List<S> getSequenceTo() {
		return sequenceTo;
	}
	public List<S> getFinalSequenceFrom() {
		return finalSequenceFrom;
	}
	public List<S> getFinalSequenceTo() {
		return finalSequenceTo;
	}
	public String getDescription() {
		return description;
	}
	public String getOperations() {
		return operations;
	}
	public Number getDistance() {
		return distance;
	}
	public Number getWeightDistance() {
		return weightDistance;
	}
	public T getStructure(){
		return structure;
	}
	
	
	@Override
	public String toString() {
		String result = sequenceFrom + "\t" + sequenceTo + "\n";
		result += finalSequenceFrom + "\t" + finalSequenceTo + "\n";
		result += distance + "\t" + weightDistance + "\n";
		result += description + "\n";
		result += operations + "\n";
		result += structure;		
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof ResultSet))
			return false;
		@SuppressWarnings("unchecked")
		ResultSet<S, T> aux = (ResultSet<S, T>)o;
		if(!sequenceFrom.equals(aux.getSequenceFrom()))
			return false;
		if(!sequenceTo.equals(aux.getSequenceTo()))
			return false;
		if(!finalSequenceFrom.equals(aux.getFinalSequenceFrom()))
			return false;
		if(!finalSequenceTo.equals(aux.getFinalSequenceTo()))
			return false;
		if(!description.equals(aux.getDescription()))
			return false;
		if(!operations.equals(aux.getOperations()))
			return false;
		if(!distance.equals(aux.getDistance()))
			return false;
		if(!weightDistance.equals(aux.getWeightDistance()))
			return false;
		if(!structure.equals(aux.getStructure()))
			return false;
		return super.equals(o);
	}

}
