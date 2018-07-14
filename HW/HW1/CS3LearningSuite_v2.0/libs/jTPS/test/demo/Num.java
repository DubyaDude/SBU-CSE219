package demo;

/**
 * Num.java
 * 
 * This class serves as the data class that our transactions
 * will manipulate. It's just an integer wrapper class.
 * 
 * @author THE McKilla Gorilla (accept no imposters)
 * @version 2.0
 */
public class Num {
    // THE NUMBER THIS CLASS MANAGES
    private int num = 0;

    /**
     * Mutator method for the num instance variable.
     * 
     * @param initNum The value to set num to.
     */
    public void setNum(int initNum) {
        num = initNum;
    }
 
    /**
     * Accessor method for num.
     * 
     * @return The num instance variable value.
     */
    public int getNum() {
        return num;
    }
}