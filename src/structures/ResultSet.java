package structures;

/**
 * Result of MetricInfo calculation
 * Box for data
 * @author Ondøej Nìmec
 *
 * @param <S>
 */
public class ResultSet<S> {
	
	private final Iterable<S> sequenceFrom;
	private final Iterable<S> sequenceTo;
	private final Iterable<S> finalSequenceFrom;
	private final Iterable<S> finalSequenceTo;
	private final String description;
	private final String operations;
	private final Number distance;
	private final Number weightDistance;
	
	public ResultSet(
			final Iterable<S> sequenceFrom,
			final Iterable<S> sequenceTo,
			final Iterable<S> finalSequenceFrom,
			final Iterable<S> finalSequenceTo,
			final String description,
			final String operations,
			final Number distance,
			final Number weightDistance
			) {
		this.sequenceFrom = sequenceFrom;
		this.sequenceTo = sequenceTo;
		this.finalSequenceFrom = finalSequenceFrom;
		this.finalSequenceTo = finalSequenceTo;
		this.description = description;
		this.operations = operations;
		this.distance = distance;
		this.weightDistance = weightDistance;
	}
	
	
	public Iterable<S> getSequenceFrom() {
		return sequenceFrom;
	}
	public Iterable<S> getSequenceTo() {
		return sequenceTo;
	}
	public Iterable<S> getFinalSequenceFrom() {
		return finalSequenceFrom;
	}
	public Iterable<S> getFinalSequenceTo() {
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
	
	
	
	
	

}
