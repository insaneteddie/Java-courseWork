public class TalkObject 
{
	private int time = 0;
	private String title = "";
	private int session = 0;
	
	public TalkObject(String title, int time)
	{
		this.title = title;
		this.time = time;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public String getTitle()
	{
		return title;
	}
	public void setSession(int sess)
	{
		this.session = sess;
	}
	
	public int getSession()
	{
		return session;
	}
	

}