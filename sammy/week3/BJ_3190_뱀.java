package study.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 벽 또는 자기자신의 몸과 부딪히면 게임 끝
 * @author anseongjae
 *
 */

public class BJ_3190_뱀 {
	public static int N,K,L,seconds,dir,arr[][];

	// 우하좌상 
	public static int dr[] = {0,1,0,-1}; 
	public static int dc[] = {1,0,-1,0}; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<int[]> q= new ArrayDeque<int[]>();
		Queue<String> cmds= new ArrayDeque<String>();

		N=Integer.parseInt(br.readLine());
		K=Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];


		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			arr[row][col]=2; // 사과는 2 
		}

		L=Integer.parseInt(br.readLine());

		int nr=1;
		int nc=1;
		int r=1;
		int c=1;
		int x=0;
		char cmd=' ';
		arr[r][c]=1;
		q.offer(new int[] {r,c});

		// 처음 값 세팅
		st = new StringTokenizer(br.readLine());
		x= Integer.parseInt(st.nextToken());
		// L: 왼쪽으로 90도 회전, D: 오른쪽으로 90도회전 
		cmd = st.nextToken().charAt(0);

		for(int i=1;i<L;i++) {
			cmds.offer(br.readLine());
		}

		while(true) {
			nr=r+dr[dir];
			nc=c+dc[dir];
			seconds++;

			if(nr>N || nr<1 || nc>N || nc<1 || arr[nr][nc]==1) {
				break;
			}
			
			r=nr;
			c=nc;

			if(arr[r][c]==2) {
				arr[r][c]=1; // 방문 처리 
				q.offer(new int[] {r,c});
			}else if(arr[r][c]==0) {
				arr[r][c]=1; // 방문 처리 
				q.offer(new int[] {r,c});
				int[] check = q.poll();
				arr[check[0]][check[1]]=0; // 방문 해제 

			}
			
			if(seconds==x) {
				if(cmd=='L') {
					dir--; // 왼쪽 90도 
					if(dir==-1) dir=3;
				}else if(cmd=='D') {
					dir++; // 오른쪽 90
					if(dir==4) dir=0;
				}
				if(!cmds.isEmpty()) {
					String s = cmds.poll();
					st = new StringTokenizer(s);
					x= Integer.parseInt(st.nextToken());
					// L: 왼쪽으로 90도 회전, D: 오른쪽으로 90도회전 
					cmd = st.nextToken().charAt(0);
				}
			}

		}

		System.out.println(seconds);
	}
}
