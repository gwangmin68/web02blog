package kr.hs.dgsw.web02blog.Protocol;

public enum ResponseType {
    OK                  (0, "명령을 실행했습니다"),
    FAIL                (1, "명령을 실행하지 못했습니다"),

    USER_LIST           (101, "사용자 리스트를 불러왔습니다"),
    USER_ADD            (102, "USER_ID [%d] 사용자를 추가했습니다"),
    USER_GET            (103, "[%s] 사용자를 불러왔습니다"),
    USER_UPDATE         (104, "USER_ID [%d]의 사용자를 변경했습니다"),
    USER_DELETE         (105, "사용자를 삭제했습니다"),

    POST_LIST           (201, "포스트 리스트를 불러왔습니다"),
    POST_ADD            (202, "POST_ID [%d] 포스트를 추가했습니다"),
    POST_GET            (203, "[%s] 포스트를 불러왔습니다"),
    POST_UPDATE         (204, "POST_ID [%d] 포스트를 변경했습니다"),
    POST_DELETE         (205, "포스트를 삭제했습니다"),

    USER_LIST_ERR      (301, "사용자 리스트를 불러오지 못했습니다"),
    USER_ADD_ERR       (302, "사용자를 추가하지 못했습니다"),
    USER_GET_ERR       (303, "사용자를 불러오지 못했습니다"),
    USER_UPDATE_ERR    (304, "사용자를 변경하지 못했습니다"),
    USER_DELETE_ERR    (305, "사용자를 삭제하지 못했습니다"),

    POST_LIST_ERR      (401, "포스트 리스트를 불러오지 못했습니다"),
    POST_ADD_ERR       (402, "포스트를 추가하지 못했습니다"),
    POST_GET_ERR       (403, "포스트를 불러오지 못했습니다"),
    POST_UPDATE_ERR    (404, "포스트를 변경하지 못했습니다"),
    POST_DELETE_ERR    (405, "포스트를 삭제하지 못했습니다"),

    ATTACHMENT_STORED   (501, "첨부파일을 저장했습니다")
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
