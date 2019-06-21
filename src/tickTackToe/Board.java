package tickTackToe;

public class Board {
	public int[][][] board = new int[9][9][9];
	// board[out][mid][in]

	public final int BLANK = 0;
	public final int X = 1;
	public final int O = 2;

	public String symbolOf(int value) {
		if (value == 1) {
			return "X";
		} else if (value == 2) {
			return "O";
		} else if (value == -1) {
			return "?";
		} else {
			return "-";
		}
	}
	/**
	 * Sets all values on the board back to blank (0).
	 */
	public void reset() {
		for (int[][] i : board) {
			for (int[] j : i) {
				for (int k : j) {
					k = BLANK;
				}
			}
		}
	}

	/**
	 * Sets the value of a spot on the board.
	 * 
	 * @param out The area of the outermost board
	 * @param mid The area on the middle-size board within out
	 * @param in  The spot on the smallest board within mid
	 */
	public void set(int out, int mid, int in, int team) {
		board[out][mid][in] = team;
	}

	/**
	 * Sets the value of a spot on the board.
	 * 
	 * @param out The x, y values for the location of the middle board from the top
	 *            left of the outermost board
	 * @param mid The x, y values for the location of the smallest board from the
	 *            top left of the middle board
	 * @param in  The x, y values for the spot on the smallest board from the top
	 *            left
	 */
	public void set(int[] out, int[] mid, int[] in, int team) {
		// If any of the x, y pairs are larger than the allowed side, do nothing.
		if (out.length == 2 && mid.length == 2 && in.length == 2) {
			// If it is not an x, y pair, just do nothing
			if (out[0] > 2 || out[1] > 2 || mid[0] > 2 || mid[1] > 2 || in[0] > 2 || in[1] > 2) {
				set(out[0] + 3 * out[1], mid[0] + 3 * mid[1], in[0] + 3 * in[1], team);
			}
		}
	}

	/**
	 * todo javadoc
	 * 
	 * @param out
	 * @param mid
	 * @param in
	 * @return
	 */
	public int get(int out, int mid, int in) {
		return board[out][mid][in];
	}

	/**
	 * todo javadoc
	 * 
	 * @param out
	 * @param mid
	 * @param in
	 * @return
	 */
	public int get(int[] out, int[] mid, int[] in) {
		if (out.length == 2 && mid.length == 2 && in.length == 2) {
			if (out[0] <= 2 || out[1] <= 2 || mid[0] <= 2 || mid[1] <= 2 || in[0] <= 2 || in[1] <= 2) {
				return get(out[0] + 3 * out[1], mid[0] + 3 * mid[1], in[0] + 3 * in[1]);
			}
			// If any of the x, y pairs are larger than the allowed side return -1.
		}
		// If it is not an x, y pair, return -1
		return -1;
	}
	

	/**
	 * Creates a text-based representation of the board.
	 * 
	 * @return A multiline string containing a text-graphic of the board.
	 */
	public String out() {
		String out = "=========================================================================\n";
		String[] rows = new String[3 * 3 * 3];
		for (int i = 0; i < 3 * 3 * 3; i++) {
			rows[i] = "[ ";
			for (int j = 0; j < 3 * 3 * 3; j++) {
				rows[i] += symbolOf(get(new int[]{j / 9, i / 9}, new int[]{j % 9 / 3, i % 9 / 3}, new int[]{j % 3, i % 3}));
				if (j % 9 == 8) {
					rows[i] += " [";
				} else if (j % 3 == 2) {
					rows[i] += " |";
				}
				rows[i] += " ";
			}
		}
		for (int i = 0; i < 3 * 3 * 3; i++) {
			out += rows[i] + "\n";
			if (i % 9 == 8) {
				out += "=========================================================================\n";
			} else if (i % 3 == 2) {
				out += "-------------------------------------------------------------------------\n";
			}
		}
		return out;
	}

}
