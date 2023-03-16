package com.megateam.lab.common.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class EnvException extends Exception
{
	private String message;
}
