package de.ohmen.fxgame.test;

import org.junit.Assert;
import org.junit.Test;

import de.ohmen.fxgame.view.CrossGameMap;

public class CrossGameMapTest {

    private CrossGameMap crossMapTest;
    
    @Test
    public void initCrossgameMap() {
        crossMapTest = new CrossGameMap();   
        Assert.assertTrue(true);
    }
    
    @Test
    public void checkCrossGameMapValues() {
        crossMapTest = new CrossGameMap();
        Assert.assertTrue(crossMapTest.getSquareXYCount() > 0);
        Assert.assertTrue(crossMapTest.getSquareViews().length > 0);        
    }
    
    @Test
    public void checkCrossGameMapMainGroup() {
        crossMapTest = new CrossGameMap();
        Assert.assertNotNull(crossMapTest.getMainGroup());
    }
    
}
