package com.sa.clothingstore.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectAlreadyExistsException extends RuntimeException{
    public ObjectAlreadyExistsException(String message){
        super(message);
    }
}
