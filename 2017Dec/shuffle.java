import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")
public class shuffle {

	static int n;
	static TreeSet<Integer>[] inbound;
	static int[] next;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
		n = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		inbound = new TreeSet[n];
		next = new int[n];
		for (int i = 0; i < n; i++) {
			inbound[i] = new TreeSet<>();
		}
		for (int i = 0; i < n; i++) {
			int destination = Integer.parseInt(st.nextToken()) - 1;
			next[i] = destination;
			inbound[destination].add(i);
		}
		in.close();

		for (int i = 0; i < n; i++) {
			prune(i);
		}
		
		int ans = 0;
		for (int i = 0; i < n; i++) {
			if (inbound[i].size() > 0) {
				ans++;
			}
		}

		PrintWriter out = new PrintWriter(new FileWriter("shuffle.out"));
		out.println(ans);
		out.close();
	}

	private static void prune(int position) {
		if (inbound[position].size() != 0) return;
		
		inbound[next[position]].remove(position);
		prune(next[position]);
	}
}
