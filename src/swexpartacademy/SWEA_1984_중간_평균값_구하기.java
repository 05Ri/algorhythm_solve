package swexpartacademy;

import java.util.Arrays;
import java.util.Scanner;

public class SWEA_1984_중간_평균값_구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		int[] arr = new int[10];
		float avg = 0;
		
		
		for(int t = 1; t <= test_case; t++) {
			
			for(int i = 0; i < arr.length; i++) {
				arr[i] = sc.nextInt();
			}
			
			Arrays.sort(arr);
			
			int sum = 0;
			for(int i = 1; i < arr.length - 1; i++) {
				sum += arr[i];
			}
			
			avg = (float) sum / (arr.length - 2);
			avg = Math.round(avg);
			System.out.printf("#%d %d\n", t, (int) avg);
		}
	}
}