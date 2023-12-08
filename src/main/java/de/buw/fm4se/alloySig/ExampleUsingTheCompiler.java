package de.buw.fm4se.alloySig;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.alloy4.Err;
import edu.mit.csail.sdg.alloy4.ErrorWarning;
import edu.mit.csail.sdg.alloy4viz.VizGUI;
import edu.mit.csail.sdg.ast.Command;
import edu.mit.csail.sdg.ast.Module;
import edu.mit.csail.sdg.parser.CompUtil;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Solution;
import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod;

/**
 * This class demonstrates how to access Alloy4 via the compiler methods.
 * 
 * Example from original Alloy code. Copied here for convenience of inspection
 * and modification.
 * 
 * @see edu.mit.csail.sdg.alloy4whole.ExampleUsingTheCompiler
 *
 */
public final class ExampleUsingTheCompiler {

	/*
	 * Execute every command in every file. This method parses every file, then
	 * execute every command. If there are syntax or type errors, it may throw a
	 * ErrorSyntax or ErrorType or ErrorAPI or ErrorFatal exception. You should
	 * catch them and display them, and they may contain filename/line/column
	 * information.
	 */
	public static void main(String[] args) throws Err {

		// The visualizer (We will initialize it to nonnull when we visualize an
		// Alloy solution)
		VizGUI viz = null;

		// Alloy4 sends diagnostic messages and progress reports to the
		// A4Reporter.
		// By default, the A4Reporter ignores all these events (but you can
		// extend the A4Reporter to display the event for the user)
		A4Reporter rep = new A4Reporter() {

			// For example, here we choose to display each "warning" by printing
			// it to System.out
			@Override
			public void warning(ErrorWarning msg) {
				System.out.print("Relevance Warning:\n" + (msg.toString().trim()) + "\n\n");
				System.out.flush();
			}
		};

		for (String filename : args) {

			// Parse+typecheck the model
			System.out.println("=========== Parsing+Typechecking " + filename + " =============");
			Module world = CompUtil.parseEverything_fromFile(rep, null, filename);

			// Choose some default options for how you want to execute the
			// commands
			A4Options options = new A4Options();

			options.solver = A4Options.SatSolver.SAT4J;

			for (Command command : world.getAllCommands()) {
				// Execute the command
				System.out.println("============ Command " + command + ": ============");
				A4Solution ans = TranslateAlloyToKodkod.execute_command(rep, world.getAllReachableSigs(), command,
						options);
				// Print the outcome
				System.out.println(ans);
				// If satisfiable...
				if (ans.satisfiable()) {
					// You can query "ans" to find out the values of each set or
					// type.
					// This can be useful for debugging.
					//
					// You can also write the outcome to an XML file
					ans.writeXML("alloy_example_output.xml");
					//
					// You can then visualize the XML file by calling this:
					if (viz == null) {
						viz = new VizGUI(false, "alloy_example_output.xml", null);
					} else {
						viz.loadXML("alloy_example_output.xml", true);
					}
				}
			}
		}
	}
}