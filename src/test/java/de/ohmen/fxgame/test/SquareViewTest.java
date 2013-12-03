package de.ohmen.fxgame.test;

import org.junit.Assert;
import org.junit.Test;

import de.ohmen.fxgame.view.CrossGameMap;
import de.ohmen.fxgame.view.SquareView;

public class SquareViewTest {

    @Test
    public void createSquareView() {                
        Assert.assertNotNull(new SquareView(null, 0, 0, SquareView.INACCESSIBLE));
    }
        
    @Test
    public void testSquareViewValues() {                
        CrossGameMap crossMap = new CrossGameMap();        
        SquareView squareView = new SquareView(crossMap, 4, 4, SquareView.OCCUPIED);
        
        Assert.assertTrue(squareView.getCurrentState() == SquareView.OCCUPIED);
        Assert.assertNotNull(squareView.getGameMap());
        Assert.assertTrue(squareView.getxPos() == 4);
        Assert.assertTrue(squareView.getyPos() == 4);
    }
      
}
