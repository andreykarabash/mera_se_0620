package src.lesson10;

public class NavigatorCoordinate {
    private int x;
    private int y;
    private boolean isNode = false;
    private boolean isFinish = false;
    private int value;

    public NavigatorCoordinate(int x, int y, boolean isNode, int value) {
        this.x = x;
        this.y = y;
        this.isNode = isNode;
        this.value = value;
    }

    public NavigatorCoordinate() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isNode() {
        return isNode;
    }

    public void setNode(boolean node) {
        isNode = node;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
