package structures;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import structures.DistanceResultSet;

@RunWith(JUnitParamsRunner.class)
public class ResultSetTest {
	
	@Test
	@Parameters
	public void testGetOperationsDescriptionWorks(
			DistanceResultSet<Character, String> resultSet,
			String operationsDescription,
			List<Character> showFrom,
			List<Character> showTo){
		assertEquals(operationsDescription, resultSet.getOperationsDescription());
	}
	
	public Object[] parametersForTestGetOperationsDescriptionWorks() {
		return dataProvider();
	}
	
	@Test
	@Parameters
	public void testShowOperationsWorks(
			DistanceResultSet<Character, String> resultSet,
			String operationsDescription,
			List<Character> showFrom,
			List<Character> showTo) {
		assertEquals(
				showFrom,
				resultSet.showOperations(
						(a)->a,
						(a)->a,
						(a)->'-',
						(a)->(a+"").toUpperCase().charAt(0),
						(a)->(a+"").toUpperCase().charAt(0),
						true
					)
				);
		assertEquals(
				showTo,
				resultSet.showOperations(
						(a)->a,
						(a)->'-',
						(a)->a,
						(a)->(a+"").toUpperCase().charAt(0),
						(a)->(a+"").toUpperCase().charAt(0),
						false
					)
				);
	}
	
	public Object[] parametersForTestShowOperationsWorks() {
		return dataProvider();
	}
	
	@Test
	@Parameters
	public void testEqualsWorks(Object comparedObject, boolean expectedResult) {
		DistanceResultSet<Character, String> actualObject = new DistanceResultSet<Character, String>(
				Arrays.asList('a', 'b', 'c', 'd', 'e'),
				Arrays.asList('f', 'g', 'h', 'i', 'j'),
				"This is description",
				"ESIDT",
				987,
				"structure"
			);
		assertSame(expectedResult, actualObject.equals(comparedObject));	
	}
	
	public Object[] parametersForTestEqualsWorks() {
		return new Object[] {
				new Object[]{
					new String("Alrealy not ResultSet"), false	
				},
				new Object[]{
						new DistanceResultSet<Integer, Logger>(
								Arrays.asList(1, 123, 987),
								Arrays.asList(654, 456, 0),
								"Description",
								"SETID",
								0,
								Logger.getAnonymousLogger()
							),
						false
				},
				new Object[]{
						new DistanceResultSet<Character, String>(
								Arrays.asList('a', 'b', 'c', 'd'),
								Arrays.asList('f', 'g', 'h', 'i', 'j'),
								"This is description",
								"ESIDT",
								987,
								"structure"
						),
						false
				},
				new Object[]{
						new DistanceResultSet<Character, String>(
								Arrays.asList('a', 'b', 'c', 'd', 'e'),
								Arrays.asList('f', 'g', 'h', 'i'),
								"This is description",
								"ESIDT",
								987,
								"structure"
						),
						false
				},				
				new Object[]{
						new DistanceResultSet<Character, String>(
								Arrays.asList('a', 'b', 'c', 'd', 'e'),
								Arrays.asList('f', 'g', 'h', 'i', 'j'),
								"Another description",
								"ESIDT",
								987,
								"structure"
						),
						false	
				},
				new Object[]{
						new DistanceResultSet<Character, String>(
								Arrays.asList('a', 'b', 'c', 'd', 'e'),
								Arrays.asList('f', 'g', 'h', 'i', 'j'),
								"This is description",
								"TDISE",
								987,
								"structure"
						),
						false	
				},
				new Object[]{
						new DistanceResultSet<Character, String>(
								Arrays.asList('a', 'b', 'c', 'd', 'e'),
								Arrays.asList('f', 'g', 'h', 'i', 'j'),
								"This is description",
								"ESIDT",
								987.1,
								"structure"
						),
						false	
				},
				new Object[]{
						new DistanceResultSet<Character, String>(
								Arrays.asList('a', 'b', 'c', 'd', 'e'),
								Arrays.asList('f', 'g', 'h', 'i', 'j'),
								"This is description",
								"ESIDT",
								987.000001,
								"structure"
						),
						true	
				},
				new Object[]{
						new DistanceResultSet<Character, String>(
								Arrays.asList('a', 'b', 'c', 'd', 'e'),
								Arrays.asList('f', 'g', 'h', 'i', 'j'),
								"This is description",
								"ESIDT",
								987,
								"some string"
						),
						false	
				},
				new Object[]{
					new DistanceResultSet<Character, String>(
							Arrays.asList('a', 'b', 'c', 'd', 'e'),
							Arrays.asList('f', 'g', 'h', 'i', 'j'),
							"This is description",
							"ESIDT",
							987,
							"structure"
					),
					true
				}
		};
	}
	
	public Object[] dataProvider() {
		return new Object[]{
				new Object[]{
						getResultSet(
								new ArrayList<>(),
								new ArrayList<>(),
								""),
						"",
						new ArrayList<>(),
						new ArrayList<>(),
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
						+ "Substitution 'o' to 't' at position 4\n",
						Arrays.asList('S', 'L', 'O', 'V', 'O'),
						Arrays.asList('A', 'F', 'C', 'E', 'T')
				},
				new Object[]{
						getResultSet(
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
								Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
								"EEEEEE"),
						"",
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
				},
				new Object[]{
						getResultSet(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList(' ', ' ', ' ', ' ', ' '),
								"DDDDD"),
						"Deletion 's' at position 0\n"
						+ "Deletion 'l' at position 1\n"
						+ "Deletion 'o' at position 2\n"
						+ "Deletion 'v' at position 3\n"
						+ "Deletion 'o' at position 4\n",
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						Arrays.asList('-', '-', '-', '-', '-'),
				},new Object[]{
						getResultSet(
								Arrays.asList(' ', ' ', ' ', ' ', ' '),
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								"IIIII"),
						"Insertion 's' at position 0\n"
						+ "Insertion 'l' at position 1\n"
						+ "Insertion 'o' at position 2\n"
						+ "Insertion 'v' at position 3\n"
						+ "Insertion 'o' at position 4\n",
						Arrays.asList('-', '-', '-', '-', '-'),
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
				},
				new Object[]{
						getResultSet(
								Arrays.asList('k', 'o', 'l', 'o'),
								Arrays.asList('o', 'k', ' ', 'o'),
								"TTDE"),
						"Transposition 'k' and 'o' at position 0 and 1\n"
						+ "Deletion 'l' at position 2\n",
						Arrays.asList('K', 'O', 'l', 'o'),
						Arrays.asList('O', 'K', '-', 'o'),
				},
				new Object[]{
						getResultSet(
								Arrays.asList('k', ' ', 'o', 'k', 'l', 'i', 'n', 'n'),
								Arrays.asList('k', 'p', 'o', ' ', 'i', 'l', 'i', 'm'),
								"EIEDTTSS"),
						"Insertion 'p' at position 1\n"
						+ "Deletion 'k' at position 3\n"
						+ "Transposition 'l' and 'i' at position 4 and 5\n"
						+ "Substitution 'n' to 'i' at position 6\n"
						+ "Substitution 'n' to 'm' at position 7\n",
						Arrays.asList('k', '-', 'o', 'k', 'L', 'I', 'N', 'N'),
						Arrays.asList('k', 'p', 'o', '-', 'I', 'L', 'I', 'M'),
				},
				new Object[]{
						getResultSet(
								Arrays.asList('s', 'l', 'o', 'v', 'o'),
								Arrays.asList('s', 'l', ' ', 'v', ' '),
								"EEDED"),
						"Deletion 'o' at position 2\n"
						+ "Deletion 'o' at position 4\n",
						Arrays.asList('s', 'l', 'o', 'v', 'o'),
						Arrays.asList('s', 'l', '-', 'v', '-'),
				}
		};
	}	
	
	private DistanceResultSet<Character, String> getResultSet(
			final List<Character> from, final List<Character> to, final String operations){
		return new DistanceResultSet<>(
				from,
				to,
				null,
				operations,
				null,
				null
			);
	}
	
}
