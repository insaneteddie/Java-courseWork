
/**
 *
 * @author Steve
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Random;        
import java.util.Collections;
import java.lang.*;
import java.util.Scanner;
public class firstFitTalkSchedule 
{  
    public static void main(String [] args)
    {
        File readIn;
        File outPut;
        if(args.length < 1)
        {   
            Scanner in = new Scanner(System.in);
            System.out.println("Please Enter a filename to read: \n(Example : filename.txt)");
            readIn = new File(in.nextLine()); 
           
        }
        else if(args.length == 1)
			{
				readIn = new File(args[0]);
			}
			else
			readIn = new File("testInput.txt");
    if(readIn.exists())
    {
        if(readIn.canRead())
        {		
            //readInput(readIn);
            ArrayList<TalkObject> talks = new ArrayList<>();
			talks = readInput(readIn);
			//firstFit sorting Algorithm
			sortTalks(talks);
			//print output to screen
			printSchedule(talks);
		}
        else 
			System.out.println("Error file " + readIn + "cannot be read, check persmissions and try again.");
    }
    else
        System.out.println("Error " + readIn + " cannot be found, please check the filename and try again");
    }
    //read input method
    public static ArrayList<TalkObject> readInput(File input)
    {
        //creating the filereader
        FileReader fileRead;
		TalkObject talk;
        //arrayLists to store the talkObject - name and length of talk
        ArrayList<TalkObject> containerArray = new ArrayList<TalkObject>();
        //buffered reader for reading wrapping around the filereader
        BufferedReader bfReader;	
        try
        {
            fileRead = new FileReader(input);
            bfReader = new BufferedReader(fileRead);
            String line = "";
            String strTime = "";
            String topic = "";
			int talkLength = 0;
			//patterns for matching when reading from times arrayList
			String timePattern = "[0-9]{2}[A-Za-z]{3}";
			String lightningPattern = "[A-Za-z]{9}";
            //reading the lines in from the file and adding to arraylist.
            while((line = bfReader.readLine()) != null)
            {	//trimming leading / ending whitespaces from line 
                line.trim();
				//reseting talkLength to 0;
				talkLength = 0;
                int lastIndex = line.lastIndexOf(" ");
                //getting the last word from the line of the file from the index of the last white space
                strTime = line.substring( lastIndex + 1);
				if(strTime.matches(timePattern))
				{
					talkLength = Integer.parseInt(strTime.substring(0,strTime.length() - 3));
				}//if statement for any "lightning" talks and giving the value of 5(min) for it.
				else if(strTime.matches(lightningPattern))
				{
					talkLength = 5;
				}
                //getting the rest of the string from index 0 to last index of the last white space
                topic = line.substring(0,lastIndex);
				talk = new TalkObject(topic,talkLength);
				containerArray.add(talk);				
            }
            //closing the filereader
            fileRead.close();
            //closing the buffered reader as its no longer needed.
            bfReader.close();
        }
        catch(FileNotFoundException notFound)
        {
                System.out.println("Error : file "+ input + "not found, check directory and try again.");
        }
        catch(IOException anIOExcept)
        {
                System.out.println("Error :" + anIOExcept.getMessage());
        }
        return containerArray;
    }
	//firstFit sorting of talks for schedule 
	public static void sortTalks(ArrayList<TalkObject> talks)
	{	
		int maxBin = 4;
		//creating the bins and giving them the capacity
		int  [] lengthOfSlot = new int[maxBin];
		lengthOfSlot[0] = 180;
		lengthOfSlot[1] = 180;
		lengthOfSlot[2] = 240;
		lengthOfSlot[3] = 240;				
		int [] sum = new int [maxBin];
		int i = 0;		
		for(int session = 0; session < maxBin;session++)
		{
			for(; i < talks.size();i++)
			{
				if(sum[session] + talks.get(i).getTime() <= lengthOfSlot[session])
				{
					sum[session] += talks.get(i).getTime();
					talks.get(i).setSession(session);
					//System.out.println(sum[session] + " "+talks.get(i).getTime() + " "+talks.get(i).getTitle());
				}
				else
				{
					break;	
				}					
			}
		}
	}
	//output method
	public static void printSchedule(ArrayList<TalkObject>talks)
	{
		int numberSessions = 4;
		int startTime = 9;
		int afterLunch = 13;
		int totalMinutes = 0;
		String results = "";	
		for(int i = 0; i < numberSessions ; i++)
		{
			if(i < 2 )
			{
				totalMinutes = startTime * 60;
				results += "Morning :"+(i+1)+"\n";
			}
			else
			{
				totalMinutes = afterLunch * 60;
				results += "Afternoon :"+(i+1) / 2+"\n";
			}
			
			for(int j = 0; j < talks.size();j++)
			{
				if(talks.get(j).getSession()== i)
				{	
					int startHour = totalMinutes/60;
					int startMinute = totalMinutes %60;
					//formatting string
					results += String.format("%02d", startHour) + ":" + String.format("%02d", startMinute) + "\t"+ talks.get(j).getTitle() + "  " + talks.get(j).getTime()+"Mins\n";
					totalMinutes += talks.get(j).getTime();
				}
			}
		} 
        System.out.println(results);
	}
}