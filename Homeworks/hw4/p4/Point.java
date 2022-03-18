// This class does all that you need for this assignment in terms
// of creating and comparing points based on either x value or y value
// depending on whether it is a Pointx object or a Pointy object.
class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public double distance(Point other) {
	double xDiff = this.x - other.x;
	double yDiff = this.y - other.y;
	return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }

    public String toString () {
	return "(" + x + "," + y + ")";
    }
}

// this class compares Points on x-coordinate
class Pointx extends Point implements Comparable{
    public Pointx(int x, int y) {
	super(x,y);
    }

    // returns negative value is this Point's x-coordinate is less
    // than the other Point's x-coordinate
    public int compareTo(Object o) {
        Point other = (Point) o;
        return this.x - other.x;
    }
}

// this class compares Points on y-coordinate
class Pointy extends Point implements Comparable{
    public Pointy(int x, int y) {
	super(x,y);
    }

    // returns negative value if this Point's y-coordinate is less
    // than the other Point's y-coordinate
    public int compareTo(Object o) {
        Point other = (Point) o;
        return this.y - other.y;
    }
}
