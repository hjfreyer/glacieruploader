package de.kopis.glacier;

/*
 * #%L
 * uploader
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2012 Carsten Ringe
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.amazonaws.services.glacier.transfer.ArchiveTransferManager;

public class CommandLineGlacierUploader extends AbstractGlacierCommand {

  public CommandLineGlacierUploader(File credentials) throws IOException {
    super(credentials);
  }

  public void upload(URL endpointUrl, String vaultName, File uploadFile) {
    try {
      System.out.println("Using endpoint " + endpointUrl);
      client.setEndpoint(endpointUrl.toString());

      System.out.println("Starting upload of " + uploadFile);
      final ArchiveTransferManager atm = new ArchiveTransferManager(client, credentials);
      final String archiveId = atm.upload(vaultName, uploadFile.getName(), uploadFile).getArchiveId();
      System.out.println("Uploaded archive " + archiveId);
    } catch (IOException e) {
      System.err.println("Something went wrong while uploading " + uploadFile + ".");
      e.printStackTrace();
    }
  }
}
