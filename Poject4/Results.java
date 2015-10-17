	public class Results
	{
		private int fixtureNumber;
		private int homeScore;
		private int awayScore;

		Results(int aFixtureNumber, int aHomeScore, int anAwayScore)
		{
			fixtureNumber = aFixtureNumber;
			homeScore = aHomeScore;
			awayScore = anAwayScore;
		}

		public int getFixtureNumber()
		{
			return fixtureNumber;
		}

		public int getHomeScore()
		{
			return homeScore;
		}

		public int getAwayScore()
		{
			return awayScore;
		}
	}