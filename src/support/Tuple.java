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
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Tuple<?, ?>))
			return false;
		@SuppressWarnings("unchecked")
		Tuple<S, T> aux = (Tuple<S, T>)o;
		if(!first.equals(aux))
			return false;
		if(!second.equals(aux))
			return false;
		return super.equals(o);
	}

}
