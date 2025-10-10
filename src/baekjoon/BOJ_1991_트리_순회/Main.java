package baekjoon.BOJ_1991_트리_순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    char curr;
    Node leftChild;
    Node rightChild;

    public Node(char val) {
        this.curr = val;
        this.leftChild = null;
        this.rightChild = null;
    }
}

public class Main {
    // 트리구조를 담을 배열
    public static Node[] treeArr;
    // 정답
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 노드의 개수
        int N = Integer.parseInt(br.readLine());

        treeArr = new Node[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            char parentVal = st.nextToken().charAt(0);
            char leftVal = st.nextToken().charAt(0);
            char rightVal = st.nextToken().charAt(0);
            
            // 'A'는 65, '.'은 46 값이 나왔다
            // 부모 노드 없으면 생성
            if (treeArr[parentVal - 'A'] == null) {
                treeArr[parentVal - 'A'] = new Node(parentVal);
            }
            // 자식 값이 있으면 자식 노드 생성 후 값 적용
            if (leftVal != '.') {
                treeArr[leftVal - 'A'] = new Node(leftVal);
                treeArr[parentVal - 'A'].leftChild = treeArr[leftVal - 'A'];
            }
            if (rightVal != '.') {
                treeArr[rightVal - 'A'] = new Node(rightVal);
                treeArr[parentVal - 'A'].rightChild = treeArr[rightVal - 'A'];
            }
        }

        // 전위, 중위, 후위 순회 후 출력
        preorder(treeArr[0]);
        sb.append("\n");
        inorder(treeArr[0]);
        sb.append("\n");
        postorder(treeArr[0]);
        System.out.println(sb.toString());
    }

    /**
     * 전위 순회 함수
     * @param node 현재 노드
     */
    public static void preorder(Node node) {
        // 노드가 없다면 리턴
        if (node == null) return;

        // 루트 출력
        sb.append(node.curr);
        // 왼쪽 탐색
        preorder(node.leftChild);
        // 오른쪽 탐색
        preorder(node.rightChild);
    }

    /**
     * 중위 순회 함수
     * @param node 현재 노드
     */
    public static void inorder(Node node) {
        // 노드가 없다면 리턴
        if (node == null) return;

        // 왼쪽 탐색
        inorder(node.leftChild);
        // 루트 출력
        sb.append(node.curr);
        // 오른쪽 탐색
        inorder(node.rightChild);
    }

    /**
     * 후위 순회 함수
     * @param node 현재 노드
     */
    public static void postorder(Node node) {
        // 노드가 없다면 리턴
        if (node == null) return;

        // 왼쪽 탐색
        postorder(node.leftChild);
        // 오른쪽 탐색
        postorder(node.rightChild);
        // 루트 출력
        sb.append(node.curr);
    }
}
