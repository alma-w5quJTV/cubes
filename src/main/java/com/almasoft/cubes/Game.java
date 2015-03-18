package com.almasoft.cubes;

import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;

public class Game {
    
    private PrintWriter writer;
    
    
    public Game(PrintWriter writer) {
        this.writer = writer;
    }


    public void calculate(Deque<Side> pieces, Cube cube){
        
        //we can fix first figure into A side of the cube
        if(cube.isEmpty()){
            Side first = pieces.pollFirst();
            cube.fillSide(first);
        }
        
        if(pieces.isEmpty()){
            onReadySolution(cube);
        }
        
        for(Side p : pieces){
            
            for(Side pp : p.iterateOverAllRotations()){
                if(cube.match(pp)){
                    //perform next induction iteration with smaller pieces set
                    Deque<Side> smallerPices = new LinkedList<>(pieces);
                    smallerPices.removeFirstOccurrence(p);
                    
                    Cube nextcube = new Cube(cube);
                    nextcube.fillSide(pp);
                    
                    calculate(smallerPices, nextcube);
                }
            }
        }
        
    }

    
    private void onReadySolution(Cube cube) {
        cube.print(writer);
        
        writer.println("------------------");
    }
    
}
