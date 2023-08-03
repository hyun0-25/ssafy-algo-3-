package study.week2;

import java.util.Scanner;

public class _15650_N과M_2 {
	
	public static int n,m;
	
	public static int[] arr;
	public static StringBuilder sb = new StringBuilder();
	
	public static void combi(int start,int cnt) { // 조합은 중복허용 안되므로 start 매개변수 추가 
		if(cnt==m) { // 종료조건 : cnt가 m이 될 때 
			for(int i: arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start;i<=n;i++) {
			arr[cnt]=i;
			combi(i+1,cnt+1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr= new int[m];
		
		combi(1,0);
		System.out.println(sb);
		sc.close();
		
	}

}