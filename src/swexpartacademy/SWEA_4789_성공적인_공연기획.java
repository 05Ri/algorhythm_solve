package swexpartacademy;

import java.util.Scanner;

public class SWEA_4789_성공적인_공연기획 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		for (int t = 1; tc <= 10; t++) {
			
			String st = sc.next();
			
			char[] ch = st.toCharArray();
			int L = ch.length;
			
			int[] aud = new int [L];
			for (int i = 0; i < L; i++) {				
				aud[i] = ch[i] - '0';
			}
			
			int cnt = 0;
			int check = 0;
			int buy = 0;
			
			while (aud[check] > L) {
				
			}
		}
	}
}
