package com.almasoft.cubes;

import java.io.PrintWriter;

public class Cube {

    /**
     * Sides of the cube. The numeration is the following:
     * 
     * 0 1 2
     *   3
     *   4
     *   5
     *   
     * For more code reedability we use the following notation. See the correspondent methods: a(), b(), ... , f()
     * 
     * A B C
     *   D
     *   E
     *   F
     */
    private Side[] sides = new Side[6];
    /**
     * Current fill pointer;
     */
    private int pointer = 0;
    
    
    public Cube() {}
    /**
     * Copy constructor. creates semantic copy of given argument cube
     */
    public Cube(Cube cube) {
        System.arraycopy(cube.sides, 0, sides, 0, sides.length);
        this.pointer = cube.pointer;
    }
    
    
    /**
     * Verify correctness of putting side <code>p</code> to 
     * current <code>pointer</code> position 
     * 
     * The strategy is to fist check the vertex correctness, and then faces, since checking vertex correctness is faster.
     * 
     * @param p -- side matching of which we test for current cube state
     * @return true if the current position matches with given side
     */
    public boolean match(Side p) {
        //free cube accept any side
        if(pointer == 0) return true;
        
        if(pointer == 1){//means we have to test matching given side with sides[0]
            //1). test corner points (necessary condition)
            return  meetNecessaryMatchVertexCondition(a().north(), p.west()) &&
                    meetNecessaryMatchVertexCondition(a().east(), p.south()) &&
                    //2). test matching of sides
                    match(a().east(), p.west());
                    
        }else if(pointer == 2){
            return  meetNecessaryMatchVertexCondition(b().north(), p.west()) &&
                    meetNecessaryMatchVertexCondition(b().east(), p.south()) &&
                    match(b().east(), p.west());
        }else if(pointer == 3){
            return  
                    correctVertex(a().east(), b().south(), p.west()) && 
                    correctVertex(b().east(), c().south(), p.north()) && 
                    
                    meetNecessaryMatchVertexCondition(p.east(), c().east()) &&
                    meetNecessaryMatchVertexCondition(p.south(), a().south()) &&
                    
                    
                    match(p.north(), b().south()) &&
                    match(p.east(), c().south()) &&
                    match(p.west(), a().south());
        }else if(pointer == 4){
            return  
                    correctVertex(p.north(), d().east(), c().east()) && 
                    correctVertex(p.west(), d().south(), a().south()) && 
                    
                    meetNecessaryMatchVertexCondition(p.east(), c().north()) &&
                    meetNecessaryMatchVertexCondition(p.south(), a().west()) &&
                    
                    
                    match(p.north(), d().south()) &&
                    match(p.east(), c().east()) &&
                    match(p.west(), a().west());
        }else if(pointer == 5){
            return  
                    correctVertex(p.north(), e().east(), c().north()) && 
                    correctVertex(p.west(), e().south(), a().west()) && 
                    correctVertex(p.east(), c().west(), b().north()) && 
                    correctVertex(p.south(), b().west(), a().north()) && 
                    
                    
                    match(p.north(), e().south()) &&
                    match(p.east(), c().north()) &&
                    match(p.south(), b().north()) &&
                    match(p.west(), a().north());
        }else{
            throw new IllegalArgumentException("Wrong side pointer provided: "+pointer);
        }
    }
    /**
     * Necessary and sufficient condition to 3 edges constitute right vertex
     * 
     * @param a - edge a
     * @param b - edge b
     * @param c - edge c
     * 
     * @return true if and only if sides constitute correct cube vertex
     */
    private boolean correctVertex(int[] a, int[] b, int[] c){
        return a[3] + b[3] + c[3] == 1;
    }
    /**
     * Necessary condition for 2 edges a and b constitute correct cube vertex
     * @param a
     * @param b
     * 
     * @return true if necessary condition meets or false otherwise
     */
    private boolean meetNecessaryMatchVertexCondition(int[] a, int[] b){
        return a[3] + b[3] <= 1;
    }
    
    /**
     * Match following edges
     *     3
     *   0 2
     * a=1 1=b
     *   2 0
     *   3
     * @param a fist edge
     * @param b second edge
     * @return true -- if these edges match (means that can be used in puzzle)
     */
    private boolean match(int[] a, int[] b) {
        for(int i = 0 ; i < 3; i++){
            if(a[i] + b[2 - i] != 1) return false;
        }
        return true;
    }


    public void fillSide(Side pp) {
        sides[pointer++] = pp; 
    }
    /**
     * 
     * @return true if the cube is empty (means no one Side was put in)
     */
    public boolean isEmpty(){
        return pointer == 0;
    }

    public void print(PrintWriter out) {
        for (int i = 0; i < 5; i++) {
            out.println(String.format("%s%s%s",a().line(i), b().line(i), c().line(i)));
        }
        printSide(d(), out);
        printSide(e(), out);
        printSide(f(), out);
    }
    
    private void printSide(Side side, PrintWriter out){
        for (int i = 0; i < 5; i++) {
            out.println(String.format("     %s",side.line(i)));
        }
    }
    
    private Side a(){
        return sides[0];
    }
    private Side b(){
        return sides[1];
    }
    private Side c(){
        return sides[2];
    }
    private Side d(){
        return sides[3];
    }
    private Side e(){
        return sides[4];
    }
    private Side f(){
        return sides[5];
    }


}
