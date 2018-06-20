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
						"Substitution 's' to 'a' at position 0\n"
						+ "Substitution 'l' to 'f' at position 1\n"
						+ "Substitution 'o' to 'c' at position 2\n"
						+ "Substitution 'v' to 'e' at position 3\n"
						+ "Substitution 'o' to 't' at position 4\n"
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
						"Deletion 's' at position 0\n"
						+ "Deletion 'l' at position 1\n"
						+ "Deletion 'o' at position 2\n"
						+ "Deletion 'v' at position 3\n"
						+ "Deletion 'o' at position 4\n"
				},new Object[]{
						getResultSet(
								new LinkedList<>(),
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								"IIIII"),
						"Insertion 's' at position 0\n"
						+ "Insertion 'l' at position 1\n"
						+ "Insertion 'o' at position 2\n"
						+ "Insertion 'v' at position 3\n"
						+ "Insertion 'o' at position 4\n"
				},
				new Object[]{
						getResultSet(
								Arrays.asList('k', 'o', 'l', 'o'),
								Arrays.asList('o', 'k', 'o'),
								"TTDE"),
						"Transposition 'k' and 'o' at position 0 and 1\n"
						+ "Deletion 'l' at position 2\n"
				},
				new Object[]{
						getResultSet(
								Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
								Arrays.asList('k', 'p', 'o', 'i', 'l', 'i', 'm'),
								"ETTTTSS"),
						"Transposition 'o' and 'p' at position 1 and 2\n"
						+ "Transposition 'l' and 'i' at position 3 and 4\n"
						+ "Substitution 'n' to 'i' at position 5\n"
						+ "Substitution 'n' to 'm' at position 6\n"
				},
				new Object[]{
						getResultSet(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList('s', 'l', 'v'),
								"EEDED"),
						"Deletion 'o' at position 2\n"
						+ "Deletion 'o' at position 4\n"
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
