/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;

/**
 *
 * @author Mauricio G. Coello
 */
public class QueensGA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int boardSize = 8;
        final int pop = 2048;
        final int maxGen = 100000;
        final float crossover = 0.8f;
        final float elitism = 0.1f;
        final float mutation = 0.1f;
        long startTime = System.currentTimeMillis();
        

        Generation gen = new Generation(boardSize, pop, elitism, mutation, crossover);

        int i = 0;
        int[] members = gen.Evolve();

        while ((i++ <= maxGen) && (members.length != boardSize)) {
            members = gen.Evolve();
        }
        long endTime = System.currentTimeMillis();
        if(members.length == boardSize){
            System.out.println("Found a solution in: " + (endTime-startTime) + " and in " + i +  " generations.");
            createAndShowGui(members);
        }
        else
            System.out.println("NO LUCK :(");
    }
    
    private static void createAndShowGui(int[] x) {
        Tablero8Q mainPanel = new Tablero8Q(x);

        JFrame frame = new JFrame("imgTablero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}

