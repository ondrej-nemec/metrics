package metricquick;

import java.util.List;

public class JaroWinklerQuick<S> implements MetricQuick<S> {

	private double p;
	
	public JaroWinklerQuick(final double p) {
		this.p = p;
	}
	
	public JaroWinklerQuick() {
		this.p = 0.1;
	}
	
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
		int border = Math.min(4, Math.min(from.size(), to.size()));
		int i = 0;
		while(i<border && from.get(i).equals(to.get(i))){
			result++;
			i++;
		}
		return result;
	}
	

}
