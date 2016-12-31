/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import Classes.KillBill;

/**
 *
 * @author bruno
 */
public class Main {
    public static int strToInt( String str ){
    int i = 0;
    int num = 0;
    boolean isNeg = false;

    //Check for negative sign; if it's there, set the isNeg flag
    if (str.charAt(0) == '-') {
        isNeg = true;
        i = 1;
    }

    //Process each character of the string;
    while( i < str.length()) {
        num *= 10;
        num += str.charAt(i++) - '0'; //Minus the ASCII code of '0' to get the value of the charAt(i++).
    }

    if (isNeg)
        num = -num;
    return num;
}
    public static void main (String [] args){
        KillBill killbill = new KillBill();
        
        Login inicial = new Login (killbill);
        inicial.setVisible(true);
    }
}
