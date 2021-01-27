package org.straguzzi.FeUnitRater;

/**
 * Wrapper class for a character's class path. A character has a class path which represents the level they change class and what class they become.
 * 
 * @author Scott Straguzzi
 * @version 1/26/2021
 */
public class ClassPath {
	CharClass charClass;
	int level;
	
	/**
	 * Blank constructor
	 * 
	 * Defaults a unit to starting as a level 1 commoner
	 */
	public ClassPath() {
		charClass = new CharClass("Commoner", 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		level = 1;
	}
	
	/**
	 * @param charClass the character will change into
	 * @param level that the character becomes that class
	 */
	public ClassPath(CharClass charClass, int level) {
		this.charClass = charClass;
		this.level = level;
	}

	public CharClass getCharClass() {
		return charClass;
	}

	public void setCharClass(CharClass charClass) {
		this.charClass = charClass;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
