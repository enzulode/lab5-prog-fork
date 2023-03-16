package com.megateam.lab.common.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class DatabaseException extends Exception
{
	private String message;
}
