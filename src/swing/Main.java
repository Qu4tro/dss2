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
    public static void main (String [] args){
        KillBill killbill = new KillBill();
        
        Login inicial = new Login (killbill);
        inicial.setVisible(true);
    }
}
