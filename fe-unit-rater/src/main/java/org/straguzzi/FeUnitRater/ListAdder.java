package org.straguzzi.FeUnitRater;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for adding and subtracting the values of 2 numerical classes
 * 
 * All methods return ArrayLists
 * 
 * @author Scott Straguzzi
 */
public class ListAdder {

 	/**
 	 * Adds 2 lists together, lists must be the same size.
 	 * @param list1
 	 * @param list2
 	 * @return the sum of 2 lists, or null if they're different sizes
 	 */
 	public static List<Integer> addIntLists(List<Integer> list1, List<Integer> list2) {
 		if(list1.size() != list2.size()) {
 			return null; // ERROR case
 		}
 		
 		List<Integer> sum = new ArrayList<Integer>();
 		
 		for(int i = 0; i < list1.size(); i++) {
 			sum.add(list1.get(i) + list2.get(i));
 		}
 		
 		return sum;
 	}
 	
 	/**
 	 * Subtracts 2 lists
 	 * 
 	 * Remember, order matters here
 	 * 
 	 * @param list1
 	 * @param list2
 	 * @return difference of 2 lists, or null if they're different sizes
 	 */
 	public static List<Integer> subIntLists(List<Integer> list1, List<Integer> list2) {
 		for(int i = 0; i < list1.size(); i++) {
 			list2.set(i, list2.get(i) * -1);
 		}
 		
 		return addIntLists(list1, list2);
 	}
 	
 	/**
 	 * Adds 2 lists of floats
 	 * 
 	 * @param list1
 	 * @param list2
 	 * @return sum of 2 lists, or null if they're different sizes
 	 */
 	public static List<Float> addFloatLists(List<Float> list1, List<Float> list2) {
 		if(list1.size() != list2.size()) {
 			return null; // ERROR case
 		}
 		
 		List<Float> sum = new ArrayList<Float>();
 		
 		for(int i = 0; i < list1.size(); i++) {
 			sum.add(list1.get(i) + list2.get(i));
 		}
 		
 		return sum;
 	}
 	
 	/**
 	 * Subtracts 2 lists
 	 * 
 	 * Remember, order matters here
 	 * 
 	 * @param list1
 	 * @param list2
 	 * @return the difference of 2 lists
 	 */
 	public static List<Float> subFloatLists(List<Float> list1, List<Float> list2) {
 		for(int i = 0; i < list1.size(); i++) {
 			list2.set(i, list2.get(i) * -1);
 		}
 		
 		return addFloatLists(list1, list2);
 	}

}
