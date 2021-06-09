package com.example.memoap

import android.content.Context
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException


class NoteEditer {

    fun EditNote()
    {

    }

    fun MakeJsonFile(noteData: NoteData, context: Context)
    {
        val path : String = context.getExternalFilesDir("")!!.absolutePath
        val fname : String = "/test.txt"
        Log.d("test",path)
        try {
            val writer : FileWriter  = FileWriter(path+fname)
            val buffer : BufferedWriter = BufferedWriter(writer)

            buffer.write(noteData.text)
            Log.d("test","Create file")

            buffer.close()
            writer.close()
            Log.d("test","Close file")
        }
        catch (e : IOException)
        {
            Log.d("test",e.toString())
        }
    }
}