package swexpartacademy;


import java.util.Scanner;

public class SWEA_1974_스도쿠_검증 {

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);

		int[][] sudoku_matrix = new int[9][9];
		int sudoku_matrix_sum = 0;

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			
			int check = 1;
			
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sudoku_matrix[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sudoku_matrix_sum += sudoku_matrix[i][j];
				}

				if (sudoku_matrix_sum != 45) {
					check = 0;
					break;
				}

				sudoku_matrix_sum = 0;
			}

			for (int j = 0; j < 9; j++) {
				for (int i = 0; i < 9; i++) {
					sudoku_matrix_sum += sudoku_matrix[i][j];
				}

				if (sudoku_matrix_sum != 45) {
					check = 0;
					break;
				}

				sudoku_matrix_sum = 0;
			}

			for (int n = 0; n < 9; n += 3) {
				for (int m = 0; m < 9; m += 3) {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							sudoku_matrix_sum += sudoku_matrix[i + n][j + m];
						}
					}
					if (sudoku_matrix_sum != 45) {
						check = 0;
						break;
					}
					sudoku_matrix_sum = 0;
				}
				sudoku_matrix_sum = 0;
			}

			System.out.println("#" + test_case + " " + check);
		}
	}
}