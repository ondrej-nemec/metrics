package distance.quick;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import distance.quick.LevenshteinDistanceQuick;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;


@RunWith(JUnitParamsRunner.class)
public class LevenshteinQuickTest {

	@Test
	@Parameters
	public void testCalculateWorks(List<Character> from, List<Character> to, Number distance) {
		LevenshteinDistanceQuick<Character> dis = new LevenshteinDistanceQuick<>();
		assertEquals(
				distance,
				dis.calculate(from, to)
			);		
	}

	public Collection<Object[]> parametersForTestCalculateWorks() {
		return Arrays.asList(
				//equals
				new Object[]{
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					0,
				},
				//different
				new Object[]{
					Arrays.asList('a', 'a', 'a'),
					Arrays.asList('b', 'b', 'b'),
					3,
				},
				//substitution
				new Object[]{
					Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
					Arrays.asList('s', 't', 'r', 'y', 'n', 'g'),
					1,
				},
				//insertion and deletion
				new Object[]{
						Arrays.asList('s', 't', 'r', 'n', 'g', 'a'),
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						2,
					},		
				//transposition
				new Object[]{
						Arrays.asList('s', 't', 'r', 'i', 'n', 'g'),
						Arrays.asList('t', 's', 'r', 'i', 'n', 'g'),
						2,
					},				
				//so long sequence
				new Object[]{
					Arrays.asList('n', 'e', 'j', 'n', 'e', 'z', 'p', 'r', 'a', 'v', 'd',
								  'ì', 'p', 'o', 'd', 'o', 'b', 'ò', 'o', 'v', 'á', 'v',
								  'a', 't', 'e', 'l', 'n', 'ì', 'j', '', 'í', 'h', 'o'),
					Arrays.asList('n', 'e', 'n', 'e', 's', 'p', 'r', 'a', 'v', 'd', 'j',
								  'e', 'p', 'o', 'd', 'o', 'b', 'n', 'o', 'v', 'a', 'v',
								  'á', 't', 'e', 'l', 'n', 'ì', 'j', '', 'í', 'h', 'o'),
					7,
				},
				//koolipan vs kopllisa
				new Object[]{
					Arrays.asList('k', 'o', 'o', 'l', 'i', 'p', 'a', 'n'),
					Arrays.asList('k', 'o', 'p', 'l', 'l', 'i', 's', 'a'),
					4,
				},				
				//kolinn vs kpollim
				new Object[]{
						Arrays.asList('k', 'o', 'p', 'l', 'i', 'n', 'n'),
						Arrays.asList('k', 'p', 'o', 'l', 'l', 'i', 'm'),
						4,
				},
				/************/
				//second empty
				new Object[]{
					new LinkedList<>(),
					Arrays.asList('s', 'l', 'o', 'v', 'o'),
					5,
				},				
				//first empty				
				new Object[]{
					Arrays.asList('s', 'l', 'o', 'v', 'o'),
					new LinkedList<>(),
					5,
				},
				//special case	
				new Object[]{
					Arrays.asList('k', 'o', 'l', 'o'),
					Arrays.asList('o', 'k', 'o'),
					2,
				}					
		);
	}

}
