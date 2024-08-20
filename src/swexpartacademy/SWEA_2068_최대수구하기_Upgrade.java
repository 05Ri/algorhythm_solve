package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2068_최대수구하기_Upgrade {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= testCase; t++) {
			int max = Integer.MIN_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			while (st.hasMoreTokens()) {
				int next = Integer.parseInt(st.nextToken());
				max = Math.max(max, next);
			}
			
			System.out.println("#" + t + ' ' + max);
		}
	}
}

