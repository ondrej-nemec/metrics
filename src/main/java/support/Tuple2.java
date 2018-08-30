package support;

import java.io.Serializable;

public class Tuple2<S, T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private S first;
	
	private T second;
	
	public Tuple2(final S first, final T second) {
		this.first = first;
		this.second = second;
	}

	public S getFirst() {
		return first;
	}

	public T getSecond() {
		return second;
	}
	
	public void setFirst(S first) {
		this.first = first;
	}

	public void setSecond(T second) {
		this.second = second;
	}

	public Tuple2<S, T> withFirst(final S value){
		return new Tuple2<>(value, second);
	}
	
	public Tuple2<S, T> withSecond(final T value){
		return new Tuple2<>(first, value);
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Tuple2<?, ?>))
			return false;
		@SuppressWarnings("unchecked")
		Tuple2<S, T> aux = (Tuple2<S, T>)o;
		//TODO HOTFIX
		if(toString().equals(aux.toString()))
			return true;
		
		if(!first.equals(aux))
			return false;
		if(!second.equals(aux))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "{" + first + ", " + second + "}";
	}

}
