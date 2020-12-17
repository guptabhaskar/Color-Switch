package sample;

import java.io.Serializable;

class ObstacleState implements Serializable {
    private String type;
    private double pos;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPos() {
        return pos;
    }

    public void setPos(double pos) {
        this.pos = pos;
    }

    public ObstacleState(String type, double pos) {
        this.type = type;
        this.pos = pos;
    }
}
