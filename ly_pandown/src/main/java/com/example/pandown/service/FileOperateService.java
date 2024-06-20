package com.example.pandown.service;

import com.example.pandown.dao.FileOperate;
import com.example.pandown.dto.FileAdd;
import com.example.pandown.dto.FileSelect;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;


@Service
public interface FileOperateService {
    FileSelect getFileOperateByName(String name);

    String addNewFile(FileAdd fileAdd);

    List<FileOperate> getAllFiles();

    void deleteFileByName(String name);

    List<String> listFiles(String directory);

    void uploadFile(String directory, String fileName, InputStream fileContent);

    InputStream downloadFile(String directory, String fileName);

    void deleteRepositoryFile(String directory, String fileName);
}
