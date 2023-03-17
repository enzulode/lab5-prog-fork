package com.megateam.lab.server.db;

import com.megateam.lab.common.exceptions.EnvException;
import com.megateam.lab.server.util.EnvHelper;
import java.io.File;

public class FileManipulationService {
  public File retrieveSavingFile() throws EnvException {
    String filePath = EnvHelper.retrieveFileName();

    return new File(filePath);
  }
}
