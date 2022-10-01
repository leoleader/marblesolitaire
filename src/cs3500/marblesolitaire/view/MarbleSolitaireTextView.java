package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This is the class for what will be the visual representation of the board.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  MarbleSolitaireModelState given;

  /**
   * This constructor takes in a game state and throws an exception
   * if the provided game state is null.
   * @param game the state that is being turned into a view.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState game) {
    if (game == null) {
      throw new IllegalArgumentException("The provided game model state can not be null");
    }
    this.given = game;
  }

  /**
   * this toString method iterates through the given board and
   * prints out the corresponding symbol.
   * @return a String that is the board
   */
  public String toString() {
    String game_view = "";
    for (int i = 0; i < given.getBoardSize(); i++) {
      if (i > 0) {
        game_view = game_view + "\n";
      }
      for (int j = 0; j < given.getBoardSize(); j++) {
        if (given.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          game_view = game_view + "O";
        }
        if (given.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          game_view = game_view + "_";
        }
        if (given.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          game_view = game_view + " ";
        }
        if (j < given.getBoardSize() - 1 ) {
          game_view = game_view + " ";
        }
      }
    }
    return game_view;
  }


}
