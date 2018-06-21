package operations;

import java.util.ArrayList;
import java.util.List;

import support.Tuple2;
import support.Tuple3;

public class Entropy<S> {
	
	private final double entropyFrom;
	private final double entropyTo;
	private List<Tuple2<S, Integer>> from;
	private List<Tuple2<S, Integer>> to;
	private List<Tuple3<S, S, Integer>> twins;
	
	
	public Entropy(List<List<S>> first, List<List<S>> second) {
		if(first.size() != second.size())
			throw new RuntimeException(); //TODO vlastni vyjimka
		this.twins = calculateTwins(first, second);
		this.from = calculateFonems(first);
		this.to = calculateFonems(second);
		this.entropyFrom = calculateEntropy(
				twins,
				from,
				true
			);
		this.entropyTo = calculateEntropy(
				twins,
				to,
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
		return from;
	}

	public List<Tuple2<S, Integer>> getFonemsToWithCount() {
		return to;
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

	private List<Tuple2<S, Integer>> calculateFonems(List<List<S>> sequence) {
		List<Tuple2<S, Integer>> result = new ArrayList<>();
		for(int i = 0; i < sequence.size(); i++){
			for(int j = 0; j < sequence.get(i).size(); j++){
				partOf(sequence.get(i).get(j), result);
					
			}
		}
		return result;
	}

	private List<Tuple3<S, S, Integer>> calculateTwins(List<List<S>> first, List<List<S>> second) {
		List<Tuple3<S, S, Integer>> result = new ArrayList<>();
		for(int i = 0; i < first.size(); i++){
			for(int j = 0; j < first.get(i).size(); j++){
				partOf(result, first.get(i).get(j), second.get(i).get(j));
			}
		}
		return result;
	}

	private void partOf(List<Tuple3<S, S, Integer>> aux, S a, S b) {
		if(aux.size() < 1){
			aux.add(new Tuple3<>(a, b, 1));
			return;
		}
		int index = getIndex(aux, a, b);
		for(int i = 0; i < aux.size(); i++){
			if(i == index){
				aux.get(i).setThird(aux.get(i).getThird() + 1);
				return;
			}
			if(i == -1)
				aux.add(new Tuple3<>(a, b, 1));
		}
	}
			private int getIndex(List<Tuple3<S, S, Integer>> aux, S a, S b) {
		for(int i = 0; i < aux.size(); i++){
			if(aux.get(i).getFirst().equals(a) && aux.get(i).getSecond().equals(b))
				return i;
		}
		return -1;
	}


	private void partOf(S s, List<Tuple2<S, Integer>> aux) {
		if(aux.size() < 1){
			aux.add(new Tuple2<>(s, 1));
			return;
		}
		int index = getIndex(aux, s);
		for(int i = 0; i < aux.size(); i++){
			if(i == index){
				aux.get(i).setSecond(aux.get(i).getSecond() + 1);
				return;
			}
			if(i == -1)
				aux.add(new Tuple2<>(s, 1));
		}
	}

	private int getIndex(List<Tuple2<S, Integer>> aux, S s) {
		for(int i = 0; i < aux.size(); i++){
			if(aux.get(i).getFirst().equals(s))
				return i;
		}
		return -1;
	}

	
}
