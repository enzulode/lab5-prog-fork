package com.megateam.lab.server;

import com.megateam.lab.common.data.*;
import com.megateam.lab.common.exceptions.DatabaseException;
import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.server.dao.Dao;
import com.megateam.lab.server.dao.TicketDaoImpl;
import com.megateam.lab.server.db.*;

public class Server {
  public static void main(String[] args) {
    try {
      FileManipulationService fileManipulationService = new FileManipulationService();
      FileValidationService fileValidationService = new FileValidationService();

      TicketMarshallingUnmarshallingService marService =
          new TicketMarshallingUnmarshallingService(fileManipulationService, fileValidationService);
      Database<Ticket> ticketDatabase = new TicketDatabase(marService);
      ticketDatabase.load();
      Dao<Ticket> ticketDao = new TicketDaoImpl(ticketDatabase);

      System.out.println(ticketDao.getAll());
      //			Dao<Ticket> ticketDao = new TicketDaoImpl(ticketDatabase);

      //			Coordinates coordinates1 = new Coordinates(10.2F, 17);
      //			Venue venue1 = new Venue(1, "DUPA-PUPA", 100, VenueType.LOFT);
      //			Ticket ticket1 = new Ticket(
      //					1,
      //					"Ivan",
      //					coordinates1,
      //					LocalDateTime.now(ZoneId.systemDefault()),
      //					17F,
      //					"comment1",
      //					true,
      //					TicketType.VIP,
      //					venue1
      //			);
      //
      //			Coordinates coordinates2 = new Coordinates(10.2F, 17);
      //			Venue venue2 = new Venue(1, "DUPA-PUPA", 100, VenueType.LOFT);
      //			Ticket ticket2 = new Ticket(
      //					2,
      //					"PETYA",
      //					coordinates2,
      //					LocalDateTime.now(ZoneId.systemDefault()),
      //					17F,
      //					"comment2",
      //					false,
      //					TicketType.BUDGETARY,
      //					venue2
      //			);
      //
      //			ticketDao.add(ticket1);
      //			ticketDao.add(ticket2);
      //			ticketDatabase.save();

    } catch (EnvException | DatabaseException e) {
      System.out.println(e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
