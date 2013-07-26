package com.shnarped.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.channels.FileChannel;

public class FileHelper {
	
	public static void copyFile(InputStream fromFile, OutputStream toFile) throws IOException {
		byte[] buffer = new byte[1024];
		int length;
		
		try {
			while ((length = fromFile.read(buffer)) > 0) {
                toFile.write(buffer, 0, length);
            }
        }
		
        finally {
            try {
                if (toFile != null) {
                    try {
                        toFile.flush();
                    } finally {
                        toFile.close();
                    }
                }
            } finally {
                if (fromFile != null) {
                    fromFile.close();
                }
            }
        }
    }
	public static void copyFile(String fromFile, String toFile) throws IOException {
        copyFile(new FileInputStream(fromFile), new FileOutputStream(toFile));
    }
    
    public static void copyFile(File fromFile, File toFile) throws IOException {
        copyFile(new FileInputStream(fromFile), new FileOutputStream(toFile));
    }
    
    public static void copyFile(FileInputStream fromFile, FileOutputStream toFile) throws IOException {
        FileChannel fromChannel = fromFile.getChannel();
        FileChannel toChannel = toFile.getChannel();

        try {
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close();
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close();
                }
            }
        }
    }
    public static String[] parseSqlFile(String sqlFile) throws IOException {
        return parseSqlFile(new BufferedReader(new FileReader(sqlFile)));
    }
    
    public static String[] parseSqlFile(InputStream sqlFile) throws IOException {
        return parseSqlFile(new BufferedReader(new InputStreamReader(sqlFile)));
    }
    
    public static String[] parseSqlFile(Reader sqlFile) throws IOException {
        return parseSqlFile(new BufferedReader(sqlFile));
    }
    
    public static String[] parseSqlFile(BufferedReader sqlFile) throws IOException {
        String line;
        StringBuilder sql = new StringBuilder();
        String multiLineComment = null;

        while ((line = sqlFile.readLine()) != null) {
        	line = line.trim();
            if (multiLineComment == null) {
            	if (line.startsWith("/*")) {
                    if (!line.endsWith("}")) {
                        multiLineComment = "/*";
                    }
                } else if (line.startsWith("{")) {
                    if (!line.endsWith("}")) {
                        multiLineComment = "{";
                    }
                } else if (!line.startsWith("--") && !line.equals("")) {
                    sql.append(line);
                }
            } else if (multiLineComment.equals("/*")) {
                if (line.endsWith("*/")) {
                    multiLineComment = null;
                }
            } else if (multiLineComment.equals("{")) {
                if (line.endsWith("}")) {
                    multiLineComment = null;
                }
            }

        }
        sqlFile.close();
        return sql.toString().split(";");
    }

}
