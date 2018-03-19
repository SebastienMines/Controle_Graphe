package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DefaultTeam {
	
  public ArrayList<Point> calculDominatingSet(ArrayList<Point> points, int edgeThreshold) {
	  
    ArrayList<Point> result = (ArrayList<Point>)points.clone();
    double distanceTotal = 0;
    
    //Se positionne sur un point i
    for (int i = 0; i < result.size(); i++) {
    	
		//Parcours tous les autres points afin de determiner les voisins de i
    	for( int j = 0; j < result.size(); j++) {
    		
    		//calcul la distance entre le point i et le point j.
    		distanceTotal = result.get(i).distance(result.get(j).getX(), result.get(j).getY());
        	    		    		
    		//Regarde sie le point j peux etre considere comme un voisin du point i
    		if(distanceTotal <= edgeThreshold) {
    			
    			//supprime les voisins afin de ne pas les considerer comme surveillant plustard
    			result.remove(j);
    			        		
        	}
    		
    	}	
    	
    }
  
    return result;
  }
  
  
  //FILE PRINTER
  public void saveToFile(String filename,ArrayList<Point> result){
	  
    int index=0;
    
    try {
    	
      while(true){
    	  
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(filename+Integer.toString(index)+".points")));
        
        try {
          input.close();
        } catch (IOException e) {
          System.err.println("I/O exception: unable to close "+filename+Integer.toString(index)+".points");
        }
        index++;
      }
      
    } catch (FileNotFoundException e) {
      printToFile(filename+Integer.toString(index)+".points",result);
    }
  }
  public void printToFile(String filename,ArrayList<Point> points){
	  
    try {
      PrintStream output = new PrintStream(new FileOutputStream(filename));
      int x,y;
      for (Point p:points) output.println(Integer.toString((int)p.getX())+" "+Integer.toString((int)p.getY()));
      output.close();
    } catch (FileNotFoundException e) {
      System.err.println("I/O exception: unable to create "+filename);
    }
    
  }

  //FILE LOADER
  public ArrayList<Point> readFromFile(String filename) {
	  
    String line;
    String[] coordinates;
    ArrayList<Point> points=new ArrayList<Point>();
    
    try {
      BufferedReader input = new BufferedReader(
          new InputStreamReader(new FileInputStream(filename))
          );
      try {
        while ((line=input.readLine())!=null) {
          coordinates=line.split("\\s+");
          points.add(new Point(Integer.parseInt(coordinates[0]),
                Integer.parseInt(coordinates[1])));
        }
      } catch (IOException e) {
        System.err.println("Exception: interrupted I/O.");
      } finally {
        try {
          input.close();
        } catch (IOException e) {
          System.err.println("I/O exception: unable to close "+filename);
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println("Input file not found.");
    }
    return points;
  }
}
