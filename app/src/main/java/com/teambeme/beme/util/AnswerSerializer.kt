package com.teambeme.beme.util

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

object AnswerSerializer : Serializer<AnswerInfo> {
    override val defaultValue: AnswerInfo
        get() = AnswerInfo.getDefaultInstance()

    override fun readFrom(input: InputStream): AnswerInfo {
        try {
            return AnswerInfo.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override fun writeTo(t: AnswerInfo, output: OutputStream) = t.writeTo(output)
}