package study.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_1941_소문난칠공주 {

	public static char[][] arr = new char[5][5]; // 5x5 배열
	public static int[] picked = new int[7]; // 7명 뽑기
	public static int count;
	public static boolean[] isVisited;
	
	// 상하좌우 방향벡터 
	static int dr[] = {-1,1,0,0};
    static int dc[] = {0,0,-1,1};
	
	public static void combi(int start,int cnt,int yCnt) { // 일반 조합
		if(yCnt>3) return; //S가 4명 이상이어야 하므로 
		if(cnt==7) { // 7공주가 됐을 경우 연결되어 있는지 확인. 
			if(isChained()) count++; // 연결되어 있다면 cnt++ 
			return;
		}

		for(int i=start;i<25;i++) { // 5X5 배열이므로 24번 인덱스까지 조합 생성 
				picked[cnt]=i;
				if(arr[i/5][i%5]=='Y') combi(i+1,cnt+1,yCnt+1);
				else combi(i+1,cnt+1,yCnt);
		}
	}

	private static boolean isChained() { // 연결되어있는지 확인하는 함
		Queue<Integer> q = new LinkedList<>(); // Queue 생성 
		isVisited=new boolean[25];
		
		int cnt=1; // 모두 연결되어 있는지 확인하기 위한 변수 
		int nr=0,nc=0;
		
		q.offer(picked[0]); // 큐에 picked[0] 삽입 
		isVisited[picked[0]]=true; // picked[0] 방문처리 
		
		while(!q.isEmpty()) { //큐가 빌 때까지 반복 진행 
			int x = q.poll(); //큐에서 하나씩 원소 뽑기 
			
			for(int i=0;i<4;i++) { // 상하좌우 탐색 
				nr=x/5;
				nc=x%5;
				if(nr+dr[i]>4 || nc+dc[i]>4 || nr+dr[i]<0 || nc+dc[i]<0 || isVisited[(nr+dr[i])*5+nc+dc[i]]) continue;
				nr+=dr[i];
				nc+=dc[i];
				for(int j=1;j<7;j++) {
					if(picked[j]==(nr*5+nc)) {
						isVisited[nr*5+nc]=true;
						q.offer(picked[j]);
						cnt+=1;
					}
				}
			}
		}
		return (cnt==7) ? true : false;
	
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String line ="";
		for(int i=0;i<5;i++) { // arr 값 입력 받기 
			line = bf.readLine();
			for(int j=0;j<5;j++) {
				arr[i][j]=line.charAt(j);
			}
		}
		combi(0,0,0); // 조합 start 
		
		System.out.println(count); // 조건에 만족하는 모든 경우의 수 count 출력  
	}

}

