package study.week1;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2178_미로탐색 {

	public static int [][] graph;
	public static int m;
	public static int n;

	public static int bfs() {
		// 상하좌우 
		int[] dr= new int[] {-1,1,0,0};
		int[] dc= new int[] {0,0,-1,1};

		Queue<Point> q = new LinkedList<>(); // Queue 생성 
		q.offer(new Point(1,1)); //큐에 (1,1) 삽입 

		while(!q.isEmpty()) {
			Point p = q.poll();

			for(int i=0; i<4; i++) {
				int nr = p.x+dr[i];
				int nc = p.y+dc[i];

				if (nr<1 || nr>n || nc<1 || nc>m || graph[nr][nc]==0) continue; 

				if (graph[nr][nc] == 1) {
					q.offer(new Point(nr,nc)); // 큐에 넣어주기 
					graph[nr][nc]= graph[p.x][p.y]+1; // 지나간 곳에 +1 
				}
			}
		}

		return graph[n][m];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());

		graph = new int [n+1][m+1];

		for(int i=1; i<n+1; i++) { // graph 값 입력 받기 
			String s = bf.readLine();
			for(int j=1; j<m+1; j++) {
				graph[i][j] = s.charAt(j-1) - '0';
			}
		}

		System.out.println(bfs());

	}

}
