package swexpartacademy;

public class SWEA__종이붙이기 {
	public static void main(String[] args) {

	}

	public static int paperCheck(int n) {
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else
			return paperCheck(n - 1) + 2 * paperCheck(n - 2);
	}
}
