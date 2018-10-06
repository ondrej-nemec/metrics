package support;

import java.util.List;

public abstract class AbstractMetricInfoTest {
	
	public Object[] makeParams(Object[] second) {
		Object[] res = new Object[sequences().size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = new Object[]{
					sequences().get(i)[0],
					sequences().get(i)[1],
					second[i]
			};
		}		
		return res;
	}
	
	public abstract List<Object[]> sequences();
	
	public abstract Object[] weightDistances();
	
	public abstract Object[] resultSets();
}
