package ko.co.dongyang.study.minilms.user.model;

import ko.co.dongyang.study.minilms.user.dto.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class ServiceResult<T> {

    private boolean result;
    private String message;

    private int totalCount;
    private List<T> list;
    private T detail;

    public ServiceResult(){
        this.result = false;
        this.message = "";
        this.list = null;
        this.detail = null;
    }

    public static ServiceResult success(){
        ServiceResult<Object> result = new ServiceResult<>();
        result.setResult(true);
        return result;
    }

    public static <S extends BaseDto> ServiceResult success(S detail){
        ServiceResult<Object> result = new ServiceResult<>();
        result.setResult(true);
        result.setDetail(detail);
        return result;
    }

    public static <S> ServiceResult success(List<S> list){
        ServiceResult<S> result = new ServiceResult<>();
        result.setResult(true);
        result.setList(list);
        return result;
    }

    public static <S> ServiceResult success(int totalCount,List<S> list){
        ServiceResult<S> result = new ServiceResult<>();
        result.setResult(true);
        result.setTotalCount(totalCount);
        result.setList(list);
        return result;
    }

    public static ServiceResult fail(String message){
        ServiceResult<Object> result = new ServiceResult<>();
        result.setMessage(message);
        return result;
    }

    public boolean isFail() {
        return !result;
    }

    public boolean isSuccess() {
        return result;
    }
}
