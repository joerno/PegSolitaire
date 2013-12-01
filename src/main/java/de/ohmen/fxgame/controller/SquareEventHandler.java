package de.ohmen.fxgame.controller;

import org.apache.log4j.Logger;

import de.ohmen.fxgame.view.GameMap;
import de.ohmen.fxgame.view.SquareView;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * This class handles the mouse events of a SquareView.
 * 
 * @author Joern Ohmen
 *
 */
public class SquareEventHandler implements EventHandler<MouseEvent> {

	private static Logger logger =  Logger.getLogger(SquareEventHandler.class);		
	 
	/**
	 * Handles mouse clicks on a square. It's the game logic, evaluates valid moves and 
	 * changes the state of the fields.  
	 * 
	 * @param mouseEvent
	 */
	@Override
	public void handle(MouseEvent me) {

		/* Get the current clicked object and its position on the map */
		SquareView currentClickedSquare = (SquareView) me.getSource();
		int currentxPos = currentClickedSquare.getxPos();
		int currentyPos = currentClickedSquare.getyPos();

		GameMap gameMap = currentClickedSquare.getGameMap();
		SquareView alreadyActiveSquare = gameMap.getActiveSquare();

		/* a knight was selected before */
		if (alreadyActiveSquare != null) {
			int activexPos = alreadyActiveSquare.getxPos();
			int activeyPos = alreadyActiveSquare.getyPos();
			
			switch (currentClickedSquare.getCurrentState()) {
				
			/* A highlighted filed is clicked (a valid move, so remove a knight) */	
			case (SquareView.ACCESSIBLE_EMPTY_HIGHLIGHT):
					
				/* remove a knight at removeX and removeY */
				int removeX = ((currentxPos != activexPos) ? Math.abs((currentxPos + activexPos) / 2) : activexPos);
				int removeY = ((currentyPos != activeyPos) ? Math.abs((currentyPos + activeyPos) / 2) : activeyPos);
					
				removeKnight(gameMap, currentClickedSquare, removeX, removeY);
				logger.info("Knights on map: " + countRemainingKnights(gameMap));
				break;
			
			/* a knight is clicked */
			case (SquareView.ACCESSIBLE_OCCUPIED):
				
				/* not the same knight like earlier activated */
				if (currentClickedSquare != alreadyActiveSquare) {
					activateKnight(gameMap, currentClickedSquare, alreadyActiveSquare);
				/* it's the same knight, deactivate him */
				} else {
					alreadyActiveSquare.setEffect(null);
					gameMap.setActiveSquare(null);
					clearPossibleFieldsFromHighlight(gameMap);
				}
				break;
			}
			/* nothing is selected yet, activate clicked knight */
		} else if (currentClickedSquare.getCurrentState() == SquareView.ACCESSIBLE_OCCUPIED) {
			activateKnight(gameMap, currentClickedSquare);
		}
		me.consume();
	}
	
	/**
	 * Activates the current selected figure
	 * 
	 * @param gameMap
	 * @param currentClickedSquare
	 */
	public void activateKnight(GameMap gameMap, SquareView currentClickedSquare) {
		currentClickedSquare.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.LIGHTGREEN, 8, 0.5, 0, 0));
		gameMap.setActiveSquare(currentClickedSquare);
		clearPossibleFieldsFromHighlight(gameMap);
		hightlightPossibleFields(gameMap, currentClickedSquare.getxPos(), currentClickedSquare.getyPos());
	}
	
	/**
	 * Activates the current selected figure and deactivates the one selected before
	 * 
	 * @param gameMap
	 * @param currentClickedSquare
	 * @param alreadyActiveSquare
	 */
	public void activateKnight(GameMap gameMap, SquareView currentClickedSquare, SquareView alreadyActiveSquare) {
		alreadyActiveSquare.setEffect(null);
		clearPossibleFieldsFromHighlight(gameMap);
		
		activateKnight(gameMap, currentClickedSquare);
	}
	
	/**
	 * Removes a knight from the map (changes the state of the affected fields)
	 * 
	 * @param gameMap
	 * @param currentClickedSquare
	 * @param x
	 * @param y
	 */
	public void removeKnight(GameMap gameMap, SquareView currentClickedSquare, int x, int y) {
		gameMap.getSquareViews()[x][y].setCurrentState(SquareView.ACCESSIBLE_EMPTY);
		gameMap.getActiveSquare().setEffect(null);
		gameMap.getActiveSquare().setCurrentState(SquareView.ACCESSIBLE_EMPTY);
		currentClickedSquare.setCurrentState(SquareView.ACCESSIBLE_OCCUPIED);
		gameMap.setActiveSquare(null);
		clearPossibleFieldsFromHighlight(gameMap);
		isFinished(gameMap);
	}

	/**
	 * When only one knight is left, the finish image is shown 
	 * 
	 * @param gameMap
	 */
	private void isFinished(GameMap gameMap) {
	    if (countRemainingKnights(gameMap) == 1) {
            ImageView finishedView = new ImageView(GameMap.FINISHED_IMAGE);
            Group finishedGroup = new Group(finishedView);
            gameMap.getMainGroup().getChildren().add(finishedGroup);                    
        }
	}

    /**
	 * Fields are highlighted if they are empty, two steps away and there is at 
	 * least one knight between the empty field and the first selected. Only valid in 
	 * x or y direction, not both. 
	 * 
	 * @param gameMap
	 * @param xPos
	 * @param yPos
	 */ 
	private void hightlightPossibleFields(GameMap gameMap, int xPos, int yPos){	    	    	   
		SquareView[][] squareViews = gameMap.getSquareViews();
		/* check side to the left from selection */
		if (xPos - 2 > 0 
				&& squareViews[xPos -2 ][yPos].getCurrentState() == SquareView.ACCESSIBLE_EMPTY 
				&& squareViews[xPos - 1 ][yPos].getCurrentState() == SquareView.ACCESSIBLE_OCCUPIED) {
	        squareViews[xPos - 2 ][yPos].setCurrentState(SquareView.ACCESSIBLE_EMPTY_HIGHLIGHT);
	    }
		/* check side to the right from selection */
	    if (xPos + 2 < gameMap.getSquareXYCount() 
	    		&& squareViews[xPos + 2][yPos].getCurrentState() == SquareView.ACCESSIBLE_EMPTY 
	    		&& squareViews[xPos + 1 ][yPos].getCurrentState() == SquareView.ACCESSIBLE_OCCUPIED) {
            squareViews[xPos + 2 ][yPos].setCurrentState(SquareView.ACCESSIBLE_EMPTY_HIGHLIGHT);
        }
	    /* check fields above selection */
	    if (yPos - 2 > 0 
	    		&& squareViews[xPos][yPos - 2].getCurrentState() == SquareView.ACCESSIBLE_EMPTY 
	    		&& squareViews[xPos][yPos - 1].getCurrentState() == SquareView.ACCESSIBLE_OCCUPIED) {
            squareViews[xPos][yPos - 2].setCurrentState(SquareView.ACCESSIBLE_EMPTY_HIGHLIGHT);
        }
	    /* check fields under selection */
	    if (yPos + 2 < gameMap.getSquareXYCount() 
	    		&& squareViews[xPos][yPos + 2].getCurrentState() == SquareView.ACCESSIBLE_EMPTY 
	    		&& squareViews[xPos][yPos + 1].getCurrentState() == SquareView.ACCESSIBLE_OCCUPIED) {
            squareViews[xPos][yPos + 2].setCurrentState(SquareView.ACCESSIBLE_EMPTY_HIGHLIGHT);
        }
	}
	
	/**
	 * Changes the state of highlighted fields to normal fields
	 * 
	 * @param gameMap
	 */
	private void clearPossibleFieldsFromHighlight(GameMap gameMap) {                     
	    for (int x = 0; x < gameMap.getSquareXYCount(); x++) 
            for (int y = 0; y < gameMap.getSquareXYCount(); y++) 
                if (gameMap.getSquareViews()[x][y] != null && gameMap.getSquareViews()[x][y].getCurrentState() == SquareView.ACCESSIBLE_EMPTY_HIGHLIGHT)
                	gameMap.getSquareViews()[x][y].setCurrentState(SquareView.ACCESSIBLE_EMPTY);
    }
	
	/**
	 * Counts the knights still on the map
	 * 
	 * @param gameMap
	 * @return
	 */
	private int countRemainingKnights(GameMap gameMap) {    
		int counter = 0;
		for (int x = 0; x < gameMap.getSquareXYCount(); x++) 
            for (int y = 0; y < gameMap.getSquareXYCount(); y++) 
				if (gameMap.getSquareViews()[x][y].getCurrentState() == SquareView.ACCESSIBLE_OCCUPIED)
					counter++;
		return counter;		
	}
}
