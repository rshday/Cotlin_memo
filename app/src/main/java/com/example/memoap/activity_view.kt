package com.example.memoap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_view.*

class activity_view : AppCompatActivity() {
    var index : Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        title_text.text = intent.getStringExtra("title")
        text_text.text = intent.getStringExtra("text")
        index = intent.getIntExtra("index",-1)

        Bar_init(intent.getStringExtra("title").toString())

    }
    private fun Bar_init(title: String) {
        supportActionBar?.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.view_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.mm_del->{
                if(index > -1)
                {
                    val editer = NoteEditer()
                    editer.JsonDel(this,index)
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}