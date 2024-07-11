package com.example.dto;

import com.example.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Integer id;
    private String title;
    private String content;
    private String fileName;
    private String filePath;

    public BoardEntity toEntity() {
        BoardEntity entity = new BoardEntity();
        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        entity.setFileName(this.fileName);
        entity.setFilePath(this.filePath);
        return entity;
    }

}
