package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;

public class TryController {
    // Constructor
    public TryController(){
        RotateT.start();
    }

    // For Rotating Groups
    @FXML private Group Triangle;
    @FXML private Group Rectangle;
    @FXML private Group Eight1;
    @FXML private Group Eight2;
    @FXML private Group Concentric1;
    @FXML private Group Concentric2;
    @FXML private Group Plus1;
    @FXML private Group Plus2;
    @FXML private SVGPath Star;
    private double diff=0.01;
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
            Rotate REight1 = new Rotate();
            REight1.setAngle(1);
            REight1.setPivotX(0);
            REight1.setPivotY(0);
            Eight1.getTransforms().add(REight1);
            Rotate REight2 = new Rotate();
            REight2.setAngle(-1);
            REight2.setPivotX(0);
            REight2.setPivotY(0);
            Eight2.getTransforms().add(REight2);
            Rotate RConcentric1 = new Rotate();
            RConcentric1.setAngle(1);
            RConcentric1.setPivotX(0);
            RConcentric1.setPivotY(0);
            Concentric1.getTransforms().add(RConcentric1);
            Rotate RConcentric2 = new Rotate();
            RConcentric2.setAngle(-1);
            RConcentric2.setPivotX(0);
            RConcentric2.setPivotY(0);
            Concentric2.getTransforms().add(RConcentric2);
            Rotate RPlus1 = new Rotate();
            RPlus1.setAngle(1);
            RPlus1.setPivotX(64);
            RPlus1.setPivotY(0);
            Plus1.getTransforms().add(RPlus1);
            Rotate RPlus2 = new Rotate();
            RPlus2.setAngle(-1);
            RPlus2.setPivotX(64);
            RPlus2.setPivotY(0);
            Plus2.getTransforms().add(RPlus1);

            Star.setScaleX(Star.getScaleX()+diff);
            Star.setScaleY(Star.getScaleY()+diff);
            if(Star.getScaleX()>=2.5) {
                diff *= -1;
            } else if(Star.getScaleX()<=2.0) {
                diff *= -1;
            }
        }
    }

}
