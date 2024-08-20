package swexpartacademy;

import java.util.Scanner;
import java.util.Arrays;

public class SWEA_2063_중간값_찾기_선택정렬 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] arr = new int[n];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 선택 정렬
		
		for(int i = 0; i < arr.length; i++) {
			int min = Integer.MAX_VALUE;
			int minOfIdx = 0;
			for (int j = i; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j];
					minOfIdx = j;
				}
			}
			arr[minOfIdx] = arr[i];
			arr[i] = min;
		}
		
		System.out.println(arr[n / 2]);
	}
}
