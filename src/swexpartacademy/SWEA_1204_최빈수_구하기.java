package swexpartacademy;

import java.util.Arrays;
import java.util.Scanner;

public class SWEA_1204_최빈수_구하기 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
        int[] K = new int[1000];
        int temp_num = 0;
        int count = 0;
        int highFreqNum = 0;
        int highCount = 0;
        
		for (int test_case = 1; test_case <= T; test_case++) {
			int scoreBundle = sc.nextInt();
			
            for(int i = 0; i < 1000; i++) {
            	K[i] = sc.nextInt();
            }
            
            Arrays.sort(K);
            
            for(int i = 0; i < 1000; i++) {
            	if(temp_num != K[i]) {
            		if(highCount <= count) {
            			highFreqNum = temp_num;
            			highCount = count;
            			temp_num = K[i];
            			count = 0;
            		} else {
            			count = 0;
            			temp_num = K[i];
            		}
            	} else {
            		count++;
            	}
            }
            System.out.println("#" + test_case + " "+ highFreqNum);
            temp_num = 0;
            count = 0;
            highFreqNum = 0;
            highCount = 0;
        }
	}
}
