package structures;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class LengthResult<S, T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Number length;	
	private final String description;	
	private final T structure;	
	private final Collection<List<S>> subs;
	
	public LengthResult(Number length, String description, T structure, Collection<List<S>> subs) {
		super();
		this.length = length;
		this.description = description;
		this.structure = structure;
		this.subs = subs;
	}

	public Number getLength() {
		return length;
	}

	public String getDescription() {
		return description;
	}

	public T getStructure() {
		return structure;
	}

	public Collection<List<S>> getSubs() {
		return subs;
	}
	
	@Override
	public String toString() {
		return description + " "
				+ length + "\n"
				+ structure.toString() + "\n"
				+ subs;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LengthResult))
			return false;
		@SuppressWarnings("unchecked")
		LengthResult<S, T> aux = (LengthResult<S, T>) o;
		if (!length.equals(aux.length))
			return false;
		if (!description.equals(aux.description))
			return false;
		if (!structure.equals(aux.structure))
			return false;
		if (!subs.equals(aux.subs))
			return false;
		return true;
	}
}
