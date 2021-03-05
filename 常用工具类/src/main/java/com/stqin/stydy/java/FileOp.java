package com.stqin.stydy.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileOp {
    /**
     * 基于一个路径构建的file，获得所有file，包括子文件夹下的file
     *
     * @param baseFile
     * @return
     */
    public static List<File> getAllFile(File baseFile) {
        List<File> fileList = new ArrayList<>();
        if (!baseFile.exists()) {
            return fileList;
        }
        if (baseFile.isFile()) {
            fileList.add(baseFile);
            return fileList;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                fileList.addAll(getAllFile(file));
            } else {
                fileList.add(file);
            }
        }
        return fileList;
    }

    /**
     * 获得一个文件夹下所有file
     *
     * @param filePath
     * @return
     */
    public static File[] getFile(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            return file.listFiles();
        } else {
            File[] files = new File[1];
            files[0] = file;
            return files;
        }
    }

    /**
     * 过滤掉名称不包含 fileNameFilter 的file
     *
     * @param fileList
     * @return
     */
    public static File[] fileNameFilter(List<File> fileList, String fileNameFilter) {
        List<File> tmp = new ArrayList<>();
        for (File file : fileList) {
            if (file.getName().contains(fileNameFilter)) {
                tmp.add(file);
            }
        }

        File[] files = new File[tmp.size()];
        files = tmp.toArray(files);
        return files;
    }


    /**
     * 拷贝文件
     *
     * @param source
     * @param target
     */
    public static void copy(String source, String target) throws IOException {
        if (null == source || null == target) {
            return;
        } else {
            File sourceFile = new File(source);
            File targetFile = new File(target);

            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }

            if (!sourceFile.exists()) {
                return;
            } else {
                Files.copy(sourceFile.toPath(), targetFile.toPath());
            }
        }
    }
}
