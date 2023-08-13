package study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_24416_피보나치수1 {
	
	public static int N,dp[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		dp = new int[N+1];

		dp[1]=dp[2]=1;
		
		for(int i=3;i<N+1;i++) {
			dp[i]=dp[i-2]+dp[i-1];
		}
		
		System.out.println(dp[N]+" "+(N-2));
	}
	
}
