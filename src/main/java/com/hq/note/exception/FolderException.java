package com.hq.note.exception;

/**
 * 文件夹自定义异常
 *
 * @author HQ
 **/
public class FolderException extends RuntimeException {

    public FolderException(String message) {
        super(message);
    }

}
