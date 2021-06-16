package com.example.memoap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.note.*

class MainActivity : AppCompatActivity() {

    val editer = NoteEditer()
    lateinit var onClickListener : View.OnClickListener
    lateinit var noteAdepter : RecycleAdepter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Bar_init("My Memos")
        NoteSet()
    }

    private fun Bar_init(title: String) {
        supportActionBar?.title = title
    }

    private fun NoteSet()
    {
        val path : String = editer.getPath(this)
        var dataArr = editer.ArrRead(path)
        noteAdepter = RecycleAdepter(this)
        noteAdepter.datas = dataArr
        noteAdepter.notifyDataSetChanged()
        note_view.adapter = noteAdepter
        note_view.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.mm_add->{
                val intent = Intent(this,NoteActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {

    }
}