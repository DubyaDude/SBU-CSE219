package demo;

import java.io.PrintStream;
import java.util.Scanner;
import jtps.jTPS;
import jtps.jTPS_Transaction;

/**
 * This driver demonstrates simple usage of the jTPS API.
 * 
 * @author THE McKilla Gorilla (accept no imposters)
 * @version 2.0
 */
public class jTPS_Tester {
    // HERE'S OUR TRANSACTION PROCESSING SYSTEM
    static jTPS tps = new jTPS();
    
    // HERE'S THE DATA WE'RE MANIPULATING IN THIS DEMO
    static Num num = new Num();
    
    // THESE ARE TO HELP WITH I/O
    static Scanner input = new Scanner(System.in);
    static PrintStream out = System.out;

    /**
     * This runs our demo program. Note that it presents a 
     * menu, retrieves the input, and executes the selected
     * behavior.
     * 
     * @param args Not used in this demo.
     */
    public static void main(String[] args) {
        // LOOP FLAG VARIABLE
        boolean keepGoing = true;
        while (keepGoing) {
            // DISPLAY THE CURRENT TPS
            out.println("CURRENT jTPS:");
            out.println(tps);
            out.println();
            
            // DISPLAY NUM
            out.println("num is " + num.getNum());
            out.println();
            
            // DISPLAY THE MENU
            out.println("ENTER A SELECTION");
            out.println("1) Add a Transaction");
            out.println("2) Undo a Transaction");
            out.println("3) Redo a Transaction");
            out.println("4) Clear All Transactions");
            out.println("5) Reset Num and Transactions");
            out.print("-");

            // GET THE USER SELECTION
            String entry = input.nextLine();
            
            // ADD AND EXECUTE A TRANSACTION
            if (entry.startsWith("1")) {
                System.out.print("\nEnter an amount to add: ");
                entry = input.nextLine();
                int amountToAdd = Integer.parseInt(entry);
                jTPS_Transaction transaction = new AddToNum_Transaction(num, amountToAdd);
                tps.addTransaction(transaction);
            }            
            // UNDO A TRANSACTION
            else if (entry.startsWith("2")) {
                tps.undoTransaction();
            }
            // REDO A TRANSACTION
            else if (entry.startsWith("3")) {
                tps.doTransaction();
            }
            // CLEAR ALL TRANSACTIONS
            else if (entry.startsWith("4")) {
                tps.clearAllTransactions();
            }
            // CLEAR ALL TRANSACTIONS AND RESET NUM TO 0
            else if (entry.startsWith("5")) {
                tps.clearAllTransactions();
                num.setNum(0);
            }
            // QUIT
            else if (entry.startsWith("Q")) {
                keepGoing = false;
            }
        }
        System.out.println("GOODBYE");
    }
}