package de.ohmen.fxgame;
 
import org.apache.log4j.Logger;

import de.ohmen.fxgame.view.CrossGameMap;
import de.ohmen.fxgame.view.GameMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
/**
 * A little retro board game in JavaFx. Just for fun :) Remove all the figures from the map except the last.
 * 
 * Graphics created with Tiled: http://www.mapeditor.org/
 * Thanks for the images: http://opengameart.org/content/2d-lost-garden-zelda-style-tiles-resized-to-32x32-with-additions
 *  
 * @author Joern Ohmen
 *
 */
public class PegSolitaire extends Application {
 
    private static Logger logger =  Logger.getLogger(PegSolitaire.class);
    
    /**
     * Initializes a new JavaFx-Scene with a game map
     * 
     * @return Scene
     */
    public Scene initGameScene(){
        CrossGameMap crossGameMap = new CrossGameMap();
        Scene scene = new Scene(crossGameMap.getMainGroup(), 
                crossGameMap.getSquareXYCount() * GameMap.SQUARE_PX_WIDTH, 
                crossGameMap.getSquareXYCount() * GameMap.SQUARE_PX_HEIGHT);
        return scene;       
    }
        
    /**
     * Anchor for JavaFx to start the application
     * 
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Steckhalma");
        primaryStage.setScene(initGameScene());
        primaryStage.show();    	
        logger.info("JavaFx game started");
    } 
    
    /**
     * Usually ignored, only used if JavaFx can not not be started with start(Stage)
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}