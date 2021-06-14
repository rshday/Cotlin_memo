package com.example.memoap

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.*


class NoteEditer {

    fun JsonAdd(context: Context, item : NoteData)
    {
        val path : String = context.getExternalFilesDir("")!!.absolutePath + "/notelist.json"
        var dataArr : ArrayList<NoteData> = ArrayList<NoteData>()
        val gson = GsonBuilder().create()

        if(JsonExist(path))
        {
            dataArr = ArrRead(path)
            dataArr.add(item)
            val Jdata = gson.toJson(dataArr,object :TypeToken<ArrayList<NoteData>>(){}.type)
            write(path,Jdata.toString())
            Log.d("test","JsonAdd dataArr - ${dataArr.toString()}")
        }
        else
        {
            JsonCreate(path)
            JsonAdd(context,item)
        }
    }

    private fun JsonExist(path : String) : Boolean
    {
        val file : File = File(path)
        var cnt :Int = 0

        if(file.exists())
        {
            val reader : FileReader  = FileReader(path)
            val buffer : BufferedReader = BufferedReader(reader)
            try {
                cnt = buffer.read()
                Log.d("test","exists cnt - $cnt")
            }
            catch (e : IOException)
            {
                Log.d("test",e.toString())
            }

            buffer.close()
            reader.close()

            if(cnt <= 0) {
                return false
            }
        }

        return true
    }

    private fun JsonCreate(path : String)
    {
        var dataArr : ArrayList<NoteData> = ArrayList<NoteData>()
        val gson = GsonBuilder().create()
        val Jdata = gson.toJson(dataArr,object :TypeToken<ArrayList<NoteData>>(){}.type)

        write(path,Jdata.toString())

    }

    private fun ArrRead(path : String) : ArrayList<NoteData>
    {
        var dataArr : ArrayList<NoteData> = ArrayList<NoteData>()
        val gson = GsonBuilder().create()
        val Rdata : String = read(path)

        if(!Rdata.isEmpty())
        {
            dataArr = gson.fromJson(Rdata, object :TypeToken<ArrayList<NoteData>>(){}.type)
        }

        return dataArr
    }

    private fun write(path: String , data :String)
    {
        val writer : FileWriter  = FileWriter(path)
        val buffer : BufferedWriter = BufferedWriter(writer)
        try {
            buffer.write(data)
            Log.d("test","write file - $data")
        }
        catch (e : IOException)
        {
            Log.d("test",e.toString())
        }
        buffer.close()
        writer.close()
        Log.d("test","Close file")
    }

    private fun read(path: String) :String
    {
        val reader : FileReader  = FileReader(path)
        val buffer : BufferedReader = BufferedReader(reader)
        var ret : String = ""

        try {
            ret = buffer.readText()
            Log.d("test","read file - $ret")
        }
        catch (e : IOException)
        {
            Log.d("test",e.toString())
        }

        buffer.close()
        reader.close()

        return ret
    }

}