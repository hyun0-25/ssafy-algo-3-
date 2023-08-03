package study.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14247_나무자르기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int [][] arr;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		long result=0L; // 최대 100,000*10,000이 넘어갈 수 있기 때문에 long 타입으로 변환 
				
		arr=new int[n][2];

		StringTokenizer st;

		for(int i=0;i<2;i++) { // arr 배열 만들기 
			int idx=0;
			st= new StringTokenizer(br.readLine()," ");
			while(st.hasMoreTokens()) {
				arr[idx][i]=Integer.parseInt(st.nextToken());
				idx++;
			}
		}
		
		Arrays.sort(arr,(o1,o2)->{ // 자라나는 길이에 대해 오름차순, 현재 나무 길이에 대해서 내림차순 정렬 진행 
			if(o1[1]==o2[1]) return Integer.compare(o1[0], o2[0])*-1;
			return Integer.compare(o1[1],o2[1]);
		});
		
		for(int i=0;i<arr.length;i++) {
			result+=(long)arr[i][0]+(long)arr[i][1]*i;
		}
		System.out.println(result);
		

	}

}
