package de.ohmen.fxgame.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.ohmen.fxgame.PegSolitaire;
import de.ohmen.fxgame.test.helper.JavaFxJUnit4ClassRunner;
 
@RunWith(JavaFxJUnit4ClassRunner.class) 
public class FxGameTest {

    @Test
    public void initScene(){
        PegSolitaire fxGame = new PegSolitaire();
        Assert.assertNotNull(fxGame.initGameScene());
    }
    
}
