package swexpartacademy.SWEA_2058_자릿수_더하기;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int num_len = String.valueOf(num).length();
        int tot = 0;
         
        for (int i = 0; i < num_len; i++) {
            int temp = num % 10;
            num = num / 10;
            tot += temp;
        }
 
        System.out.println(tot);
	}
}
