package com.sama.communicationclassjava.Lisetner;

import com.sama.communicationclassjava.Data.CommentData;

public interface OnCommentInsertListener {
    void OnInsertSuccess(CommentData item);
    void OnInsertFailure();
}
