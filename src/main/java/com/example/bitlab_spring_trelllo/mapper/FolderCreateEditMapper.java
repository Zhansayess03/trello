package com.example.bitlab_spring_trelllo.mapper;
import com.example.bitlab_spring_trelllo.dto.FolderCreateEditDto;
import com.example.bitlab_spring_trelllo.model.Folder;
import com.example.bitlab_spring_trelllo.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FolderCreateEditMapper implements Mapper<FolderCreateEditDto, Folder> {
    private final MyUserService userService;

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
        folder.setUser(userService.getCurrentUser());
    }
}
