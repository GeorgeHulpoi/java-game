package Engine.Core;

public class Force
{
    Point<Double> direction; // normalizat (-1, 1)
    double magtitude;

    public Force(Point<Double> direction, double magtitude)
    {
        this.direction = direction;
        this.magtitude = magtitude;
    }

    public void setMagtitude(double magtitude)
    {
        this.magtitude = magtitude;
    }

    public Point<Double> LengthDirection(double length)
    {
        return new Point<Double>(new Double(direction.getX() * length), new Double(direction.getY() * length));
    }

    public Point<Double> getDirection()
    {
        return direction;
    }

    public void setDirection(Point<Double> direction)
    {
        this.direction = direction;
    }

    public double getMagtitude()
    {
        return magtitude;
    }

    public double getRadAngle()
    {
        return Math.atan2(direction.getY(), direction.getX());
    }

    public double getDegreeAngle()
    {
        return getRadAngle() * 180 / Math.PI;
    }

    // https://www.youtube.com/watch?v=RY2gyAWsiVI
    public static Force resultant(Force f1, Force f2)
    {
        double i1 = f1.magtitude * Math.cos(f1.getRadAngle());
        double j1 = f1.magtitude * Math.sin(f1.getRadAngle());

        double i2 = f2.magtitude * Math.cos(f2.getRadAngle());
        double j2 = f2.magtitude * Math.sin(f2.getRadAngle());

        double ir = i1 + i2;
        double jr = j1 + j2;

        double rad = Math.atan2(jr, ir);

        double x = Math.cos(rad);
        double y = Math.sin(rad);

        double m = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        return new Force(new Point<Double>(x, y), m);
    }
}
