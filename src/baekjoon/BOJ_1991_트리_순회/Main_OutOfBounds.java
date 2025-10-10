package baekjoon.BOJ_1991_트리_순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_OutOfBounds {
    // 트리구조를 담을 배열
    public static int[][] treeArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 노드의 개수
        int N = Integer.parseInt(br.readLine());

        treeArr = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            // 'A'는 65, '.'은 46 값이 나왔다
            int firstCh = st.nextToken().charAt(0) - 'A';
            int secondCh = st.nextToken().charAt(0) - 65;
            int thirdCh = st.nextToken().charAt(0) - 65;

            treeArr[i][0] = firstCh;
            treeArr[i][1] = secondCh;
            treeArr[i][2] = thirdCh;
        }

        preorder(0);
        System.out.println();
        inorder(0);
        System.out.println();
        postorder(0);
    }

    /**
     * 다음 노드를 찾는 함수
     * @param parentNum 다음 노드를 찾을 자식 트리의 숫자
     * @return 부모 노드의 인덱스
     */
    public static int findParentIdx(int parentNum) {
        for (int i = 0; i < 'Z' - 'A'; i++) {
            if (parentNum == treeArr[i][0]) {
                return i;
            }
        }

        return 0;
    }

    /**
     * 전위 순회 함수
     * @param currNode 현재 노드 숫자
     */
    public static void preorder(int currNode) {
        // 알기 쉽게 저장해놓기
        int leftChild = treeArr[currNode][1];
        int rightChild = treeArr[currNode][2];

        // 우선 루트 출력
        System.out.printf("%c", treeArr[currNode][0] + 'A');
        
        // 왼쪽 자식이 있으면 그쪽 먼저 탐색
        if (leftChild > 0) {
            preorder(findParentIdx(leftChild));
        }

        // 오른쪽 자식이 있으면 탐색
        if (rightChild > 0) {
            preorder(findParentIdx(rightChild));
        }
    }

    /**
     * 중위 순회 함수
     * @param currNode 현재 노드 숫자
     */
    public static void inorder(int currNode) {
        int leftChild = treeArr[currNode][1];
        int rightChild = treeArr[currNode][2];

        // 왼쪽 자식이 있으면 그쪽 먼저 탐색
        if (leftChild > 0) {
            inorder(findParentIdx(leftChild));
        }

        // 가운데 루트 출력
        System.out.printf("%c", treeArr[currNode][0] + 'A');

        // 오른쪽 자식이 있으면 탐색
        if (rightChild > 0) {
            inorder(findParentIdx(rightChild));
        }
    }

    /**
     * 후위 순회 함수
     * @param currNode 현재 노드 숫자
     */
    public static void postorder(int currNode) {
        int leftChild = treeArr[currNode][1];
        int rightChild = treeArr[currNode][2];

        // 왼쪽 자식이 있으면 그쪽 먼저 탐색
        if (leftChild > 0) {
            postorder(findParentIdx(leftChild));
        }
        
        // 오른쪽 자식이 있으면 탐색
        if (rightChild > 0) {
            postorder(findParentIdx(rightChild));
        }

        // 마지막 루트 출력
        System.out.printf("%c", treeArr[currNode][0] + 'A');
    }
}
