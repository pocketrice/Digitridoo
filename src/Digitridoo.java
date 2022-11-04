//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -

import java.lang.System;
import java.util.List;
import java.util.Scanner;

public class Digitridoo
{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";

	// Attributes
	private String materialType;
	private int materialQuality;
	private double naturalImperfection, artificialImperfection, overtoneDisparity, volumeDisparity, calibration, pitchBendDisparity, uniqueInstrumentKey; // the UIK functions the same was as UMK.

	// Quality metrics
	private double rawRelativeTimbre, rawRelativeQuality, absoluteTimbre, absoluteQuality, audiophileMetric, audioplebMetric, creativityIndex;
	private String soundColor, contextColorShift;




	Scanner input = new Scanner(System.in);
	Digitridoo digiridoo = new Digitridoo();

	public void condenseMelody(int melody) // Condense the melody (e.g. 2435) into one "song" (e.g. "16"). Equivalent of actually adding up the digits.
	{

	}

	public int fetchMelody() { // Prompt for the melody

	}

	public static <T, R extends String> R unitize(List<T> list, boolean isMusicalUnit) { // Input "1,3,2,4,E,D,C,8,2". Convert between "regular units" -- numbers -- and "musical units" -- notes.

	}

	public String prompt(String message, String errorMessage, String[] bounds, boolean lineMode, boolean isCaseSensitive)
	{
		String nextInput;

		while (true)
		{
			if (!(message.matches("NO_MESSAGE")))
			{
				System.out.println(message);
			}

			if (lineMode) {
				input.nextLine();
				nextInput = input.nextLine();
			}
			else {
				nextInput = input.next();
			}

			if (!isCaseSensitive)
			{
				nextInput = nextInput.toLowerCase();
			}

				if (nextInput.matches("cancel"))
				{
					System.out.println(ANSI_RED + "Action cancelled.\n" + ANSI_RESET);
					return null;
				}

				if (nextInput.matches(String.join("|", bounds)) || bounds[0].equals("")) {
					return nextInput;
				} else {
					System.out.println(ANSI_RED + errorMessage + ANSI_RESET);
				}

			}
		}
	}

}