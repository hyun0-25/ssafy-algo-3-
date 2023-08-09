package study.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14620_꽃길 {

	public static int N,arr[][],minCost;
	public static boolean isVisited[];
	public static int pickedIdx[];
	// 상하좌우 
	public static int[] dr= {-1,1,0,0};
	public static int[] dc= {0,0,-1,1};

	public static void solution(int cnt,int start,int cost) {
//		if(cost>minCost) return;

		if(cnt==3) {
			System.out.println(cost);
			minCost = Math.min(minCost, cost);
			
			isVisited[pickedIdx[2]]=false;
			for(int j=0;j<4;j++) {
				isVisited[(pickedIdx[2]/N+dr[j])*N+(pickedIdx[2]%N+dc[j])]=false;
			}
			
			return;
		}

		for(int idx=start;idx<N*N;idx++) {
			if(isVisited[idx]) continue;
			int checkCost=isPosible(idx);
			if(checkCost==-1) continue;
			System.out.println(Arrays.toString(isVisited));
			System.out.println(idx + " " + checkCost);
			pickedIdx[cnt]=idx;
			
			solution(cnt+1,idx+1,cost+checkCost);
		}
	}

	public static int isPosible(int idx) { // 상하좌우 4방 탐색 
		int nr=idx/N,nc=idx%N;
		int cost=arr[nr][nc];
		isVisited[nr*N+nc] = true;
		for(int i=0;i<4;i++) {
			nr =idx/N + dr[i];
			nc = idx%N + dc[i];	
			
			if(nr<0 || nr>N-1 || nc<0 || nc>N-1 || isVisited[nr*N+nc]) {
				isVisited[idx]=false;
				for(int j=0;j<i;j++) {
					isVisited[(idx/N+dr[j])*N+(idx%N+dc[j])]=false;
				}
				return -1;
			}
			
			isVisited[nr*N+nc] = true;
			cost+=arr[nr][nc];
		}
		
		return cost;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		minCost=Integer.MAX_VALUE;

		N=Integer.parseInt(br.readLine());
		arr = new int[N][N];
		pickedIdx = new int [3];
		isVisited = new boolean[N*N];

		for(int i=0;i<N;i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		solution(0,0,0);
		System.out.println(minCost);
	}

}
