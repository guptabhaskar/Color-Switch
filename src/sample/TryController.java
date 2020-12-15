package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

public class TryController {
    // Constructor
    public TryController(){
        RotateT.start();
    }

    // For Rotating Groups
    @FXML private Group Triangle;
    @FXML private Group Rectangle;
    AnimationTimer RotateT = new Timer();
    private class Timer extends AnimationTimer{
        @Override
        public void handle(long time){
            Rotate RTriangle1 = new Rotate();
            RTriangle1.setAngle(1);
            RTriangle1.setPivotX(0);
            RTriangle1.setPivotY(0);
            Triangle.getTransforms().add(RTriangle1);
            Rotate RRectangle1 = new Rotate();
            RRectangle1.setAngle(1);
            RRectangle1.setPivotX(0);
            RRectangle1.setPivotY(0);
            Rectangle.getTransforms().add(RRectangle1);
        }
    }

}
