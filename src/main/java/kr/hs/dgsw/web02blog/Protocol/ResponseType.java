package kr.hs.dgsw.web02blog.Protocol;

public enum ResponseType {
    OK                  (0, "명령을 실행했습니다"),
    FAIL                (1, "명령을 실행하지 못했습니다"),

    USER_DELETE         (101, "사용자를 삭제했습니다"),
    USER_ADD            (102, "사용자를 추가했습니다"),
    USER_UPDATE         (103, "ID [%d]의 사용자를 변경했습니다"),
    USER_GET            (104, "[%s] 사용자를 불러왔습니다"),
    USER_LIST           (105, "사용자 리슽를 불러왔습니다"),

    POST_GET            (201, "포스트를 불러왔습니다"),
    POST_ADD            (202, "포스트를 추가했습니다"),
    POST_UPDATE         (203, "포스트를 변경했습니다"),
    POST_DELETE         (204, "포스트를 삭제했습니다"),
    POST_LIST           (205, "포스트 리스트를 불러왔습니다"),

    ATTACHMENT_STORED   (301, "첨부파일을 저장했습니다")
    ;

    final private int code;
    final private String desc;
    ResponseType(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
    public int getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
}
