package week2;

import java.util.*;
import java.io.*;
public class BOJ_15649_N과_M1_이주혁 {
		static int n, m;
		static Stack<Integer> combStack;
		static boolean[] visited;
		static StringBuilder sb;
		
		public static void comb(int depths) {
			
			if(depths == m) {
				for(Integer num : combStack) {
					sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<n+1; i++) {
			if(!visited[i]) {
				visited[i] = true;
				combStack.add(i);
				comb(depths+1);
				visited[i] = false;
				combStack.pop();
			}
		}
		
		
	}
	
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
	
		String[] input = br.readLine().split(" ");
			n = Integer.parseInt(input[0]);
			m = Integer.parseInt(input[1]);
			combStack = new Stack<>();
			visited = new boolean[n+1];
			
			comb(0);
			System.out.println(sb.toString());
	}
}
