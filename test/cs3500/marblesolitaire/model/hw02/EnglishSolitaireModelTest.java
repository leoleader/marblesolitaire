package cs3500.marblesolitaire.model.hw02;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class is the tests for the methods in EnglishSolitaireModel.
 */
public class EnglishSolitaireModelTest {
  EnglishSolitaireModel emptyModel = new EnglishSolitaireModel();
  EnglishSolitaireModel fullModel = new EnglishSolitaireModel(3, 3, 3);
  EnglishSolitaireModel armModel = new EnglishSolitaireModel(3);
  EnglishSolitaireModel rowColModel = new EnglishSolitaireModel(3, 3);

  /**
   * This method tests that for each different type of constructor that the board is
   * initialized correctly by checking key SlotStates of the board.
   */
  @Test
  public void init_Board_Works() {
    Assert.assertEquals(emptyModel.board[1][1], MarbleSolitaireModelState.SlotState.Invalid);
    Assert.assertEquals(emptyModel.board[3][3], MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(emptyModel.board[3][5], MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertNotEquals(emptyModel.board[3][5], emptyModel.board[3][3]);

    Assert.assertEquals(fullModel.board[1][1], MarbleSolitaireModelState.SlotState.Invalid);
    Assert.assertEquals(fullModel.board[3][3], MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(fullModel.board[3][5], MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertEquals(fullModel.board[3][5], emptyModel.board[3][5]);
    Assert.assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(3, 7, 20));
    Assert.assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(4, 3, 3));

    Assert.assertEquals(armModel.board[1][1], MarbleSolitaireModelState.SlotState.Invalid);
    Assert.assertEquals(armModel.board[3][3], MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(armModel.board[3][5], MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertNotEquals(armModel.board[3][5], armModel.board[3][3]);
    Assert.assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(4));
    Assert.assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(-5));
    Assert.assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(243));

    Assert.assertEquals(rowColModel.board[1][1], MarbleSolitaireModelState.SlotState.Invalid);
    Assert.assertEquals(rowColModel.board[3][3], MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(rowColModel.board[3][5], MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertNotEquals(rowColModel.board[3][5], rowColModel.board[3][3]);
    Assert.assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(-10, 3));
    Assert.assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(0, 1));
    Assert.assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(3, 15));

  }

  @Test
  public void getBoardSize() {
    Assert.assertEquals(emptyModel.getBoardSize(), 7);
    Assert.assertEquals(new EnglishSolitaireModel(5).getBoardSize(), 13);
    Assert.assertEquals(new EnglishSolitaireModel(5, 7, 7).getBoardSize(), 13);
    Assert.assertEquals(rowColModel.getBoardSize(), 7);
  }

  @Test
  public void test_getSlot() {
    Assert.assertEquals(emptyModel.getSlotAt(1, 1), MarbleSolitaireModelState.SlotState.Invalid);
    Assert.assertEquals(emptyModel.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(emptyModel.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertThrows(IllegalArgumentException.class, () -> emptyModel.getSlotAt(10, 10));
    Assert.assertThrows(IllegalArgumentException.class, () -> emptyModel.getSlotAt(3, -1));
  }

  @Test
  public void test_getScore() {
    Assert.assertEquals(emptyModel.getScore(), 32);
  }

  @Test
  public void test_move() {
    EnglishSolitaireModel moveModel = new EnglishSolitaireModel();
    Assert.assertEquals(moveModel.board[3][3], MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(moveModel.board[3][4], MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertEquals(moveModel.board[3][5], MarbleSolitaireModelState.SlotState.Marble);
    moveModel.move(3, 5, 3, 3);
    Assert.assertEquals(moveModel.board[3][3], MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertEquals(moveModel.board[3][4], MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(moveModel.board[3][5], MarbleSolitaireModelState.SlotState.Empty);

    Assert.assertEquals(moveModel.board[3][2], MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertEquals(moveModel.board[3][3], MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertEquals(moveModel.board[3][4], MarbleSolitaireModelState.SlotState.Empty);
    moveModel.move(3, 2, 3, 4);
    Assert.assertEquals(moveModel.board[3][2], MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(moveModel.board[3][3], MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(moveModel.board[3][4], MarbleSolitaireModelState.SlotState.Marble);

    Assert.assertEquals(moveModel.board[1][2], MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertEquals(moveModel.board[2][2], MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertEquals(moveModel.board[3][2], MarbleSolitaireModelState.SlotState.Empty);
    moveModel.move(1, 2, 3, 2);
    Assert.assertEquals(moveModel.board[1][2], MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(moveModel.board[2][2], MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(moveModel.board[3][2], MarbleSolitaireModelState.SlotState.Marble);

    Assert.assertEquals(moveModel.board[5][3], MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertEquals(moveModel.board[4][3], MarbleSolitaireModelState.SlotState.Marble);
    Assert.assertEquals(moveModel.board[3][3], MarbleSolitaireModelState.SlotState.Empty);
    moveModel.move(5, 3, 3, 3);
    Assert.assertEquals(moveModel.board[5][3], MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(moveModel.board[4][3], MarbleSolitaireModelState.SlotState.Empty);
    Assert.assertEquals(moveModel.board[3][3], MarbleSolitaireModelState.SlotState.Marble);

    Assert.assertEquals(moveModel.getScore(), 28);

    EnglishSolitaireModel throwModel = new EnglishSolitaireModel();

    Assert.assertThrows(IllegalArgumentException.class,
            () -> throwModel.move(-10, 3, 3, 4));
    Assert.assertThrows(IllegalArgumentException.class,
            () -> throwModel.move(4, 3, 3, 15));
    Assert.assertThrows(IllegalArgumentException.class,
            () -> throwModel.move(3, 3, 3, 5));
    Assert.assertThrows(IllegalArgumentException.class,
            () -> throwModel.move(3, 4, 3, 6));
    Assert.assertThrows(IllegalArgumentException.class,
            () -> throwModel.move(3, 4, 3, 2));
    Assert.assertThrows(IllegalArgumentException.class,
            () -> throwModel.move(3, 0, 3, 3));
    // 2 away but diagonal
    Assert.assertThrows(IllegalArgumentException.class,
            () -> moveModel.move(4, 0, 2, 2));





  }

  @Test
  public void isValidMove() {
    Assert.assertEquals(emptyModel.validMove(3, 5, 3, 3), true);
    Assert.assertEquals(emptyModel.validMove(0, 0, 0, 2), false);
    Assert.assertEquals(emptyModel.validMove(6, 6, 7, 7), false);
    Assert.assertEquals(emptyModel.validMove(10, 10, 10, 12), false);
  }

  @Test
  public void isGameOver() {
    Assert.assertEquals(emptyModel.isGameOver(), false);
    EnglishSolitaireModel gameOverModel = new EnglishSolitaireModel();
    emptyModel.move(3, 1, 3, 3);
    emptyModel.move(3, 4, 3, 2);
    emptyModel.move(5, 4, 3, 4);
    emptyModel.move(5, 2, 5, 4);
    emptyModel.move(4, 2, 4, 4);
    emptyModel.move(2, 2, 4, 2);
    emptyModel.move(0, 2, 2, 2);
    emptyModel.move(0, 4, 0, 2);
    emptyModel.move(1, 4, 1, 2);
    emptyModel.move(1, 2, 3, 2);
    emptyModel.move(4, 2, 2, 2);
    emptyModel.move(3, 5, 3, 3);
    emptyModel.move(3, 3, 1, 3);
    emptyModel.move(2, 5, 2, 3);
    emptyModel.move(2, 2, 2, 4);
    emptyModel.move(2, 0, 2, 2);
    emptyModel.move(4, 0, 2, 0);
    emptyModel.move(4, 5, 4, 3);
    emptyModel.move(6, 4, 4, 4);
    emptyModel.move(6, 2, 6, 4);
    emptyModel.move(4, 4, 4, 2);
    emptyModel.move(4, 1, 4, 3);
    Assert.assertEquals(emptyModel.getScore(), 10);
    Assert.assertEquals(emptyModel.isGameOver(), true);

  }
}