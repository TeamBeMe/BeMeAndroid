package com.teambeme.beme.reply.model

data class ReplyData(
    val txt_id: String,
    val txt_comment: String,
    val txt_time: String
)

fun initReplyList(): MutableList<ReplyData> {
    val datas = mutableListOf<ReplyData>(
        ReplyData(
            txt_id = "asdf",
            txt_comment = "asdfsdafwsdfkjewfulsdglnkvdflnkbvdfiuglewrjflksdf",
            txt_time = "12월24일"
        ),
        ReplyData(
            txt_id = "asdf",
            txt_comment = "asdfsdafwsdfkjewfulsdglnkvdflnkbvdfiuglewrjflksdf",
            txt_time = "12월24일"
        ),
        ReplyData(
            txt_id = "asdf",
            txt_comment = "asdfsdafwsdfkjewfulsdglnkvdflnkbvdfiuglewrjflksdf",
            txt_time = "12월24일"
        )

    )
    return datas
}