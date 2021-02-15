import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

@SuppressWarnings("unchecked")
public class moocast {

	static int n;
	static cow[] cows;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
		n = Integer.parseInt(br.readLine());

		cows = new cow[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int power = Integer.parseInt(st.nextToken());

			cows[i] = new cow(x, y, power);
		}
		br.close();

		ArrayList<Integer>[] inRange = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			inRange[i] = new ArrayList<>();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (cows[i].canReach(cows[j])) {
					inRange[i].add(j);
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			TreeSet<Integer> visited = new TreeSet<>();
			Queue<Integer> q = new LinkedList<>();

			visited.add(i);
			q.add(i);

			while (!q.isEmpty()) {
				int curr = q.poll();

				for (Integer next : inRange[curr]) {
					if (!visited.contains(next)) {
						q.add(next);
						visited.add(next);
					}
				}
			}

			ans = Math.max(ans, visited.size());
		}

		PrintWriter out = new PrintWriter(new FileWriter("moocast.out"));
		out.println(ans);
		out.close();
	}

	static class cow {
		int x;
		int y;
		int power;

		public cow(int x, int y, int p) {
			this.x = x;
			this.y = y;
			power = p * p;
		}

		public boolean canReach(cow other) {
			return (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y) <= power;
		}
	}
}
