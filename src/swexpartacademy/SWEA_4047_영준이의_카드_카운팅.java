package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_4047_영준이의_카드_카운팅 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			sb = new StringBuilder();
			int[] cardS = new int[14];
			int[] cardD = new int[14];
			int[] cardH = new int[14];
			int[] cardC = new int[14];
			
			String str = br.readLine();
			
			out: for (int i = 0; i < str.length(); i += 3) {
				char type = str.charAt(i);
				String numStr = "";
				numStr += str.charAt(i + 1);
				numStr += str.charAt(i + 2);
				int num = Integer.parseInt(numStr);
				
				switch (type) {
				case 'S':
					if (cardS[num] == 1) {
						sb.append("ERROR");
						break out;
					}
					cardS[num] = 1;
					break;
				case 'D':
					if (cardD[num] == 1) {
						sb.append("ERROR");
						break out;
					}
					cardD[num] = 1;
					break;
				case 'H':
					if (cardH[num] == 1) {
						sb.append("ERROR");
						break out;
					}
					cardH[num] = 1;
					break;
				case 'C':
					if (cardC[num] == 1) {
						sb.append("ERROR");
						break out;
					}
					cardC[num] = 1;
					break;
				}
			}
			
			int cntS = 0, cntD = 0, cntH = 0, cntC = 0;
			
			for (int i = 1; i <= 13; i++) {
				if (cardS[i] == 0)
					cntS++;
				if (cardD[i] == 0)
					cntD++;
				if (cardH[i] == 0)
					cntH++;
				if (cardC[i] == 0)
					cntC++;
			}
			
			if (sb.length() == 0) {
				sb.append(cntS).append(" ").append(cntD).append(" ").append(cntH).append(" ").append(cntC);
			}
			
			System.out.printf("#%d %s\n", tc, sb);
		}
	}
}
