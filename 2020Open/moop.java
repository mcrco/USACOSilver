import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class moop {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("moop.in"));
		int n = Integer.parseInt(in.readLine());

		particle[] particles = new particle[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			particles[i] = new particle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		in.close();
		Arrays.sort(particles);
		
		TreeSet<Integer> lowerbounds = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			lowerbounds.add(particles[i].y);

			int temp = lowerbounds.first();
			while (!lowerbounds.isEmpty() && lowerbounds.first() <= particles[i].y) {
				lowerbounds.pollFirst();
			} 
			lowerbounds.add(temp);
		}

		PrintWriter out = new PrintWriter(new FileWriter("moop.out"));
		out.println(lowerbounds.size());
		out.close();
	}

	static class particle implements Comparable<particle> {
		int x;
		int y;

		public particle(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int compareTo(particle other) {
			return x - other.x;
		}
	}
}
