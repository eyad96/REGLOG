package com.google.eyadhewware.reglog.Secreens

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.google.eyadhewware.reglog.R
import kotlinx.android.synthetic.main.fragment_register_secreen.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class RegisterSecreen : Fragment() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // in here you can do logic when backPress is clicked
                    activity?.onBackPressed()
                }

            })
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_secreen, container, false)
    }


    private val GALLERY = 1
    private val CAMERA = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AddImage?.apply {
            setOnClickListener {
                showPictureDialog()
            }
        }
        Text2?.apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_registerSecreen_to_loginSecreen)

            }
        }
        RegisterBTN?.apply {
            setOnClickListener {
                if (UserNameREG.text.toString().isEmpty()
                    && PasswordREG.text.toString().isEmpty()
                    && EmailREG.text.toString().isEmpty()
                ) {
                    Toast.makeText(activity, "pls fill all field", Toast.LENGTH_SHORT).show()
                }
                else if (UserNameREG.text.toString().isEmpty()
                    && PasswordREG.text.toString().isEmpty()
                ) {
                    Toast.makeText(activity, "enter UserNam and password", Toast.LENGTH_SHORT).show()
                }
                else if (UserNameREG.text.toString().isEmpty()
                    && EmailREG.text.toString().isEmpty()
                    && PasswordREG.text.toString().isNotEmpty()
                ) {
                    Toast.makeText(activity, "enter Email and UserName", Toast.LENGTH_SHORT).show()
                }
                else if (PasswordREG.text.toString().isEmpty()
                    && EmailREG.text.toString().isEmpty()
                ) {
                    Toast.makeText(activity, "enter Email and password", Toast.LENGTH_SHORT).show()
                }
                else if (EmailREG.text.toString().isEmpty()) {
                    Toast.makeText(activity, "enter Email", Toast.LENGTH_SHORT).show()
                } else if (UserNameREG.text.toString().isEmpty()) {
                    Toast.makeText(activity, "enter username", Toast.LENGTH_SHORT).show()
                }  else {


                }
            }

        }

    }
    private fun showPictureDialog() {
        val pictureDialog = android.app.AlertDialog.Builder(requireContext())

        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select image from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> chooseImageFromGallery()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }
    fun chooseImageFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA)
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data!!.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, contentURI)
                    saveImage(bitmap)
                    Toast.makeText(activity, "Image Show!", Toast.LENGTH_SHORT).show()
                    AddImage!!.setImageBitmap(bitmap)
                }
                catch (e: IOException)
                {
                    e.printStackTrace()
                    Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else if (requestCode == CAMERA)
        {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            AddImage!!.setImageBitmap(thumbnail)
            saveImage(thumbnail)
            Toast.makeText(activity, "Photo Show!", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.PNG, 98, bytes)
        val wallpaperDirectory = File (
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        Log.d("fee", wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {
            wallpaperDirectory.mkdirs()
        }
        try
        {
            Log.d("heel", wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                .getTimeInMillis()).toString() + ".png"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(requireContext(), arrayOf(f.getPath()), arrayOf("image/png"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        }
        catch (e1: IOException){
            e1.printStackTrace()
        }
        return ""
    }

    companion object {
        private val IMAGE_DIRECTORY = "/nalhdaf"
    }

}