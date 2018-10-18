package structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import exception.SequencesMustHaveSameLengthException;

/**
 * Result of MetricInfo calculation
 * Box for data
 * @author Ondrej Nemec
 *
 * @param <S, T>
 */
public class DistanceResult<S, T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final List<S> finalSequenceFrom;
	private final List<S> finalSequenceTo;
	private final Number distance;
	private final T structure;
	private final String description;
	private final String operations;
	
	public DistanceResult(
			final List<S> finalSequenceFrom,
			final List<S> finalSequenceTo,
			final String description,
			final String operations,
			final Number distance,
			final T structure
			) {
		this.finalSequenceFrom = finalSequenceFrom;
		this.finalSequenceTo = finalSequenceTo;
		this.description = description;
		this.operations = operations;
		this.distance = distance;
		this.structure = structure;
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
	public T getStructure(){
		return structure;
	}
		
	public List<S> getFinalSequenceFrom(
			Function<S, S> equals,
			Function<S, S> deletion,
			Function<S, S> insertion,
			Function<S, S> substitution,
			Function<S, S> transposition) {
		return showOperations(equals, deletion, insertion, substitution, transposition, true);
	}
	public List<S> getFinalSequenceTo(
			Function<S, S> equals,
			Function<S, S> deletion,
			Function<S, S> insertion,
			Function<S, S> substitution,
			Function<S, S> transposition) {
		return showOperations(equals, deletion, insertion, substitution, transposition, false);
	}

	
	@Override
	public String toString() {
		String result = finalSequenceFrom + "\t" + finalSequenceTo + "\n";
		result += distance + "\n";
		result += description + "\n";
		result += operations + "\n";
		result += structure;		
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof DistanceResult))
			return false;
		@SuppressWarnings("unchecked")
		DistanceResult<S, T> aux = (DistanceResult<S, T>)o;
		if(!finalSequenceFrom.equals(aux.getFinalSequenceFrom()))
			return false;
		if(!finalSequenceTo.equals(aux.getFinalSequenceTo()))
			return false;
		if(!description.equals(aux.getDescription()))
			return false;
		if(!operations.equals(aux.getOperations()))
			return false;
		if(Math.abs(distance.doubleValue() - aux.distance.doubleValue()) > 0.00001)
			return false;
		if(!structure.equals(aux.getStructure()))
			return false;
		return true;
	}
	
	public List<S> showOperations(
			Function<S, S> equals,
			Function<S, S> deletion,
			Function<S, S> insertion,
			Function<S, S> substitution,
			Function<S, S> transposition,
			boolean first
			){
		if(finalSequenceFrom.size() != finalSequenceTo.size()
			&& finalSequenceTo.size() != operations.length())
			throw new SequencesMustHaveSameLengthException();
		List<S> aux = (first) ? finalSequenceFrom : finalSequenceTo;
		List<S> result = new ArrayList<>();
		for(int i = 0; i < aux.size(); i++) {
			switch(operations.charAt(i)) {
			case 'I':
				result.add(
						insertion.apply(aux.get(i))
						);
				break;
			case 'D':
				result.add(
						deletion.apply(aux.get(i))
						);
				break;
			case 'S':
				result.add(
						substitution.apply(aux.get(i))
						);
				break;
			case 'T':
				result.add(
						transposition.apply(aux.get(i))
						);
				break;
			case 'E':
				result.add(
						equals.apply(aux.get(i))
						);
				break;
			default:
				break;
			}
		}
		return result;
	}
	
	
	public String getOperationsDescription(){
		return getOperationsDescription((a)->a);
	}
	
	public String getOperationsDescription(Function<String, String> translator){
		String result = "";
		boolean wasTransposition = false;
		for(int i = 0; i < operations.length(); i++){
			switch (operations.charAt(i)) {
			case 'S':
				result += 
					translator.apply("Substitution") + " '"
					+ finalSequenceFrom.get(i) + "' "
					+ translator.apply("to") + " '"
					+ finalSequenceTo.get(i) + "' "
					+ translator.apply("at position") + " "
					+ i + "\n";					
				break;
			case 'I':
				result += 
					translator.apply("Insertion") + " '"
					+ finalSequenceTo.get(i) + "' "
					+ translator.apply("at position") + " "
					+ i + "\n";
				break;
			case 'D':
				result += 
					translator.apply("Deletion") + " '"
					+ finalSequenceFrom.get(i) + "' "
					+ translator.apply("at position") + " "
					+ i + "\n";
				break;
			case 'T':
				if(!wasTransposition){
					result += 
						translator.apply("Transposition") + " '"
						+ finalSequenceFrom.get(i) + "' "
						+ translator.apply("and") + " '"
						+ finalSequenceFrom.get(i+1) + "' "
						+ translator.apply("at position") + " "
						+ i + " "
						+ translator.apply("and") + " "
						+ (i+1) + "\n";
					wasTransposition = true;
				}else{
					wasTransposition = false;
				}				
				break;
			case 'E':
			default:
				break;
			}
		}
		return result;
	}
}
