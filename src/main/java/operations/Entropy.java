package operations;

import java.util.ArrayList;
import java.util.List;

import exception.SequencesMustHaveSameLengthException;
import support.Tuple2;
import support.Tuple3;

public class Entropy<S> {
	
	private final double entropyFrom;
	private final double entropyTo;
	private List<Tuple2<S, Integer>> tupleFrom;
	private List<Tuple2<S, Integer>> tupleTo;
	private List<Tuple3<S, S, Integer>> twins;
	
	
	public Entropy(List<List<S>> first, List<List<S>> second) {
		if(first.size() != second.size())
			throw new SequencesMustHaveSameLengthException();
		
		this.tupleFrom = new ArrayList<>();
		this.tupleTo = new ArrayList<>();
		this.twins = new ArrayList<>();
		createData(first, second);
		
		this.entropyFrom = calculateEntropy(
				twins,
				tupleFrom,
				true
			);
		this.entropyTo = calculateEntropy(
				twins,
				tupleTo,
				false
			);
	}
	
	public Double getEntropyFrom() {
		return entropyFrom;
	}

	public Double getEntropyTo() {
		return entropyTo;
	}

	public List<Tuple2<S, Integer>> getFonemsFromWithCount() {
		return tupleFrom;
	}

	public List<Tuple2<S, Integer>> getFonemsToWithCount() {
		return tupleTo;
	}

	public List<Tuple3<S, S, Integer>> getFonemsTwinsWithCounts() {
		return twins;
	}

	private double calculateEntropy(
			List<Tuple3<S, S, Integer>> fonemTwinsAndCount,
			List<Tuple2<S, Integer>> fonemsAndCount,
			boolean first){
		double entropy = 0.0;
		for(int i = 0; i < fonemTwinsAndCount.size(); i++){
			entropy += 
					-
					(fonemTwinsAndCount.get(i).getThird().doubleValue()
							/(double)(fonemTwinsAndCount.size()))
					* Math.log(
							(fonemTwinsAndCount.get(i).getThird().doubleValue())
							/ fonemsAndCount.get(
									getIndex(
											fonemsAndCount,
									       ((first)?
									    		   fonemTwinsAndCount.get(i).getFirst()
									    		   :fonemTwinsAndCount.get(i).getSecond()
									    		 ))
									)
									.getSecond().doubleValue()
							)
					/Math.log(2.0);
		}
		return entropy;
	}

	/*************************************/
	
	private void createData(List<List<S>> from, List<List<S>> to){
		for(int i = 0; i < from.size(); i++){
			if(from.get(i).size() != to.get(i).size())
				throw new SequencesMustHaveSameLengthException();
			for(int j = 0; j < from.get(i).size(); j++){
				S f = from.get(i).get(j);
				S t = to.get(i).get(j);
				int indexFrom = getIndex(tupleFrom, f);
				int indexTo = getIndex(tupleTo, t);
				int indexTwins = getIndex(twins, f, t);
				//from
				if(indexFrom == -1){
					tupleFrom.add(new Tuple2<>(f, 1));
				}else{
					tupleFrom.get(indexFrom).setSecond(
							tupleFrom.get(indexFrom).getSecond()+1
						);
				}
				//to
				if(indexTo == -1){
					tupleTo.add(new Tuple2<>(t, 1));
				}else{
					tupleTo.get(indexTo).setSecond(
							tupleTo.get(indexTo).getSecond()+1
						);
				}
				//twins
				if(indexTwins == -1){
					twins.add(new Tuple3<>(f, t, 1));
				}else{
					twins.get(indexTwins).setThird(
							twins.get(indexTwins).getThird()+1
						);
				}
			}
		}
	}
	
	private int getIndex(List<Tuple3<S, S, Integer>> aux, S a, S b) {
		for(int i = 0; i < aux.size(); i++){
			if(aux.get(i).getFirst().equals(a) && aux.get(i).getSecond().equals(b))
				return i;
		}
		return -1;
	}
	private int getIndex(List<Tuple2<S, Integer>> aux, S s) {
		for(int i = 0; i < aux.size(); i++){
			if(aux.get(i).getFirst().equals(s))
				return i;
		}
		return -1;
	}	
}
