import java.util.*;
import java.io.*;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JFrame;
//imports


public class Project4B extends JFrame
{
	public static String initialSelection;
	public static ArrayList<Fixtures> fixtureList = new ArrayList<Fixtures>();
	public static ArrayList<Results> listResults = new ArrayList<Results>();
	public static ArrayList<TeamN> listTeamN = new ArrayList<TeamN>();
	public static ArrayList<Leaderboard> aLeaderBoard = new ArrayList<Leaderboard>();
	//public static ArrayList<Integer> fixtureNumbers = new ArrayList<Integer>();
	
	public static void main(String[] args)throws IOException
	{
		readTeamFile("PremiershipTeamsOrPlayers.txt");
		readFixturesFile("PremiershipFixtures.txt");
		readResultsFile("PremiershipResults.txt");
		initialSelection = "1: View the List of Fixtures and results to date.";
	
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
		loop();
	}
	
	public static void loop()
	{
		byte input = menuOpt();
		
		while(input != 0)
		{
			switch(input)
			{
				case 1:
					resultsAndFixturesToDate();
				break;
				case 2:
					fixturesYetToBePlayed();
				break;
				case 3:
					displayLeaderBoard();
				break;
				case 0:
				
				break;
			}	
			input = 0;
		}
	}
	
	public static byte menuOpt()
	{
		String selectionUser;
		String [] selectionValues = { "1: View the List of Fixtures and results to date.",
									 "2: View the List of Fixtures yet to be played.", 
									 "3: View the Premiership Table to date.", 
									 "4: Quit"};
									 
		selectionUser = (String)JOptionPane.showInputDialog(null, "Please make selection", "Main Menu", -1, null, selectionValues, initialSelection);
			//if user clicks an option in the drop down menu
		if(selectionUser != null && selectionUser != "7: Quit")
		{
			switch(selectionUser)
			{
				case "1: View the List of Fixtures and results to date.":
					return 1;
					
				case "2: View the List of Fixtures yet to be played.":
					return 2;
						
				case "3: View the Premiership Table to date.":
					return 3;
						
				case "4: Quit":
					return 0;
			}
		}
		return 0;
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
		String topRow [] = new String [4];
		topRow[0] = "Number";
		topRow[1] = "Home Team";
		topRow[2] = "Score";
		topRow[3] = "Away Team";
		
		String results [][] = new String [listResults.size()] [4];
		//for(int a = (lastFixtureNumber - 1); a < fixtureList.size(); a++)
		for(int a = 0; a < listResults.size();a++)
			{
				results[a][0] = Integer.toString(fixtureList.get(a).getFixtureNumber());
				results[a][1] = listTeamN.get((fixtureList.get(a).getHomeTeamNumber()-1)).getTeamName();
				results[a][2] = Integer.toString(listResults.get(a).getHomeScore()) + " - " + Integer.toString(listResults.get(a).getAwayScore());
				results[a][3] = listTeamN.get((fixtureList.get(a).getAwayTeamNumber())-1).getTeamName();
			}
		new Project4B(results, topRow, "Results and Fixtures to date", 1);
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
		//getting the final fixture number that has been played
		int lastFixtureNumber = (listResults.size() - 1);
		lastFixtureNumber = (listResults.get(lastFixtureNumber).getFixtureNumber());
		
		String topRow [] = new String [4];
		topRow[0] = "Fixture No";
		topRow[1] = "Home Team";
		topRow[2] = "V's";
		topRow[3] = "Away Team";
		
		String fixtures [][] = new String [fixtureList.size() -1] [4];

		int b = 0;
		for(int a = (lastFixtureNumber ); a < fixtureList.size(); a++ )
			{
				fixtures[b][0] = Integer.toString(fixtureList.get(a).getFixtureNumber());
				fixtures[b][1] = listTeamN.get((fixtureList.get(a).getHomeTeamNumber()-1)).getTeamName();
				fixtures[b][2] = "V's";
				fixtures[b][3] = listTeamN.get((fixtureList.get(a).getAwayTeamNumber())-1).getTeamName();
				b++;
			}
		new Project4B(fixtures, topRow, "Fixtures yet to be played", 2);
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
		//for displaying leader board.
	public static void displayLeaderBoard()
	{
		String topRow [] = {"Position", "Team Name", "P", "Home", "W", "D", "L", "F", "A", "Away", "W", "D", "L","F", "A", "GD", "Pts"};
		
		String leaderBoard [][] = new String [aLeaderBoard.size()] [17];
		//for(int a = (lastFixtureNumber - 1); a < fixtureList.size(); a++)
		for(int a = 0; a < aLeaderBoard.size();a++)
		{
			leaderBoard[a][0] = Integer.toString(a+1);
			leaderBoard[a][1] = listTeamN.get(aLeaderBoard.get(a).getTeamNumber()).getTeamName();
			leaderBoard[a][2] = Integer.toString(aLeaderBoard.get(a).getGamesPlayed());
			leaderBoard[a][3] = "";
			leaderBoard[a][4] = Integer.toString(aLeaderBoard.get(a).getHomeWins());
			leaderBoard[a][5] = Integer.toString(aLeaderBoard.get(a).getHomeDraws());
			leaderBoard[a][6] = Integer.toString(aLeaderBoard.get(a).getHomeLosses());
			leaderBoard[a][7] = Integer.toString(aLeaderBoard.get(a).getHomeGoalsFor());
			leaderBoard[a][8] = Integer.toString(aLeaderBoard.get(a).getHomeGoalsAgainst());
			leaderBoard[a][9] = "";
			leaderBoard[a][10] = Integer.toString(aLeaderBoard.get(a).getAwayWins());
			leaderBoard[a][11] = Integer.toString(aLeaderBoard.get(a).getAwayDraws());
			leaderBoard[a][12] = Integer.toString(aLeaderBoard.get(a).getAwayLosses());
			leaderBoard[a][13] = Integer.toString(aLeaderBoard.get(a).getAwayGoalsFor());
			leaderBoard[a][14] = Integer.toString(aLeaderBoard.get(a).getAwayGoalsAgainst());
			leaderBoard[a][15] = Integer.toString(aLeaderBoard.get(a).getGoalDifference());
			leaderBoard[a][16] = Integer.toString(aLeaderBoard.get(a).getTotalPoints());	
		}
			
		new Project4B(leaderBoard, topRow, "LeaderBoard", 3);
	}
	
	 public Project4B(String data[][], String topRow[], String Name, int Num)
	 {
		final JFrame frame = new JFrame(Name);
		
		  JPanel panel = new JPanel();
		  JTable table = new JTable(data,topRow);
		  JScrollPane pane = new JScrollPane(table);
		  panel.add(pane);
		  frame.add(panel);
		  
		  frame.addWindowListener(
			new WindowAdapter() 
			{
				 public void windowClosing(WindowEvent e) 
				{
					frame.dispose();
					loop();
				}
			});
		
		 switch(Num)
		{
			case 1:
			case 2:
				table.setPreferredScrollableViewportSize(new Dimension(470,500));
			
				TableColumn Number = table.getColumnModel().getColumn(0);
					Number.setPreferredWidth(20);
				TableColumn Vs = table.getColumnModel().getColumn(2);
					Vs.setPreferredWidth(20);
				break;
				
			case 3:
			
				table.setPreferredScrollableViewportSize(new Dimension(800,320));
			 
				TableColumn placeColumn = table.getColumnModel().getColumn(0);
					placeColumn.setPreferredWidth(60);
				TableColumn teamName = table.getColumnModel().getColumn(1);
					teamName.setPreferredWidth(330);
				break;
		} 
		
		  frame.setResizable(false);
		  frame.pack();
		  frame.setLocationRelativeTo(null);
		  frame.setVisible(true);
	 }
}