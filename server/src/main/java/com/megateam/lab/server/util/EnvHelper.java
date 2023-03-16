package com.megateam.lab.server.util;

import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.exceptions.impl.VariableAccessDeniedException;
import com.megateam.lab.common.exceptions.impl.VariableNotDefinedException;

public class EnvHelper
{

	public static String retrieveFileName() throws EnvException
	{
		try
		{
			return System.getenv("SAVING_FILE");
		}
		catch (NullPointerException e)
		{
			throw new VariableNotDefinedException("Collection saving file path variable is not set.");
		}
		catch (SecurityException e)
		{
			throw new VariableAccessDeniedException("You're not able to access collection saving file path variable.");
		}
	}
}
