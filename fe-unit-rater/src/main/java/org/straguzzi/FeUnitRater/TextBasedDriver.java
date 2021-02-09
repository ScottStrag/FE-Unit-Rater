package org.straguzzi.FeUnitRater;

import java.util.*;

/**
 * Simple text based driver for users to use the unit rater.
 * 
 * As of 2/6/21, only supports students, does not support church of seiros
 * Does not support recruiting students from out of house yet - need to add a method for inputting a higher leveled student's stats that aren't their base stats
 * 
 * @author Scott Straguzzi
 * @version 1/27/2021
 */
public class TextBasedDriver {
	private static Map<String, Character> characters; // Maps the character's name in lowercase to their Character object
	private static Map<String, CharClass> classes; // Maps the class's name in lowercase to their CharClass object
	
	public TextBasedDriver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// Initialization methods
		System.out.println("Initializing...");
		buildCharacters();
		buildClasses();
		
		System.out.println("Successfully loaded all students + playable classes!\n");
		System.out.println("Please note that this calculator does not account for minimum class base boosts upon certification.\nSupport for those is coming in a later version!\n");
		
		// Gonna put a while loop here to take input and all
		boolean running = true;
		String input = "";
		Scanner scan = new Scanner(System.in);
		while(running) {
			// Prints the first layer of the menu
			// Workflow goes like this: select character, create classpath, project to some level, display the projected stats, loop back here
			System.out.println("PLease select a character, or type in \"close\" to close."); // Should print as a lowercase
			boolean valid = false;
			Character character = null;
			while(!valid) { // Makes sure only valid input will be read
				input = formatInput(scan.nextLine()); // Takes input as a string
				if(input.equalsIgnoreCase("close")) { // Closes the program gracefully
					System.out.println("Thank you for using this calculator!");
					valid = true;
					running = false;
				}
				else {
					character = characters.get(input);
					if(character != null) {
						valid = true; // The given character was successfully pulled from the hashmap and the user can move on to the next step
					}
					else {
						System.out.println("Invalid input. Did you misspell the character's name?");
					}
				}
				if(character != null) { // Character is selected, now gets classes + levels
					System.out.println("Please input " + character.getName() + "'s class path. Type \"done\" for the class to move on to the next step.\n"
							+ "Please input their classes in the order they were in.");
					valid = false;
					while(!valid) {
						System.out.println("What class will " + character.getName() + " become?");
						CharClass charClass = null;
						int level = 0;
						
						input = formatInput(scan.nextLine());
						
						if(input.equalsIgnoreCase("done")) {
							valid = true; // Moves on to the next step
						}
						else {
							charClass = classes.get(input);
							if(charClass != null) {
								System.out.println("What level does " + character.getName() + " become this class?");
								int next = scan.nextInt(); // Just uses scan.nextInt() for now, will likely change in the future for better error handling
								if(next > level) {
									level = next;
									character.addClassChange(new ClassPath(charClass, level)); // Adds their class change
									System.out.println(character.getName() + " becomes a " + charClass.getName() + " at level " + level + ".");
									scan.nextLine();
								}
							}
							else { // Invalid input, class name likely misspelled
								System.out.println("Invalid input. Did you misspell the class name?");
							}
						}
					}
					// Finished getting classes + levels, now finds what level the character's stats will be projected to
					System.out.println("What level would you like to project " + character.getName() + "'s stats to?");
					int level = scan.nextInt();
					// Prints their stats at that level
					System.out.println(character.project(level));
					scan.nextLine(); // Clears the input buffer
				}
			}
			
		}
		scan.close();
		
		System.out.println("\nThank you for using the character stats projector!");

	}
	
	/**
	 * Static method for populating the characters map with all of the characters in 3H
	 * Maps the character's lowercase name to their character object
	 * 
	 * All characters are instantiated as their base stats listed on this website:
	 * https://serenesforest.net/three-houses/characters/base-stats/
	 */
	private static void buildCharacters() {
		CharClass commoner = new CharClass("Commoner", 0, 0, 0, 0, 0, 0, 0, 0, 0, 
										   0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f); // Instantiates one of the 2 default classes for the students
		CharClass noble = new CharClass("Commoner", 0, 0, 0, 0, 0, 0, 0, 0, 0, 
				   						0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.05f); // Instantiates one of the 2 default classes for the students
		
		characters = new HashMap<String, Character>();
		
		// Adds Byleth
		characters.put("byleth", new Character("Byleth", commoner, 1, 27, 13, 6, 9, 8, 8, 6, 6, 7,
					.45f, .45f, .35f, .45f, .45f, .45f, .35f, .30f, .45f));
		
		// Adds Black Eagles
		characters.put("edelgard", new Character("Edelgard", noble, 1, 29, 13, 6, 5, 8, 5, 6, 4, 10,
				  	.4f, .55f, .45f, .45f, .40f, .30f, .35f, .35f, .60f));
		characters.put("hubert", new Character("Hubert", noble, 1, 22, 6, 12, 6, 7, 6, 4, 7, 6,
					.35f, .30f, .55f, .45f, .45f, .35f, .25f, .40f, .35f));
		characters.put("dorothea", new Character("Dorothea", commoner, 1, 24, 5, 11, 6, 7, 6, 6, 7, 6,
					.4f, .2f, .4f, .45f, .40f, .35f, .15f, .35f, .40f));
		characters.put("ferdinand", new Character("Ferdinand", noble, 1, 28, 8, 5, 6, 8, 6, 6, 2, 7,
					.5f, .45f, .2f, .40f, .50f, .40f, .35f, .20f, .40f));
		characters.put("bernadetta", new Character("Bernadetta", noble, 1, 25, 8, 5, 7, 7, 5, 4, 2, 6,
					.35f, .35f, .2f, .55f, .50f, .25f, .20f, .30f, .35f));
		characters.put("caspar", new Character("Caspar", noble, 1, 26, 9, 3, 5, 6, 8, 6, 2, 4,
					.55f, .45f, .25f, .45f, .45f, .40f, .30f, .2f, .25f));
		characters.put("petra", new Character("Petra", commoner, 1, 25, 9, 3, 7, 10, 7, 5, 2, 6,
					.45f, .4f, .25f, .5f, .6f, .45f, .3f, .15f, .35f));
		characters.put("linhardt", new Character("Linhardt", noble, 1, 24, 5, 10, 6, 5, 7, 5, 9, 3,
					.3f, .3f, .45f, .4f, .40f, .45f, .3f, .45f, .20f));
		
		// Adds Blue Lions
		characters.put("dimitri", new Character("Dimitri", noble, 1, 28, 12, 4, 7, 7, 5, 7, 4, 9,
					.55f, .6f, .2f, .5f, .5f, .25f, .4f, .2f, .55f));
		characters.put("dedue", new Character("Dedue", commoner, 1, 30, 12, 2, 5, 7, 5, 8, 1, 4,
					.6f, .5f, .15f, .3f, .2f, .25f, .5f, .1f, .30f));
		characters.put("felix", new Character("Felix", noble, 1, 26, 10, 5, 6, 9, 5, 5, 3, 5,
					.45f, .55f, .3f, .45f, .55f, .4f, .3f, .2f, .3f));
		characters.put("mercedes", new Character("Mercedes", commoner, 1, 25, 6, 10, 6, 8, 5, 5, 9, 8,
					.3f, .25f, .5f, .45f, .4f, .3f, .25f, .45f, .30f));
		characters.put("ashe", new Character("Ashe", commoner, 1, 23, 8, 5, 8, 9, 6, 5, 6, 5,
					.35f, .35f, .25f, .55f, .5f, .4f, .2f, .35f, .25f));
		characters.put("annette", new Character("Annette", noble, 1, 23, 6, 11, 7, 7, 6, 5, 4, 6,
					.25f, .3f, .5f, .5f, .35f, .35f, .2f, .3f, .35f));
		characters.put("sylvain", new Character("Sylvain", noble, 1, 27, 9, 5, 5, 8, 6, 6, 2, 7,
					.55f, .45f, .3f, .35f, .5f, .35f, .4f, .25f, .40f));
		characters.put("ingrid", new Character("Ingrid", noble, 1, 27, 8, 6, 6, 8, 6, 5, 8, 8,
					.4f, .35f, .35f, .4f, .6f, .45f, .3f, .4f, .45f));
		
		// Adds Golden Deer
		characters.put("claude", new Character("Claude", noble, 1, 26, 11, 5, 8, 8, 7, 6, 4, 8,
					.35f, .4f, .25f, .6f, .55f, .45f, .3f, .25f, .55f));
		characters.put("lorenz", new Character("Lorenz", noble, 1, 28, 8, 7, 6, 7, 5, 6, 6, 3,
					.55f, .4f, .4f, .45f, .4f, .25f, .3f, .40f, .45f));
		characters.put("hilda", new Character("Hilda", noble, 1, 29, 10, 5, 5, 8, 6, 6, 3, 7,
					.5f, .45f, .25f, .3f, .5f, .35f, .35f, .2f, .5f));
		characters.put("raphael", new Character("Raphael", commoner, 1, 30, 11, 3, 5, 6, 6, 7, 1, 4,
					.65f, .5f, .15f, .35f, .15f, .35f, .45f, .1f, .25f));
		characters.put("lysithea", new Character("Lysithea", noble, 1, 22, 4, 11, 7, 7, 4, 3, 4, 5,
					.2f, .15f, .6f, .6f, .5f, .15f, .1f, .25f, .25f));
		characters.put("ignatz", new Character("Ignatz", commoner, 1, 25, 8, 5, 7, 8, 8, 4, 6, 4,
					.35f, .35f, .3f, .5f, .5f, .55f, .25f, .35f, .25f));
		characters.put("marianne", new Character("Marianne", noble, 1, 23, 5, 11, 6, 7, 6, 4, 8, 7,
					.35f, .2f, .5f, .4f, .4f, .35f, .15f, .45f, .4f));
		characters.put("leonie", new Character("Leonie", noble, 1, 26, 9, 5, 8, 9, 6, 7, 2, 7,
					.4f, .4f, .2f, .55f, .6f, .4f, .4f, .15f, .4f));
		
		// Adds Ashen Wolves
		characters.put("yuri", new Character("Yuri", commoner, 1, 24, 10, 6, 7, 9, 7, 5, 7, 8,
					.3f, .4f, .35f, .4f, .65f, .45f, .3f, .35f, .5f));
		characters.put("balthus", new Character("Balthus", commoner, 1, 28, 11, 5, 4, 7, 3, 7, 4, 4,
					.5f, .5f, .3f, .25f, .3f, .2f, .45f, .3f, .3f));
		characters.put("constance", new Character("Constance", commoner, 1, 23, 5, 11, 6, 6, 4, 3, 4, 6,
					.2f, .2f, .6f, .3f, .35f, .15f, .15f, .3f, .25f));
		characters.put("hapi", new Character("Hapi", commoner, 1, 26, 6, 11, 8, 6, 4, 4, 7, 4,
					.35f, .35f, .45f, .45f, .4f, .2f, .15f, .45f, .25f));
		
		
		// Adds Church of Seiros
		// TODO in a bit when I have another method for manually updating a character's base stats
		// Their stats vary too much based on when you recruit them, and I'm not sure how to calculate them manually just given their level / when you recruit them
		// Will be done at another time
	}
	
	/**
	 * Static method for populating the classes list object with all of the classes in 3H
	 * Maps the class name in lowercase to the CharClass object of the same name
	 */
	private static void buildClasses() {
		classes = new HashMap<String, CharClass>();
		
		// Unique classes
		classes.put("commoner", new CharClass("Commoner", 0, 0, 0, 0, 0, 0, 0, 0, 0, 
					0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
		classes.put("noble", new CharClass("Noble", 0, 0, 0, 0, 0, 0, 0, 0, 0, 
				   	0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.05f));
		classes.put("dancer", new CharClass("Dancer", 0, 1, 0, 3, 3, 0, 2, 1, 2, 
					0.2f, -0.05f, 0.0f, 0.0f, 0.0f, 0.0f, -0.05f, -0.05f, 0.1f));		
		classes.put("enlightened one", new CharClass("Enlightened One", 1, 2, 3, 1, 1, 1, 2, 2, 1, 
					0.2f, 0.1f, 0.1f, 0.0f, 0.0f, 0.0f, 0.05f, 0.0f, 0.05f));
		classes.put("armored lord", new CharClass("Armored Lord", 5, 0, 0, 0, -3, 0, 5, 0, 2, 
				0.2f, 0.05f, 0.05f, 0.0f, 0.0f, 0.0f, 0.05f, 0.05f, 0.1f));
		classes.put("high lord", new CharClass("High Lord", 3, 1, 0, 0, 3, 0, 1, 0, 2, 
				0.2f, 0.05f, 0.0f, 0.05f, 0.0f, 0.0f, 0.05f, 0.0f, 0.10f));
		classes.put("wyvern master", new CharClass("Wyvern Master", 2, 2, 0, 3, 1, 0, 1, 0, 2, 
				0.2f, 0.1f, 0.0f, 0.0f, 0.05f, 0.0f, 0.05f, 0.0f, 0.1f));
		classes.put("emperor", new CharClass("Emperor", 8, 0, 0, 0, -4, 1, 8, 0, 2, 
				0.3f, 0.1f, 0.1f, 0.0f, 0.0f, 0.0f, 0.05f, 0.05f, 0.1f));
		classes.put("great lord", new CharClass("Great Lord", 5, 2, 0, 0, 4, 1, 1, 0, 2, 
				0.3f, 0.1f, 0.0f, 0.1f, 0.0f, 0.0f, 0.05f, 0.0f, 0.1f));
		classes.put("barbarossa", new CharClass("Barbarossa", 3, 3, 0, 5, 2, 0, 2, 0, 2, 
				0.3f, 0.15f, 0.0f, 0.0f, 0.1f, 0.0f, 0.05f, 0.0f, 0.1f));
		
		// Beginner
		classes.put("myrmidon", new CharClass("Myrmidon", 0, 0, 0, 0, 1, 0, 0, 0, 0, 
				0.1f, 0.0f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, -0.05f, 0.05f));
		classes.put("soldier", new CharClass("Soldier", 0, 0, 0, 1, 0, 0, 0, 0, 0, 
				0.1f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, 0.0f, -0.05f, 0.05f));
		classes.put("fighter", new CharClass("Fighter", 0, 1, 0, 0, 0, 0, 0, 0, 0, 
				0.1f, 0.05f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.05f, 0.05f));
		classes.put("monk", new CharClass("Monk", 0, 0, 0, 0, 0, 0, 0, 1, 0, 
				0.05f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.05f, 0.05f));
		
		// Intermediate
		classes.put("lord", new CharClass("Lord", 1, 0, 0, 0, 1, 1, 0, 0, 2, 
				0.2f, 0.0f, 0.0f, 0.1f, 0.00f, 0.0f, 0.0f, 0.0f, 0.1f));
		classes.put("mercenary", new CharClass("Mercenary", 1, 1, 0, 0, 1, 0, 0, 0, 0, 
				0.2f, 0.05f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, -0.05f, 0.05f));
		classes.put("theif", new CharClass("Theif", 0, 0, 0, 2, 2, 0, 0, 0, 0, 
				0.2f, 0.0f, 0.0f, 0.1f, 0.1f, 0.0f, 0.0f, 0.0f, 0.05f));
		classes.put("armored knight", new CharClass("Armored Knight", 3, 0, 0, 0, -2, 0, 4, -1, 0, 
				0.2f, 0.0f, 0.0f, 0.0f, -0.1f, 0.0f, 0.1f, -0.05f, 0.05f));
		classes.put("cavalier", new CharClass("Cavalier", 1, 1, 0, 2, -1, 0, 1, 0, 0, 
				0.2f, 0.05f, 0.0f, 0.05f, -0.1f, 0.0f, 0.05f, 0.0f, 0.05f));
		classes.put("brigand", new CharClass("Brigand", 2, 2, 0, 0, 0, 0, 0, 0, 0, 
				0.3f, 0.1f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.05f, 0.05f));
		classes.put("archer", new CharClass("Archer", 0, 0, 0, 2, 0, 2, 0, 0, 0, 
				0.05f, 0.0f, 0.0f, 0.1f, 0.0f, 0.05f, 0.0f, 0.0f, 0.05f));
		classes.put("brawler", new CharClass("Brawler", 1, 0, 0, 2, 2, 0, 0, -1, 0, 
				0.3f, 0.0f, -0.1f, 0.1f, 0.1f, 0.0f, 0.0f, -0.1f, 0.05f));
		classes.put("mage", new CharClass("Mage", 0, 0, 1, 1, 0, 0, 0, 2, 0, 
				0.05f, -0.05f, 0.1f, 0.05f, 0.0f, 0.0f, -0.05f, 0.05f, 0.05f));
		classes.put("dark mage", new CharClass("Dark Mage", 0, 0, 2, 1, 0, 0, 0, 1, 0, 
				0.05f, -0.05f, 0.1f, 0.05f, 0.1f, 0.0f, -0.05f, 0.05f, 0.0f));
		classes.put("priest", new CharClass("Priest", 0, 0, 0, 1, 0, 0, 0, 3, 0, 
				0.05f, -0.05f, 0.05f, 0.05f, 0.0f, 0.0f, -0.05f, 0.1f, 0.1f));
		classes.put("pegasus knight", new CharClass("Pegasus Knight", 0, 0, 0, 2, 3, 0, 0, 2, 0, 
				0.15f, 0.0f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.05f, 0.1f));
		
		
		// Advanced
		classes.put("hero", new CharClass("Hero", 3, 2, 0, 0, 2, 1, 1, 0, 0, 
				0.3f, 0.1f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, -0.05f, 0.05f));
		classes.put("swordmaster", new CharClass("Swordmaster", 1, 2, 0, 1, 4, 0, 1, 0, 0, 
				0.25f, 0.1f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, -0.05f, 0.05f));
		classes.put("assassin", new CharClass("Assassin", 0, 0, 0, 3, 5, 1, 0, 0, 0, 
				0.2f, 0.0f, 0.0f, 0.2f, 0.2f, 0.0f, 0.0f, 0.0f, 0.0f));
		classes.put("fortress knight", new CharClass("Fortress Knight", 5, 0, 0, 0, -6, 0, 10	, 0, 0, 
				0.3f, 0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.15f, 0.0f, 0.05f));
		classes.put("paladin", new CharClass("Paladin", 2, 2, 0, 2, -1, 0, 2, 2, 0, 
				0.3f, 0.1f, 0.0f, 0.05f, -0.1f, 0.05f, 0.05f, 0.05f, 0.05f));
		classes.put("wyvern rider", new CharClass("Wyvern Rider", 1, 3, 0, 1, 3, 0, 2, 0, 0, 
				0.3f, 0.1f, -0.05f, 0.0f, 0.0f, 0.0f, 0.05f, -0.05f, 0.05f));
		classes.put("warrior", new CharClass("Warrior", 3, 3, 0, 0, 1, 1, 1, 0, 0, 
				0.4f, 0.15f, -0.05f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.05f));
		classes.put("sniper", new CharClass("Sniper", 0, 1, 0, 5, 0, 3, 0, 0, 0, 
				0.1f, 0.05f, 0.0f, 0.2f, 0.0f, 0.1f, 0.0f, 0.0f, 0.05f));
		classes.put("grappler", new CharClass("Grappler", 2, 1, 0, 3, 3, 0, 1, -1, 0, 
				0.4f, 0.1f, 0.0f, 0.1f, 0.1f, 0.0f, 0.0f, 0.0f, 0.05f));
		classes.put("warlock", new CharClass("Warlock", 0, 0, 3, 1, 1, 0, 0, 4, 0, 
				0.1f, 0.0f, 0.1f, 0.0f, 0.1f, 0.0f, -0.05f, 0.05f, 0.05f));
		classes.put("dark bishop", new CharClass("dark bishop", 0, 0, 4, 2, 0, 0, 0, 3, 0, 
				0.1f, 0.0f, 0.1f, 0.0f, 0.0f, 0.0f, -0.05f, 0.05f, 0.0f));
		classes.put("bishop", new CharClass("Bishop", 1, 0, 2, 1, 0, 0, 0, 5, 0, 
				0.1f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, -0.05f, 0.05f, 0.1f));
		
		// Master
		classes.put("falcon knight", new CharClass("Falcon Knight", 0, 1, 0, 4, 5, 0, 0, 4, 0, 
				0.3f, 0.1f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.05f, 0.1f));
		classes.put("wyvern lord", new CharClass("Wyvern Lord", 2, 4, 0, 1, 4, 0, 3, 0, 0, 
				0.3f, 0.15f, -0.05f, 0.0f, 0.1f, 0.0f, 0.05f, 0.0f, 0.05f));
		classes.put("mortal savant", new CharClass("Mortal Savant", 1, 1, 2, 1, 1, 0, 2, 2, 0, 
				0.2f, 0.1f, 0.1f, 0.0f, -0.1f, 0.1f, 0.0f, 0.00f, 0.05f));
		classes.put("great knight", new CharClass("Great Knight", 5, 1, 0, 0, -4, 0, 8, 0, 0, 
				0.3f, 0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.05f, -0.05f, 0.05f));
		classes.put("bow knight", new CharClass("Bow Knight", 2, 1, 0, 3, 1, 1, 1, 0, 0, 
				0.1f, 0.0f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, 0.0f, 0.05f));
		classes.put("dark knight", new CharClass("Dark Knight", 1, 1, 2, 2, -1, 0, 2, 3, 0, 
				0.1f, 0.05f, 0.1f, 0.0f, -0.05f, 0.0f, 0.05f, 0.1f, 0.05f));
		classes.put("holy knight", new CharClass("Holy Knight", 2, 1, 1, 2, -1, 0, 1, 4, 0, 
				0.1f, 0.0f, 0.1f, 0.0f, -0.05f, 0.1f, 0.05f, 0.1f, 0.1f));
		classes.put("war master", new CharClass("War Master", 3, 5, 0, 0, 2, 1, 1, 0, 0, 
				0.4f, 0.15f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.0f, 0.05f));
		classes.put("gremory", new CharClass("Gremory", 0, 0, 5, 2, 1, 0, 0, 4, 2, 
				0.1f, 0.0f, 0.1f, 0.1f, 0.0f, 0.0f, 0.0f, 0.05f, 0.1f));
		
		// DLC
		classes.put("trickster", new CharClass("Trickster", 0, 0, 0, 3, 4, 3, 0, 1, 0, 
				0.2f, 0.0f, 0.0f, 0.15f, 0.2f, 0.1f, 0.0f, 0.05f, 0.0f));
		classes.put("war monk", new CharClass("War Monk / Cleric", 3, 2, 0, 1, 0, 0, 1, 1, 0, 
				0.4f, 0.1f, 0.05f, 0.1f, 0.0f, 0.0f, 0.0f, 0.05f, 0.05f));
		classes.put("dark flier", new CharClass("Dark Flier", 0, 0, 0, 3, 4, 0, 0, 3, 0, 
				0.2f, 0.0f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, 0.1f));
		classes.put("valkyrie", new CharClass("Valkyrie", 1, 0, 4, 0, -2, 0, 0, 2, 2, 
				0.05f, 0.0f, 0.05f, 0.0f, -0.05f, 0.05f, 0.05f, 0.1f, 0.1f));
	}
	
	/**
	 * Trims leading and trailing whitespace
	 * Cannot trim middle whitespace because some classes have a space in their name
	 * @return formatted string
	 */
	private static String formatInput(String input) {
//		String output = "";
//		
//		input = input.toLowerCase();
//		for(char c : input.toCharArray()) {
//			if(c != ' ') {
//				output = output + c;
//			}
//		}
//		
//		return output;
		return input.toLowerCase();
	}
}
