package com.almasoft.cubes;

import static com.almasoft.cubes.RotationGroup.a;
import static com.almasoft.cubes.RotationGroup.r;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class SideTest {
    @Test public void testSymmetryRotations(){
        Side symmetrySide = new Side().north(0,1,0,0).east(0,1,0,0).south(0,1,0,0).west(0,1,0,0);
        Set<Side> r = symmetrySide.iterateOverAllRotations();
        
        r.forEach(s -> System.out.println(s.toString()));
        
        
        Assert.assertEquals(1, r.size());
        Assert.assertTrue(r.contains(symmetrySide));
    }
    @Test public void testASymmetryRotations(){
        Side symmetrySide = new Side().north(1,1,0,0).east(0,1,0,0).south(0,1,0,0).west(0,1,0,1);
        System.out.println(symmetrySide);
        System.out.println("------------------");
        Set<Side> r = symmetrySide.iterateOverAllRotations();
        
        r.forEach(s -> System.out.println(s.toString()));
        
        
    }
    @Test public void equalsTest(){
        Side a = new Side().north(0,1,0,0).east(0,1,0,0).south(0,1,0,0).west(0,1,0,0);
        
        
        Assert.assertEquals(a,r(a));
        Assert.assertEquals(a,a(r(a)));
        
        Set<Side> s = new HashSet<Side>();
        s.add(a);
        s.add(r(a));
        s.add(a(a));
        Assert.assertEquals(1, s.size());
    }
    @Test public void encodeTest(){
        Side a = new Side().north(0,1,0,0).east(0,1,0,0).south(0,1,0,0).west(0,1,0,0);
        
        Side b = new Side();
        b.encode(
                "  o  ", 
                " ooo ",
                "ooooo",
                " ooo ",
                "  o  ");
        
        
        Assert.assertEquals(a,b);
        
        a = new Side().north(0,1,1,0).east(0,1,0,0).south(0,1,0,0).west(0,1,1,1);
        
        b = new Side();
        b.encode(
                "o oo ", 
                "oooo ",
                "ooooo",
                " ooo ",
                "  o  ");
        
        
        Assert.assertEquals(a,b);
    }
}
