package com.megateam.lab.server.db;

import com.megateam.lab.common.data.MarshUnmarshContainer;
import com.megateam.lab.common.data.util.LocalDateTimeAdapter;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.common.exceptions.impl.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class MarshallingUnmarshallingService<T>
{
	@NonNull
	private FileManipulationService fileManipulationService;
	@NonNull
	private FileValidationService validationService;

	public void marshal(@NonNull LocalDateTime dateTime, @NonNull List<T> collectionElements) throws DatabaseException, EnvException
	{
		File file = fileManipulationService.retrieveSavingFile();
		validationService.validateSavingFileExistence(file);
		validationService.validateSavingFileExistence(file);

		try (FileOutputStream fos = new FileOutputStream(file))
		{
			JAXBContext context = JAXBContext.newInstance(MarshUnmarshContainer.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setAdapter(new LocalDateTimeAdapter());

			MarshUnmarshContainer<T> container = new MarshUnmarshContainer<>(dateTime, collectionElements);
			marshaller.marshal(container, fos);
		}
		catch (IOException e)
		{
			throw new DatabaseFileFailedInputStreamOpenException(
					"Something went wrong during opening database file: " + e.getMessage()
			);
		}
		catch (JAXBException e)
		{
			throw new DatabaseMarshallingException(
					"Something went wrong during marshalling: " + e.getMessage()
			);
		}
	}

	@SuppressWarnings("unchecked")
	public Optional<MarshUnmarshContainer<T>> unmarshal() throws DatabaseException, EnvException
	{
		File file = fileManipulationService.retrieveSavingFile();
		validationService.validateSavingFileExistence(file);
		validationService.validateSavingFilePermissions(file);

		try
		{
			JAXBContext context = JAXBContext.newInstance(MarshUnmarshContainer.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setAdapter(new LocalDateTimeAdapter());
			MarshUnmarshContainer<T> container = (MarshUnmarshContainer<T>) unmarshaller.unmarshal(file);
			return Optional.ofNullable(container);
		}
		catch (JAXBException e)
		{
			throw new DatabaseUnmarshallingException("Something went wrong during unmarshalling: " + e.getMessage());
		}
	}

}
