package com.almasoft.cubes;

import static com.almasoft.cubes.RotationGroup.a;
import static com.almasoft.cubes.RotationGroup.r;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Side {
    
    /**
     * internalMask[0] -- array of {0,1} of size 4 for North edge of Side
     * internalMask[1] -- --//-- for East edge of Side
     * internalMask[2] -- --//-- for South edge of Side
     * internalMask[3] -- --//-- for West edge of Side
     * 
     * So we use clockwise numeration of edges starting from North edge
     */
    private int[][] internalMask = new int[4][4];
    /**
     * Cached set of rotations for given Side
     */
    private Set<Side> rotations = null;
    
    
    /**
     * Encodes form human readable array to internal representation:
     *    
     *|o o o a[0] 
      |ooooo a[1]
      | ooo  a[2] 
      |ooooo a[3]
      |o o o a[4]
 
     * 
     * @param human representation of Side.
     */
    public Side encode(String...a){
        if(a == null || a.length != 5 || 
                a[0] == null || a[0].length() != 5 ||
                a[1] == null || a[1].length() != 5 || 
                a[2] == null || a[2].length() != 5 ||
                a[3] == null || a[3].length() != 5 ||
                a[4] == null || a[4].length() != 5){
            throw new IllegalArgumentException();
        } 
        
        Encoder e = new Encoder(a);
        
        north(e.enc(0,1), e.enc(0,2), e.enc(0,3), e.enc(0,4));
        east (e.enc(1,4), e.enc(2,4), e.enc(3,4), e.enc(4,4));
        south(e.enc(4,3), e.enc(4,2), e.enc(4,1), e.enc(4,0));
        west (e.enc(3,0), e.enc(2,0), e.enc(1,0), e.enc(0,0));
        
        return this;
    }
    
    /**
     * @return all unique rotations of given piece including original 
     */
    public Set<Side> iterateOverAllRotations(){
        if(rotations == null){

            rotations = new HashSet<>();
            
            rotations.add(this);
            rotations.add(r(this));
            rotations.add(r(r(this)));
            rotations.add(r(r(r(this))));
            rotations.add(a(this));
            rotations.add(r(a(this)));
            rotations.add(r(r(a(this))));
            rotations.add(r(r(r(a(this)))));
        }
        return rotations;
    }
    
    public Side north(int...mask){
        internalMask[0] = mask;
        return this;
    }
    public int[] north(){
        return internalMask[0];
    }
    public Side east(int...mask){
        internalMask[1] = mask;
        return this;
    }
    public int[] east(){
        return internalMask[1];
    }
    public Side south(int...mask){
        internalMask[2] = mask;
        return this;
    }
    public int[] south(){
        return internalMask[2];
    }
    public Side west(int...mask){
        internalMask[3] = mask;
        return this;
    }
    public int[] west(){
        return internalMask[3];
    }

    
    public String line(int i) {
        return new Encoder(null).decode(i);
    }
    @Override
    public String toString() {
        Encoder e = new Encoder(null);
        return String.format("%s%n%s%n%s%n%s%n%s", 
                e.decode(0),
                e.decode(1),
                e.decode(2),
                e.decode(3),
                e.decode(4));
    }
    @Override
    public int hashCode() {
        return 3 * Arrays.hashCode(north()) + 5 * Arrays.hashCode(west()) + 7 * Arrays.hashCode(south()) + 31 * Arrays.hashCode(east());
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Side other = (Side) obj;
        if (!Arrays.deepEquals(internalMask, other.internalMask))
            return false;
        return true;
    }
    private class Encoder{
        private String[] encoded;

        public Encoder(String[] encoded){
            this.encoded = encoded;
        }
        private int enc(int row, int column){
            char c = encoded[row].charAt(column);
            if(c == 'o'){
                return 1;
            }else if(c == ' '){
                return 0;
            }else{
                throw new IllegalArgumentException("Wrong encode character: "+c);
            }
        }
        private String decode(int line){
            if(line == 0){
                return String.format("%s%s%s%s%s",d(west()[3]), d(north()[0]), d(north()[1]), d(north()[2]), d(north()[3]));
            }else if(line == 1){
                return String.format("%sooo%s",d(west()[2]), d(east()[0]));
            }else if(line == 2){
                return String.format("%sooo%s",d(west()[1]),d(east()[1]));
            }else if(line == 3){
                return String.format("%sooo%s",d(west()[0]),d(east()[2]));
            }else if(line == 4){
                return String.format("%s%s%s%s%s",d(south()[3]),d(south()[2]),d(south()[1]),d(south()[0]), d(east()[3]));
            }else{
                throw new IllegalArgumentException("Wrong line numbe given: "+line);
            }
        }
        private char d(int i){
            return i == 0 ? ' ' : 'o';
        }
    }

    
}
