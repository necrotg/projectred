package com.crimson.projectred.helper;

import java.util.Objects;

public class ServiceHelper {
    public Long getValidId(String id){
        if (isValidId(id)){
            return Long.parseLong(id);
        }
        return 0L;
    }

    public boolean isValidId(String id){
        if (Objects.isNull(id) || id.isEmpty()){
            return false;
        }
        try{
            Long.parseLong(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
