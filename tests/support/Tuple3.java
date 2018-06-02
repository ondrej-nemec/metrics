package support;

import java.util.List;

public class Tuple3<S, R> {
	
	private final List<S> first;
	
	private final List<S> second;
	
	private final R result;
	
	public Tuple3(final List<S> first, List<S> second, R result) {
		this.first = first;
		this.second = second;
		this.result = result;
	}

	public List<S> getFirst() {
		return first;
	}

	public List<S> getSecond() {
		return second;
	}

	public R getResult() {
		return result;
	}

}
