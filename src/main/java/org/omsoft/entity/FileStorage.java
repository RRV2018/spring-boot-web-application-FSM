package org.omsoft.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "FILE_STORAGE")
@ToString
public class FileStorage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id", nullable = false)
    private Integer fileId;
    @Column(name = "file_name")
    private String fileName;
    @Lob
    @Column(name = "file_content")
    private byte[] fileContent;
    @Column(name = "file_size")
    private Long fileSize;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;
}
