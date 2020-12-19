package com.sama.communicationclass.Lisetner;

import com.sama.communicationclass.Data.CommentData;

public interface OnCommentInsertListener {
    void OnInsertSuccess(CommentData item);
    void OnInsertFailure();
}
