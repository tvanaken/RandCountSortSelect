
public class Main {

	public static void main(String[] args) {

		int k = 0;
		
		int[] arr1 = {1,7,4,5,2,2,6,7,9,1,7,4};
		int[] arr2 = {12,34,52,101,43,23,12,52};
		
		int[] out1 = new int[arr1.length];
		countingSort(arr1, out1, k);
		System.out.println(out1.toString());
	}

	private static void countingSort(int[] arr, int[] out, int k) {

		k = findMax(arr);
		int[] temp = new int[k];
		
		for (int i = 0; i < arr.length; i++) {
			
			temp[arr[i]]++;
		}
		
		for (int i = 1; i < k; i++) {
			
			temp[i] = temp[i] + temp[i-1];
		}
		
		for (int i = arr.length; i > 0; i--) {
			
			out[temp[arr[i]]] = arr[i];
			temp[arr[i]]--;
		}	
	}

	private static int findMax(int[] arr) {

		int max = arr[0];
		
		for (int i = 1; i < arr.length; i++) {
			
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}
}
