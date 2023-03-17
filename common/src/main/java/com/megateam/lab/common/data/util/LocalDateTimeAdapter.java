package com.megateam.lab.common.data.util;

import lombok.NonNull;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime>
{
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	public LocalDateTime unmarshal(@NonNull String value)
	{
		if ("".equals(value))
			return LocalDateTime.now(ZoneId.systemDefault());

		return LocalDateTime.parse(value, formatter);
	}

	@Override
	public String marshal(@NonNull LocalDateTime value)
	{
		return value.format(formatter);
	}
}
