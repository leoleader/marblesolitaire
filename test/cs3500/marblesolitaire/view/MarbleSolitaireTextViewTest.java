package cs3500.marblesolitaire.view;

import org.junit.Assert;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * This class tests the method in the MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest {

  EnglishSolitaireModel model1 = new EnglishSolitaireModel();
  EnglishSolitaireModel model2 = new EnglishSolitaireModel(1);
  EnglishSolitaireModel model3 = new EnglishSolitaireModel(3, 0, 2);

  MarbleSolitaireTextView test1 = new MarbleSolitaireTextView(model1);
  MarbleSolitaireTextView test2 = new MarbleSolitaireTextView(model2);

  MarbleSolitaireTextView test3 = new MarbleSolitaireTextView(model3);

  /**
   * Tests that constructor throws IlleaglArgumentException when given null object.
   */
  @Test
  public void textViewThrows() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new MarbleSolitaireTextView(null));;
  }
  /**
   * Tests the toString method to see that it displays the board correctly.
   */
  @Test
  public void toStringWorks() {
    Assert.assertEquals(test1.toString(), "    O O O    " + "\n" +
            "    O O O    " + "\n" + "O O O O O O O" + "\n" +
            "O O O _ O O O" + "\n" +
            "O O O O O O O" + "\n" +
            "    O O O    " + "\n" +
            "    O O O    ");
    Assert.assertEquals(test3.toString(), "    _ O O    " + "\n" +
            "    O O O    " + "\n" + "O O O O O O O" + "\n" +
            "O O O O O O O" + "\n" +
            "O O O O O O O" + "\n" +
            "    O O O    " + "\n" +
            "    O O O    ");
    Assert.assertEquals(test2.toString(), "_");

    EnglishSolitaireModel moveModel = new EnglishSolitaireModel();
    MarbleSolitaireTextView test4 = new MarbleSolitaireTextView(moveModel);
    moveModel.move(3, 1, 3, 3);
    moveModel.move(3, 4, 3, 2);
    moveModel.move(5, 4, 3, 4);
    moveModel.move(5, 2, 5, 4);
    moveModel.move(4, 2, 4, 4);
    moveModel.move(2, 2, 4, 2);
    moveModel.move(0, 2, 2, 2);
    moveModel.move(0, 4, 0, 2);
    moveModel.move(1, 4, 1, 2);
    moveModel.move(1, 2, 3, 2);
    moveModel.move(4, 2, 2, 2);
    moveModel.move(3, 5, 3, 3);
    moveModel.move(3, 3, 1, 3);
    moveModel.move(2, 5, 2, 3);
    moveModel.move(2, 2, 2, 4);
    moveModel.move(2, 0, 2, 2);
    moveModel.move(4, 0, 2, 0);
    moveModel.move(4, 5, 4, 3);
    moveModel.move(6, 4, 4, 4);
    moveModel.move(6, 2, 6, 4);
    moveModel.move(4, 4, 4, 2);
    moveModel.move(4, 1, 4, 3);
    Assert.assertEquals(test4.toString(), "    O _ _    " + "\n" +
            "    _ O _    " + "\n" + "O _ O _ O _ O" + "\n" +
            "_ _ _ _ _ _ O" + "\n" +
            "_ _ _ O _ _ O" + "\n" +
            "    _ _ _    " + "\n" +
            "    _ _ O    ");


  }

}