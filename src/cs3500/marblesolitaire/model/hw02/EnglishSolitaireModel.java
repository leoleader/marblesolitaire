package cs3500.marblesolitaire.model.hw02;

/**
 * This class is where all the methods we need to get
 * the game running will be.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  /**
   * Setting default board variables.
   */
  static int def_at = 3;
  static int def_sRow = 3;
  static int def_sCol = 3;
  int a;
  int sRow;
  int sCol;
  public SlotState[][] board;

  /**
   * Empty constructor that defaults to arm length of 3 and empty
   * position at (3, 3).
   */
  public EnglishSolitaireModel() {
    this.a = def_at;
    this.sRow = def_sRow;
    this.sCol = def_sCol;
    initBoard(this.a, sRow, sCol);

  }

  /**
   * Constructor that takes in 2 ints for the coordinates of the
   * empty position.
   * @param sRow is the y value.
   * @param sCol is the x value.
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    this.a = def_at;
    initBoard(this.a, sRow, sCol);

  }

  /**
   * Constructor that takes in the arm thickness and then finds
   * the center to put the empty position at.
   * @param a is an int, the arm thickness.
   */
  public EnglishSolitaireModel(int a) {
    validArm(a);
    this.a = a;
    this.sRow = findCenter(a);
    this.sCol = findCenter(a);
    initBoard(a, sRow, sCol);

  }

  /**
   * This constructor takes in both the arm thickness and the
   * coordinates of the empty position.
   * @param a is an int, the arm thickness.
   * @param sRow is the y position.
   * @param sCol is the x position.
   */
  public EnglishSolitaireModel(int a, int sRow, int sCol) {
    validArm(a);
    initBoard(a, sRow, sCol);
  }

  /**
   * This method initializes and builds the board by making a 2D
   * array of SlotStates.
   * @param a the given arm thickness.
   * @param sRow the given y value for the empty position.
   * @param sCol the given x value for the empty position.
   */
  public void initBoard(int a, int sRow, int sCol) {
    int b_size = getBoardSize();
    board = new SlotState[b_size][b_size];

    for (int i = 0; i < b_size; i++) {
      for (int j = 0; j < b_size; j++) {
        board[i][j] = SlotState.Invalid;
      }
    }

    for (int i = a - 1; i < (2 * a - 1); i++) {
      for (int j = 0; j < b_size; j++) {
        board[i][j] = SlotState.Marble;
      }
    }

    for (int i = 0; i < b_size; i++) {
      for (int j = a - 1; j < (2 * a - 1); j++) {
        board[i][j] = SlotState.Marble;
      }
    }

    outOfBounds(sRow, sCol);
    if (board[sRow][sCol] == SlotState.Invalid) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position %s, %s",
              sRow, sCol));
    } else {
      board[sRow][sCol] = SlotState.Empty;
    }

  }

  /**
   * Finds the center of the board, used in constructor that is given arm
   * thickness but not center.
   *
   * @param a given arm thickness.
   * @return int of what slot the center is at.
   */
  private int findCenter(int a) {
    if (a > 3) {
      int num = (int) (Math.pow(2, (a - 5)));
      return a + num;
    }
    if (a == 3) {
      return a;
    } else {
      return 0;
    }
  }

  /**
   * Method to test that the provided arm thickness value is positive
   * and odd.
   *
   * @param arm given arm thickness.
   */
  private void validArm(int arm) {
    if (arm % 2 == 1) {
      this.a = arm;
    } else {
      throw new IllegalArgumentException("Arm thickness must be positive and odd");
    }
  }

  /**
   * Checks to see if the provided slot position is within the
   * bounds of the board.
   *
   * @param x row position
   * @param y column position
   */
  private void outOfBounds(int x, int y) {
    if (x < 0 || y < 0 || x > getBoardSize() || y > getBoardSize()) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position %s, %s", x, y));
    }
  }

  @Override
  public int getBoardSize() {
    return this.a + (2 * (this.a - 1));
  }

  @Override
  public SlotState getSlotAt(int row, int col) {
    outOfBounds(row, col);
    return board[row][col];
  }

  @Override
  public int getScore() {
    int count = 0;
    for (int i = 0; i < getBoardSize(); i++) {
      for (int j = 0; j < getBoardSize(); j++) {
        if (board[i][j] == SlotState.Marble) {
          count = count + 1;
        }
      }
    }
    return count;
  }

  /**
   * This method determines if, given from and to positions, the move is valid.
   * @param fromRow starting y value.
   * @param fromCol starting x value.
   * @param toRow ending y value.
   * @param toCol ending x value.
   * @return boolean true or false.
   */
  public boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow >= getBoardSize() || fromCol >= getBoardSize()
            || toRow >= getBoardSize() || toCol >= getBoardSize()
            || fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0) {
      return false;
    }
    if (board[fromRow][fromCol] != SlotState.Marble
            || board[toRow][toCol] != SlotState.Empty) {
      return false;
    }
    if (Math.abs((fromRow - toRow) + (fromCol - toCol)) != 2) {
      return false;
    }
    return board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == SlotState.Marble;
  }

  private int numOfValidMoves() {
    int vm_count = 0;
    for (int i = 0; i < getBoardSize(); i++) {
      for (int j = 0; j < getBoardSize(); j++) {
        if (validMove(i, j, i + 2, j)) {
          vm_count = vm_count + 1;
        }
        if (validMove(i, j, i - 2, j)) {
          vm_count = vm_count + 1;
        }
        if (validMove(i, j, i, j + 2)) {
          vm_count = vm_count + 1;
        }
        if (validMove(i, j, i, j - 2)) {
          vm_count = vm_count + 1;
        }
      }
    }
    return vm_count;
  }

  /**
   * Makes a move on the board.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0).
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0).
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0).
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0).
   * @throws IllegalArgumentException if the start and end positions are not valid, if the
   * two given positions are not exactly 2 slots horizontally or vertically away from each other,
   * if the start position is not a marble, if the end position is not empty, and
   * if there is not a marble in between the two positions.
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    if (validMove(fromRow, fromCol, toRow, toCol)) {
      board[fromRow][fromCol] = SlotState.Empty;
      board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
      board[toRow][toCol] = SlotState.Marble;
    } else {
      throw new IllegalArgumentException("That was not a valid move. There are "
              + numOfValidMoves() + " valid moves possible");
    }
  }

  /**
   * Checks to see if the game is over by checking every possible move.
   * @return boolean true or false.
   */
  public boolean isGameOver() {
    return numOfValidMoves() == 0;
  }

}
