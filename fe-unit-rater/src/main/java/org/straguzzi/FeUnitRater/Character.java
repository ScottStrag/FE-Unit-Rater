package org.straguzzi.FeUnitRater;

import java.util.*; // Mostly for List

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
	private List<Integer> charGrowths; // The character's personal growth rates, before class modifiers
	
	private CharClass currentClass; // Modifiers from the class are added to the above during calculations
	
	// TODO, some sort of way of tracking previous levels?
	
	/**
	 * Kept for making this class a Java Bean
	 */
 	public Character() {
		name = "";
		
		curStats = new ArrayList<Integer>();
		avgStats = new ArrayList<Float>();
		charGrowths = new ArrayList<Integer>();
	}
 	
 	

}
