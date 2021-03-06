import java.util.*;

import java.io.*;

import static java.lang.Math.*;

public class EllysScrabble {

	String[][] dp;
	int d;
	char[] word;

	String rec(int pos, int mask) {
		if (pos == word.length) {
			return "";
		}
		String ans = dp[pos][mask];
		if (ans != null) {
			return ans;
		}
		if (getBit(mask, pos, -d)) {
			ans = word[pos - d] + rec(pos + 1, (mask >> 1) | (1 << (2 * d)));
		} else {
			char let = 'Z' + 1;
			for (int i = -d; i <= d; ++i) {
				if (getBit(mask, pos, i)) {
					let = word[pos + i] < let ? word[pos + i] : let;
				}
			}
			String rest = null;
			for (int i = -d; i <= d; ++i) {
				if (getBit(mask, pos, i) && word[pos + i] == let) {
					int nextMask = (mask >> 1) | (1 << (2 * d));
					nextMask &= ~(1 << (i + d - 1));
					String a = rec(pos + 1, nextMask);
					if (rest == null || rest.compareTo(a) > 0) {
						rest = a;
					}
					if (sorted(rest))
						break;
				}
			}
			ans = let + rest;
		}
		dp[pos][mask] = ans;
		return ans;
	}

	boolean sorted(String s) {
		for (int i = 1; i < s.length(); ++i)
			if (s.charAt(i) > s.charAt(i - 1))
				return false;
		return true;
	}

	boolean getBit(int mask, int pos, int bit) {
		if (pos + bit < 0 || pos + bit >= word.length)
			return false;
		return (mask & (1 << (bit + d))) != 0;
	}

	public String getMin(String letters, int maxDistance) {
		long time = System.currentTimeMillis();
		word = letters.toCharArray();
		d = maxDistance;
		dp = new String[word.length][1 << (2 * d + 1)];
		String ret = rec(0, (1 << (2 * d + 1)) - 1);
		// System.out.println(System.currentTimeMillis() - time);
		return ret;
	}

	// BEGIN CUT HERE
	static boolean DEBUG = false;

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

	public static void main(String[] args) {
		try {
			DEBUG = true;
			eq(0, (new EllysScrabble()).getMin(
					"AAAAAABAAAAAAAAAAAAAAAAAAAABAAAAAAAAAAAAAABAAAAAAA", 9),
					"ABC");
			// eq(0, (new
			// EllysScrabble()).getMin("CBACBACBAQCBACBACBAQCBACBACBAQCBACBACBAQCBACBACBAQ",
			// 9), "ABC");
			eq(0, (new EllysScrabble()).getMin("CBA", 2), "ABC");
			eq(0, (new EllysScrabble()).getMin("TOPCODER", 3), "CODTEPOR");
			eq(1, (new EllysScrabble()).getMin("ESPRIT", 3), "EIPRST");
			eq(2, (new EllysScrabble()).getMin("BAZINGA", 8), "AABGINZ");
			eq(3,
					(new EllysScrabble()).getMin("ABCDEFGHIJKLMNOPQRSTUVWXYZ",
							9), "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			eq(4, (new EllysScrabble()).getMin("GOODLUCKANDHAVEFUN", 7),
					"CADDGAHEOOFLUKNNUV");
			eq(5, (new EllysScrabble()).getMin(
					"AAAWDIUAOIWDESBEAIWODJAWDBPOAWDUISAWDOOPAWD", 6),
					"AAAADDEIBWAEUIODWADSBIAJWODIAWDOPOAWDUOSPWW");
			eq(6, (new EllysScrabble()).getMin("ABRACADABRA", 2), "AABARACBDAR");
		} catch (Exception exx) {
			System.err.println(exx);
			exx.printStackTrace(System.err);
		}
	}

	private static void eq(int n, int a, int b) {
		if (a == b)
			System.err.println("Case " + n + " passed.");
		else
			System.err.println("Case " + n + " failed: expected " + b
					+ ", received " + a + ".");
	}

	private static void eq(int n, char a, char b) {
		if (a == b)
			System.err.println("Case " + n + " passed.");
		else
			System.err.println("Case " + n + " failed: expected '" + b
					+ "', received '" + a + "'.");
	}

	private static void eq(int n, long a, long b) {
		if (a == b)
			System.err.println("Case " + n + " passed.");
		else
			System.err.println("Case " + n + " failed: expected \"" + b
					+ "L, received " + a + "L.");
	}

	private static void eq(int n, boolean a, boolean b) {
		if (a == b)
			System.err.println("Case " + n + " passed.");
		else
			System.err.println("Case " + n + " failed: expected " + b
					+ ", received " + a + ".");
	}

	private static void eq(int n, double a, double b) {
		if (eq(a, b))
			System.err.println("Case " + n + " passed.");
		else
			System.err.println("Case " + n + " failed: expected " + b
					+ ", received " + a + ".");
	}

	private static void eq(int n, String a, String b) {
		if (a != null && a.equals(b))
			System.err.println("Case " + n + " passed.");
		else
			System.err.println("Case " + n + " failed: expected \"" + b
					+ "\", received \"" + a + "\".");
	}

	private static void eq(int n, int[] a, int[] b) {
		if (a.length != b.length) {
			System.err.println("Case " + n + " failed: returned " + a.length
					+ " elements; expected " + b.length + " elements.");
			return;
		}
		for (int i = 0; i < a.length; i++)
			if (a[i] != b[i]) {
				System.err
						.println("Case "
								+ n
								+ " failed. Expected and returned array differ in position "
								+ i);
				print(b);
				print(a);
				return;
			}
		System.err.println("Case " + n + " passed.");
	}

	private static void eq(int n, long[] a, long[] b) {
		if (a.length != b.length) {
			System.err.println("Case " + n + " failed: returned " + a.length
					+ " elements; expected " + b.length + " elements.");
			return;
		}
		for (int i = 0; i < a.length; i++)
			if (a[i] != b[i]) {
				System.err
						.println("Case "
								+ n
								+ " failed. Expected and returned array differ in position "
								+ i);
				print(b);
				print(a);
				return;
			}
		System.err.println("Case " + n + " passed.");
	}

	private static void eq(int n, double[] a, double[] b) {
		if (a.length != b.length) {
			System.err.println("Case " + n + " failed: returned " + a.length
					+ " elements; expected " + b.length + " elements.");
			return;
		}
		for (int i = 0; i < a.length; i++)
			if (!eq(a[i], b[i])) {
				System.err
						.println("Case "
								+ n
								+ " failed. Expected and returned array differ in position "
								+ i);
				print(b);
				print(a);
				return;
			}
		System.err.println("Case " + n + " passed.");
	}

	private static void eq(int n, String[] a, String[] b) {
		if (a.length != b.length) {
			System.err.println("Case " + n + " failed: returned " + a.length
					+ " elements; expected " + b.length + " elements.");
			return;
		}
		for (int i = 0; i < a.length; i++)
			if (!a[i].equals(b[i])) {
				System.err
						.println("Case "
								+ n
								+ " failed. Expected and returned array differ in position "
								+ i);
				print(b);
				print(a);
				return;
			}
		System.err.println("Case " + n + " passed.");
	}

	private static void print(int a) {
		System.err.print(a + " ");
	}

	private static void print(long a) {
		System.err.print(a + "L ");
	}

	private static void print(String s) {
		System.err.print("\"" + s + "\" ");
	}

	private static void print(int[] rs) {
		if (rs == null)
			return;
		System.err.print('{');
		for (int i = 0; i < rs.length; i++) {
			System.err.print(rs[i]);
			if (i != rs.length - 1)
				System.err.print(", ");
		}
		System.err.println('}');
	}

	private static void print(long[] rs) {
		if (rs == null)
			return;
		System.err.print('{');
		for (int i = 0; i < rs.length; i++) {
			System.err.print(rs[i]);
			if (i != rs.length - 1)
				System.err.print(", ");
		}
		System.err.println('}');
	}

	private static void print(double[] rs) {
		if (rs == null)
			return;
		System.err.print('{');
		for (int i = 0; i < rs.length; i++) {
			System.err.print(rs[i]);
			if (i != rs.length - 1)
				System.err.print(", ");
		}
		System.err.println('}');
	}

	private static void print(String[] rs) {
		if (rs == null)
			return;
		System.err.print('{');
		for (int i = 0; i < rs.length; i++) {
			System.err.print("\"" + rs[i] + "\"");
			if (i != rs.length - 1)
				System.err.print(", ");
		}
		System.err.println('}');
	}

	private static void nl() {
		System.err.println();
	}

	private static double eps = 1e-9;

	private static boolean eq(double a, double b) {
		return abs(a - b) <= eps;
	}
	// END CUT HERE
}
