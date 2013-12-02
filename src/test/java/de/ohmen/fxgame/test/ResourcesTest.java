package de.ohmen.fxgame.test;

import javafx.scene.image.ImageView;

import org.junit.Assert;
import org.junit.Test;

import de.ohmen.fxgame.view.CrossGameMap;
import de.ohmen.fxgame.view.GameMap;
import de.ohmen.fxgame.view.SquareView;

public class ResourcesTest {

    @Test
    public void checkImages() {        
        Assert.assertNotNull(new CrossGameMap().getBackgroundImage());
        Assert.assertNotNull(new ImageView(GameMap.FINISHED_IMAGE).getImage());
        Assert.assertNotNull(new ImageView(SquareView.KNIGHT_IMAGE).getImage());
        Assert.assertNotNull(new ImageView(SquareView.EMPTY_IMAGE).getImage());
        Assert.assertNotNull(new ImageView(SquareView.EMPTY_HIGHTLIGHT_IMAGE).getImage());
    }
}
