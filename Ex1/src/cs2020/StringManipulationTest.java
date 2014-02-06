package cs2020;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Contains a variety of tests for the StringManipulation problem.
 * @author gilbert
 *
 */
public class StringManipulationTest {

	@Test
	public void testBasic() {
		assertEquals("Basic test", StringManipulation.InstructionSearch("abcd3jfinneroutew", "abcdefg", "hijklmnop"), "efghij");
		assertEquals("Basic test", StringManipulation.InstructionSearch("abcd3jfinneouterw", "abcdefg", "hijklmnop"), "abcnop");
		assertEquals("Basic test", StringManipulation.InstructionSearch("abjoincd3jfinnroutew", "abcdefg", "hijklmnop"), "abcdefghijklmnop");
	}
	
	@Test
	public void testMixedCase() {
		assertEquals("Basic case test", StringManipulation.InstructionSearch("abcd3jfinNeRoutew", "abcdefg", "hijklmnop"), "efghij");
		assertEquals("Basic case test", StringManipulation.InstructionSearch("abcd3jfinneOuteRw", "abcdefg", "hijklmnop"), "abcnop");
		assertEquals("Basic case test", StringManipulation.InstructionSearch("abJoiNcd3jfinnroutew", "abcdefg", "hijklmnop"), "abcdefghijklmnop");
		assertEquals("Basic case test", StringManipulation.InstructionSearch("abJOINcd3jfin5nrouterw", "abcdefg", "hijklmnop"), "ERROR");
	}
	
	@Test
	public void testCornerCases(){
		assertEquals("Zerolength", StringManipulation.InstructionSearch("Trs0yjLkinneruyojf", "aaaaaabbccddd", "eeffffhhhhh"), "");
		assertEquals("Zerolength", StringManipulation.InstructionSearch("Trs0yjLkinnjoinuyojf", "", "eeffffhhhhh"), "eeffffhhhhh");
		assertEquals("Zerolength", StringManipulation.InstructionSearch("Trs0yjLkinnjoinuyojf", "eeffffhhhhh", ""), "eeffffhhhhh");
		assertEquals("Zerolength", StringManipulation.InstructionSearch("Trs0yjLkinnjoinuyojf", "", ""), "");
		assertEquals("Zerolength", StringManipulation.InstructionSearch("Trs7yjLkinnerjinuyojf", "abc", "defghi"), "abcdefghi");
	}
	
	@Test
	public void testError() {
		assertEquals("Error (two commands)", StringManipulation.InstructionSearch("abcd3jfinnerouterw", "abcdefg", "hijklmnop"), "ERROR");
		assertEquals("Error (two commands)", StringManipulation.InstructionSearch("abcd3jfjoinneroutw", "abcdefg", "hijklmnop"), "ERROR");
		assertEquals("Error (two same commands)", StringManipulation.InstructionSearch("abcd3jfinnerbobinnerw", "abcdefg", "hijklmnop"), "ERROR");
		assertEquals("Error (no commands)", StringManipulation.InstructionSearch("abcd3jfinneoutew", "abcdefg", "hijklmnop"), "ERROR");
		assertEquals("Error (no number)", StringManipulation.InstructionSearch("abjoncdjfinnrouterw", "abcdefg", "hijklmnop"), "NO_NUMBER");
		assertEquals("Error (no number 2)", StringManipulation.InstructionSearch("abjoncdjfinneruterw", "abcdefg", "hijklmnop"), "NO_NUMBER");
		assertEquals("Error (two numbers)", StringManipulation.InstructionSearch("abjonc4djfinn0erouterw", "abcdefg", "hijklmnop"), "NO_NUMBER");
		assertEquals("Error (two numbers)", StringManipulation.InstructionSearch("abjonc10djfinerouterw", "abcdefg", "hijklmnop"), "NO_NUMBER");

	}

}
