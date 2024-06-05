package com.techmania.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class Itemstorage {
//    the items or the tasks will be stored here so that when app is closed they are not deleted
//    device's memory will be used to store them
    val FileName="listItem.data"

    fun write(item:ArrayList<String>,context: Context){  //this function will write the data into the file
        var fos:FileOutputStream=context.openFileOutput(FileName,Context.MODE_PRIVATE)
//        fos is object of class file output stream
    //this method will create a new file in device memory and open it
    //private mode means only this application can access the file not other applications
        var oas=ObjectOutputStream(fos)
        oas.writeObject(item)
//        writeObject(item): This method is called on the ObjectOutputStream to write the specified object (item) to the output stream.
    //        The item object must be serializable, meaning its class must implement the Serializable interface.
    //        This method serializes the object and writes it to the file.
//        serializable means that data can be converted to sequence of bytes to be stored in file or transferred
     oas.close()     //once data is written into the file close it


    }

    fun read(context: Context):ArrayList<String>     //: k baad return type specify kri hai
    {
        var itemlist:ArrayList<String>    //an array is created to read the data
        try {

            var fis:FileInputStream=context.openFileInput(FileName)
            var ois=ObjectInputStream(fis)
            itemlist= ois.readObject() as ArrayList<String>
        }catch (e:FileNotFoundException){
            itemlist=ArrayList()
        }

    //as arrayList<string> isliye likha because data ek object jaise read hoga to use array item mei convert krna pdega
    return itemlist
    }

}