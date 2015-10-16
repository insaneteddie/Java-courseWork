public class StockMarket
{
	public static void main(String [] args)
	{
		Stock test1 = new Stock("Ftse","Footsie",100.00,150.00);
		Stock test2 = new Stock("NSDQ","Nasdaq",10.00,12.00);
		//double a = test1.getChangePercent();
		// for testing System.out.println(a);
		System.out.println("Name: "+ test1.getName() + "\nSymbol: "+test1.getSymbol()+"\nStockNumber:"+ test1.getStockNumber()+"\nPrice Change Percent: "+test1.getChangePercent()+"%");
		System.out.println("Name: "+ test2.getName() + "\nSymbol: "+test2.getSymbol()+"\nStockNumber:"+ test2.getStockNumber()+"\nPrice Change Percent: "+test2.getChangePercent()+"%");
	}
}