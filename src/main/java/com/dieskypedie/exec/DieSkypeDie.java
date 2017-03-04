package com.dieskypedie.exec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dieskypedie.exception.DieSkypeDieException;
import com.dieskypedie.generic.KillProcess;


public class DieSkypeDie implements IDieSkypeDieConstants{
	
	Timestamp timeStamp;
	String OS;
	String pid;
	
	public DieSkypeDie(){
		timeStamp = new Timestamp(new java.util.Date().getTime());
		OS = System.getProperty("os.name").toLowerCase();
		pid = "";
	};
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getOS() {
		return OS;
	}
	
	public void setOS(String oS) {
		OS = oS;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	
	public boolean validOS(String OS)
	{
		if (OS.indexOf(IDieSkypeDieConstants.WINDOWS) >= 0)
		{
			setOS(IDieSkypeDieConstants.WINDOWS);
			return true;
		}else if (OS.indexOf(IDieSkypeDieConstants.MAC) >= 0)
		{
			setOS(IDieSkypeDieConstants.MAC);
			return true;
		}
		else
		{
			return false;
		}
       
	}
	
	public void readProcessesList(Process runningProcs) throws IOException
	{
		String process = "";
		int matchCounter = 0;
		@SuppressWarnings("unused")
		Process procToTerminate = null;

		//AppToKill appToKill = new AppToKill();
		//appToKill.getAppToKill();
		//String pattern = appToKill.getAppName();
		String pattern = IDieSkypeDieConstants.SKYPE;
		Pattern r = Pattern.compile(pattern);
		Matcher match = null;
		BufferedReader input = new BufferedReader(new InputStreamReader(runningProcs.getInputStream()));
		
		while ((process = input.readLine()) != null)
		{
			match = r.matcher(process);
			if (match.find())
			{
				if (process.contains("Skype.app") || process.contains("Skype.exe"))
				{
					getProcessPID(process);
					matchCounter++;
					
					try 
					{
						procToTerminate = new KillProcess(getPid(), getOS()).killProcess();
					} catch (DieSkypeDieException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if (matchCounter == 0)
		{
			System.out.println(pattern + " isn't running!");
		}
		else
		{
			System.out.println(pattern + " app was terminated.");
		}
		input.close();
	}
	
	public void getRunningProcesses() throws IOException, DieSkypeDieException
	{
		Process runningProcs = null;
		
		if (getOS() == IDieSkypeDieConstants.WINDOWS)
		{
			runningProcs = Runtime.getRuntime().exec("tasklist");
		  
		}
		else if ( getOS() == IDieSkypeDieConstants.MAC)
		{
		    runningProcs = Runtime.getRuntime().exec("ps -ef");		
		}
		else
		{
			throw new DieSkypeDieException ("An error occurred while trying to list the running processes for " + OS);
		}
		
		readProcessesList(runningProcs);
	}
	
	public void getProcessPID(String processLine)
	{
		String pattern = "\\s+"; //Blank space operating as a separator character
		int i = 0;
		String[] splitArray = null;
		
		if (getOS() == IDieSkypeDieConstants.MAC || getOS() == IDieSkypeDieConstants.WINDOWS)
		{
			splitArray = processLine.split(pattern);
			while (i < splitArray.length)
			{
				if (i == 1 && getOS() == IDieSkypeDieConstants.WINDOWS)
				{
					setPid(splitArray[i].trim());
					break;
				}
				if (i == 2 && getOS() == IDieSkypeDieConstants.MAC)
				{
					setPid(splitArray[i].trim());
					break;
				}
				i++;
			}
		}
	}
	

}
