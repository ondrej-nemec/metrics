package support;

import java.util.List;

public abstract class AbstractMetricInfoTest {
	
	public Object[] parameters(int index) {
		Object[] res = new Object[resultSet().size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = new Object[] {
					resultSet().get(i)[0],
					resultSet().get(i)[1],
					resultSet().get(i)[index]
			};
		}
		
		return res;
	}
	
	public abstract List<Object[]> resultSet();
	
}
