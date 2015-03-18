package com.almasoft.cubes;
/**
 * a -- horizontal symmetry 
 * r -- clockwise rotation 
 * 
 * Rotation group has 8 elements and can be generated with one rotation and one symmetry
 */
public class RotationGroup {
    /**
     * 
     * @param x -- given side
     * @return new side, that horizontally rotated, (<code>a*x</code> - action of a on x)
     */
    public static Side a(Side x){
        return new Side()
            .north(x.south()[2],x.south()[1],x.south()[0],x.east()[3])
            .south(x.north()[2],x.north()[1],x.north()[0],x.west()[3])
            .east(x.east()[2],x.east()[1],x.east()[0],x.north()[3])
            .west(x.west()[2],x.west()[1],x.west()[0],x.south()[3]);
    }   
    /**
     * 
     * @param x -- given side
     * @return new side, that vertically rotated, (<code>b*x</code> - action of b on x)
     */
    public static Side r(Side x){
        return new Side()
        .north(x.west()[0],x.west()[1],x.west()[2],x.west()[3])
        .east(x.north()[0],x.north()[1],x.north()[2],x.north()[3])
        .south(x.east()[0],x.east()[1],x.east()[2],x.east()[3])
        
        .west(x.south()[0],x.south()[1],x.south()[2],x.south()[3])
        ;
    }
}
