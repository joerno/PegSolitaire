package de.ohmen.fxgame.view;

import de.ohmen.fxgame.controller.SquareEventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents one field of the game. It can have different conditions, an empty field, 
 * an field where no moves are allowed and so on...
 * 
 * @author Joern Ohmen
 *
 */
public class SquareView extends ImageView {

    public final static Image KNIGHT_IMAGE = new Image(SquareView.class.getResource("/images/knight.png").toString());
    public final static Image EMPTY_HIGHTLIGHT_IMAGE = new Image(SquareView.class.getResource("/images/empty_highlight.png").toString());

	public static final int INACCESSIBLE = 0;  
	public static final int OCCUPIED = 1;  
	public static final int EMPTY = 2;  
	public static final int EMPTY_HIGHLIGHT = 3;  
    
	private GameMap gameMap;
	private int xPos, yPos;
	private int currentState;
	
	public SquareView(GameMap gameMap, int xPos, int yPos, int currentState) {
		this.setCurrentState(currentState);;
		this.xPos = xPos;
		this.yPos = yPos;
		this.gameMap = gameMap;
		setOnMousePressed(new SquareEventHandler());
	}
	
	public void setCurrentState(int currentState) {
		this.currentState = currentState;
		switch(currentState) {
			case (INACCESSIBLE): this.setImage(null); break;
			case (OCCUPIED): this.setImage(KNIGHT_IMAGE); break;
			case (EMPTY): this.setImage(null); break;
			case (EMPTY_HIGHLIGHT): this.setImage(EMPTY_HIGHTLIGHT_IMAGE); break;
		}
	}
	
	public int getCurrentState() {
		return currentState;
	}
	
	/**
	 * Returns the GameMap of this SquareView
	 * 
	 * @return gameMap
	 */
	public GameMap getGameMap() {
		return gameMap;
	}
	
	/**
	 * Set the GameMap of this SquareView
	 * 
	 * @param gameMap
	 */
	public void setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}

	/**
	 * Returns the x position of this SquareView on the GameMap
	 * 
	 * @return
	 */
	public int getxPos() {
		return xPos;
	}
	
	/**
	 * Set the x position of this SquareView on the GameMap
	 * 
	 * @param xPos
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * Returns the y position of this SquareView on the GameMap
	 * 
	 * @return
	 */
	public int getyPos() {
		return yPos;
	}
	
	/**
	 * Set the y position of this SquareView on the GameMap
	 * 
	 * @param yPos
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
}
