package ko.co.dongyang.study.minilms.user.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserSearch {

    String userId;

    long pageIndex;
    long pageSize;

    String searchType;
    String searchValue;

    private void init(){
        if(pageIndex < 1){
            pageIndex = 1;
        }
        if(pageSize < 1){
            pageSize = 10;
        }
    }

    public long getStartLimit(){
        init();
        return (pageIndex -1) * pageSize;
    }
    public long getEndLimit(){
        init();
        return pageSize;
    }

    public String getQueryString(){

        StringBuilder sb = new StringBuilder();

        if(searchType != null && searchType.length() > 0){
            sb.append("searchType=" + searchType);
        }
        if(searchValue != null && searchValue.length() > 0){
            if(sb.length() > 0){
                sb.append("&");
            }
            sb.append("searchValue=" + searchValue);
        }

        return sb.toString();
    }
}
