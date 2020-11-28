package org.straguzzi.FeUnitRater;

import java.util.*;

/**
 * Contains info on the character classes
 * 
 * Classes have a name, base stat modifiers, and growth rate modifiers
 * 
 * Mostly just getters and setters
 * 
 * @author scott
 *
 */
public class CharClass {
	private String name;
	
	private List<Float> classGrowths;
	private List<Integer> classBases;
	
	/**
	 * Defaults characters to commoner (0s for bases and growths)
	 */
	public CharClass() {
		name = "Commoner";
		classGrowths = new ArrayList<Float>(9);
		classBases = new ArrayList<Integer>(9);
		
		for(int i = 0; i < classBases.size(); i++) {
			classGrowths.add(0.0f);
			classBases.add(0);
		}
	}

	/**
	 * TODO: Constructor for any class. Overall a similar constructor to the one in Character
	 * 
	 * @param name
	 * @param str
	 * @param mag
	 * @param skl
	 * @param spd
	 * @param luk
	 * @param def
	 * @param res
	 * @param strGrowth
	 * @param magGrowth
	 * @param sklGrowth
	 * @param spdGrowth
	 * @param lukGrowth
	 * @param defGrowth
	 * @param resGrowth
	 */
	public CharClass(String name, int hp, int str, int mag, int skl, int spd, int luk, int def, int res, int cha,
			float hpGrowth, float strGrowth, float magGrowth, float sklGrowth, float spdGrowth, float lukGrowth, float defGrowth, float resGrowth, float chaGrowth) {
		this.name = name;
		
		classBases = new ArrayList<Integer>(9);
		classBases.add(0, hp);
		classBases.add(1, str);
		classBases.add(2, mag);
		classBases.add(3, skl);
		classBases.add(4, spd);
		classBases.add(5, luk);
		classBases.add(6, def);
		classBases.add(7, res);
		classBases.add(8, cha);
		
		classGrowths = new ArrayList<Float>(9);
		classGrowths.add(0, hpGrowth);
		classGrowths.add(1, strGrowth);
		classGrowths.add(2, magGrowth);
		classGrowths.add(3, sklGrowth);
		classGrowths.add(4, spdGrowth);
		classGrowths.add(5, lukGrowth);
		classGrowths.add(6, defGrowth);
		classGrowths.add(7, resGrowth);
		classGrowths.add(8, chaGrowth);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Float> getClassGrowths() {
		return classGrowths;
	}

	public void setClassGrowths(List<Float> classGrowths) {
		this.classGrowths = classGrowths;
	}

	public List<Integer> getClassBases() {
		return classBases;
	}

	public void setClassBases(List<Integer> classBases) {
		this.classBases = classBases;
	}
	
	

}
