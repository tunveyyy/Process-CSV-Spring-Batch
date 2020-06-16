package com.hcl.onetest.exception;

import antlr.StringUtils;
import com.hcl.onetest.config.FileStorageProperties;
import net.bytebuddy.implementation.bytecode.Throw;

public class FileStorageException extends RuntimeException{

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
