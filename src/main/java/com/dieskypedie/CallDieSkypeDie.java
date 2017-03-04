package com.dieskypedie;

import java.io.IOException;

import com.dieskypedie.exception.DieSkypeDieException;
import com.dieskypedie.exec.DieSkypeDie;

public class CallDieSkypeDie {

	public static void main(String[] args) {
		
		DieSkypeDie dsd = new DieSkypeDie();
		
		if (dsd.validOS(dsd.getOS()))
		{
			try
			{
			  dsd.getRunningProcesses();
			}
			catch (DieSkypeDieException dsde)
			{
				System.out.println("An error occurred: " + dsde.getMessage());
			}
			catch (IOException ioe)
			{
				System.out.println("An error occurred: " + ioe.getMessage());
			}

		}
		else
		{
			System.out.println("I'm sorry but the OS isn't compatible.");
		}
	}

}
