//� A+ Computer Science  -  www.apluscompsci.com
//Name - Lucas Xie
//Date - 11/5/22
//Class - AP CSA P5
//Lab - 9.2

import java.lang.System;
import java.util.*;
import java.util.stream.DoubleStream;

public class Digitridoo
{
	/*
	public enum Importance { // todo: look into enums
		Secondary,
		Primary,
	}*/

	// ...and (todo) look into func delegates (see C# stuff from 11/8 in Tower.cs) and maybe if they'll work in java?



	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";

	// Attributes
	private double overtoneDisparity, uniqueInstrumentKey; // todo: use the UIK for something
	private int calibration;
	private List<Integer> melodyCache = new ArrayList<>();

	// Metrics
	public static int totalPhrases, totalDraftedPhrases, totalCalibrationUsed;



	static Scanner input = new Scanner(System.in);
	public int numOfDigidoos = 0;

	public Digitridoo(int calib, int UIK, int overDisp, List<Integer> mel) {
		calibration = calib;
		uniqueInstrumentKey = UIK;
		overtoneDisparity = overDisp;
		melodyCache.addAll(mel);

		numOfDigidoos++;
	}

	public Digitridoo() {
		calibration = 100;
		uniqueInstrumentKey = generateUIK(numOfDigidoos);
		overtoneDisparity = weightedRandom(new Double[]{-0.1, -0.05, 0.0, 0.05, 0.1}, new double[]{0.05, 0.2, 0.5, 0.2, 0.05}, false);

		numOfDigidoos++;
	}

	public List<Integer> condenseToSong(List<Integer> melody) // Condense the phrases (e.g. 2435) into "song form" (e.g. "16"). Equivalent of actually adding up the digits.
	{
		List<Integer> song = new ArrayList<>();

		for (Integer phrase : melody) {
			int condensedPhrase = 0;

			while (phrase > 0) {
				condensedPhrase += phrase % 10;
				phrase /= 10;
			}

			song.add(condensedPhrase);
		}

		return song;
	}

	public List<Integer> playPhrases(List<Integer> draft) { // Play (no support for multiphrases. Everything's on-the-spot.)
		boolean isStillPlaying = true;
		List<Integer> phrases = new ArrayList<>();

		if (draft.size() == 0) {
			System.out.println(ANSI_BLUE + "\n\nYou're now in the Zone, ready to play 'till the end of time. You can stop whenever you want by playing a '0', otherwise keep on truckin'!" + ANSI_RESET);
			while (isStillPlaying && calibration > 0) {
				try {
					System.out.println(ANSI_CYAN + "Play the next phrase." + ANSI_RESET);
					int phrase = input.nextInt();
					if (phrase == 0)
						isStillPlaying = false;

					calibration -= 5 + Integer.toString(phrase).length();
					totalCalibrationUsed += 5 + Integer.toString(phrase).length(); // Metrics stuff


					System.out.println("You played a " + colorize(phrase)[0] + ", yet " + colorize(phrase)[1] + " phrase. It felt like a " + colorize(phrase)[2] + "!\n");
					phrases.add(phrase);
				} catch (Exception e) {
					input.nextLine();
					System.out.println(ANSI_PURPLE + "The Zone crumbles, but you swiftly end the " + phrases.size() + "-phrase melody somewhat decently.");
					return phrases;
				}
			}
		}
		else { // A melody has been pre-prepped. todo: embezzlement (if you're really good, you can embezzle your phrases on-the-spot) and user influence on playing (plus, playing will never have the same exact qualities than drafting). Allow the user to fluidly interchange between improv and pre-prepped melodies.
			System.out.println(ANSI_GREEN + "You briskly dive into the Zone and instinctively start playing the melody tucked in your mind.\n" + ANSI_RESET);
			int phraseCount = 0;

			while (isStillPlaying && calibration > 0) {
				for (Integer phrase : draft) {
					switch (phraseCount) { // todo: look into switch expressions! More applicable in a "setting a var" scenario though. Here switch-case is perfectly fine.
						case 0 -> {
							System.out.println(ANSI_GREEN + "You began with a " + colorize(phrase)[0] + " and " + colorize(phrase)[1] + " overture. It felt like a " + colorize(phrase)[2] + "!" + ANSI_RESET);
						}
						case 1 -> { // Switch-cases cannot accept conditions. Look at ternary operators instead (https://stackoverflow.com/questions/31497818/switches-in-java-can-i-include-a-condition-in-a-case) or write your own method (APM TIME?)
							System.out.println(ANSI_GREEN + "You then followed quickly with a " + colorize(phrase)[0] + " and " + colorize(phrase)[1] + " phrase. It felt like a " + colorize(phrase)[2] + "!" + ANSI_RESET);
						}
						default -> { // todo: vary the types of messages outputted (weightedrandom?).
							System.out.println(ANSI_GREEN + "You hymned a " + colorize(phrase)[0] + " yet " + colorize(phrase)[1] + " phrase. It felt like a " + colorize(phrase)[2] + "!" + ANSI_RESET);
						}
					}

					calibration -= 5 + Integer.toString(phrase).length();
					totalCalibrationUsed += 5 + Integer.toString(phrase).length(); // Metrics stuff

					phrases.add(phrase);
					phraseCount++;
				}

				isStillPlaying = false;
			}
		}

		if (calibration <= 0) { // Miscalibration ending
			System.out.println(ANSI_RED + "\nYour digiridoo's gone a little too far without being calibrated! The performance concluded abruptly with a disarray of guttural squeaks n' squabbles.");
			System.out.println(ANSI_PURPLE + "The average audio quality was " + assessAudioQuality(phrases)[0] + "% and creativity index was " + assessAudioQuality(phrases)[1] + ".");
			System.out.println("Your song, for some reason, made you think of the numbers " + grammaticParse(condenseToSong(phrases)) + ".\n\n" + ANSI_RESET);

			totalPhrases += phrases.size();
			return phrases;
		}

		// Perfect ending
		System.out.println(ANSI_PURPLE + "\nYou elegantly conclude the " + phrases.size() + "-phrase melody resoundingly and coolly exit the Zone.");
		System.out.println("The average audio quality was " + assessAudioQuality(phrases)[0] + "% and creativity index was " + assessAudioQuality(phrases)[1] + ".");
		System.out.println("Your song, for some reason, made you think of the numbers " + grammaticParse(condenseToSong(phrases)) + ".\n\n" + ANSI_RESET);

		totalPhrases += phrases.size();
		return phrases;
	}

	public List<Integer> fetchPhrases() { // Prepare phrases in your "mind" to play. todo: make this process different from playPhrases; maybe restrict pP more than this one.
		boolean isStillFetching = true;
		List<Integer> phrases = new ArrayList<>();

		System.out.println(ANSI_BLUE + "\n\nYou're in the mental Zone and ready to plan out a darn-good performance. Remember to end by conjuring up a '0'!" + ANSI_RESET);

		while (isStillFetching) {
			try {
				System.out.println(ANSI_CYAN + "Draft the next phrase." + ANSI_RESET);
				int phrase = input.nextInt();
				if (phrase == 0)
					isStillFetching = false;

				phrases.add(phrase);
			} catch (Exception e) {
				input.nextLine();
				System.out.println(ANSI_PURPLE + "The Zone evaporates and you're left remniscing on your " + phrases.size() + "-phrase tune, not quite satisfied." + ANSI_RESET);
				isStillFetching = false;
			}
		}

		int noteCount = 0; // Calculate number of notes (digits) involved.
		for (Integer phrase : phrases)
			noteCount += phrase.toString().length();

		int calibrationLoss = 5 * phrases.size() + noteCount;
		System.out.println(ANSI_YELLOW + "\nDo you want to keep this " + phrases.size() + "-phrase melody? Anticipated calibration loss is " + calibrationLoss + ".");
		System.out.println(Arrays.toString(phrases.toArray()).substring(1, Arrays.toString(phrases.toArray()).length() - 1) + ANSI_RESET);

		switch (prompt("NO_MESSAGE", "Error: invalid choice.", new String[]{"yes", "no"}, false, false)) {
			case "yes" -> {
				System.out.println(ANSI_PURPLE + "You're all done composing now and ready to hit the stage. Great " + phrases.size() + "-phraser!\n\n" + ANSI_RESET);
			}

			case "no" -> {
				System.out.println(ANSI_PURPLE + "Melody draft scrapped." + ANSI_RESET);
				phrases.clear();
			}
		}

		totalDraftedPhrases += phrases.size();
		return phrases;
	}


	public double[] assessAudioQuality(List<Integer> phrases) { // Post-playing analysis.
		double[] audioQuality = new double[phrases.size()], creativityIndex = new double[phrases.size()];

		for (int i = 0; i < phrases.size(); i++) { // todo: return/process individual "quality metrics".
			audioQuality[i] = 100.0 - Math.abs(50.0 * overtoneDisparity) - Math.abs(0.8 * (100-calibration));
			creativityIndex[i] = Math.abs(25.0 * overtoneDisparity) + Math.abs(0.1 * (100-calibration)) + 5 * phrases.get(i).toString().length();
		}

		return new double[]{DoubleStream.of(audioQuality).average().getAsDouble(), DoubleStream.of(creativityIndex).average().getAsDouble()}; // warning: .average() returns OptionalDouble which can have "no double" -- in that case exception is thrown.
	}

	public String[] colorize(int phrase) { // todo: change int to long to avoid int limits.
		// Sound color comes in different general categorizations: brightness (light blue vs dark blue -- vertical axis of color picker), temperature (red vs blue), and depth (a pale, whitish blue vs a "blue" blue -- hor. axis of color picker).
		// Temporarily this only returns simple "vague" categorizations that are really just garbage data. And another todo: add synonyms to categories to avoid dull repeats (e.g. the category might be hot, but when printing replace with a synonym like "blazing" or "cinderous". Go ham with the vivid wording!)
		String[] colorCategories = new String[3];

		if (phrase % 2 == 1) // Odd = cold, even = hot -- todo: add more than just two options! Add some nuance (e.g. if near an extreme, add a different temp!)
			colorCategories[0] = "chilling";
		else
			colorCategories[0] = "cinderous";

		if (phrase * (1 + overtoneDisparity) > (double)Integer.MAX_VALUE / 2) // Higher than half-of-intmax = rich, deep tone, lower = shallow tone -- todo: same applies as above! Especially here, tone can be categorized in so many ways
			colorCategories[1] = "rich";
		else
			colorCategories[1] = "paper-thin";

		colorCategories[2] = weightedRandom(new String[]{"wistful mahogany", "passionate red-orange", "sombre blue", "transcendent violet", "tranquil lime", "thoughtful gray", "melancholy yellow"}, new double[7], true);

		return colorCategories;

		// Add more, too! Build the full string in-house rather than exterior, so you can add things like "yet" versus "and also" to deal with contrast ("chilling yet rich"). And add more impactful categorizations too -- like "haunting, chilling, yet inspirational". Additionally, maybe categorize the type of melody here too -- "warm, soothing short-burst" versus "warm, soothing chorus"
	} // ** Unrelated todo: it may be useful to be able to articulate in a more tangible way too -- e.g. staccato, tenuto, dynamics, etc. Make it less "pure randomized" and more under the musician's control. Plus, maybe a sheet music feature to play-along too (see "online-piano" or whatever it's called for an exemplar).

	public void calibrate() { // todo: make this a little step-by-step minigame perhaps. Better quality = more calibration. Think back to my calibration process with my alto (e.g. key-check, reed-check, neck strap readjustments).
		System.out.println("You meticulously recalibrated your digeridoo. You're all set for another groovin'!");
		calibration = 100; // Hard coded for now!
	}

	public int generateUIK(int n) { // todo: get nth rng from IntStream (e.g. for UIK #1, get the first rng from the seed. Since numOfDigidoos++ for each instrument you could then get "numOfDigidoo"th rng from the seed. You could then call the method with a particular value to get the same key again if needed (e.g. need to see what 6th key is? just pass in 6.)
		long seed = 92395828;
		Random rng = new Random(seed);

		// ** NOTE: n is not used right now. Figure out how to get nth key from stream to do this.
		return rng.ints(1,0, Integer.MAX_VALUE).sum();
	}

	public static <T extends Collection<Integer>> int[] intTypeToPrimitive(T collection) { // <+> APM -- although not used in this lab.
		return collection.stream().mapToInt(Integer::intValue).toArray();
	}



	public static void main(String[] args) {
		Digitridoo digiridoo = new Digitridoo();
		System.out.println(ANSI_PURPLE + "Welcome back, you're just in time for our jam session tonight. Introducing our world-famous digeridoo player in town for our Aboriginal Originals show!");
		System.out.println("\nAll eyes and ears are on you now -- get ready to play that digeridoo like there's no tomorrow. \nNeed a refresher on how to play? Just whisper 'help' and I'll get that guidebook of yours to peek at. G'luck!" + ANSI_RESET);

		while (true) {
			switch (prompt(ANSI_CYAN + "\nWhat'll you do next? (calibration: " + digiridoo.calibration + "%)" + ANSI_RESET, "Whoops, you're not quite sure what you were thinking. Need help? Type 'help'!", new String[]{"help", "play", "think", "calibrate", "done"}, false, false )) {
				case "help" -> {
					System.out.println(ANSI_BLUE + "Psst, here you go! But hurry, the crowd's waiting for your awe-inspiring performance...");
					System.out.println("\n\n****************************************************************************************************************");
					System.out.println("\t\t\t\t\t\tGUIDE TO PLAYING THE DIGITRIDOO");
					System.out.println("\nThank you for your interest in playing the Digitridoo. Unlike other instruments, this is played using the magic of numbers.");

					System.out.println("\nIn the event you are on-stage and have no idea how to play this instrument, here are some possible options:");
					System.out.println("\t* 'help' -- see this manual again");
					System.out.println("\t* 'play' -- perform a melody on-the-spot");
					System.out.println("\t* 'think' -- draft a melody to play later");
					System.out.println("\t* 'calibrate' -- recalibrate your instrument");
					System.out.println("\t* 'done' -- conclude your performance!");

					System.out.println("\nHere are some additional things to note.");
					System.out.println("\t* Continuous playing will degrade your instrument's calibration. If calibration hits 0, you won't be able to play no more!");
					System.out.println("\t* Think before you play! This can help you plan out how you want to structure your performance.");
					System.out.println("\t* Sounds have colors! See what kinds of different sounds you can play and what tone they have.");

					System.out.println("\n\n****************************************************************************************************************" + ANSI_RESET);

				}
				case "play" -> {
					digiridoo.playPhrases(digiridoo.melodyCache);
				}

				case "think" -> {
					digiridoo.melodyCache.addAll(digiridoo.fetchPhrases());
				}

				case "calibrate" -> {
					digiridoo.calibrate();
				}

				case "done" -> {
					System.out.println(ANSI_YELLOW + "You peep out the last note.. and the audience roars with a thunderous frenzy of applause, wallops, and the soulful feeling of a performance well-done. Finally, success!");
					System.out.println("Good showing! Here's how you did tonight...");

					System.out.println("\nTotal phrases played: " + totalPhrases + " phrases");
					System.out.println("Total phrases drafted: " + totalDraftedPhrases + " phrases");
					System.out.println("Total calibration exhausted: " + totalCalibrationUsed + "%");
					System.out.println("Average rating from audience: 10/10!");

					System.out.println("\nUntil next time!");
					System.exit(0);
				}
			}
		}

	}

	public static <T> String grammaticParse(T[] array) { // <+> APM
		StringBuilder gParsedString = new StringBuilder(Arrays.toString(array));

		gParsedString.deleteCharAt(0)
				.deleteCharAt(gParsedString.length()-1)
				.insert(gParsedString.lastIndexOf(",") + 1, " and");

		return gParsedString.toString();
	}

	public static <T extends Collection<E>, E> String grammaticParse(T collection) { // <+> APM
		StringBuilder gParsedString = new StringBuilder(collection.toString());

		gParsedString.deleteCharAt(0)
				.deleteCharAt(gParsedString.length()-1)
				.insert(gParsedString.lastIndexOf(",") + 1, " and");

		return gParsedString.toString();
	}


	public static String prompt(String message, String errorMessage, String[] bounds, boolean lineMode, boolean isCaseSensitive) // <+> APM
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

	public <T> T weightedRandom(T[] choices, double[] weights, boolean autoEqualize) // <+> APM
	{
		double rng = Math.random();

		if (autoEqualize) {
			Arrays.fill(weights, 1.0 / choices.length);
		}

		assert (DoubleStream.of(weights).sum() != 1);
		for (int i = 0; i < weights.length; i++) {
			if (rng < weights[i])
				return choices[i];
			else
				rng -= weights[i];
		}

		return null;
	}

	/*
	public <T extends Number> T weightedRandom(T[] range, Map<T, T> anchorAndWeight)
	{
		assert(range.length == 2); // todo: assert that anchors are within the range.

		double rng = Math.random() * Math.abs(range[1] - range[0]); // Generics problem: can't subtract them despite being Numbers?

		// todo: figure out a way to get this working. Basically, within the range there are anchors, which are values that are given weights (so it's more likely to land on them if weight = pos or less likely if weight = neg). What makes this diff from the above one is there's still a chance to hit values between weights too.
	}*/

	}
