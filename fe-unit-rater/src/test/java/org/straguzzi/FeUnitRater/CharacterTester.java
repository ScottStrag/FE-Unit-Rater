package org.straguzzi.FeUnitRater;

import java.util.*;

/**
 * Tester class for the Character and CharClass classes.
 * 
 * Unit data is pulled from: https://serenesforest.net/three-houses/characters/base-stats/
 * 							 https://serenesforest.net/three-houses/characters/growth-rates/
 * 							 https://serenesforest.net/three-houses/classes/base-stats/
 * 							 https://serenesforest.net/three-houses/classes/growth-rates/
 * 
 * Currently only uses Byleth
 * 
 * @author scott
 *
 */
public class CharacterTester {

	public CharacterTester() {
		// TODO Auto-generated constructor stub
	}
	
	private static void testSumAndDiff() {
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> badList1 = new ArrayList<Integer>();
		
		List<Float> list3 = new ArrayList<Float>();
		List<Float> list4 = new ArrayList<Float>();
		List<Float> badList2 = new ArrayList<Float>();
		
		for(int i = 0; i < 10; i++) {
			list1.add(i);
			list2.add(10 - i);
		}
		badList1.add(5); // Size is 1 to check returning null
		
		List<Integer> sumList1 = ListAdder.addIntLists(list1, list2);
		List<Integer> sumList2 = ListAdder.addIntLists(list1, badList1);
		
		System.out.println("The sum of list1 and list2 should be all tens: " + sumList1);
		System.out.println("The sum of list1 and badList1 should be null: " + sumList2);
		
		List<Integer> diffList1 = ListAdder.subIntLists(list1, list2);
		System.out.println("The difference of the lists should be all zeros: " + diffList1);
		
		for(int i = 0; i < 10; i++) {
			list3.add((float) i);
			list4.add((float) (10 - i));
		}
		badList2.add(5.0f);
		
		List<Float> sumList3 = ListAdder.addFloatLists(list3, list4);
		List<Float> sumList4 = ListAdder.addFloatLists(list3, badList2);
		
		System.out.println("The sum of list3 and list4 should be all 10.0f: " + sumList3);
		System.out.println("The sum of list3 and badList2 should be null: " + sumList4);
		
		List<Float> diffList2 = ListAdder.subFloatLists(list3, list4);
		System.out.println("The difference of the lists should be all zeros: " + diffList2);
	}
	
	/**
	 * Creates a level 1 byleth based on their base stats, prints their info,
	 * and then makes a projection of their stats on average at level 5
	 */
	private static void testCharProject() {
		CharClass testClass = new CharClass("Commoner", 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		Character test = new Character("Byleth", testClass, 1,
										27, 13, 6, 9, 8, 8, 6, 6, 7,
										.45f, .45f, .35f, .45f, .45f, .45f, .35f, .30f, .45f);
		
		System.out.println(test); // Should print out info about Byleth at level 1 (See link above for source on info)
		
		System.out.println("On average, after 5 levels in the Commoner class, Byleth's stats look like this:\n" + test.project(5));
		
	}

	public static void main(String[] args) {
		System.out.println("Testing the list adding methods...");
		testSumAndDiff();
		
		System.out.println("\n");
		
		System.out.println("Testing the character creation and projection adding methods...");
		testCharProject();
	}
}
