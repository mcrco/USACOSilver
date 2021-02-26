import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")
public class mooves {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] cowAt = new int[n];
		ArrayList<Integer>[] locationsPerCycle = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			cowAt[i] = i;
			locationsPerCycle[i] = new ArrayList<>();
			locationsPerCycle[i].add(i);
		}
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			locationsPerCycle[cowAt[a]].add(b);
			locationsPerCycle[cowAt[b]].add(a);

			int temp = cowAt[a];
			cowAt[a] = cowAt[b];
			cowAt[b] = temp;
		}
		
		int[] ans = new int[n];
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			
			int curr = i;
			HashSet<Integer> cycle = new HashSet<>(); 
			while (!visited[curr]) {
				cycle.add(curr);
				visited[curr] = true;
				curr = cowAt[curr];
			}

			HashSet<Integer> uniquePositions = new HashSet<>();
			for (int member : cycle) {
				uniquePositions.addAll(locationsPerCycle[member]);
			}

			for (int member : cycle) {
				ans[member] = uniquePositions.size();
			}
		}
		
		for (int i = 0; i < n; i++) {
			System.out.println(ans[i]);
		}
	}
}
