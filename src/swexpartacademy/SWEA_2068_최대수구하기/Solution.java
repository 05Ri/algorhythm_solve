package swexpartacademy.SWEA_2068_최대수구하기;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for(int n = 1; n <= t; n++) {
			
			int max = 0;
			
			for(int i = 0; i < 10; i++) {
				
				int temp = sc.nextInt();
				
				if(max < temp) {
					max = temp;
				}
			}
			
			System.out.println("#" + n + ' ' + max);
		}
		
		sc.close();
	}
}

