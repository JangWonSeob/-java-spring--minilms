package ko.co.dongyang.study.minilms.common.model;

import lombok.Data;

@Data
public class ResponseResult<T> {

    private ResponseResultHeader header;
    private T body;


}
