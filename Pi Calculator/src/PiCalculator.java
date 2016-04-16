/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kmhasan
 */
public class PiCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SingleThreadedPiCalculator singleThreaded = new SingleThreadedPiCalculator();
        for (int terms = 1; terms <= 10; terms++)
            System.out.printf("Terms: %2d Pi: %.10f\n", terms, singleThreaded.getPi(terms));
    }
    
}
