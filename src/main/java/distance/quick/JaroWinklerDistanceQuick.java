package distance.quick;

import java.util.List;

public class JaroWinklerDistanceQuick<S> implements DistanceQuick<S> {

	private double p;
	
	public JaroWinklerDistanceQuick(final double p) {
		this.p = p;
	}
	
	public JaroWinklerDistanceQuick() {
		this.p = 0.1;
	}
	
	@Override
	public Number calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		double jaro = new JaroDistanceQuick<S>().calculate(sequenceFrom, sequenceTo).doubleValue();
		return jaro +
				(getAlfa
					(sequenceFrom, sequenceTo)
					* p *
					(1 - jaro)
				);
	}
	
	private int getAlfa(List<S> from, List<S> to){
		int result = 0;
		int border = Math.min(4, Math.min(from.size(), to.size()));
		int i = 0;
		while(i<border && from.get(i).equals(to.get(i))){
			result++;
			i++;
		}
		return result;
	}
	

}
