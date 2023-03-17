package com.megateam.lab.server.db;

import com.megateam.lab.common.data.Ticket;
import com.megateam.lab.common.data.TicketContainer;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class TicketDatabase implements Database<Ticket> {
  @NonNull private List<Ticket> collectionElements;
  @NonNull @Getter private LocalDateTime creationDate;
  @NonNull private TicketMarshallingUnmarshallingService marshallingUnmarshallingService;

  public TicketDatabase(TicketMarshallingUnmarshallingService marshallingUnmarshallingService) {
    this.collectionElements = new ArrayList<>();
    this.creationDate = LocalDateTime.now(ZoneId.systemDefault());
    this.marshallingUnmarshallingService = marshallingUnmarshallingService;
  }

  public Optional<Ticket> get(Integer id) {
    for (Ticket ticket : collectionElements) {
      if (id.equals(ticket.getId())) {
        return Optional.of(ticket);
      }
    }

    return Optional.empty();
  }

  public List<Ticket> getAll() {
    return collectionElements;
  }

  public void add(@NonNull Ticket item) {
    collectionElements.add(item);
  }

  @Override
  public void update(@NonNull Integer id, @NonNull Ticket item) {
    int idx = 0;
    for (Ticket ticket : collectionElements) {
      if (id.equals(ticket.getId())) {
        collectionElements.set(idx, item);
      }
      idx++;
    }
  }

  public void delete(@NonNull Integer id) {
    collectionElements.removeIf(ticket -> id.equals(ticket.getId()));
  }

  public void clear() throws EnvException, DatabaseException {
    collectionElements.clear();
    marshallingUnmarshallingService.marshal(
        LocalDateTime.now(ZoneId.systemDefault()), Collections.emptyList());
  }

  public void removeFirst() {
    if (collectionElements.size() != 0) collectionElements.remove(0);
  }

  public void removeLast() {
    if (collectionElements.size() != 0) collectionElements.remove(collectionElements.size() - 1);
  }

  public void save() throws DatabaseException, EnvException {
    marshallingUnmarshallingService.marshal(creationDate, collectionElements);
  }

  public void load() throws EnvException, DatabaseException {
    Optional<TicketContainer> container = marshallingUnmarshallingService.unmarshal();
    if (container.isEmpty()) {
      this.creationDate = LocalDateTime.now(ZoneId.systemDefault());
      this.collectionElements = new ArrayList<>();
      return;
    }

    TicketContainer data = container.get();
    this.creationDate = data.getCreationDate();
    this.collectionElements = data.getTickets();
  }
}
