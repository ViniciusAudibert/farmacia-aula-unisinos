package br.com.farmacia.farmacia;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vini
 */
public class Run {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setUp();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
