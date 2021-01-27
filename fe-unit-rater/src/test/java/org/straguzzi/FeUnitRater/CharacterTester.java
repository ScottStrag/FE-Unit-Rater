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
	public static void main(String[] args) {
//		System.out.println("Testing the list adding methods...");
//		testSumAndDiff();
//		
//		System.out.println("\n");
		
		System.out.println("Testing the character creation and projection adding methods...");
		testCharProject();
		System.out.println("\n");
		ingridTest();
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

		Character byleth = new Character("Byleth", testClass, 1,
										27, 13, 6, 9, 8, 8, 6, 6, 7,
										.45f, .45f, .35f, .45f, .45f, .45f, .35f, .30f, .45f);
		
		ClassPath myrm = new ClassPath(new CharClass("Myrmadon",
													 0, 0, 0, 0, 1, 0, 0, 0, 0,
													 0.1f, 0.0f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, -.05f, 0.05f),
										5);
		ClassPath merc = new ClassPath(new CharClass("Mercenary",
				 									 1, 1, 0, 0, 1, 0, 0, 0, 0,
				 									 0.2f, .05f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, -0.05f, 0.05f),
										10);
		ClassPath SM = new ClassPath(new CharClass("Swordmaster",
				 									1, 2, 0, 1, 4, 0, 1, 0, 0,
				 									0.25f, 0.10f, 0.0f, 0.0f, 0.20f, 0.0f, 0.0f, -5.0f, 5.0f),
				 						20);
		
		byleth.addClassChange(myrm);
		byleth.addClassChange(merc);
		byleth.addClassChange(SM);
		
		System.out.println(byleth); // Should print out info about Byleth at level 1 (See link above for source on info)
		
		System.out.println("On average, after 25 levels in the swordsman classline, Byleth's stats look like this:\n" + byleth.project(25));
		
	}
	/**
	 * Tests ingrid from a level 1 commoner to a level 40 falcon knight
	 * 
	 * 4 levels in noble
	 * 5 levels in soldier
	 * 10 levels in peg knight
	 * 10 levels in paladin
	 * 10 levels in falcon knight
	 */
	private static void ingridTest() {
		CharClass testClass = new CharClass("Noble", 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.05f);
		
		Character ingrid = new Character("Ingrid", testClass, 1,
										27, 8, 6, 6, 8, 6, 5, 8, 8,
										0.4f, 0.35f, 0.35f, 0.4f, 0.6f, 0.45f, 0.3f, 0.4f, 0.45f);
		
		ClassPath soldier = new ClassPath(new CharClass("Soldier", 0, 0, 0, 1, 0, 0, 0, 0, 0,
														 0.1f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f),
										  5);
		ClassPath peg = new ClassPath(new CharClass("Pegasus Knight", 0, 0, 0, 2, 3, 0, 0, 2, 0,
													 0.15f, 0.0f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.05f, 0.1f),
									  10);
		ClassPath paladin = new ClassPath(new CharClass("Paladin", 2, 2, 0, 2, -1, 0, 2, 2, 0,
														0.3f, 0.1f, 0.0f, 0.05f, -0.1f, 0.05f, 0.05f, 0.05f, 0.05f),
										  20);
		ClassPath falcon = new ClassPath(new CharClass("Falcon Knight", 0, 1, 0, 4, 5, 0, 0, 4, 0,
														0.3f, 0.1f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.05f, 0.1f),
										 30);
		
		ingrid.addClassChange(soldier);
		ingrid.addClassChange(peg);
		ingrid.addClassChange(paladin);
		ingrid.addClassChange(falcon);
		
		System.out.println(ingrid); // Should print out info about Byleth at level 1 (See link above for source on info)
		
		System.out.println("On average, a level 40 Ingrid who went through 5 levels of Soldier, 10 levels of Pegasis Knight, 10 levels of Paladin, and 10 levels of Falcon knight will have these stats:\n" + ingrid.project(40));
	}

	
}
