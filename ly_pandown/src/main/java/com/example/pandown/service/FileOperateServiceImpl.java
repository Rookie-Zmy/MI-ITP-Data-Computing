package com.example.pandown.service;

import com.example.pandown.Converter.FileSelectConverter;
import com.example.pandown.dao.FileOperate;
import com.example.pandown.dao.FileOperateRepository;
import com.example.pandown.dto.FileAdd;
import com.example.pandown.dto.FileSelect;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileOperateServiceImpl implements FileOperateService{

    @Autowired
    private FileOperateRepository fileOperateRepository;

    @Value("${file.storage.root-dir}")
    private String rootDir;

    @Override
    public FileSelect getFileOperateByName(String name) {
        List<FileOperate> files = fileOperateRepository.findByName(name);
        if (files.isEmpty()) {
            throw new RuntimeException("File not found with name: " + name);
        }
        FileOperate file = files.get(0);  // 获取第一个匹配的文件
        return FileSelectConverter.FileSelectConverter(file);
    }

    @Override
    public String addNewFile(FileAdd fileAdd) {
        List<FileOperate> fileOperates= fileOperateRepository.findByName(fileAdd.getName());
        if(!CollectionUtils.isEmpty(fileOperates)){
            throw  new IllegalArgumentException("name:"+ fileAdd.getName() + "has been exist!");
        }
        FileOperate fileOperate =fileOperateRepository.save(FileSelectConverter.FileAddConverter(fileAdd));
        return fileOperate.getName();
    }



    @Override
    public List<FileOperate> getAllFiles() {
        return fileOperateRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteFileByName(String name) {
        fileOperateRepository.deleteByName(name);
    }

    @Override
    public List<String> listFiles(String directory) {
        try {
            return Files.list(Paths.get(rootDir, directory))
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to list files", e);
        }
    }

    @Override
    @Transactional
    public void uploadFile(String directory, String fileName, InputStream fileContent) {
        try {
            File targetFile = new File(rootDir + File.separator + directory + File.separator + fileName);
            targetFile.getParentFile().mkdirs();

            try (FileOutputStream out = new FileOutputStream(targetFile)) {
                fileContent.transferTo(out);
            }

            // 向数据库插入记录
            FileAdd fileAdd = new FileAdd();
            fileAdd.setName(fileName);
            fileAdd.setLink(Paths.get(rootDir, directory).toString());
            addNewFile(fileAdd);

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }
    @Override
    public InputStream downloadFile(String directory, String fileName) {
        try {
            return new FileInputStream(new File(rootDir + File.separator + directory + File.separator + fileName));
        } catch (Exception e) {
            throw new RuntimeException("Failed to download file", e);
        }
    }

    @Override
    @Transactional
    public void deleteRepositoryFile(String directory, String fileName) {
        try {
            // 删除文件
            Files.deleteIfExists(Paths.get(rootDir, directory, fileName));

            // 删除数据库记录
            fileOperateRepository.deleteByName(fileName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file from repository", e);
        }
    }
}




