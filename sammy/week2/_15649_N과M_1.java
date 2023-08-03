package study.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _15649_N과M_1 {
	public static int[] arr;
	public static boolean[] visited;
	public static int n;
	public static int m;
	public static StringBuilder sb = new StringBuilder(); // stringBuilder 사용 
	
	public static void dfs(int cnt) {
		if (cnt==m) { // 종료 조건 
			for(int i=0;i<arr.length;i++) {
				sb.append(arr[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i=0;i<n;i++) {
			if(!visited[i]) {
				visited[i]=true;
				arr[cnt]=i+1;
				dfs(cnt+1);
				visited[i]=false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str=bf.readLine();
		StringTokenizer st = new StringTokenizer(str);
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		arr=new int[m];
		visited=new boolean[n];
		
		dfs(0);
		
		System.out.println(sb);
	}
}
