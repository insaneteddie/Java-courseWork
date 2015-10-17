import	java.util.*;//importing the full java util package
import	java.io.*;//importing the full IO package (FileReader and IOException)
import	java.text.*; //importing for the Date data type and methods
//im sure this can be cut down to methods but i cant seem to figure out where or how if anyone wants to stab at it work away.
public class G22LottoAnalysis
{
	public static int counter = 0;
	public static void main(String[]args) throws IOException
	{
		for(int a = 0; a < args.length; a++)//just turning the args array to upper case cause I think Annette will try and break it this way(I'm on to you!)
			args[a]= args[a].toUpperCase();
		boolean argsValid  = argsValid(args);
		//String results = "";
		if(!argsValid)
		{
			help(args,argsValid);
		}
		else
		{
			String [] fromFile; // declaration of fromFile array
			String pDate = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}";//pattern for date
			String pNum = "[0-9]{1,2}";//pattern for number
			String pYear = "[0-9]{4}";//pattern for year
			String pHELP = "HELP";
			int [] userNumbers = new int [args.length];//declaration of userNumber array
			int [] numbers = new int [45];// initialising number array for counting frequency
			int [] bonusNumber = new int [45];//bonus number array to make sure it doesn't count the bonus number when working the frequency
			int [] evenOdd = new int [4];
			//just boolean's for my validation
			String errorData ="No data for Draw Type specified within file/date range";
			boolean startDate = false; // boolean for Start date supplied
			boolean endDate = false; //boolean for end-date
			boolean yearSupplied = false;//boolean for year only
			boolean jackpotOnly = false;//for 6 numbers only
			boolean allNumbers = false;//for all numbers
			boolean allDrawDays = false;//all draw days
			boolean wedDraws = false;//Wednesdays only
			boolean satDraws = false;//Saturdays only
			boolean dataError = true; // assumes there is no matches
			File myFile = new File("lotto.txt");

			if(!myFile.exists())//to check if the file exists before trying to read it.
			{
				System.out.println("Error - Cannot locate File or File Does Not Exist");//error for file not found
			}
			else
			{
				FileReader myFileReader = new FileReader(myFile);
				Scanner in = new Scanner(myFileReader);//setting up the fileReader link
				String aLine = "";

				switch(args.length)
				{
				case 1:
					if(args[0].contains(pHELP))
						help(args,argsValid);
					else if(Integer.parseInt(args[0]) == 1)
						jackpotOnly = true;
					else if(Integer.parseInt(args[0])== 2)
						allNumbers = true;
					if(allNumbers)//args[0] all 7 numbers
					{
						while(in.hasNext())//while there is data keep going
						{
							aLine = in.nextLine();
							fromFile = aLine.split(",");
							//System.out.println(aLine);//testing prints out the line
							if(fromFile[fromFile.length-2].matches(pNum))
							{
								bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
								frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
								oddOrEven(evenOdd,fromFile,jackpotOnly);
							}
							//results += "Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]+".\n";//prints bonus and occurrence testing only
						}
					}
					else if(jackpotOnly)//args[0] only jackpot numbers
					{
						while(in.hasNext())//while there is data keep going
						{
							aLine = in.nextLine();
							fromFile = aLine.split(",");//splits the line into the fromFile array
							//System.out.println(aLine);//testing prints out the line
							frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
							oddOrEven(evenOdd,fromFile,jackpotOnly);
						}
					}
					finishing(args, numbers, bonusNumber, allNumbers,evenOdd);
					break;
				case 2:
					if(Integer.parseInt(args[0]) == 1)
						jackpotOnly = true;
					else if(Integer.parseInt(args[0])== 2)
						allNumbers = true;
					while(in.hasNext())
					{
						aLine = in.nextLine();
						fromFile = aLine.split(",");
						if(jackpotOnly)
						{
							//System.out.println(fromFile[fromFile.length - 1] + " " + args[1] + "asd");
							if(fromFile[fromFile.length-1].equals(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
							{
								//System.out.println(aLine);//testing prints out the line
								frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
								oddOrEven(evenOdd,fromFile,jackpotOnly);
								dataError = false;
							}
							else if(args[1].equals("A"))
							{//this is for all draws
								//System.out.println(aLine);//testing prints out the line
								frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
								oddOrEven(evenOdd,fromFile,jackpotOnly);
								dataError = false;
							}
						}
						else if(allNumbers)
						{
							if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
							{
								//System.out.println(aLine);//testing prints out the line
								if(fromFile[fromFile.length-2].matches(pNum))//with bonus
									bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
								frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
								oddOrEven(evenOdd,fromFile,jackpotOnly);
								dataError = false;
								////System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
							}
							else if(args[1].contains("A") )
							{//this is for all draws with bonus
								if(fromFile[fromFile.length-2].matches(pNum))
									bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
								frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
								oddOrEven(evenOdd,fromFile,jackpotOnly);
								dataError = false;
							}
						}
					}
					if(dataError)
					{
						System.out.println(errorData);
					}
					else
						finishing(args, numbers, bonusNumber, allNumbers,evenOdd);
					break;
				case 3:
					if(Integer.parseInt(args[0]) == 1)
						jackpotOnly = true;
					else if(Integer.parseInt(args[0])== 2)
						allNumbers = true;
					if(Integer.parseInt(args[2]) == 0)
						allDrawDays = true;
					else if(Integer.parseInt(args[2]) == 1)
						wedDraws = true;
					else if(Integer.parseInt(args[2]) == 2)
						satDraws = true;
					//this is where it will call the zueller method, need to figure this out (think Christian is doing it) think can get rid of the 0 and just have it as if == 1 / == 2 then an else all days...also will need to add this to the year one and the start date/ end date ones.
					//think you can work on this Christian? should just be passing fromFile[0] to the method and checking what day it is and depending on which day is specified what's needed. once sorted can just add to next one
					while(in.hasNext())
					{
						aLine = in.nextLine();
						fromFile = aLine.split(",");
						if(jackpotOnly)
						{
							if(wedDraws && (zweller(fromFile[0]) == 1))
							{
								if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
								{
									//System.out.println(aLine);//testing prints out the line
									frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
									oddOrEven(evenOdd,fromFile,jackpotOnly);
									dataError = false;
								}
								else if(args[1].contains("A"))
								{//this is for all draws
									//System.out.println(aLine);//testing prints out the line
									frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
									oddOrEven(evenOdd,fromFile,jackpotOnly);
									dataError = false;
								}
							}
							if(satDraws && (zweller(fromFile[0]) == 2))
							{
								if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
								{
									//System.out.println(aLine);//testing prints out the line
									frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
									oddOrEven(evenOdd,fromFile,jackpotOnly);
									dataError = false;
								}
								else if(args[1].contains("A"))
								{//this is for all draws
									//System.out.println(aLine);//testing prints out the line
									frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
									oddOrEven(evenOdd,fromFile,jackpotOnly);
									dataError = false;
								}
							}
							if(allDrawDays)
							{
								if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
								{
									//System.out.println(aLine);//testing prints out the line
									frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
									oddOrEven(evenOdd,fromFile,jackpotOnly);
									dataError = false;
								}
								else if(args[1].contains("A"))
								{//this is for all draws
									//System.out.println(aLine);//testing prints out the line
									frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
									oddOrEven(evenOdd,fromFile,jackpotOnly);
									dataError = false;
								}
							}
						}
						else if(allNumbers)
						{
							if(wedDraws && (zweller(fromFile[0]) == 1))
							{
								if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
								{
									//System.out.println(aLine);//testing prints out the line
									if(fromFile[fromFile.length-2].matches(pNum))//with bonus
										bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
									frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
									oddOrEven(evenOdd,fromFile,jackpotOnly);
									dataError = false;
									//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
								}
								else if(args[1].contains("A") )
								{//this is for all draws with bonus
									//System.out.println(aLine);//testing prints out the line
									if(fromFile[fromFile.length-2].matches(pNum))
										bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
									frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
									oddOrEven(evenOdd,fromFile,jackpotOnly);
									dataError = false;
									//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
								}
							}
							if(satDraws && (zweller(fromFile[0]) == 2))
							{
								if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
								{
									//System.out.println(aLine);//testing prints out the line
									if(fromFile[fromFile.length-2].matches(pNum))//with bonus
										bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
									frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
									oddOrEven(evenOdd,fromFile,jackpotOnly);
									dataError = false;
									//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
								}
								else if(args[1].contains("A") )
								{//this is for all draws with bonus
									//System.out.println(aLine);//testing prints out the line
									if(fromFile[fromFile.length-2].matches(pNum))
										bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
									frequency(numbers, fromFile, args);
									oddOrEven(evenOdd,fromFile,jackpotOnly);// calling frequency methods to go through file and compare numbers
									dataError = false;
									//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
								}
							}
							if(allDrawDays)
							{
								if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
								{
									//System.out.println(aLine);//testing prints out the line
									if(fromFile[fromFile.length-2].matches(pNum))//with bonus
										bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
									frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
									oddOrEven(evenOdd,fromFile,jackpotOnly);
									dataError = false;
									//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
								}
								else if(args[1].contains("A") )
								{//this is for all draws with bonus
									//System.out.println(aLine);//testing prints out the line
									if(fromFile[fromFile.length-2].matches(pNum))
										bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
									frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
									oddOrEven(evenOdd,fromFile,jackpotOnly);
									dataError = false;
									//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
								}
							}
						}
					}
					if(!dataError)
					{
						finishing(args, numbers, bonusNumber, allNumbers,evenOdd);
					}
					else
						System.out.println(errorData);
					break;
				case 4:
					if(Integer.parseInt(args[0]) == 1)
						jackpotOnly = true;
					else if(Integer.parseInt(args[0])== 2)
						allNumbers = true;
					if(Integer.parseInt(args[2]) == 0)
						allDrawDays = true;
					else if(Integer.parseInt(args[2]) == 1)
						wedDraws = true;
					else if(Integer.parseInt(args[2]) == 2)
						satDraws = true;
					if(args[3].matches(pYear))
						yearSupplied = true;
					if(yearSupplied)
					{
						while(in.hasNext())
						{
							aLine = in.nextLine();
							fromFile = aLine.split(",");
							String searchFor = args[3];
							if(aLine.contains(searchFor))
							{
								if(jackpotOnly)
								{
									if(wedDraws && (zweller(fromFile[0]) == 1))
									{
										if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
										{
											//System.out.println(aLine);//testing prints out the line
											frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
											oddOrEven(evenOdd,fromFile,jackpotOnly);
											dataError = false;
										}
										else if(args[1].contains("A"))
										{//this is for all draws
											//System.out.println(aLine);//testing prints out the line
											frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
											oddOrEven(evenOdd,fromFile,jackpotOnly);
											dataError = false;
										}
									}
									if(satDraws && (zweller(fromFile[0]) == 2))
									{
										if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
										{
											//System.out.println(aLine);//testing prints out the line
											frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
											oddOrEven(evenOdd,fromFile,jackpotOnly);
											dataError = false;
										}
										else if(args[1].contains("A"))
										{//this is for all draws
											//System.out.println(aLine);//testing prints out the line
											frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
											oddOrEven(evenOdd,fromFile,jackpotOnly);
											dataError = false;
										}
									}
									if(allDrawDays)
									{
										if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
										{
											//System.out.println(aLine);//testing prints out the line
											frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
											oddOrEven(evenOdd,fromFile,jackpotOnly);
											dataError = false;
										}
										else if(args[1].contains("A"))
										{//this is for all draws
											//System.out.println(aLine);//testing prints out the line
											frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
											oddOrEven(evenOdd,fromFile,jackpotOnly);
											dataError = false;
										}
									}
								}
								else if(allNumbers)
								{
									if(wedDraws && (zweller(fromFile[0]) == 1))
									{
										if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
										{
											//System.out.println(aLine);//testing prints out the line
											if(fromFile[fromFile.length-2].matches(pNum))//with bonus
												bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
											frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
											oddOrEven(evenOdd,fromFile,jackpotOnly);
											dataError = false;
											//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
										}
										else if(args[1].contains("A") )
										{//this is for all draws with bonus
											//System.out.println(aLine);//testing prints out the line
											if(fromFile[fromFile.length-2].matches(pNum))
												bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
											frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
											oddOrEven(evenOdd,fromFile,jackpotOnly);
											dataError = false;
											//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
										}
									}
									if(satDraws && (zweller(fromFile[0]) == 2))
									{
										if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
										{
											//System.out.println(aLine);//testing prints out the line
											if(fromFile[fromFile.length-2].matches(pNum))//with bonus
												bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
											frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
											oddOrEven(evenOdd,fromFile,jackpotOnly);
											dataError = false;
											//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
										}
										else if(args[1].contains("A") )
										{//this is for all draws with bonus
											//System.out.println(aLine);//testing prints out the line
											if(fromFile[fromFile.length-2].matches(pNum))
												bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
											frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
											oddOrEven(evenOdd,fromFile,jackpotOnly);
											dataError = false;
											//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
										}
									}
									if(allDrawDays)
									{
										if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
										{
											//System.out.println(aLine);//testing prints out the line
											if(fromFile[fromFile.length-2].matches(pNum))//with bonus
												bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
											frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
											oddOrEven(evenOdd,fromFile,jackpotOnly);
											dataError = false;
											//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
										}
										else if(args[1].contains("A") )
										{//this is for all draws with bonus
											//System.out.println(aLine);//testing prints out the line
											if(fromFile[fromFile.length-2].matches(pNum))
												bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
											frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
											oddOrEven(evenOdd,fromFile,jackpotOnly);
											dataError = false;
											//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
										}
									}
								}
							}
						}
					}
					if(!dataError)
					{
						finishing(args, numbers, bonusNumber, allNumbers,evenOdd);
					}
					else
						System.out.println(errorData);
					minMax(args[3]);
					break;
				case 5:

					if(Integer.parseInt(args[0]) == 1)
						jackpotOnly = true;
					else if(Integer.parseInt(args[0])== 2)
						allNumbers = true;
					if(Integer.parseInt(args[2]) == 0)
						allDrawDays = true;
					else if(Integer.parseInt(args[2]) == 1)
						wedDraws = true;
					else if(Integer.parseInt(args[2]) == 2)
						satDraws = true;
					if(args[3].matches(pDate))
						startDate = true;
					if(args[4].matches(pDate))
						endDate = true;
					while(in.hasNext())
					{
						aLine = in.nextLine();
						fromFile = aLine.split(",");
						String dateFromFile = fromFile[0];
						boolean completeValid = multiDate(args,dateFromFile);
						if(startDate && endDate && completeValid)
						{
							//commenting out and moving it all to the multiDate() metho
							if(jackpotOnly)
							{
								if(wedDraws && (zweller(fromFile[0]) == 1))
								{
									if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
									{
										//System.out.println(aLine);//testing prints out the line
										frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
										oddOrEven(evenOdd,fromFile,jackpotOnly);
										dataError = false;
									}
									else if(args[1].contains("A"))
									{//this is for all draws
										//System.out.println(aLine);//testing prints out the line
										frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
										oddOrEven(evenOdd,fromFile,jackpotOnly);
										dataError = false;
									}
								}
								if(satDraws && (zweller(fromFile[0]) == 2))
								{
									if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
									{
										//System.out.println(aLine);//testing prints out the line
										frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
										oddOrEven(evenOdd,fromFile,jackpotOnly);
										dataError = false;
									}
									else if(args[1].contains("A"))
									{//this is for all draws
										//System.out.println(aLine);//testing prints out the line
										frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
										oddOrEven(evenOdd,fromFile,jackpotOnly);
										dataError = false;
									}
								}
								if(allDrawDays)
								{
									if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
									{
										//System.out.println(aLine);//testing prints out the line
										frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
										oddOrEven(evenOdd,fromFile,jackpotOnly);
										dataError = false;
									}
									else if(args[1].contains("A"))
									{//this is for all draws
										//System.out.println(aLine);//testing prints out the line
										frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
										oddOrEven(evenOdd,fromFile,jackpotOnly);
										dataError = false;
									}
								}
							}
							else if(allNumbers)
							{
								if(wedDraws && (zweller(fromFile[0]) == 1))
								{
									if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
									{
										//System.out.println(aLine);//testing prints out the line
										if(fromFile[fromFile.length-2].matches(pNum))//with bonus
											bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
										frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
										oddOrEven(evenOdd,fromFile,jackpotOnly);
										dataError = false;
										//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
									}
									else if(args[1].contains("A") )
									{//this is for all draws with bonus
										//System.out.println(aLine);//testing prints out the line
										if(fromFile[fromFile.length-2].matches(pNum))
											bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
										frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
										oddOrEven(evenOdd,fromFile,jackpotOnly);
										dataError = false;
										//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
									}
								}
								if(satDraws && (zweller(fromFile[0]) == 2))
								{
									if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
									{
										//System.out.println(aLine);//testing prints out the line
										if(fromFile[fromFile.length-2].matches(pNum))//with bonus
											bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
										frequency(numbers, fromFile, args);
										oddOrEven(evenOdd,fromFile,jackpotOnly);// calling frequency methods to go through file and compare numbers
										dataError = false;
										//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
									}
									else if(args[1].contains("A") )
									{//this is for all draws with bonus
										//System.out.println(aLine);//testing prints out the line
										if(fromFile[fromFile.length-2].matches(pNum))
											bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
										frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
										oddOrEven(evenOdd,fromFile,jackpotOnly);
										dataError = false;
										//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
									}
								}
								if(allDrawDays)
								{
									if(fromFile[fromFile.length-1].contains(args[1]))//checking draw type to see it matches type supplied need to do another if statement for all draws,.
									{
										//System.out.println(aLine);//testing prints out the line
										if(fromFile[fromFile.length-2].matches(pNum))//with bonus
											bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
										frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
										oddOrEven(evenOdd,fromFile,jackpotOnly);
										dataError = false;
										//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
									}
									else if(args[1].contains("A") )
									{//this is for all draws with bonus
										//System.out.println(aLine);//testing prints out the line
										if(fromFile[fromFile.length-2].matches(pNum))
											bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]++;//increasing the bonus number array
										frequency(numbers, fromFile, args);// calling frequency methods to go through file and compare numbers
										oddOrEven(evenOdd,fromFile,jackpotOnly);
										dataError = false;
										//System.out.println("Bonus Number " + Integer.parseInt(fromFile[fromFile.length-2])+ " : " + bonusNumber[Integer.parseInt(fromFile[fromFile.length-2])-1]);//prints bonus and occurrence testing only
									}
								}
							}
						}
					}
					if(!dataError)
					{
						finishing(args, numbers, bonusNumber, allNumbers,evenOdd);
					}
					else
						System.out.println(errorData);
					break;
				case 6:
					for(int i = 0; i < args.length; i++)//running through the args array to see what was supplied by end user
					{
						if(args[i].matches(pNum))
						{
							for (int j = 0 ;j<args.length ;j++ )
							{
								userNumbers[j] = Integer.parseInt(args[j]);
							}
							Arrays.sort(userNumbers);
							//lottoNumbers(userNumbers);
						}
					}
					lottoNumbers(userNumbers);
					//Arrays.sort(userNumbers);
					//frequency(userNumbers,args);
					break;
				case 7:
					int [] tempArray = new int [6];
					for(int i = 0; i < (args.length-1); i++)//running through the args array to see what was supplied by end user
					{
						if(args[i].matches(pNum))
						{
							tempArray[i] = Integer.parseInt(args[i]);
						}
						//userNumbers[(args.length)-1] = Integer.parseInt(args[(args.length)-1]);
					}
					Arrays.sort(tempArray);
					for(int i = 0; i < (args.length-1); i++)//running through the args array to see what was supplied by end user
					{
						if(args[i].matches(pNum))
						{
							userNumbers[i] = tempArray[i];
						}
						//userNumbers[(args.length)-1] = Integer.parseInt(args[(args.length)-1]);
					}
					userNumbers[args.length-1] = Integer.parseInt(args[args.length-1]);
					lottoNumbers(userNumbers);
					break;
				}
				myFileReader.close();
				in.close();
			}
			//finishing(args, numbers, bonusNumber, allNumbers,evenOdd);
		}
	}
	public static int zweller(String data)
	{
		String [] date;
		int [] dates = new int[3];
		//int [] days;
		int a, b, weekDay, dayMatcherForInput = 0;//giving dayMatcherForInput zero to start initially
		date = data.split("/");

		for(int i = 0; i < date.length; i++)
		{
			dates[i] = Integer.parseInt(date[i]);
		}
		if (dates[1] == 1 || dates[1] == 2)
		{
			dates[1] += 12;
			dates[2] -=  1;
		}
		a = dates[2] % 100;
		b = dates[2] / 100;
		weekDay = ((dates[0] + (((dates[1] + 1)*26)/10)+ a + (a/4) + (b/4)) + (5*b)) % 7;
		switch(weekDay)
		{
		//assigning 1 or 2 to wednesday/saturday to match with the command-line args
		case 0: dayMatcherForInput = 2; break;
		case 4: dayMatcherForInput = 1; break;
		}
		return dayMatcherForInput;
	}
	public static boolean multiDate(String [] args ,String fromFile) throws IOException
	{
		boolean  completeValid = false;
		String dateFromFile, startD = args[3], endD = args[4];
		Date sDate = new Date();
		Date eDate = new Date();
		Date fDate = new Date();
		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			sDate = (Date) dateFormatter.parse(startD);
			eDate = (Date) dateFormatter.parse(endD);
		}
		catch(ParseException pe)
		{
			//since dates have been validated elsewhere there won't be a throw to catch
		}
		dateFromFile = fromFile;
		try
		{
			fDate = (Date)dateFormatter.parse(dateFromFile);
		}
		catch(ParseException pe)
		{
			//since dates on file are valid, there won't be a throw to catch
		}
		// from here on it is doing comparisons of the date type data

		if(fDate.after(sDate) && fDate.before(eDate))
		{
			completeValid = true;
		}
		else

			completeValid = false;
		return completeValid;
	}
	public static boolean argsValid(String [] args)throws IOException
	{
		boolean validArgs = false;
		if((args.length < 1) || (args.length > 7))
		{
			validArgs = false;
		}
		else
		{
			boolean uniqueNumber = true;
			String pAllorJackpotOnly = "1|2";
			String pDrawType = "A|S|R|LP1|LP2";
			String pDay = "0|1|2";
			String pYear = "[0-9]{4}";
			String pDate = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}";
			String pNum = "[0-9]{1,2}";
			String pHELP = "HELP";
			switch(args.length)
			{
			case 1:
				if(args[0].matches(pHELP))
				{
					validArgs = true;
				}
				else if(args[0].matches(pAllorJackpotOnly))
				{
					validArgs = true;
				}

				break;
			case 2:
				if(args[0].matches(pAllorJackpotOnly) && args[1].matches(pDrawType))
				{
					validArgs = true;
				}
				break;
			case 3:
				if((args[0].matches(pAllorJackpotOnly)) && (args[1].matches(pDrawType)) && (args[2].matches(pDay)))
				{
					validArgs = true;
				}
				break;
			case 4:
				if((args[0].matches(pAllorJackpotOnly)) && (args[1].matches(pDrawType)) && (args[2].matches(pDay)) && (args[3].matches(pYear)))
				{
					validArgs = true;
				}
				break;
			case 5:
				if((args[0].matches(pAllorJackpotOnly)) && (args[1].matches(pDrawType)) && (args[2].matches(pDay)) && (args[3].matches(pDate))&& (args[4].matches(pDate)))
				{
					validArgs = true;
				}
				break;
			case 6:
			case 7:
				for (int a = 0; a < args.length; a++)
				{
					if(args[a].matches(pNum))
					{
						for(int testUnique = (a + 1); testUnique < args.length ; testUnique++)
						{
							if(args[testUnique].matches(pNum))
							{
								int userNumb,compNumb;
								userNumb = Integer.parseInt(args[a]);
								compNumb = Integer.parseInt(args[testUnique]);
								//String temp;
								if((userNumb > 0) && (userNumb <= 45))
								{
									if(userNumb == compNumb)
									{
										uniqueNumber = false;
										validArgs = false;
									}
									else
										validArgs = true;
								}
							}
							else 
							{
								validArgs = false;
								break;
							}				
						}
					}
					else 
					{
					validArgs = false;
					break;
					}
				}
				if(!uniqueNumber)
				{
					validArgs = false;
					System.out.println("Error Numbers Were not Unique");
				}
				break;
			default:
				break;
			}
		}
		return validArgs;
	}
	public static int[] frequency(int [] numbers, String [] fromFile,String [] args) throws IOException
	{
		FileReader myFileReader = new FileReader("lotto.txt");
		Scanner in = new Scanner(myFileReader);
		int k = 0;
		int evenCounter = 0,oddCounter = 0;
		int [] evenOdd = new int [2];
		String pNum = "[0-9]{1,2}";
		for(int a = 0; a < fromFile.length -2; a++)//runs through from file array only jackpot numbers
		{
			if(fromFile[a].matches(pNum))
			{
				k = (Integer.parseInt(fromFile[a])-1);
				numbers[k]++;
				if(Integer.parseInt(fromFile[a]) %2 == 0)
					evenCounter++;
				else
					oddCounter++;
				if(evenCounter == 6)
					evenOdd[0]++;
				else if(oddCounter == 6)
					evenOdd[1]++;
				//System.out.println((k+1) + " : " + numbers[k]);//just for testing purposes
			}
		}
		in.close();
		myFileReader.close();
		return numbers;
	}
	public static int[] oddOrEven(int [] evenOdd, String [] fromFile, boolean jackpotOnly) throws IOException
	{
		FileReader myFileReader = new FileReader("lotto.txt");
		Scanner in = new Scanner(myFileReader);
		int evenCounter = 0,oddCounter = 0;
		String pNum = "[0-9]{1,2}";
		if(jackpotOnly)
		{
			for(int a = 0; a < fromFile.length -2; a++)//runs through from file array only jackpot numbers
			{
				if(fromFile[a].matches(pNum))
				{
					if(Integer.parseInt(fromFile[a]) %2 == 0)
						evenCounter++;
					else
						oddCounter++;
					if(evenCounter == 6)
						evenOdd[0]++;
					else if(oddCounter == 6)
						evenOdd[1]++;
				}
			}
		}
		else
			for(int a = 0; a < fromFile.length -1; a++)//runs through from file array only jackpot numbers
			{
				if(fromFile[a].matches(pNum))
				{
					if(Integer.parseInt(fromFile[a]) %2 == 0)
						evenCounter++;
					else
						oddCounter++;
					if(evenCounter == 6)
						evenOdd[2]++;
					else if(oddCounter == 6)
						evenOdd[3]++;
				}
			}
		in.close();
		myFileReader.close();
		return evenOdd;
	}

	public static void finishing(String []args, int [] numbers, int [] bonusNumber, boolean allNumbers,int [] evenOdd)throws IOException
	{
		if(args.length<=5 && args.length >=1 && !args[0].contains("HELP"))
		{
			System.out.println("These numbers occured");
			int counter = 1;
			for(int i = 0; i<numbers.length;i++)
			{
				if((counter%10)==1 && counter!=1)
					System.out.println();
				if(numbers[i] !=0)
				{
					System.out.print((i+1)+ ": " + numbers[i] + ",\t");
					counter++;
				}
			}
			if(allNumbers)
			{
				counter = 1;
				System.out.println();
				System.out.println("\nThese are the bonus numbers that occured");
				for(int i = 0; i < bonusNumber.length; i++)
				{
					if((counter%10)==1 && counter!=1)
						System.out.println();
					if(bonusNumber[i] !=0)
					{
						System.out.print((i+1)+ ": " + bonusNumber[i] + ",\t");
						counter++;
					}
				}
				System.out.println("\n\nAll numbers were even "+evenOdd[2]+" times and odd "+evenOdd[3]+" times.");
			}
			else
				System.out.println("\n\nJackpot numbers were even "+evenOdd[0]+" times and odd "+evenOdd[1]+" times.");
			System.out.println();
		}
	}
	public static void help(String[] args, boolean argsValid) throws IOException
	{
		String aLine = "";
		File myFile = new File("HELP.txt");
		if(myFile.exists())
		{
			FileReader myFileReader = new FileReader(myFile);
			Scanner in = new Scanner(myFileReader);//setting up the fileReader link
			if(argsValid)
			{
				while(in.hasNext())//while there is data keep going
				{
					aLine = in.nextLine()+"\n";
					System.out.print(aLine);
				}
			}
			//System.out.print(aLine);
			in.close();
			myFileReader.close();
		}
		//System.out.print(aLine);
		if(!argsValid)
		{
			String 	error1 = "Error : Usage G22Lotto 1/2 A/S/R/LP1/LP2 0/1/2 YYYY/START-DATE END-DATE \n";
			error1 +="Or : JP1 JP2 JP3 JP4 JP5 JP6 BONUS \n";
			error1 += "If you require further clarification please type G22Lotto Help";
			System.out.print(error1);
		}

	}
	public static void lottoNumbers(int [] userNumbers) throws IOException
	{
		FileReader myFileReader = new FileReader("lotto.txt");
		Scanner in = new Scanner(myFileReader);
		String aLine = "";
		String [] fromFile = new String [8];
		int matchesCounter = 0;
		int [] jackpot= new int [4];
		int [] bonus = new int [7];
		int [] bonusTemp = new int [7];
		int [] line = new int [userNumbers.length];
		while (in.hasNext())
		{
			matchesCounter = 0;
			aLine = in.nextLine();
			fromFile = aLine.split(",");
			for(int i = 0; i< userNumbers.length; i++)
			{
				line[i] = Integer.parseInt(fromFile[i+1]);
			}
			if(userNumbers.length == 6)
			{
				for(int i = 0; i < userNumbers.length; i++)
				{
					for (int j = 0; j < line.length; j++)
					{
						if(userNumbers[i] == line[j])
							matchesCounter++;
					}
				}
				if(matchesCounter >= 3)
					jackpot[matchesCounter-3]++;
			}
			else
			{
				for(int i = 0; i < userNumbers.length; i++)
				{
					for(int j = 0; j < line.length; j++)
					{
						if(userNumbers[i] == line[j])
							matchesCounter++;
					}
				}
				if(matchesCounter >= 3)
				{
					if (userNumbers[(userNumbers.length)-1] == line[(line.length)-1])
						bonus[(matchesCounter+1)]++;
					else
						bonus[(matchesCounter)-3]++;
				}
			}
		}

		for(int i = 3; i>=0;i--)
		{
			if(jackpot[i]!=0)
			{
				if(i == 3)
					System.out.println("Numbers were a jackpot " + jackpot[i] + " time(s)");
				else
					System.out.println((i+3) + " matches occured " + jackpot[i] + " times");
			}
		}

		if(userNumbers.length == 7)
		{
			bonusTemp[6] = bonus[3];
			bonusTemp[5] = bonus[6];
			bonusTemp[4] = bonus[2];
			bonusTemp[3] = bonus[5];
			bonusTemp[2] = bonus[1];
			bonusTemp[1] = bonus[4];
			bonusTemp[0] = bonus[0];
		}
		for(int i = 6; i>=0;i--)
		{
			if(bonusTemp[i]!=0)
			{
				switch(i)
				{
				case 0:System.out.println("3 matches occured " + bonusTemp[i] + " time(s)");
				break;

				case 1:System.out.println("3 matches and the bonus number occured " + bonusTemp[i] + " time(s)");
				break;

				case 2:System.out.println("4 matches occured " + bonusTemp[i] + " time(s)");
				break;

				case 3:System.out.println("4 matches and the bonus number occured " + bonusTemp[i] + " time(s)");
				break;

				case 4:System.out.println("5 matches occured " + bonusTemp[i] + " time(s)");
				break;

				case 5:System.out.println("5 matches and the bonus number occured " + bonusTemp[i] + " time(s)");
				break;

				case 6:System.out.println("Numbers were jackpot winners " + bonusTemp[i] + " time(s)");
				break;
				}
			}
		}

		in.close();
		myFileReader.close();
	}
	public static void minMax(String inYear)throws IOException
	{
		FileReader myFileReader = new FileReader("lotto.txt");
		Scanner in = new Scanner(myFileReader);
		String aLine = "";
		String [] fromFile = new String [8];
		String [] date = new String [3];
		int year = Integer.parseInt(inYear);
		int [] maxMin = new int [2];
		int min = 10, max =0, fileYear, counter = 0;
		boolean validYear = true;
		while(in.hasNext() &&validYear)
		{
			aLine = in.nextLine();
			fromFile = aLine.split(",");
			date = fromFile[0].split("/");
			fileYear = Integer.parseInt(date[2]);
			if (year>1988 && year<1994 && validYear)
			{
				max = 26;
				for(int i = 1; i< (fromFile.length)-2;i++)
				{
					if((Integer.parseInt(fromFile[i]))<= min)
						maxMin[0]++;
					else if((Integer.parseInt(fromFile[i]))>=max)
						maxMin[1]++;
				}
			}
			else if (year >1994 && year<2006 && validYear)
			{
				max = 32;
				for(int i = 1; i< (fromFile.length)-2;i++)
				{
					if((Integer.parseInt(fromFile[i]))<= min)
						maxMin[0]++;
					else if((Integer.parseInt(fromFile[i]))>=max)
						maxMin[1]++;
				}
			}
			else if (validYear)
			{
				max = 36;
				for(int i = 1; i< (fromFile.length)-2;i++)
				{
					if((Integer.parseInt(fromFile[i]))<= min)
						maxMin[0]++;
					else if((Integer.parseInt(fromFile[i]))>=max)
						maxMin[1]++;
				}
			}
			if(fileYear > year)
				validYear = false;
			if(maxMin[0]>maxMin[1])
				counter++;
		}
		System.out.println("\nThe low winning numbers exceeded the number of high winning numbers " + counter +" times");
		in.close();
		myFileReader.close();
	}
}