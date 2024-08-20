package swexpartacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2382_미생물_격리 {
	// 셀의 개수, 격리 시간, 미생물 군집의 개수, 남은 미생물들의 수
	static int N, M, K, microbeCnt;
	// 미생물 군집들의 정보 배열을 담을 리스트
	static List<int[]> list = new ArrayList<>();

	// 방향 델타 (상: 1, 하: 2, 좌: 3, 우: 4)
	static final int[] dr = { 0, -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// 리스트 초기화
			list.removeAll(list);
			// 남은 미생물 수 초기화
			microbeCnt = 0;

			// 조건 입력 받기
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			for (int $ = 0; $ < K; $++) {
				st = new StringTokenizer(br.readLine());

				int[] microbialColonyInfo = new int[4];
				// 세로위치
				microbialColonyInfo[0] = Integer.parseInt(st.nextToken());
				// 가로위치
				microbialColonyInfo[1] = Integer.parseInt(st.nextToken());
				// 미생물 수
				microbialColonyInfo[2] = Integer.parseInt(st.nextToken());
				// 이동방향
				microbialColonyInfo[3] = Integer.parseInt(st.nextToken());

				list.add(microbialColonyInfo);
			}

//			// 리스트 잘 입력 받았는지 테스트
//			for (int[] i : list) {
//				System.out.print(Arrays.toString(i) + " ");
//			}
//			System.out.println();

			timeFlow();

			System.out.printf("#%d %d\n", tc, microbeCnt);
		}
	}

	private static void timeFlow() {
		// 시간이 모두 흐르면 종료한다.
		while (M > 0) {
			// 군체 움직여주기
			int size = list.size();
			for (int i = 0; i < size; i++) {
				// 정보 가져오기
				int r = list.get(i)[0];
				int c = list.get(i)[1];
				int num = list.get(i)[2];
				int dir = list.get(i)[3];

				int nr = r + dr[dir];
				int nc = c + dc[dir];

				// 만약 군체가 영역의 끝부분에 도달했다면
				// 수가 반으로 줄고 방향이 반대로 바뀐다.
				if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
					num = num / 2;

					if (dir % 2 == 1)
						dir += 1;
					else
						dir -= 1;
				}

//				System.out.printf("%d %d %d %d\n", nr, nc, num, dir);

				// 다시 할당해주기
				list.get(i)[0] = nr;
				list.get(i)[1] = nc;
				list.get(i)[2] = num;
				list.get(i)[3] = dir;
			} // 군체 움직임 for 종료

			// 좌표를 확인하여 중복된 곳이 있으면 비교하여 통합 > 한꺼번에 비교 및 통합하도록 해야함
			// 중복된 좌표에 가장 많은 미생물의 군체를 넣을 리스트 생성
			List<int[]> duplicate = new ArrayList<>();
			size = list.size();
			// 좌표가 겹치는 것을 밀어 넣고 같은 것이 있는지 비교한다.
			for (int i = 0; i < size; i++) {
				for (int j = i + 1; j < size; j++) {

					int r1 = list.get(i)[0];
					int c1 = list.get(i)[1];
					int num1 = list.get(i)[2];

					int r2 = list.get(j)[0];
					int c2 = list.get(j)[1];
					int num2 = list.get(j)[2];

					// 좌표가 같으면
					if (r1 == r2 && c1 == c2) {
						// 미생물 수가 더 큰 군체가 뭔지 알아낸다.
						int[] biggerInfo;
						if (num1 > num2)
							biggerInfo = list.get(i);
						else
							biggerInfo = list.get(j);

//						System.out.printf("(%d %d) num1: %d / (%d %d) num2: %d\n", r1, c1, num1, r2, c2, num2);
//						System.out.printf("biggerInfo: (%d %d) num: %d\n", biggerInfo[0], biggerInfo[1], biggerInfo[2]);

						// 중복좌표 리스트가 비어있는지 확인하고 비어있으면 i, j 중 수가 더 많았던 자료를 넣어준다.
						// 중복좌표 리스트에 뭔가 있다면 좌표가 같은지 확인한다.
						// 좌표가 같지 않다면 i, j 중 수가 더 많았던 자료를 넣어준다.
						// 좌표가 같다면 i, j 중 수가 더 많았던 것과 좌표 안의 수를 비교하여 더 많았던 정보를 넣어준다.
						if (duplicate.isEmpty()) {
							duplicate.add(biggerInfo);
						} else {
							// 중복 좌표 리스트에 해당 좌표가 있는지 확인한다.
							int dsize = duplicate.size();
							boolean isCoinside = false;
							for (int k = 0; k < dsize; k++) {
								int r3 = duplicate.get(k)[0];
								int c3 = duplicate.get(k)[1];
								int num3 = duplicate.get(k)[2];
								
								// 좌표와 일치하지 않는다면 건너뛴다.
								if (r3 != biggerInfo[0] || c3 != biggerInfo[1])
									continue;
								
								// 여기 진입했다면 일치하는 좌표가 있다는 의미이다.
								isCoinside = true;
								
								// 미생물 수가 더 크다면 갱신해주고 변경이 되었음을 체크한다.
								if (biggerInfo[2] > num3)
									duplicate.set(k, biggerInfo);
								// 일치하는 좌표는 유일하므로 발견했다면 반복문을 탈출한다.
								break;

							} // 중복 리스트에 들어있는 좌표 비교 for 종료
							
							// 만약 일치하는 좌표가 없었다면 중복리스트에 i, j를 비교하여 더 컸던 정보를 추가해준다.
							if (!isCoinside)
								duplicate.add(biggerInfo);
						}
					} // 좌표가 같을 때 if 종료
				}
			} // 중복 좌표 리스트에 데이터 추가하는 for 종료

//			// 리스트 잘 입력 받았는지 테스트
//			System.out.print("duplicate: ");
//			for (int[] d : duplicate) {
//				System.out.print(Arrays.toString(d) + " ");
//			}
//			System.out.println();

			// 중복 리스트를 활용하여 군체를 통합한다.
			int dsize = duplicate.size();
			for (int i = 0; i < dsize; i++) {
				size = list.size();
				for (int j = size - 1; j >= 0; j--) {

					int r1 = duplicate.get(i)[0];
					int c1 = duplicate.get(i)[1];
					int num1 = duplicate.get(i)[2];

					int r2 = list.get(j)[0];
					int c2 = list.get(j)[1];
					int num2 = list.get(j)[2];

					// 좌표가 같고 크기가 작으면 흡수 & 흡수 당한 군체를 제거한다.
					if (r1 == r2 && c1 == c2 && num1 > num2) {
						duplicate.get(i)[2] = num1 + num2;
						list.remove(j);
					}
				}
			} // 군체 통합 for 종료

			// 중복 리스트의 좌표와 같은 군체를 찾아서 정보를 수정해준다.
			for (int[] d : duplicate) {
				for (int[] l : list) {
					if (d[0] == l[0] && d[1] == l[1])
						l = d;
				}
			}

			M--;
		} // while 종료

		// while이 종료되었으면 미생물들을 세준다.
		int size = list.size();
		for (int i = 0; i < size; i++) {
			microbeCnt += list.get(i)[2];
		}
	}
}
