package com.tru.core.data_store_manager.proto_data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import com.google.protobuf.GeneratedMessageLite
import com.google.protobuf.Parser
import com.tru.core.data_store_manager.proto_data_store.serializer.ProtoSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object ProtoDataStoreFactory {

    inline fun <reified T : GeneratedMessageLite<T, *>> create(
        context: Context, fileName: String, defaultInstance: T
    ): DataStore<T> {
        return DataStoreFactory.create(
            serializer = ProtoSerializer(
                defaultInstance = defaultInstance,
                parser = defaultInstance.getParserForType() as Parser<T>
            ),
            produceFile = { context.dataStoreFile(fileName) },
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { defaultInstance }
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )
    }
}