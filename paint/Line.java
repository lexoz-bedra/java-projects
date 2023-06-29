import java.awt.*;

public class Line {
    Point firstPoint;
    Point secondPoint;
    public Color color;
    public int stroke;

    Line(Point firstPoint, Point secondPoint, Color color, int stroke){
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.color = color;
        this.stroke = stroke;

    }
}
