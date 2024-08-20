package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1984_중간_평균값_구하기_Upgrade {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> arr = new ArrayList<>();
		
		int test_case = Integer.parseInt(bf.readLine());
		float avg = 0;
		
		for(int t = 1; t <= test_case; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			while (st.hasMoreTokens()) {
				arr.add(Integer.parseInt(st.nextToken()));
			}
			
			Collections.sort(arr);
			
			int sum = 0;
			for(int i = 1; i < arr.size() - 1; i++) {
				sum += arr.get(i);
			}
			
			avg = (float) sum / (arr.size() - 2);
			avg = Math.round(avg);
			System.out.printf("#%d %d\n", t, (int) avg);
			arr.clear();
		}
	}
}