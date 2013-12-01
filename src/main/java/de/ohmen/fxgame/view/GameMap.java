package de.ohmen.fxgame.view;

import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * Classes which implement this interface are the maps of the game.  
 * 
 * @author Joern Ohmen
 *
 */
public interface GameMap {

	public final static Image BACKGROUND_IMAGE = new Image(GameMap.class.getResource("/images/background.png").toString());
	public final static Image FINISHED_IMAGE = new Image(SquareView.class.getResource("/images/finished.png").toString());
	 
    public final static int SQUARE_PX_WIDTH = 32;
    public final static int SQUARE_PX_HEIGHT = 32;
    
    /**
     * Set the selected square, with the activated figure for the next step
     * 
     * @param gameSquare
     */
    public void setActiveSquare(SquareView gameSquare);
    
    /**
     * Returns the array of the map with all SquareViews
     * 
     * @return
     */
    public SquareView[][] getSquareViews();	
	
    /**
	 * Returns the selected square, with the activated figure for the next step
	 * 
	 * @return
	 */
    public SquareView getActiveSquare();
	
    /**
	 * Returns the number of fields in widht and height of the map
	 * 
	 * @return
	 */
    public int getSquareXYCount();
	
    /**
	 * Returns the Group this map belongs to
	 * 
	 * @return
	 */
    public Group getMainGroup();
    
}
