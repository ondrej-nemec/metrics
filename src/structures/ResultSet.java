package structures;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/**
 * Result of MetricInfo calculation
 * Box for data
 * @author Ondøej Nìmec
 *
 * @param <S, T>
 */
public class ResultSet<S, T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//TODO upravit getOpDess..... bude asi dostavat translator
		//mozna by mohl byt schopny final vytvorit na zaklade operations...
		//funkci pro vyznaceny operaci - dostane funkci a upravy retezce - treti dvojice retezcu, cacheovane
		//prejmenovat operations, description opeDes...
		
	private final List<S> finalSequenceFrom;
	private final List<S> finalSequenceTo;
	private final Number distance;
	private final T structure;
	private final String description;
	private final String operations;
	
	public ResultSet(
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
		if(!(o instanceof ResultSet))
			return false;
		@SuppressWarnings("unchecked")
		ResultSet<S, T> aux = (ResultSet<S, T>)o;
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
		if(!structure.equals(aux.getStructure()))
			return false;
		//return super.equals(o);
		return true;
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
