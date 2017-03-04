package com.dieskypedie.generic;

import java.io.IOException;

import com.dieskypedie.exception.DieSkypeDieException;
import com.dieskypedie.exec.IDieSkypeDieConstants;

public class KillProcess {
	
	private static String OS;
	private static String PID;
	
	public KillProcess(String PID, String OS){
		setPID(PID);
		setOS(OS);
	}

	public String getOS() {
		return OS;
	}
	
	public static void setOS(String oS) {
		OS = oS;
	}

	public String getPID() {
		return PID;
	}
	
	public static void setPID(String pID) {
		PID = pID;
	}
	
	public Process killProcess() throws IOException, DieSkypeDieException
	{
		Process procToTerminate = null;
		
		if (getOS() == IDieSkypeDieConstants.MAC)
		{
			procToTerminate = Runtime.getRuntime().exec("kill -9 " + getPID());
		}
		
		if (getOS() == IDieSkypeDieConstants.WINDOWS)
		{
			procToTerminate = Runtime.getRuntime().exec("taskkill /PID " + getPID() + " /F");
		}
		
		return procToTerminate;
	}
	
	public boolean isProcessTerminated(Process procToTerminate)
	{
		if(procToTerminate == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}



}
