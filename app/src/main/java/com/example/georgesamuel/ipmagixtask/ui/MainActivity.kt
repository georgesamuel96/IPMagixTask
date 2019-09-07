package com.example.georgesamuel.ipmagixtask.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.georgesamuel.ipmagixtask.R
import com.example.georgesamuel.ipmagixtask.model.Data
import com.example.georgesamuel.ipmagixtask.model.StudentResponse
import com.example.georgesamuel.ipmagixtask.viewModel.StudentsViewModel
import com.example.georgesamuel.ipmagixtask.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    private val TAG = "MainActivity"
    override val kodein by closestKodein()
    private val viewModelFactory: ViewModelFactory by instance()
    private lateinit var viewModel : StudentsViewModel
    private var studentsList: List<Data> = listOf()
    private val adapter: StudentsAdapter = StudentsAdapter(this@MainActivity)
    private var listUp : Boolean = true
    private lateinit var menuItem: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRV();
        initViewModel()
    }

    fun initRV(){
        rvStudents.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvStudents.adapter = adapter
    }

    fun initViewModel(){
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(StudentsViewModel::class.java)
        viewModel.getStudents()?.observe(this, Observer<StudentResponse> {
                response ->
            run {
                studentsList = response.data.sortedWith(compareBy({it.studentName}))
                animation.visibility = GONE
                adapter.setItems(studentsList)
                runAnimation()
            }
        })
        viewModel.getError()?.observe(this, Observer<String> {
            message ->
            run {
                animation.visibility = GONE
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle(getString(R.string.error_message))
                alert.setMessage(message)
                alert.setPositiveButton(getString(R.string.ok)){dialog, which ->
                }
                val dialog : AlertDialog = alert.create()
                dialog.show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menuItem = menu!!
        menu!!.findItem(R.id.up).setVisible(false)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.up -> {
                reverseList()
                menuItem.findItem(R.id.up).setVisible(false)
                menuItem.findItem(R.id.down).setVisible(true)
            }
            R.id.down -> {
                reverseList()
                menuItem.findItem(R.id.down).setVisible(false)
                menuItem.findItem(R.id.up).setVisible(true)
            }
        }
        return true
    }

    fun reverseList(){
        studentsList = studentsList.reversed()
        adapter.setItems(studentsList)
        runAnimation()
    }

    fun runAnimation(){
        val controller: LayoutAnimationController =
            AnimationUtils.loadLayoutAnimation(this@MainActivity, R.anim.layout_animation_fall_down)
        rvStudents.setLayoutAnimation(controller)
        rvStudents.getAdapter()!!.notifyDataSetChanged()
        rvStudents.scheduleLayoutAnimation()
    }
}
