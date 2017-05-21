import java.util.HashMap;
import java.util.Map;

/**
 * @title Coin Changer
 * @author Josiah Putman
 * @version May 9, 2017
 * @purpose to solve the coin changer problem
 */
public class CoinChanger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println("number of ways = " + makeChange(15, new int[] {5, 1}, 0));
		//System.out.println("number of ways = " + makeChange(27, new int[] {25, 10, 5, 1}, 0));
		System.out.println("number of ways = " + makeChange(79, new int[] {50, 25, 10, 5, 1}, 0));
	}
	
	public static long makeChange(int total, int[] coins, int index){
		if (coins.length == 0) return 0;
		return makeChange(total, coins, 0, new HashMap<String, Long>());
	}
	
	/**
	 * Recursively determines the number of ways to make change using a list of coins types in descender order of value
	 * @param total
	 * @param coinValues, sorted in descending order
	 * @param index
	 * @return number of ways a total can be reached using a set of coin values
	 */
	public static long makeChange(int total, int[] coins, int index, Map<String, Long> memo){
		System.out.println("Making change for " + total + " using " + arrayToString(coins) + " starting at index " + index);
		
		if(index >= coins.length) return 0;
		//base case: there is only 1 type of coin to use, so there is only 1 way to reach the total
		if(coins.length - index == 1) { //if there is only 1 coinValue remaining in our list
			//if this last coin can be used to make the total
			if(total % coins[index] == 0)
				return 1;
			else return 0;
		}
		
		//base case: we have calculated this case before
		String key = total + "=" + index;
		if(memo.containsKey(key)) return memo.get(key);
		
		//otherwise recurse
		long numWays = 0;
		int counter = 0;
		while(coins[index] * counter <= total ) { //while this multiple of the coin fits inside the total
			numWays += makeChange(total - coins[index] * counter, coins, index + 1, memo);
			counter++;
		}
		memo.put(key, numWays);
		return numWays;
	}
	
	/**
	 * Converts an int array into a String
	 * @param array
	 * @return
	 */
	private static String arrayToString(int[] array) {
		String result = "[";
		for(int i : array) result += i + ", ";
		return result.substring(0, result.length()-2) + "]";
	}
}
