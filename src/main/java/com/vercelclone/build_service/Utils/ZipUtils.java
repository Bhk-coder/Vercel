package com.vercelclone.build_service.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils
{
  public static void  unzip(String zipFilePath , String destdir) throws IOException
  {
      File dir = new File(destdir);
      if(!dir.exists())
      {
          dir.mkdirs();
      }

      try(ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath)))
      {
          ZipEntry zipEntry = zis.getNextEntry();

          while(zipEntry != null)
          {
              File newFile =  new File(destdir, zipEntry.getName());
              if(zipEntry.isDirectory())
              {
                  newFile.mkdirs();
              }
              else
              {
                  new File(newFile.getParent()).mkdirs();
                  try(FileOutputStream fos = new FileOutputStream(newFile))
                  {
                      byte[] buffer = new byte[1024];
                      int len;
                      while((len = zis.read(buffer)) > 0)
                      {
                          fos.write(buffer, 0, len);
                      }
                  }
              }
              zipEntry = zis.getNextEntry();
          }
          zis.closeEntry();
      }
  }
}
