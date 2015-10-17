import java.util.*;
import java.io.*;



public class Project4
{
	public static ArrayList<Fixtures> fixtureList = new ArrayList<Fixtures>();
	public static ArrayList<Results> listResults = new ArrayList<Results>();
	public static ArrayList<TeamN> listTeamN = new ArrayList<TeamN>();
	public static ArrayList<Leaderboard> aLeaderBoard = new ArrayList<Leaderboard>();
	//public static ArrayList<Integer> fixtureNumbers = new ArrayList<Integer>();
	
	public static void main(String[] args)throws IOException
	{
		int input;
		readTeamFile("PremiershipTeamsOrPlayers.txt");
		readFixturesFile("PremiershipFixtures.txt");
		readResultsFile("PremiershipResults.txt");
		input = menuOpt();
		String results = "";
	
		//creating the leader board entry for each team
		for(int i = 0; i < listTeamN.size();i++)
		{
			Leaderboard aLeaderBoardEntry = new Leaderboard(0,0,0,0,0,0,0,0,0,0,0,0,0,0);
			aLeaderBoard.add(aLeaderBoardEntry);
		}
		//processing the results
		for (int a = 0; a < listResults.size();a++)
		{
			Results aResult = listResults.get(a);
			int	aFixtureNumber = aResult.getFixtureNumber();
			int	aHomeScore = aResult.getHomeScore();
			int	anAwayScore = aResult.getAwayScore();
			Fixtures aFixture = fixtureList.get(a);
			int aHomeTeamNumber = aFixture.getHomeTeamNumber();
			int aAwayTeamNumber = aFixture.getAwayTeamNumber();
		
			if(aHomeScore == anAwayScore)
			{
				recordFixtureResultForHomeTeam(aHomeTeamNumber,0,1,0,aHomeScore,anAwayScore,1);
				recordFixtureResultForAwayTeam(aAwayTeamNumber,0,1,0,aHomeScore,anAwayScore,1);
			}
			else if(aHomeScore > anAwayScore)
			{
				recordFixtureResultForHomeTeam(aHomeTeamNumber,1,0,0,aHomeScore,anAwayScore,3);
				recordFixtureResultForAwayTeam(aAwayTeamNumber,0,0,1,aHomeScore,anAwayScore,0);
			}
			else if(aHomeScore < anAwayScore)
			{
				recordFixtureResultForHomeTeam(aHomeTeamNumber,0,0,1,aHomeScore,anAwayScore,0);
				recordFixtureResultForAwayTeam(aAwayTeamNumber,1,0,0,aHomeScore,anAwayScore,3);
			}
		}
		orderLeaderBoardOnPoints();
		while(input != 0)
		{
			switch(input)
			{
				case 1:
					//results = resultsAndFixturesToDate();
					resultsAndFixturesToDate();
					//needs formatting to make it pretty
					//System.out.println(results);
				break;
				case 2:
					//results = fixturesYetToBePlayed();
					fixturesYetToBePlayed();
					//System.out.println(results);
					//needs formatting to make pretty
				break;
				case 3:
					displayLeaderBoard();
				break;
				case 0:
				
				break;
			}
			if (input != 0)
				input = menuOpt();
			
		}
	}
	public static int menuOpt()
	{
		String menuOptions = "";
		Scanner input = new Scanner(System.in);
		String pattern1= "[0-3]{1}";
		String input1;
		int input2 = 0;
		
		menuOptions += "Please Enter the option you would like to view:(1/2/3/0)\n";
		menuOptions += "1.View the List of Fixtures and results to date.\n";
		menuOptions += "2.View the List of Fixtures yet to be played.\n";
		menuOptions += "3.View the Premiership Table to date.\n";
		menuOptions += "0.Quit\n";
		menuOptions += "Please choose: ";
		System.out.print(menuOptions);
		input1 = input.nextLine();
		
		if(input1.matches(pattern1))
		{
			input2 = Integer.parseInt(input1);
		}
		else
		{
			System.out.println("Error Invalid Input");
			return 4;
		}
		return input2;	
	}
	public static void readTeamFile(String fileName)throws IOException
	{
		String [] fromFile;
		File inputFile = new File(fileName);
		int teamNumber;
		String teamName;
		
		if(inputFile.exists())
		{
			if(inputFile.canRead())
			{
				Scanner fileReader = new Scanner(inputFile);
				while(fileReader.hasNext())
				{
					fromFile = (fileReader.nextLine()).split(",");
					teamNumber = Integer.parseInt(fromFile[0]);
					teamName = (fromFile[1]);
					TeamN aTeamN = new TeamN(teamNumber,teamName);
					listTeamN.add(aTeamN);
				}
				fileReader.close();
			}
			else//error if cant read from file
				System.out.println("Error Cannot read from File "+fileName+" please check file security settings.");
		}
		else//error for cant find file
			System.out.println("Error Cannot Locate file "+fileName+".");
	 	//testing the list is populated properly
		/* for(int i = 0; i < listTeamN.size();i++)
		{
			System.out.println(listTeamN.get(i).getTeamNumber() + " " + listTeamN.get(i).getTeamName());	
		}  */
	}
	public static void readFixturesFile(String fileName)throws IOException
	{
		String [] fromFile;
		
		File inputFile = new File(fileName);
		
		if(inputFile.exists())
		{
			if(inputFile.canRead())
			{
				Scanner fileReader = new Scanner(inputFile);
				while(fileReader.hasNext())
				{
					fromFile = (fileReader.nextLine()).split(",");
					int fixtureNumber,homeTeamNumber,awayTeamNumber;
					fixtureNumber = Integer.parseInt(fromFile[0]);
					//fixtureNumbers.add(fixtureNumber);
					homeTeamNumber = Integer.parseInt(fromFile[1]);
					awayTeamNumber = Integer.parseInt(fromFile[2]);
					Fixtures aFixture = new Fixtures(fixtureNumber,homeTeamNumber,awayTeamNumber);
					fixtureList.add(aFixture);
				}
				fileReader.close();
				//for testing list was populated correctly
			/* 	for(int a = 0; a < fixtureList.size(); a++)
				System.out.println(fixtureList.get(a).getFixtureNumber()+" "+fixtureList.get(a).getAwayTeamNumber()+" "+fixtureList.get(a).getHomeTeamNumber()); */
			}
			else//error if cant read from file
				System.out.println("Error Cannot read from File "+fileName+" please check file security settings.");
		}
		else//error for cant find file
			System.out.println("Error Cannot Locate file "+fileName+".");

	} 
	public static void readResultsFile(String fileName)throws IOException
	{
		String [] fromFile;	
		File inputFile = new File(fileName);
		
		if(inputFile.exists())
		{
			if(inputFile.canRead())
			{
				Scanner fileReader = new Scanner(inputFile);
				while(fileReader.hasNext())
				{
					fromFile = (fileReader.nextLine()).split(",");
					int fixtureNumber,homeTeamScore,awayTeamScore;
					fixtureNumber = Integer.parseInt(fromFile[0]);
					homeTeamScore = Integer.parseInt(fromFile[1]);
					awayTeamScore = Integer.parseInt(fromFile[2]);
					Results aResult = new Results(fixtureNumber,homeTeamScore,awayTeamScore);
					listResults.add(aResult);
				}
				fileReader.close();
				//for testing list was populated correctly
/* 			 	for(int a = 0; a < listResults.size(); a++)
				System.out.println(listResults.get(a).getFixtureNumber()+" "+listResults.get(a).getHomeScore()+" "+listResults.get(a).getAwayScore()); */ 
			}
			else//error if cant read from file
				System.out.println("Error Cannot read from File "+fileName+" please check file security settings.");
		}
		else//error for cant find file
			System.out.println("Error Cannot Locate file "+fileName+".");

	} 
	/*public static String resultsAndFixturesToDate()//steve's pre formatting
	{

		String results = "";
		results += "*************** Fixtures and Results to Date ***************\n";
		results += "No.   Home Team              Score        Away Team         \n";       
		for ( int a = 0; a < listResults.size(); a++ )
		{
			results += (listResults.get(a).getFixtureNumber()) + "  " + listTeamN.get((fixtureList.get(a).getHomeTeamNumber()-1)).getTeamName()+ "  " + listResults.get(a).getHomeScore() + "-" + listResults.get(a).getAwayScore() +"  "+ listTeamN.get((fixtureList.get(a).getAwayTeamNumber())-1).getTeamName()+"\n";
		}
		//for testing System.out.print(results);
		return results;
	}*/
	
	public static void resultsAndFixturesToDate()//formatting by christian
	{

		String results = "";
		results += "*************** Fixtures and Results to Date ***************\n";
		results += "No.   Home Team              Score        Away Team         \n";       
		for ( int a = 0; a < listResults.size(); a++ )
		{
			//results += (listResults.get(a).getFixtureNumber()) + "  " + listTeamN.get((fixtureList.get(a).getHomeTeamNumber()-1)).getTeamName()+ "  " + listResults.get(a).getHomeScore() + "-" + listResults.get(a).getAwayScore() +"  "+ listTeamN.get((fixtureList.get(a).getAwayTeamNumber())-1).getTeamName()+"\n";
			System.out.print((fixtureList.get(a).getFixtureNumber()) +"\t");
			System.out.printf("%-20s",listTeamN.get((fixtureList.get(a).getHomeTeamNumber()-1)).getTeamName());
			System.out.print("  " + listResults.get(a).getHomeScore() + "-" + listResults.get(a).getAwayScore() +"  " + listTeamN.get((fixtureList.get(a).getAwayTeamNumber())-1).getTeamName()+"\n");
			if( a % 10 == 0 && a !=0)
			{
				Scanner in = new Scanner(System.in);
				System.out.println("Please Press Any key to display the next ten results.");
				String input = in.nextLine();
			}
		}
		//for testing System.out.print(results);
	}
	
	/*public static String fixturesYetToBePlayed()//steve's pre formatting
	{
		String results = "";
		//getting the final fixture number that has been played
		int lastFixtureNumber = (listResults.size() - 1);
		lastFixtureNumber = (listResults.get(lastFixtureNumber).getFixtureNumber());
		
		results += "*****************    Fixtures    *****************\n";
		results += "Fixture No.   Home Team   V's      Away Team      \n";
		for ( int a = (lastFixtureNumber - 1); a < fixtureList.size(); a++ )
		{
			results += (fixtureList.get(a).getFixtureNumber()) + "  " + listTeamN.get((fixtureList.get(a).getHomeTeamNumber()-1)).getTeamName()+ " V's " + listTeamN.get((fixtureList.get(a).getAwayTeamNumber())-1).getTeamName()+"\n";
		}
		return results;
	}*/
	
	public static void fixturesYetToBePlayed()//formatting by christian
	{
		String results = "";
		int counter = 0;
		//getting the final fixture number that has been played
		int lastFixtureNumber = (listResults.size() - 1);
		lastFixtureNumber = (listResults.get(lastFixtureNumber).getFixtureNumber());
		
		System.out.println( "*****************    Fixtures    *********************\nFixture No.   Home Team      V's      Away Team      \n");
		for ( int a = (lastFixtureNumber ); a < fixtureList.size(); a++ )
		{
			System.out.print((fixtureList.get(a).getFixtureNumber()) +"\t");
			System.out.printf("%-20s",listTeamN.get((fixtureList.get(a).getHomeTeamNumber()-1)).getTeamName());
			System.out.print(" V's   " + listTeamN.get((fixtureList.get(a).getAwayTeamNumber())-1).getTeamName()+"\n");
			counter++;
			if(counter %25 == 0 && counter!=0)
			{
				Scanner in = new Scanner(System.in);
				System.out.println("Please Press Any key to display the next ten results.");
				String input = in.nextLine();
			}
		}
	}
	
	public static void recordFixtureResultForHomeTeam(int aHTeamNumber,int win,int draw, int loss,int hScore,int aScore, int points)//from annettes notes(Steve)
	{
		Leaderboard aLeaderBoardEntry = null;
		aLeaderBoardEntry = aLeaderBoard.get(aHTeamNumber -1);
		aLeaderBoardEntry.setTeamNumber(aHTeamNumber -1);
		aLeaderBoardEntry.setGamesPlayed(1);
		aLeaderBoardEntry.setHomeWins(win);
		aLeaderBoardEntry.setHomeDraw(draw);
		aLeaderBoardEntry.setHomeLoss(loss);
		aLeaderBoardEntry.setHomeGoalsFor(hScore);
		aLeaderBoardEntry.setHomeGoalsAgainst(aScore);
		aLeaderBoardEntry.setGoalDifference(hScore-aScore);
		aLeaderBoardEntry.setTotalPoints(points);
	}
	public static void recordFixtureResultForAwayTeam(int aATeamNumber,int win,int draw, int loss,int hScore,int aScore, int points)//from annettes notes(Steve)
	{
		Leaderboard aLeaderBoardEntry = null;
		aLeaderBoardEntry = aLeaderBoard.get(aATeamNumber -1);
		aLeaderBoardEntry.setTeamNumber(aATeamNumber -1);
		aLeaderBoardEntry.setGamesPlayed(1);
		aLeaderBoardEntry.setAwayWin(win);
		aLeaderBoardEntry.setAwayDraw(draw);
		aLeaderBoardEntry.setAwayLosses(loss);
		aLeaderBoardEntry.setAwayGoalsFor(aScore);
		aLeaderBoardEntry.setAwayGoalsAgainst(hScore);
		aLeaderBoardEntry.setGoalDifference(aScore-hScore);
		aLeaderBoardEntry.setTotalPoints(points);
	}
	//method to sort the leader board.(bubble sort)
  	public static void orderLeaderBoardOnPoints()
	{	
		int j;
		boolean flag = true;  
		Leaderboard temp;  

		while ( flag )
		{
			flag= false;  
			for( j=0;  j < aLeaderBoard.size()-1;  j++ )
			{
				if (aLeaderBoard.get(j ).getTotalPoints() < aLeaderBoard.get(j+1).getTotalPoints() )  
				{
					temp = aLeaderBoard.get(j );              
					aLeaderBoard.set((j),aLeaderBoard.get(j+1));
					aLeaderBoard.set((j+1),temp);
					flag = true;            
				}
			}
		}  
	}  
		//for displaying leader board.
	public static void displayLeaderBoard()
	{
		String space = "   ";
		String leaderBoardTitle = "Pos.   Team Name           P  Home W  D  L  F  A Away  W  D  L  F  A  GD  Pts  ";
		System.out.println(leaderBoardTitle);
		for(int a = 0; a < aLeaderBoard.size();a++)
		{
			System.out.printf("%-5s",a + 1);
			System.out.printf("%-22s",listTeamN.get(aLeaderBoard.get(a).getTeamNumber()).getTeamName());
			//System.out.println(space + aLeaderBoard.get(a).getGamesPlayed() + space + aLeaderBoard.get(a).getHomeWins()+ space + aLeaderBoard.get(a).getHomeDraws()+ space + aLeaderBoard.get(a).getHomeLosses() + space + aLeaderBoard.get(a).getHomeGoalsFor() + space + aLeaderBoard.get(a).getHomeGoalsAgainst() + space + aLeaderBoard.get(a).getAwayWins() + space + aLeaderBoard.get(a).getAwayDraws()+ space + aLeaderBoard.get(a).getAwayLosses()+ space + aLeaderBoard.get(a).getAwayGoalsFor() + space + aLeaderBoard.get(a).getAwayGoalsAgainst() + space + aLeaderBoard.get(a).getGoalDifference() + space + aLeaderBoard.get(a).getTotalPoints());   
			System.out.printf("%-8s",aLeaderBoard.get(a).getGamesPlayed());
			System.out.printf("%-3s",aLeaderBoard.get(a).getHomeWins());
			System.out.printf("%-3s",aLeaderBoard.get(a).getHomeDraws());
			System.out.printf("%-3s",aLeaderBoard.get(a).getHomeLosses());
			System.out.printf("%-3s",aLeaderBoard.get(a).getHomeGoalsFor());
			System.out.printf("%-8s",aLeaderBoard.get(a).getHomeGoalsAgainst());
			System.out.printf("%-3s",aLeaderBoard.get(a).getAwayWins());
			System.out.printf("%-3s",aLeaderBoard.get(a).getAwayDraws());
			System.out.printf("%-3s",aLeaderBoard.get(a).getAwayLosses());
			System.out.printf("%-3s",aLeaderBoard.get(a).getAwayGoalsFor());
			System.out.printf("%-3s",aLeaderBoard.get(a).getAwayGoalsAgainst());
			System.out.printf("%-4s",aLeaderBoard.get(a).getGoalDifference());
			System.out.printf("%-3s",aLeaderBoard.get(a).getTotalPoints());
			System.out.println();
	
		}
	}
}