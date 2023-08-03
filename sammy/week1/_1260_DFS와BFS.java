package study.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1260_DFS와BFS {
	public static boolean[] visited;
	public static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();

	public static void dfs(int sn) {

		visited[sn] = true; // 현재 노드 방문 처리
		System.out.print(sn + " ");

		for (int i=0; i<adjList.get(sn).size(); i++) {
			int nextNode = adjList.get(sn).get(i);
			if(!visited[nextNode]) dfs(nextNode);
		}
	}

	public static void bfs(int sn) {
		Queue<Integer> q = new LinkedList<>(); // Queue 생성 
		q.offer(sn); //큐에 sn 삽입 

		visited[sn] = true; // 현재 노드 방문 처리 

		while(!q.isEmpty()) { //큐가 빌 때까지 반복 진행 
			int x = q.poll(); //큐에서 하나씩 원소 뽑기 
			System.out.print( x + " ");

			for(int i = 0; i<adjList.get(x).size(); i++) { // 인접한 노드들 확인 
				int y = adjList.get(x).get(i); 

				if(!visited[y]) { //방문하지 않았다면 큐에 삽입 
					q.offer(y);
					visited[y] = true;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int sn = Integer.parseInt(st.nextToken());

		visited=new boolean[v+1]; // 방문 체크 배열 

		for(int i=0;i<=v;i++) {
			adjList.add(new ArrayList<Integer>()); 
		}

		for (int i=0;i<e;i++) { // 인접 리스트 입력 - adjList
			StringTokenizer st1 = new StringTokenizer(bf.readLine());
			int x = Integer.parseInt(st1.nextToken());
			int y = Integer.parseInt(st1.nextToken());
			adjList.get(x).add(y);
			adjList.get(y).add(x);
		}
		
		for (int i = 1; i <= v; i++) { // 방문 순서를 위해 오름차순 정렬 
			Collections.sort(adjList.get(i));
		}

		dfs(sn);
		System.out.println();
		visited=new boolean[v+1]; // 방문 체크 배열 초기화 
		bfs(sn);
	}

}
