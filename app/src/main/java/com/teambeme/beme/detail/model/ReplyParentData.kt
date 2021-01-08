package com.teambeme.beme.detail.model

data class ReplyParentData(
    val txt_id: String,
    var txt_comment: String,
    val txt_time: String,
    val dataChild: MutableList<ReplyData> = mutableListOf<ReplyData>(
        ReplyData(
            txt_id = "",
            txt_comment = "",
            txt_time = ""
        ))
)

fun initReplyParentList(): MutableList<ReplyParentData> {
    val datas = mutableListOf<ReplyParentData>(
        ReplyParentData(
            txt_id = "asdf",
            txt_comment = "asdfsdafwsdfkjewfulsdglnkvdflnkbvdfiuglewrjflksdf",
            txt_time = "12월24일",
            dataChild = initReplyList()
        ),
        ReplyParentData(
            txt_id = "asdf",
            txt_comment = "asdfsdafwsdfkjewfulsdglnkvdflnkbvdfiuglewrjflksdf",
            txt_time = "12월24일",
            dataChild = initReplyList()
        ),
        ReplyParentData(
            txt_id = "asdf",
            txt_comment = "asdfsdafwsdfkjewfulsdglnkvdflnkbvdfiuglewrjflksdf",
            txt_time = "12월24일",
            dataChild = initReplyList()
        )

    )
    return datas
}