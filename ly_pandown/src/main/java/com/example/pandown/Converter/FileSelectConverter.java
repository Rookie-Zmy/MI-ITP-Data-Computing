package com.example.pandown.Converter;

import com.example.pandown.dao.FileOperate;
import com.example.pandown.dto.FileAdd;
import com.example.pandown.dto.FileSelect;

public class FileSelectConverter {

    public static FileSelect FileSelectConverter(FileOperate fileOperate){
        FileSelect fileSelect = new FileSelect();
        fileSelect.setId(fileOperate.getId());
        fileSelect.setName(fileOperate.getName());
        fileSelect.setLink(fileOperate.getLink());
        return fileSelect;
    }

    public static FileOperate FileAddConverter(FileAdd fileAdd){
        FileOperate fileOperate = new FileOperate();
        fileOperate.setId(fileAdd.getId());
        fileOperate.setName(fileAdd.getName());
        fileOperate.setLink(fileAdd.getLink());
        return fileOperate;
    }
}
