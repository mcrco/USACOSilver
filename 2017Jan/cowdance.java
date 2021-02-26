import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cowdance {

	static int n;
	static int maxTime;
	static int[] durationOf;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		maxTime = Integer.parseInt(st.nextToken());
		
		durationOf = new int[n];
		for (int i = 0; i < n; i++) {
			durationOf[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		
		int low = 0;
		int high = n;
		while (low < high) {
			int mid = (low + high) / 2;
			if (valid(mid)) {
				high = mid;
			}
			else {
				low = mid + 1;
			}
		}

		PrintWriter out = new PrintWriter(new FileWriter("cowdance.out"));
		out.print(low);
		out.close();
	}

	private static boolean valid(int k) {
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for (int i = 0; i < k; i++) {
			q.add(durationOf[i]);
		}

		for (int i = k; i < n || !q.isEmpty(); i++) {
			int endTime = q.poll();
			if (endTime > maxTime) return false;
			
			if (i < n) q.add(endTime + durationOf[i]);
		}

		return true;
	}
}
