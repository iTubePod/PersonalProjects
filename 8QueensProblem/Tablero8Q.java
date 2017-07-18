/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Mauricio G. Coello
 */

@SuppressWarnings("serial")
public class Tablero8Q extends JPanel {
   private BufferedImage imgTablero;
   private BufferedImage imgQueenW;
   private ArrayList<Integer> pos = new ArrayList<>(8);


   public Tablero8Q(int[] x) {
      try {         
         imgTablero = ImageIO.read(new File("tablero.png") );
         imgQueenW = ImageIO.read(new File("ReinaB.png") );
                 System.out.println(Arrays.toString(x));
		 pos.add(x[0]);
		 pos.add(x[1]);
		 pos.add(x[2]);
		 pos.add(x[3]);
		 pos.add(x[4]);
		 pos.add(x[5]);
		 pos.add(x[6]);
		 pos.add(x[7]);
		 
      } catch (MalformedURLException e) {
         e.printStackTrace();
         System.exit(-1);
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(-1);
      }
	  
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (imgTablero != null && imgQueenW != null) {
         g.drawImage(imgTablero, 0, 0, this);
		 for(int i=0;i<8;i++)
           g.drawImage(imgQueenW, 35+54*i, 35+54*pos.get(i), this);         
      }
   }

   @Override
   public Dimension getPreferredSize() {
      if (imgTablero != null ) {
         int width = imgTablero.getWidth();
         int height = imgTablero.getHeight();
         return new Dimension(width , height );
      }
      return super.getPreferredSize();
   }
   
}