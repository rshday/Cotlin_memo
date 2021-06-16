package com.example.memoap

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RecycleAdepter(private val context : Context) : RecyclerView.Adapter<RecycleAdepter.ViewHolder>() {

    var datas = ArrayList<NoteData>()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val title_view : TextView = itemView.findViewById(R.id.title_text)
        private val text_view : TextView = itemView.findViewById(R.id.text_text)

        fun bind(item : NoteData, clickListener: View.OnClickListener)
        {
            title_view.text = item.title
            text_view.text = item.text
            title_view.setOnClickListener(clickListener)
            text_view.setOnClickListener(clickListener)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.note,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var clickListener : View.OnClickListener = View.OnClickListener {
            val intent = Intent(holder.itemView?.context,activity_view::class.java)
            intent.putExtra("title",datas[position].title)
            intent.putExtra("text",datas[position].text)
            intent.putExtra("index",position)
            ContextCompat.startActivity(holder.itemView?.context,intent,null)
        }
        holder.bind(datas[position],clickListener)
        holder.itemView.setOnClickListener(clickListener)

    }

    override fun getItemCount(): Int = datas.size
}