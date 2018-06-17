package structures;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import structures.ResultSet;

@RunWith(Parameterized.class)
public class ResultSetTest {
	
	private ResultSet<Character, String> resultSet;
	private String operationsDescription;
	
	public ResultSetTest(ResultSet<Character, String> resultSet,String operationsDescription){
		this.resultSet = resultSet;
		this.operationsDescription = operationsDescription;
	}
	
	@Test
	public void testGetOperationsDescriptionWorks(){
		assertEquals(resultSet.getOperationsDescription(), operationsDescription);
	}
	
	@Parameters
	public static Collection<Object[]> dataProvider() {
		return Arrays.asList(
				new Object[]{
						getResultSet(
								new ArrayList<>(),
								new ArrayList<>(),
								""),
						""
				},
				new Object[]{
						getResultSet(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList('a', 'f', 'c', 'e', 't'),
								"SSSSS"),
						""
				},
				new Object[]{
						getResultSet(
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
								"EEEEE"),
						""
				},
				new Object[]{
						getResultSet(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								new LinkedList<>(),
								"DDDDD"),
						""
				},new Object[]{
						getResultSet(
								new LinkedList<>(),
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								"IIIII"),
						""
				},
				new Object[]{
						getResultSet(
								Arrays.asList('k', 'o', 'l', 'o'),
								Arrays.asList('o', 'k', 'o'),
								"TTDE"),
						""
				},
				new Object[]{
						getResultSet(
								Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
								Arrays.asList('k', 'p', 'o', 'i', 'l', 'i', 'm'),
								"ETTTTSS"),
						""
				},
				new Object[]{
						getResultSet(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList('s', 'l', 'v'),
								"EEDED"),
						""
				}
			);
	}
	
	
	private static ResultSet<Character, String> getResultSet(
			final List<Character> from, final List<Character> to, final String operations){
		return new ResultSet<>(
				from,
				to,
				null,
				operations,
				null,
				null
			);
	}

}
