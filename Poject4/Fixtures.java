	public class Fixtures
	{
		private int fixtureNumber;
		private int homeTeamNumber;
		private int awayTeamNumber;

		Fixtures(int aFixtureNumber,int aHomeTeamNumber,int anAwayTeamNumber)
		{
			fixtureNumber = aFixtureNumber;
			homeTeamNumber = aHomeTeamNumber;
			awayTeamNumber = anAwayTeamNumber;
		}

		public int getFixtureNumber()
		{
			return fixtureNumber;
		}

		public int getHomeTeamNumber()
		{
			return homeTeamNumber;
		}

		public int getAwayTeamNumber()
		{
			return awayTeamNumber;
		}
	}