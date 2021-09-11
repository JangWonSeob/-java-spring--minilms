package ko.co.dongyang.study.minilms.common.model;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class ResponseResult<T> {

    private ResponseResultHeader header;
    private T body;

    public static <E extends Object> ResponseEntity<?> fail(String message){
       return fail(message, "");
    }

    public static <E extends Object> ResponseEntity<?> fail(String message, String resultCode){

        ResponseResult<E> result = new ResponseResult<>();

        ResponseResultHeader header = new ResponseResultHeader();
        header.setResult(false);
        header.setMessage(message);
        header.setResultCode(resultCode);

        result.setHeader(header);

        return ResponseEntity.ok(result);
    }

    public static <E extends Object> ResponseEntity<?> success(E e){
        ResponseResult<E> result = new ResponseResult<>();

        ResponseResultHeader header = new ResponseResultHeader();
        header.setResult(true);
        header.setMessage("");
        header.setResultCode("");

        result.setHeader(header);
        result.setBody(e);

        return ResponseEntity.ok(result);
    }

}
