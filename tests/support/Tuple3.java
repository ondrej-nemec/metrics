package support;

public class Tuple3<S, R> {
	
	private final Iterable<S> first;
	
	private final Iterable<S> second;
	
	private final R result;
	
	public Tuple3(final Iterable<S> first, Iterable<S> second, R result) {
		this.first = first;
		this.second = second;
		this.result = result;
	}

	public Iterable<S> getFirst() {
		return first;
	}

	public Iterable<S> getSecond() {
		return second;
	}

	public R getResult() {
		return result;
	}

}
