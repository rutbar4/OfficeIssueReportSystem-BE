package com.sourcery.oirs.exceptions;

import lombok.Getter;

@Getter
public class BusyIssueNameException extends RuntimeException{
    private static final String REASON = "issueName.exist";

    public String getReason (){
        return REASON;
    }
}
