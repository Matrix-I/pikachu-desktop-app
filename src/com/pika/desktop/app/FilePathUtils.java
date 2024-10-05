package com.pika.desktop.app;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class FilePathUtils {
  public static URL getURL(String path) {
    File file = new File(path);
    try {
      return file.toURI().toURL();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
