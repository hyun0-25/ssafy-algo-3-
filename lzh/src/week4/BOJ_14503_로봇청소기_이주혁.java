package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 구현 문제
 * 
 * 로봇 작동 순서 설명
 * 
 * 1. 현재칸 청소
 * 2. 주변 4방탐색
 * 2-1) 청소할 곳이 없다면? => 후진가능시 후진, 불가시 종료 
 * 2-2) 		있다면? => 반시계 90도 회전
 * 							=> 바라 보는 곳이 청소 안되어있으면 한칸 전진 => 1번으로 돌아간다.
 *	 
 *
 * 알고리즘)
 * while(종료 조건 만족 = false)
 * 	로봇 정해진 순서로 동작
 * 
 * 구현해야할 기능
 * 1. 청소 (map value = 2)
 * 2. 4방 탐색
 * 3. 반시계 90도 회전
 * 4. 전진, 후진
 * 5. 후진 가능 여부
 * 6. 벽 감지
 * 
 * @author SSAFY
 *
 */
public class BOJ_14503_로봇청소기_이주혁 {

	private static int N, M, d;
	private static int[][] map;
	private static int[][] deltas = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
	public static class Robot {
		
		int row;
		int col;
		int deltaIdx;
		int[] delta;
		
		public Robot(int row, int col, int deltaIdx) {
			this.row = row;
			this.col = col;
			this.delta = getDelta(deltaIdx);
		}
		
		public int[] getDelta(int deltaIdx) {
			return deltas[deltaIdx];
		}

		public void goFront() {
			this.row += delta[0];
			this.col += delta[1];
		}
		
		public void goBack() {
			this.row -= delta[0];
			this.col -= delta[1];
		}
		
		public boolean canGoFront() {
			int nr = this.row + delta[0];
			int nc = this.col + delta[1];
			
			if(isInRange(nr, nc) && map[nr][nc] == 0) {
				return true;
			}
			
			return false;
		}
		
		public boolean canGoBack() {
			int nr = this.row + delta[0];
			int nc = this.col + delta[1];
			
			if(!isInRange(nr, nc) || map[nr][nc] == 1) {
				return false;
			}
			return true;
		}
		
		public void turnCounterClock() {
			deltaIdx = (deltaIdx-1)%4;
			this.delta = getDelta(deltaIdx);
		}
		
		
		public boolean searchDirty() {
			
			for(int[] d: deltas) {
				int nr = this.row + d[0];
				int nc = this.col + d[1];
				
				if(isInRange(nr, nc) && map[nr][nc] == 0) {
					return true;
				}
			}
			
			return false;
		}
		
		private boolean isInRange(int row, int col) {
			return row >=0 && row < N && col >= 0 && col < M;
		}
		
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		input = br.readLine().split(" ");
		int robotRow = Integer.parseInt(input[0]);
		int robotCol = Integer.parseInt(input[1]);
		d = Integer.parseInt(input[2]);
		
		Robot robot = new Robot(robotRow, robotCol, d);

		map = new int[N][M];
		for(int i=0; i<N; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		int ans = 0;
		
		while(true) {
			
			if(map[robot.row][robot.col] == 0) {
				ans++;
				map[robot.row][robot.col] = 2;
			}
			
			if(robot.searchDirty() == true) {
				while(!robot.canGoFront()) {
					robot.turnCounterClock();
				}
				robot.goFront();
			} else if(robot.canGoBack()){
				robot.goBack();
			} else {
				System.out.println(ans);
				return;
			}
		}
		
	}

}
