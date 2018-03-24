package com.test;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PathTest {
    @Test
    public void main() {
        String path = "E:/ParsePowerPointWithApachePOI/ZeroFilesOutput/NO1PPTS/";
        for (int i = 80; i < 100; i++) {
            File file = new File(path + i + "/");
            System.out.println("_________________________");
            System.out.println(i);
            List<File> fileList = new ArrayList<>();
            fileList = getPptFile(file.listFiles(), new ArrayList<>());
            for (File file1 : fileList) {
                if (file1 != null) {
                    System.out.println(file1.getName());
                    System.out.println(file1.getPath());
                    System.out.println(file1.getAbsolutePath());
                }
            }

            System.out.println("++++++++++++++");
        }

    }

    private List<File> getPptFile(File[] files, List<File> fileList) {
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getPptFile(file.listFiles(), fileList);
                } else if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.toLowerCase().contains(".ppt") || fileName.toLowerCase().contains(".pptx")) {
                        System.out.println(file.getName());
                        System.out.println("【" + file.getPath() + "】");
                        System.out.println("【" + file.getAbsolutePath() + "】");
                        fileList.add(file);
                    }
                }
            }
        }
        return fileList;
    }
}
