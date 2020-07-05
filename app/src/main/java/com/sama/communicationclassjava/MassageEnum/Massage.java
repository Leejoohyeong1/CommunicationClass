package com.sama.communicationclassjava.MassageEnum;

public enum Massage {

    OnDeleteFailure("댓글 삭제에 실패했습니다",1),
    OnInsertFailure("댓글 작성에 실패했습니다",2);
    String massage;
    int flg;

    Massage(String massage, int flg) {
        this.massage = massage;
        this.flg = flg;
    }
}
