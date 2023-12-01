package com.example.bitlab_spring_trelllo.mapper;
import com.example.bitlab_spring_trelllo.dto.FolderCreateEditDto;
import com.example.bitlab_spring_trelllo.model.Folder;
import org.springframework.stereotype.Component;

@Component
public class FolderCreateEditMapper implements Mapper<FolderCreateEditDto, Folder> {

    @Override
    public Folder map(FolderCreateEditDto fromObject, Folder toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Folder map(FolderCreateEditDto object) {
        Folder folder = new Folder();
        copy(object, folder);
        return folder;
    }

    private void copy(FolderCreateEditDto object, Folder folder) {
        folder.setName(object.getName());
    }
}
