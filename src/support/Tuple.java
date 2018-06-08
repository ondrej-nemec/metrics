package support;

import java.io.Serializable;

public class Tuple<S, T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

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
	
	public Tuple<S, T> withFirst(final S value){
		return new Tuple<>(value, second);
	}
	
	public Tuple<S, T> withSecond(final T value){
		return new Tuple<>(first, value);
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
