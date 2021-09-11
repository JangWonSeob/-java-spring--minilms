package ko.co.dongyang.study.minilms.common.model;

import lombok.Data;

@Data
public class ResponseResultHeader {

    private boolean result;
    private String message;
    private String resultCode;
}
