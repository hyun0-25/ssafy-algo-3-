package study.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _16918_봄버맨 {

	public static char[][] bombs;
	public static int[][] bombtime;
	public static int r;
	public static int c;
	public static int n;

	public static void printarr(char[][] arr) {
		for(char[] k: arr) {
			for(char c : k) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void solution() {
		// 상하좌우 
		int[] dr= new int[] {-1,1,0,0};
		int[] dc= new int[] {0,0,-1,1};
		int time=0;

		while(time++ < n) {
			if(time%2==0) { // 비어있는 모든 칸에 폭탄을 설치
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						if (bombs[i][j] == '.') {
							bombs[i][j] = 'O';
							bombtime[i][j] = time+3; // 3초 더해줌 
						}
					}
				}
			}else if(time%2==1) { // 시간이 다 된 폭탄 터트리기 
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						if (bombtime[i][j] == time) {
							bombs[i][j] = '.';
							for (int d = 0; d < 4; d++) {
								int nr = i + dr[d];
								int nc = j + dc[d];

								if (nr < 0 || nc < 0 || nr >= r || nc >= c) continue; // 범위 벗어나는지 체

								// 이번에 터트려야 할 폭탄을 연쇄반응으로 미리 터트리게 되면 주변 폭탄을 연쇄시키지 않고 파괴됨. 
								if(bombs[nr][nc]=='O' && bombtime[nr][nc] != time) { 
									bombs[nr][nc] = '.';
									bombtime[nr][nc] = 0;
								}
							}
						}
					}
				}
			}
		}


		for (int i = 0; i < r; i++) {
			System.out.println(bombs[i]);

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String s = bf.readLine();

		StringTokenizer st = new StringTokenizer(s); // 구분자가 있을 때 사용

		r = Integer.parseInt(st.nextToken()); // row
		c = Integer.parseInt(st.nextToken()); // col
		n = Integer.parseInt(st.nextToken()); // 시간

		bombs= new char[r][c]; // bombs r행 c열로 초기화
		bombtime= new int[r][c];

		for(int i=0; i<r; i++) { // bombs 배열 초기화
			String s1 = bf.readLine();
			for(int j=0; j<c; j++) {
				bombs[i][j] = s1.charAt(j);
				if(bombs[i][j]=='O') {
					bombtime[i][j] = 3; // 폭탄 터질 시
				}
			}
		}

		solution();
	}
}