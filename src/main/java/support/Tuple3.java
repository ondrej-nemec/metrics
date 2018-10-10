package support;

public class Tuple3<F, S, T> {

	private F first;
	private S second;
	private T third;
	
	public Tuple3(final F first, final S second, final T third){
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public F getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}

	public T getThird() {
		return third;
	}

	public void setFirst(F first) {
		this.first = first;
	}

	public void setSecond(S second) {
		this.second = second;
	}

	public void setThird(T third) {
		this.third = third;
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Tuple3<?, ?, ?>))
			return false;
		@SuppressWarnings("unchecked")
		Tuple3<S, S, T> aux = (Tuple3<S, S, T>)o;
		if(!first.equals(aux.first))
			return false;
		if(!second.equals(aux.second))
			return false;
		if(!third.equals(aux.third))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "{" + first + ", " + second + ", " + third + "}";
	}
	
}
