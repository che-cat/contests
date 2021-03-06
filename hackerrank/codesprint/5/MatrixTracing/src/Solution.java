import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Solution {

	BufferedReader in;
	StringTokenizer st;
	PrintWriter out;

	final long mod = 1000000007L;

	long pow(long a, int pow) {
		long ret = 1;
		while (pow > 0) {
			if ((pow & 1) == 1) {
				ret = (ret * a) % mod;
			}
			a = (a * a) % mod;
			pow >>= 1;
		}
		return ret;
	}

	void solve() throws IOException {
		long[] fact = new long[2000001];
		fact[0] = 1;
		for (int i = 1; i < fact.length; ++i) {
			fact[i] = (fact[i - 1] * i) % mod;
		}
		for (int t = ni(); t > 0; --t) {
			int n = ni() - 1;
			int m = ni() - 1;
			n += m;
			long a = fact[n];
			long b = (fact[m] * fact[n - m]) % mod;
			b = pow(b, (int) mod - 2);
			a = (a * b) % mod;
			out.println(a);
		}
	}

	public void run() throws IOException {
		Locale.setDefault(Locale.US);
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		in.close();
		out.close();
	}

	String ns() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	int ni() throws IOException {
		return Integer.valueOf(ns());
	}

	long nl() throws IOException {
		return Long.valueOf(ns());
	}

	double nd() throws IOException {
		return Double.valueOf(ns());
	}

	public static void main(String[] args) throws IOException {
		if ("true".equals(System.getProperty("LOCAL_DEBUG"))) {
			DEBUG = true;
		}
		new Solution().run();
	}

	class Timer {

		long time;

		void start() {
			time = System.currentTimeMillis();
		}

		long time() {
			return System.currentTimeMillis() - time;
		}

		void print() {
			print("Time spent = ");
		}

		void print(String message) {
			dbg(message, time());
		}

	}

	static boolean DEBUG = true;

	void dbg(Object... objs) {
		if (!DEBUG) {
			return;
		}
		for (Object o : objs) {
			String printLine;
			if (o.getClass().isArray()) {
				printLine = arrayToString(o);
			} else {
				printLine = o.toString();
			}
			System.err.print(printLine + " ");
		}
		System.err.println();
	}

	String arrayToString(Object o) {
		if (o instanceof long[])
			return Arrays.toString((long[]) o);
		if (o instanceof int[])
			return Arrays.toString((int[]) o);
		if (o instanceof short[])
			return Arrays.toString((short[]) o);
		if (o instanceof char[])
			return Arrays.toString((char[]) o);
		if (o instanceof byte[])
			return Arrays.toString((byte[]) o);
		if (o instanceof double[])
			return Arrays.toString((double[]) o);
		if (o instanceof float[])
			return Arrays.toString((float[]) o);
		if (o instanceof boolean[])
			return Arrays.toString((boolean[]) o);
		if (o instanceof Object[])
			return Arrays.deepToString((Object[]) o);
		throw new IllegalStateException();
	}

}
