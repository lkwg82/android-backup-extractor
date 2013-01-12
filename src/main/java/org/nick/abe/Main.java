package org.nick.abe;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;


public class Main {

  public static void main(String[] args) {
    Security.addProvider(new BouncyCastleProvider());

    if (args.length < 3) {
      usage();

      System.exit(1);
    }

    String mode = args[0];
    if (!"pack".equals(mode) && !"unpack".equals(mode)) {
      usage();

      System.exit(1);
    }

    boolean unpack = "unpack".equals(mode);
    String backupFilename = unpack ? args[1] : args[2];
    String tarFilename = unpack ? args[2] : args[1];
    String password = null;
    if (args.length > 3) {
      password = args[3];
    }

    if (unpack) {
      AndroidBackup.extractAsTar(backupFilename, tarFilename, password);
    } else {
      AndroidBackup.packTar(tarFilename, backupFilename, password);
    }
  }

  private static void usage() {
    System.out.println("Android backup extractor");
    System.out.println("Usage:");
    System.out
        .println("\tunpack:\tabe unpack <backup.ab> <backup.tar> [password]");
    System.out
        .println("\tpack:\tabe pack <backup.tar> <backup.ab> [password]");
  }
}
