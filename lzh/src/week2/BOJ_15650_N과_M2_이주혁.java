package week2;

import java.util.*;
import java.io.*;
public class BOJ_15650_N과_M2_이주혁 {
	static int n, m;
	static Stack<Integer> combStack;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void comb(int start, int depths) {
		
		if(depths == m) {
			for(Integer num : combStack) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<n+1; i++) {
			if(!visited[i]) {
				visited[i] = true;
				combStack.add(i);
				comb(i, depths+1);
				visited[i] = false;
				combStack.pop();
			}
		}
		
		
	}
		
		
		
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
	
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		combStack = new Stack<>();
		visited = new boolean[n+1];
		
		comb(1, 0);
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
}
