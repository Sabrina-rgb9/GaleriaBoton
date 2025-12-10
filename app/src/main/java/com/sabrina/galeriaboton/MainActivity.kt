package com.sabrina.galeriaboton
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var btnGaleria: Button
    private lateinit var btnFoto: Button

    // 1) Obrir la galeria → retorna una URI
    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageView.setImageURI(it)
        }
    }

    // 2) Fer foto thumbnail → retorna un BITMAP
    private val takePicturePreviewLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        bitmap?.let {
            imageView.setImageBitmap(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        btnGaleria = findViewById(R.id.button)
        btnFoto = findViewById(R.id.thumbButton)

        // Obrir galeria
        btnGaleria.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        // Fer una foto
        btnFoto.setOnClickListener {
            takePicturePreviewLauncher.launch(null)
        }
    }
}
