import java.util.Arrays;
import java.util.Random;

//Taylor Van Aken
//9/21/2022
//Homework 4

/**
* Implementation of Counting Sort and Randomized Quickselect
* @author Taylor Van Aken
*/
public class Main {

	/**
	 * Sorts a list by counting the # of times each number occurs,
	 * then rebuilds the list in order from least to greatest.
	 * @param arr input array
	 * @param out output array
	 * @param k largest integer in input array
	 */
	private static void countingSort(int[] arr, int[] out, int k) {

		k = findMax(arr);
		int[] temp = new int[k + 1];
		
		for (int i = 0; i < arr.length; i++) {
			
			temp[arr[i]]++;
		}
		
		for (int i = 1; i <= k; i++) {
			
			temp[i] += temp[i-1];
		}
		
		for (int i = arr.length - 1; i >= 0; i--) {
			
			out[temp[arr[i]]-1] = arr[i];
			temp[arr[i]]--;
		}
	}
	
	/**
	 * Uses random partition to find the i'th smallest number from the input array.
	 * @param arrT input array
	 * @param start starting index to search from
	 * @param end ending index to search to
	 * @param i Order statistic being searched for
	 * @return the i'th smallest number in the list
	 */
	private static int randomizedQuickselect(int [] arrT, int start, int end, int i) {
		
		Random rand = new Random();
		int[] tempArr = Arrays.copyOf(arrT, arrT.length);
		
		if (start == end) {
			return tempArr[start];
		}
		
		int z  = rand.nextInt(end - start) + start;
		swap(tempArr, end, z);
		int q = partition(tempArr, start, end);
		int k = q - start + 1;
		
		if (i == k) {
			
			return tempArr[q];
		} else if (i < k) {
			
			return randomizedQuickselect(tempArr, start, q-1, i);
		} else {
			
			return randomizedQuickselect(tempArr, q+1, end, i-k);
		}
	}
	
	/**
	 * Chooses a pivot index and swaps integers lower than pivot to the front of array
	 * @param arr the array being passed in
	 * @param start start index of array
	 * @param end end index of array
	 * @return pivot index going to be used as a partition in array
	 */
	private static int partition(int[] arr, int start, int end) {
		
		int piv = arr[end];
		int i = start - 1;
		
		for (int j = start; j < end; j++) {
			
			if (arr[j] < piv) {
				
				i++;
				swap(arr, i , j);
			}
		}
		
		swap(arr, i+1, end);
		return i+1;
	}
	
	/**
	 * Swaps two indexes of an array
	 * @param arr the array being passed in
	 * @param i index being swapped with j
	 * @param j index being swapped with i
	 */
	private static void swap(int[] arr, int i, int j) {
		
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/**
	 * Finds the largest number in a list
	 * @param arr input array
	 * @return largest number within arr
	 */
	private static int findMax(int[] arr) {

		int max = arr[0];
		
		for (int i = 1; i < arr.length; i++) {
			
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}
	
	/**
	 * Main testing
	 * @param args
	 */
	public static void main(String[] args) {

		int k = 0;
		
		int[] arr1 = {1,7,4,5,2,2,6,7,9,1,7,4};
		int[] arr2 = {12,34,52,101,43,23,12,52};
		int[] arr3 = {0,0,9,4,2,3,102,3,0};
		int[] arr4 = {1000,2000,1,3,400,450};
		int[] arr5 = {1,9,2,8,3,7,4,4,4,4,4,4,4,5,6,7,0};
		
		int[] out1 = new int[arr1.length];
		int[] out2 = new int[arr2.length];
		int[] out3 = new int[arr3.length];
		int[] out4 = new int[arr4.length];
		int[] out5 = new int[arr5.length];
		countingSort(arr1, out1, k);
		countingSort(arr2, out2, k);
		countingSort(arr3, out3, k);
		countingSort(arr4, out4, k);
		countingSort(arr5, out5, k);
		System.out.println("arr1 original: " + Arrays.toString(arr1));
		System.out.println("arr1 countSort: " + Arrays.toString(out1));
		System.out.println();
		System.out.println("arr2 original: " + Arrays.toString(arr2));
		System.out.println("arr2 countSort: " + Arrays.toString(out2));
		System.out.println();
		System.out.println("arr3 original: " + Arrays.toString(arr3));
		System.out.println("arr3 countSort: " + Arrays.toString(out3));
		System.out.println();
		System.out.println("arr4 original: " + Arrays.toString(arr4));
		System.out.println("arr4 countSort: " + Arrays.toString(out4));
		System.out.println();
		System.out.println("arr5 original: " + Arrays.toString(arr5));
		System.out.println("arr5 countSort: " + Arrays.toString(out5));
		System.out.println();
		System.out.println("randomQuickselect arr1 for order stat 3: " + randomizedQuickselect(arr1, 0, arr1.length - 1, 3));
		System.out.println("randomQuickselect arr2 for order stat 1: " + randomizedQuickselect(arr2, 0, arr2.length - 1, 1));
		System.out.println("randomQuickselect arr3 for order stat 3: " + randomizedQuickselect(arr3, 0, arr3.length - 1, 3));
		System.out.println("randomQuickselect arr4 for order stat 2: " + randomizedQuickselect(arr4, 0, arr4.length - 1, 2));
		System.out.println("randomQuickselect arr5 for order stat 8: " + randomizedQuickselect(arr5, 0, arr5.length - 1, 8));
		System.out.println();
		System.out.println("arr1 original after quickselect: " + Arrays.toString(arr1));
		System.out.println("arr2 original after quickselect: " + Arrays.toString(arr2));
		System.out.println("arr3 original after quickselect: " + Arrays.toString(arr3));
		System.out.println("arr4 original after quickselect: " + Arrays.toString(arr4));
		System.out.println("arr5 original after quickselect: " + Arrays.toString(arr5));
	}
}
