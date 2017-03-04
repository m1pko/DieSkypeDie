package com.dieskypedie.exception;

public class DieSkypeDieException extends Exception 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DieSkypeDieException()
	{
	}

	public DieSkypeDieException(String message)
	{
		super(message);
	}

	public DieSkypeDieException(Throwable cause)
	{
		super(cause);
	}

	public DieSkypeDieException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
