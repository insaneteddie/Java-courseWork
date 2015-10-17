	public class Leaderboard
	{
		private int teamNumber;
		private int gamesPlayed;
		private int homeWins;
		private int homeDraws;
		private int homeLosses;
		private int homeGoalsFor;
		private int homeGoalsAgainst;
		private int awayWins;
		private int awayDraws;
		private int awayLosses;
		private int awayGoalsFor;
		private int awayGoalsAgainst;
		private int goalDifference;
		private int totalPoints;
		
		Leaderboard(int aTeamNumber,int numberOfGames,int numberOfHomeWins,int aHomeDraw,int aHomeLoss,int aHomeGoalsFor,int aHomeGoalsAgainst, int anAwayWin, int anAwayDraw, int anAwayLoss, int anAwayGoalFor, int anAwayGoalAgainst, int aGoalDifference, int aTotalPoints)
		{
			teamNumber = aTeamNumber;
			gamesPlayed = numberOfGames;
			homeWins = numberOfHomeWins;
			homeDraws = aHomeDraw;
			homeLosses = aHomeLoss;
			homeGoalsFor = aHomeGoalsFor;
			homeGoalsAgainst = aHomeGoalsAgainst;
			awayWins = anAwayWin;
			awayDraws = anAwayDraw;
			awayLosses = anAwayLoss;
			awayGoalsFor = anAwayGoalFor;
			awayGoalsAgainst = anAwayGoalAgainst;
			goalDifference = aGoalDifference;
			totalPoints = aTotalPoints;
		}
		//set Methods
		public void setTeamNumber(int aTeamNumber)
		{
			teamNumber = aTeamNumber;
		}
		public void setGamesPlayed(int aGamePlayed)
		{
			gamesPlayed += aGamePlayed;
		}
		public void setHomeWins(int aHomeWin)
		{
			homeWins += aHomeWin;
		}
		public void setHomeDraw(int aHomeDraw)
		{
			homeDraws += aHomeDraw;
		}
		public void setHomeLoss(int aHomeLoss)
		{
			homeLosses +=aHomeLoss;
		}
		public void setHomeGoalsFor(int aHomeGoal)
		{
			homeGoalsFor += aHomeGoal;
		}
		public void setHomeGoalsAgainst(int aHomeGoalAgainst)
		{
			homeGoalsAgainst += aHomeGoalAgainst;
		}
		public void setAwayWin(int anAwayWin)
		{
			awayWins += anAwayWin;
		}
		public void setAwayLosses(int anAwayLoss)
		{
			awayLosses += anAwayLoss;
		}
		public void setAwayDraw(int anAwayDraw)
		{
			awayDraws += anAwayDraw;
		}
		public void setAwayGoalsFor(int anAwayGoal)
		{
			awayGoalsFor += anAwayGoal;
		}
		public void setAwayGoalsAgainst(int anAwayGoal)
		{
			awayGoalsAgainst += anAwayGoal;
		}
		public void setGoalDifference(int aGoalDifference)
		{
			goalDifference += aGoalDifference;
		}
		public void setTotalPoints(int aTotalPoints)
		{
			totalPoints += aTotalPoints;
		}
		//get Methods
		public int getTeamNumber()
		{
			return teamNumber;
		}
		public int getGamesPlayed()
		{
			return gamesPlayed;
		}
		public int getHomeWins()
		{
			return homeWins;
		}
		public int getHomeDraws()
		{
			return homeDraws;
		}
		public int getHomeLosses()
		{
			return homeLosses;
		}
		public int getHomeGoalsFor()
		{
			return homeGoalsFor;
		}
		public int getHomeGoalsAgainst()
		{
			return homeGoalsAgainst;
		}
		public int getAwayWins()
		{
			return awayWins;
		}
		public int getAwayDraws()
		{
			return awayDraws;
		}
		public int getAwayLosses()
		{
			return awayLosses;
		}
		public int getAwayGoalsFor()
		{
			return awayGoalsFor;
		}
		public int getAwayGoalsAgainst()
		{
			return awayGoalsAgainst;
		}
		public int getGoalDifference()
		{
			return goalDifference;
		}
		public int getTotalPoints()
		{
			return totalPoints;
		}
	}
	