package com.techmania.todolist

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.textfield.TextInputEditText
import com.techmania.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {
    lateinit var item:TextInputEditText
    lateinit var button: Button
    lateinit var listview:ListView
    var itemlist=ArrayList<String>()    //this is where the items will be present

    var filehelper=Itemstorage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        item=findViewById(R.id.editText)
        button=findViewById(R.id.button)
        listview=findViewById(R.id.list)











//        on opening the app we need to check if already some data is saved
       itemlist=filehelper.read(this)  //it will read data and send it to the array created

//        creating adapter so that data from array can go to list view
        var arrayadapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,itemlist)
        listview.adapter=arrayadapter


        button.setOnClickListener(){
            var itemName:String=item.text.toString()     //input is taken and given to variable item name which converts it to string
            itemlist.add(itemName)    //input added to array
            filehelper.write(itemlist,applicationContext)  //write the data given by user in the file created
            item.setText("")                  //clear edit text
            arrayadapter.notifyDataSetChanged()  //it notifies the list view that data has been changed


        }

        listview.setOnItemClickListener { adapterView, view, position, id ->
            var alert=AlertDialog.Builder(this)        //object alert is created of alert dialog builder class which create alert dialog boxes
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete this task?")
            alert.setCancelable(false)    //if set to false then the dialog box will not disappear by clicking on screen or back button
            alert.setNegativeButton("NO",DialogInterface.OnClickListener{ dialogInterface,  i ->
                dialogInterface.cancel()
            })
            alert.setPositiveButton("YES",DialogInterface.OnClickListener { dialogInterface, i ->
                itemlist.removeAt(position)
                arrayadapter.notifyDataSetChanged()
                filehelper.write(itemlist,applicationContext)
            })
            alert.create().show()         //creates the dialog box and shows it on the screen

        }

    }
}

