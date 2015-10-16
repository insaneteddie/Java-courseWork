public class Circle extends GeometricObject implements Comparable
{
	private double radius;
	
	public Circle()
	{
	}
	
	public Circle(double radius)
	{
		this.radius = radius;
	}
	
	/** Return radius */
	public double getRadius()
	{
		return radius;
	}
	
	/** Set a new radius */
	public void setRadius(double radius)
	{
		this.radius = radius;
	}
	
	@Override /** Return area */
	public double getArea()
	{
		return radius * radius * Math.PI;
	}
	
	/** Return diameter */
	public double getDiameter()
	{
		return 2 * radius;
	}
	
	@Override /** Return perimeter */
	public double getPerimeter() {
		return 2 * radius * Math.PI;
	}
	
	public boolean equals(Circle x)
	{
		if(this.radius == ((Circle)x).getRadius())
		{
		return true;
		}
		else
		return false;
	}
	
	public int compareTo(Circle x)
	{
		//if (x instanceof Circle)
		//{	
			if(this.radius == x.getRadius())
				return 0;
			else if(this.radius > x.getRadius())
				return 1;
			else
				return -1;
		/*}
		else
			return 1;*/
	}
	
	/* Print the circle info */
	public void printCircle() {
		System.out.println("The circle is created " + getDateCreated() +
		" and the radius is " + radius);
	}
}