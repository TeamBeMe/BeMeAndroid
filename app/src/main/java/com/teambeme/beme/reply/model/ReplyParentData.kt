package com.teambeme.beme.reply.model

data class ReplyParentData(
    val txt_id: String,
    val txt_comment: String,
    val txt_time: String,
    val data_child: MutableList<ReplyData> = mutableListOf<ReplyData>(
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
            data_child = initReplyList()
        ),
        ReplyParentData(
            txt_id = "asdf",
            txt_comment = "asdfsdafwsdfkjewfulsdglnkvdflnkbvdfiuglewrjflksdf",
            txt_time = "12월24일",
            data_child = initReplyList()
        ),
        ReplyParentData(
            txt_id = "asdf",
            txt_comment = "asdfsdafwsdfkjewfulsdglnkvdflnkbvdfiuglewrjflksdf",
            txt_time = "12월24일",
            data_child = initReplyList()
        )

    )
    return datas
}