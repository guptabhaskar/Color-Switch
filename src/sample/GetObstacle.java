package sample;

// Factory Design Pattern
public class GetObstacle {
    public Obstacle getObstacle(int i){
        if(i == 0){
            return new Rectangle();
        }
        if(i==1) {
            return new Concentric();
        }
        else if(i==2){
            return new Eight();
        }
        else if(i==3) {
            return new Plus();
        }
        else if(i==4) {
            return new NormalCircle();
        }

        return null;
    }
}
