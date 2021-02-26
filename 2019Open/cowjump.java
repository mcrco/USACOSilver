import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class cowjump {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
		int n = Integer.parseInt(br.readLine());
		
		point[] points = new point[2 * n];
		segment[] segments = new segment[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken()); 
			
			point a = new point(x1, y1, i);
			point b = new point(x2, y2, i);
			points[2 * i] = a;
			points[2 * i + 1] = b;
			segments[i] = new segment(point.min(a, b), point.max(a, b));
		}
		br.close();
		
		Arrays.sort(points);
		int[] numIntersectionsOf = new int[n];
		TreeSet<point> active = new TreeSet<>();
		
		for (int i = 0; i < 2 * n; i++) {
			if (active.lower(points[i]) != null) {
				int lower = active.lower(points[i]).index;
				if (segments[points[i].index].interescts(segments[lower])) {
					numIntersectionsOf[points[i].index]++;
					numIntersectionsOf[lower]++;
				} 
			}

			if (active.higher(points[i]) != null) {
				int higher = active.higher(points[i]).index;
				if (segments[points[i].index].interescts(segments[higher])) {
					numIntersectionsOf[points[i].index]++;
					numIntersectionsOf[higher]++;
				}
			}

			if (active.contains(segments[points[i].index].left)) {
				active.remove(segments[points[i].index].left);
			}
			else {
				active.add(points[i]);
			}
		}
		
		int ans = 0;
		for (int i = 0; i < n; i++) {
			if (numIntersectionsOf[i] > numIntersectionsOf[ans]) {
				ans = i;
			} 
		}
		
		PrintWriter out = new PrintWriter(new FileWriter("cowjump.out"));
		out.println(ans + 1);
		out.close();
	}

	static class point implements Comparable<point> {
		long x;
		long y;
		int index;

		public point(int x, int y, int i) {
			this.x = x;
			this.y = y;
			index = i;
		}

		public int compareTo(point other) {
			if (this.y == other.y) return this.x > other.x ? 1 : 0;
			return this.y > other.y ? 1 : -1;
		}

		public static point min(point a, point b) {
			if (a.compareTo(b) < 0) return a;
			return b;
		}
		
		public static point max(point a, point b) {
			if (a.compareTo(b) < 0) return b;
			return a;
		}
	}

	static class segment {
		point left;
		point right;

		public segment(point a, point b) {
			left = a;
			right = b;
		}
		
		public boolean interescts(segment other) {
			if (this.orientationWith(other.left) != this.orientationWith(other.right) && (other.orientationWith(this.left) != other.orientationWith(this.right))) {
				return true;
			}

			return false;
		}

		public int orientationWith(point other) {
			long crossProduct = (other.y - right.y) * (right.x - left.x) - (right.y - left.y) * (other.x - right.x);
			if (crossProduct > 0) return 1; //counterclockwise
			if (crossProduct < 0) return -1; //clockwise
			return 0; //collinear
		}
	}
}
