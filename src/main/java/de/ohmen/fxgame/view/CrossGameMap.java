package de.ohmen.fxgame.view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This game board shape is created in 1779 by J. C. Wiegleb from Germany with 45 holes
 * 
 * @author Joern Ohmen
 *
 */
public class CrossGameMap implements GameMap {
	
	private final static int SQUARE_XY_COUNT = 11;
	private final static Image BACKGROUND_IMAGE = new Image(CrossGameMap.class.getResource("/images/background.png").toString());
	
	private Group mainGroup; 
	private Group knightGroup; 
	private SquareView activeSquare;
	private SquareView[][] squareViews;
	
	/**
	 * Creates a new CrossGameMap 
	 */
	public CrossGameMap() {
		initCrossGameMap();
	}
	
	/**
	 * Initializes a new CrossGameMap
	 */
	private void initCrossGameMap() {
		knightGroup = new Group();
		mainGroup = new Group();
		squareViews = new SquareView[SQUARE_XY_COUNT][SQUARE_XY_COUNT];
		 
		/* Initialize all fields with knights and empty images */
		for (int x = 0; x < SQUARE_XY_COUNT; x++) {
			for (int y = 0; y < SQUARE_XY_COUNT; y++) {
				if ((x > 3 && x < 7 && y > 0 && y < 10 && y != 5) 
				        || (y > 3 && y < 7 && x > 0 && x < 10 && x != 5)) 
					squareViews[x][y] = new SquareView(this, x, y, SquareView.OCCUPIED);
				else if (y == 5 && x == 5)
					squareViews[x][y] = new SquareView(this, x, y, SquareView.EMPTY);
				else
					squareViews[x][y] = new SquareView(this, x, y, SquareView.INACCESSIBLE);
			}
		} 
		
		/* Set the position of all the layers and add all the squares to a group */
		for (int x = 0; x < SQUARE_XY_COUNT; x++) {
			for (int y = 0; y < SQUARE_XY_COUNT; y++) {
				if (squareViews[x][y] != null) {
					squareViews[x][y].setTranslateX(x * SQUARE_PX_WIDTH);
					squareViews[x][y].setTranslateY(y * SQUARE_PX_HEIGHT);	
					knightGroup.getChildren().add(squareViews[x][y]);
				}
			}
		}
		ImageView backgroundView = new ImageView(BACKGROUND_IMAGE);
		mainGroup = new Group(backgroundView, knightGroup);
	}

	/**
	 * Returns the Group this map belongs to.
	 * 
	 * @return Group
	 */
	public Group getMainGroup() {
		return mainGroup;
	}
	
	/**
	 * Returns the SquareView array of this Map
	 * 
	 * @return SquareView[][]
	 */
	public SquareView[][] getSquareViews(){
		return squareViews;
	}
	
	/**
	 * Sets the selected square
	 * 
	 * @param activeSquare
	 */
	@Override
	public void setActiveSquare(SquareView activeSquare) {
		this.activeSquare = activeSquare;
	}
 
	/**
     * Returns the selected square
     * 
     * @return SquareView
     */
	@Override
	public SquareView getActiveSquare() {
		return activeSquare;
	}

	/**
     * Returns the size of SquareView array belonging to this map
     * 
     * @return SquareView[][]
     */
	@Override
	public int getSquareXYCount() {
		return SQUARE_XY_COUNT;
	}

    @Override
    public Image getBackgroundImage() {        
        return BACKGROUND_IMAGE;
    }

}
