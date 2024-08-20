package swexpartacademy;

import java.util.*;public class SWEA_1970_쉬운_거스름돈{public static void main(String[]a){Scanner s=new Scanner(System.in);int T=s.nextInt();int[] m={50000,10000,5000,1000,500,100,50,10};for(int t=1;t<=T;t++){int c=s.nextInt();System.out.println("#"+t);for(int i:m) {System.out.print(c/i+" ");c%=i;}System.out.println();}}}