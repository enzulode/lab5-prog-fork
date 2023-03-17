package com.megateam.lab.common.data;

import com.megateam.lab.common.data.util.LocalDateTimeAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class MarshUnmarshContainer<T>
{
	@NonNull
	@XmlElement(name = "databaseCreationDate", required = true)
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime localDateTime;
	@NonNull
	@XmlElementWrapper(name = "elements", required = true)
	@XmlElement(name = "element")
	private List<T> data;
}
