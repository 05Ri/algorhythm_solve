package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16935_배열_돌리기_3 {
	// 세로, 가로, 연산 횟수
	static int N, M, R;
	// 가로, 세로의 길이가 변할 경우 갱신하여 대신 쓴다.
	static int a, b;
	// 배열
	static int[][] arr;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		while (st.hasMoreTokens()) {
			a = arr.length;
			b = arr[0].length;
			
			int opNum = Integer.parseInt(st.nextToken());
			
			switch (opNum) {
			case 1:
				upsideDown();
				break;
			case 2:
				LRInversion();
				break;
			case 3:
				rightQuarter();
				break;
			case 4:
				leftQuarter();
				break;
			case 5:
				quadrantRightRotate();
				break;
			case 6:
				quadrantLeftRotate();
				break;
			}
		}
		
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr[r].length; c++) {
				sb.append(arr[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static void upsideDown() {
		for (int r = 0; r < a / 2; r++) {
			int[] tmp = arr[r];
			arr[r] = arr[(a - 1) - r];
			arr[(a - 1) - r] = tmp;
		}
	}

	private static void LRInversion() {
		for (int r = 0; r < a; r++) {
			for (int c = 0; c < b / 2; c++) {
				int tmp = arr[r][c];
				arr[r][c] = arr[r][(b - 1) - c];
				arr[r][(b - 1) - c] = tmp;
			}
		}
	}

	private static void rightQuarter() {
		int a = arr.length;
		int b = arr[0].length;
		
		int[][] temp = new int[b][a];
		
		for (int r = 0; r < b; r++) {
			for (int c = 0; c < a; c++) {
				temp[r][c] = arr[(a - 1) - c][r];
			}
		}
		
		arr = new int[b][a];
		
		for (int r = 0; r < b; r++) {
			for (int c = 0; c < a; c++) {
				arr[r][c] = temp[r][c];
			}
		}
	}

	private static void leftQuarter() {
		int a = arr.length;
		int b = arr[0].length;
		
		int[][] temp = new int[b][a];
		
		for (int r = 0; r < b; r++) {
			for (int c = 0; c < a; c++) {
				temp[r][c] = arr[c][(b - 1) - r];
			}
		}
		
		arr = new int[b][a];
		
		for (int r = 0; r < b; r++) {
			for (int c = 0; c < a; c++) {
				arr[r][c] = temp[r][c];
			}
		}
	}

	private static void quadrantRightRotate() {
		int halfA = a / 2;
		int halfB = b / 2;
		
		int temp = 0;
		
		for (int r = 0; r < halfA; r++) {
			for (int c = 0; c < halfB; c++) {
				temp = arr[r][c];
				arr[r][c] = arr[r + halfA][c];
				arr[r + halfA][c] = arr[r + halfA][c + halfB];
				arr[r + halfA][c + halfB] = arr[r][c + halfB];
				arr[r][c + halfB] = temp;
			}
		}
	}

	private static void quadrantLeftRotate() {
		int halfA = a / 2;
		int halfB = b / 2;
		
		int temp = 0;
		
		for (int r = 0; r < halfA; r++) {
			for (int c = 0; c < halfB; c++) {
				temp = arr[r][c];
				arr[r][c] = arr[r][c + halfB];
				arr[r][c + halfB] = arr[r + halfA][c + halfB];
				arr[r + halfA][c + halfB] = arr[r + halfA][c];
				arr[r + halfA][c] = temp;
			}
		}		
	}

}
