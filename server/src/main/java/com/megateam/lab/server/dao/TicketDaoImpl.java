package com.megateam.lab.server.dao;

import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.server.db.Database;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TicketDaoImpl implements Dao<Ticket>
{
	@NonNull
	private Database<Ticket> database;

	@Override
	public LocalDateTime getCreationDate()
	{
		return database.getCreationDate();
	}

	@Override
	public void add(@NonNull Ticket item)
	{
		database.add(item);
	}

	@Override
	public Optional<Ticket> get(@NonNull Integer id)
	{
		return database.get(id);
	}

	@Override
	public List<Ticket> getAll()
	{
		return database.getAll();
	}

	@Override
	public void update(@NonNull Integer id, @NonNull Ticket item)
	{
		database.update(id, item);
	}

	@Override
	public void delete(@NonNull Integer id)
	{
		database.delete(id);
	}

	@Override
	public void clear() throws EnvException, DatabaseException
	{
		database.clear();
	}

	@Override
	public void removeFirst()
	{
		database.removeFirst();
	}

	@Override
	public void removeLast()
	{
		database.removeLast();
	}
}
