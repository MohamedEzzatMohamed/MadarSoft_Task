package com.tru.core.data_store_manager.proto_data_store.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.GeneratedMessageLite
import com.google.protobuf.InvalidProtocolBufferException
import com.google.protobuf.Parser
import java.io.InputStream
import java.io.OutputStream

class ProtoSerializer<T : GeneratedMessageLite<T, *>>(
    defaultInstance: T, private val parser: Parser<T>
) : Serializer<T> {

    override val defaultValue: T = defaultInstance

    override suspend fun readFrom(input: InputStream): T {
        try {
            return parser.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: T, output: OutputStream) {
        t.writeTo(output)
    }
}