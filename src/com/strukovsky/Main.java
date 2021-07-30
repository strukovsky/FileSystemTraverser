package com.strukovsky;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        String path = new Scanner(System.in).nextLine();
        try
        {
            execute(path, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void execute(String path, int depth) throws IOException {
        DirectoryStream stream = Files.newDirectoryStream(Path.of(path));

        for (Object pathObject : stream) {
            Path pathInDirectory = (Path) pathObject;
            BasicFileAttributes attributes = Files.readAttributes(pathInDirectory, BasicFileAttributes.class);
            if(attributes.isDirectory())
            {
                for (int i = 0; i < depth; i++) {
                    System.out.print("  ");
                }
                System.out.print("<DIR> " + pathInDirectory.getFileName());
                System.out.println();
                execute(pathInDirectory.toAbsolutePath().toString(), depth+1);
            }
            else {
                for (int i = 0; i < depth; i++) {
                    System.out.print("  ");
                }
                System.out.print(pathInDirectory.getFileName());
                System.out.println();
            }
        }

        stream.close();


    }
}
