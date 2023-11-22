package entity;

import java.io.Serializable;

/**
 * @Title: FileSystem
 * @Author Mr.罗
 * @Package entity
 * @Date 2023/11/21 18:53
 * @description: 上传的文件
 */
public class FileSystem implements Serializable {
    private String fileId;
    private String filePath;
    private String fileName;

    public FileSystem() {
    }

    public FileSystem(String fileId, String filePath, String fileName) {
        this.fileId = fileId;
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileSystem{" +
                "fileId='" + fileId + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
