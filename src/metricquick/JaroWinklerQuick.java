package metricquick;

import java.util.List;

public class JaroWinklerQuick<S> implements MetricQuick<S> {

	private double p;
	
	@Override
	public Number calculate(List<S> sequenceFrom, List<S> sequenceTo) {
		double jaro = new JaroQuick<S>().calculate(sequenceFrom, sequenceTo).doubleValue();
		return jaro +
				(getAlfa(sequenceFrom, sequenceTo) * p *
						(1 - jaro)
						);
	}
	
	private int getAlfa(List<S> from, List<S> to){
		int result = 0;
		for(int i = 0; i < Math.min(4, Math.min(from.size(), to.size())); i++){
			if(from.get(i).equals(to.get(i)))
				result++;
		}
		return result;
	}
	

}
