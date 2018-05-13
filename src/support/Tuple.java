package support;

public class Tuple<S, T> {
	
	private final S first;
	
	private final T second;
	
	public Tuple(final S first, final T second) {
		this.first = first;
		this.second = second;
	}

	public S getFirst() {
		return first;
	}

	public T getSecond() {
		return second;
	}
	
	

}
