//Stehen Harte 09001409
public class Stock
{
	//declaring data fields
	public String symbol;
	public String name;
	private int stockNumber = 1000;
	double previousClosingPrice;
	double currentPrice;
	//no arg constructor
	public Stock()
	{
	stockNumber = stockNumber++;
	}
	//constructor
	public Stock(String symb, String nam,double previousPrice, double currentPric)
	{
	symbol = symb;
	name = nam;
	stockNumber = stockNumber++;
	previousClosingPrice = previousPrice;
	currentPrice = currentPric;
	}
	public void setName(String newName)
	{
		name = newName;
	}
	public String getName()
	{
		return name;
	}
	public void setSymbol(String newSymbol)
	{
		symbol = newSymbol;
	}
	public String getSymbol()
	{
		return symbol;
	}
	public void setPreviousPrice(Double oldPrice)
	{
		previousClosingPrice = oldPrice;
	}
	public double getPreviousPrice()
	{
		return previousClosingPrice;
	}
	public void setCurrentPrice(Double newPrice)
	{
		currentPrice = newPrice;
	}
	public double getCurrentPrice()
	{
		return currentPrice;
	}
	public double getChangePercent()
	{
		double difference =  currentPrice - previousClosingPrice;
		double percentDiff = previousClosingPrice/difference * 100/1 ;
		return percentDiff;
	}
	public int getStockNumber()
	{
		return stockNumber;
	}
	public int getNumStocks()
	{
		int numberOfStockObjects = stockNumber - 1000;
		return numberOfStockObjects;
	}
}