package de.buw.fm4se.alloySig;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import de.buw.fm4se.alloySig.AlloyChecker;
import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Options.SatSolver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class T1AlloyCheckerDeadSignatureTest {
	private static A4Reporter rep;
	private static A4Options opt;

	@BeforeAll
	public static void setUpOptions() {
		rep = A4Reporter.NOP;
		opt = new A4Options();
		opt.solver = SatSolver.SAT4J;
	}

	@Test
	@Order(1)
	void checkDeadSigsForList() {
		String fileName = "src/main/resources/list.als";
		List<String> dead = AlloyChecker.findDeadSignatures(fileName, opt, rep);
		assertEquals(0, dead.size(), "List should have no dead signatures, but has " + dead.size()
				+ " instead. Check the [list.als](src/main/resources/list.als) file.");
	}

	@Test
	@Order(2)
	void checkDeadSigsForDead1() {
		String fileName = "src/main/resources/dead1.als";
		List<String> dead = AlloyChecker.findDeadSignatures(fileName, opt, rep);
		assertEquals(1, dead.size(), "Dead1 should have one dead signature, but has " + dead.size()
				+ " instead. Check the [dead1.als](src/main/resources/dead1.als) file.");
	}

	@Test
	@Order(3)
	void checkDeadSigsForDead2() {
		String fileName = "src/main/resources/dead2.als";
		List<String> dead = AlloyChecker.findDeadSignatures(fileName, opt, rep);
		assertEquals(2, dead.size(), "Dead2 should have two dead signatures, but has " + dead.size()
				+ " instead. Check the [dead2.als](src/main/resources/dead2.als) file.");
	}

	@Test
	@Order(4)
	void checkDeadSigsForDreadburyPuzzle() {
		String fileName = "src/main/resources/dreadbury.als";
		List<String> dead = AlloyChecker.findDeadSignatures(fileName, opt, rep);
		assertEquals(0, dead.size(), "Dreadbury Puzzle should have no dead signatures, but has " + dead.size()
				+ " instead. Check the [dreadbury.als](src/main/resources/dreadbury.als) file.");
	}

	@Test
	@Order(5)
	void checkDeadSigsForHousesPuzzle() {
		String fileName = "src/main/resources/houses.als";
		List<String> dead = AlloyChecker.findDeadSignatures(fileName, opt, rep);
		assertEquals(0, dead.size(), "Houses Puzzle should have no dead signatures, but has " + dead.size()
				+ " instead. Check the [houses.als](src/main/resources/houses.als) file.");
	}

}
