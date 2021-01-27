package org.straguzzi.FeUnitRater;

import java.util.*; // Mostly for List
import java.math.*;

/**
 * Basic class for any character.
 * 
 * Has base stats, character growth rates, a level, and a CharClass
 * 
 * TODO make sure it's a Java Bean so it can be serialized
 * Mostly gonna be getters and setters + a level up method for tracking growths
 * 
 * @author Scott Straguzzi
 * @version 11/24/2020
 */
public class Character {
	private String name;
	private int level;
	
	private List<Integer> curStats; // The character's personal stats, semi-user controlled
	private List<Float> avgStats; // The character's projected stats. Controlled by running the "level up" function
	private List<Float> charGrowths; // The character's personal growth rates, before class modifiers
	
	private CharClass currentClass; // Modifiers from the class are added to the above during calculations
	
	private List<Float> curGrowths; // charGrowths + classGrowths
	
	private List<ClassPath> classPath; // A collection of classes and levels for a character

	
	// TODO, some sort of way of tracking previous levels?
	
	/**
	 * Kept for making this class a Java Bean
	 */
 	public Character() {
		name = "";
		
		curStats = new ArrayList<Integer>();
		avgStats = new ArrayList<Float>();
		charGrowths = new ArrayList<Float>();
		curGrowths = new ArrayList<Float>();
		classPath = new ArrayList<ClassPath>();
	}
 	
 	/**
 	 * The LONG constructor to generate a character, need to give ALL info about the character to generate them.
 	 * 
 	 * All constructor info should be pretty self explanatory.
 	 * 
 	 * Give character growths as floats instead of integers (e.g. 55% growth would go in as .55);
 	 * 
 	 * @param name
 	 * @param charClass
 	 * @param baseLevel
 	 * @param hp
 	 * @param strength
 	 * @param magic
 	 * @param skill
 	 * @param speed
 	 * @param luck
 	 * @param def
 	 * @param res
 	 * @param hpGrowth
 	 * @param strGrowth
 	 * @param magGrowth
 	 * @param spdGrowth
 	 * @param lukGrowth
 	 * @param defGrowth
 	 * @param resGrowth
 	 */
 	public Character(String name, CharClass charClass, int baseLevel,
 			int hp, int strength, int magic, int skill, int speed, int luck, int def, int res, int charm,
 			float hpGrowth, float strGrowth, float magGrowth, float sklGrowth, float spdGrowth,
 			float lukGrowth, float defGrowth, float resGrowth, float chaGrowth) {
 		this.name = name;
 		this.currentClass = charClass;
 		this.level = baseLevel;
 		
 		curStats = new ArrayList<Integer>(9);
 		curStats.add(0, hp);
 		curStats.add(1, strength);
 		curStats.add(2, magic);
 		curStats.add(3, skill);
 		curStats.add(4, speed);
 		curStats.add(5, luck);
 		curStats.add(6, def);
 		curStats.add(7, res);
 		curStats.add(8, charm);
 		
 		charGrowths = new ArrayList<Float>(9);
 		charGrowths.add(0, hpGrowth);
 		charGrowths.add(1, strGrowth);
 		charGrowths.add(2, magGrowth);
 		charGrowths.add(3, sklGrowth);
 		charGrowths.add(4, spdGrowth);
 		charGrowths.add(5, lukGrowth);
 		charGrowths.add(6, defGrowth);
 		charGrowths.add(7, resGrowth);
 		charGrowths.add(8, chaGrowth);
 		
 		avgStats = new ArrayList<Float>(9);
 		for(int i = 0; i < curStats.size(); i++) {
 			avgStats.add(i, (float) curStats.get(i));
 		}
 		
 		curGrowths = ListAdder.addFloatLists(charGrowths, currentClass.getClassGrowths());
 		
 		classPath = new ArrayList<ClassPath>(6); // Assuming most characters can use 1 basic class, 2 beginner classes, 2 intermediate, 1 advanced, 1 master at most
 		classPath.add(new ClassPath(currentClass, level)); // Adds the character's base class to their ClassPath
 	}
 	
 	/**
 	 * Projects a character's stats at a given level. The calculation works similarly to PoR's fixed growth mode.
 	 * Stats are rounded to the nearest whole number.
 	 * 
 	 * Old, deprecated method. Replaced with a new project(int) which uses the ClassPath collection to account for character's class changes.
 	 * Kept mostly for my personal reference to help me make the new project method.
 	 * 
 	 * @param level to take the character to. Will not work if you try to level down
 	 * @return a List of the character's projected stats at a given level.
 	 */
 	public List<Integer> project_OLD(int level) {
 		List<Integer> leveledStats = new ArrayList<Integer>();
 		
 		// Will not project backwards
 		if(this.level > level) {
 			return null;
 		}
 		else if(this.level == level) {
 			return curStats; // Projecting to the same level are the same stats...
 		}
 		else { // Main algorithm. Adds the growth rate to each stat for each level, then rounds the resulting floats to an int
 			int roundedStat = 0;
 			int levels = level - this.level;
 			
 			for(int i = 0; i < this.curStats.size(); i++) {
 				roundedStat = Math.round(curStats.get(i) + (curGrowths.get(i) * levels));
 				leveledStats.add(i, roundedStat);
 			}
 		}
 		
 		return leveledStats;
 	}
 	
 	public List<Integer> project(int level) {
 		List<Integer> leveledStats = new ArrayList<Integer>();
 		
 		// Will not project backwards
 		if(this.level > level) {
 			return null;
 		}
 		else if(this.level == level) {
 			return curStats; // Projecting to the same level are the same stats...
 		}
 		else { // Main algorithm
 			// Makes a list of the unit's stats as a float
 			List<Float> floatStats = new ArrayList<Float>();
 			for(int i = 0; i < curStats.size(); i++) {
 				floatStats.add((float) curStats.get(i));
 			}
 			
 			
 			Iterator<ClassPath> it = classPath.iterator();
 			ClassPath nextClass = it.next(); // Assumes there is at least one class in a character's class path, enforced by giving commoner by default

 			for(int i = this.level; i < level; i++) {
 				if((nextClass.getLevel() == i) && it.hasNext()) { // Changes the character's class as designated by their class path
 					changeClass(nextClass.getCharClass());
 					nextClass = it.next();
 				}
 				
 				// Changes the leveledStats according to the character's growth rates
 				floatStats = ListAdder.addFloatLists(floatStats, curGrowths);
 			}
 			
 			// Consider doing some sort of rounding, >.5 rounds up and <.5 rounds down
 			// Perhaps in a future version?
 			for(int i = 0; i < floatStats.size(); i++) {
 	 			leveledStats.add(Math.round(floatStats.get(i)));
 	 		}
 			
 		// Adds class stat bonuses to the shown stats projection
 			leveledStats = ListAdder.addIntLists(leveledStats, nextClass.getCharClass().getClassBases());
 		}
 		
 		
 		
 		return leveledStats;
 	}
 	
 	/**
 	 * Changes the caracter's class to the new class
 	 * 
 	 * Also changes the character's current stats and growths to reflect their new class
 	 * 
 	 * @param newClass
 	 */
 	public void changeClass(CharClass newClass) {
 		// Removes old class
 		curStats = ListAdder.subIntLists(curStats, currentClass.getClassBases());
 		curGrowths = ListAdder.subFloatLists(curGrowths, currentClass.getClassGrowths());
 		
 		// Adds new class
 		curStats = ListAdder.addIntLists(curStats, newClass.getClassBases());
 		curGrowths = ListAdder.addFloatLists(curGrowths, newClass.getClassGrowths());
 		
 		currentClass = newClass;
 	}
 	
 	/**
 	 * Adds a premade ClassPath object to the Character
 	 * @param newClass is a ClassPath object
 	 */
 	public void addClassChange(ClassPath newClass) {
 		classPath.add(newClass);
 	}
 	
 	public void addClassChange(CharClass charClass, int level) {
 		classPath.add(new ClassPath(charClass, level));
 	}
 	
 	/**
 	 * Returns character's info and stats in an easily readable format
 	 * 
 	 * First row is Name, level, class
 	 * Second row is headers for stats
 	 * Third row is current stats
 	 * Fourth row is current growths
 	 */
 	public String toString() {
 		String output = "";
 		
 		// Add headers
 		output = name + ", Level " + level + " " + currentClass.getName() + "\n";
 		output = output + "HP\tStr\tMag\tDex\tSpd\tLuk\tDef\tRes\tCha\n";
 		
 		for(int i = 0; i < curStats.size(); i++) {
 			output = output + curStats.get(i) + "\t";
 		}
 		output = output + "\n";
 		
 		for(int i = 0; i < curGrowths.size(); i++) {
 			output = output + curGrowths.get(i) + "\t";
 		}
 		output = output + "\n";
 		
 		return output;
 	}

}
