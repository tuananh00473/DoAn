package com.qsoft.augen.business.common;

import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileJava
{

	public static void WritToFile(String path, String fileName, String content) {
		try {
            File file = new File(path);
            file.mkdir();
			FileWriter fstream = new FileWriter(path + fileName);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(content);
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void copyFile(String source, String destination) {
		File sourceFile = new File(source);
		String name = sourceFile.getName();
		File targetFile = new File(destination + name);
		try {
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
