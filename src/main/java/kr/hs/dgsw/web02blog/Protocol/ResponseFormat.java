package kr.hs.dgsw.web02blog.Protocol;

import lombok.Data;

@Data
public class ResponseFormat {
    private int code;
    private String desc;
    private Object data;

    public ResponseFormat(ResponseType rt, Object data,Object option){
        this.code = rt.getCode();
        this.desc = option instanceof Long || option instanceof String
                    ? String.format(rt.getDesc(), option)
                    : rt.getDesc();
        this.data = data;
    }

    public ResponseFormat(ResponseType rt, Object data){
        this(rt, data, null);
    }
}
