package com.example.zerowaste.pindai

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.media.ThumbnailUtils
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.zerowaste.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder

@Composable
fun pindai(navController: NavController) {
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var resultText by remember { mutableStateOf("") }
    val context = LocalContext.current
    val imageSize = 32

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val image = result.data?.extras?.get("data") as? Bitmap
            image?.let {
                try {
                    val dimension = minOf(it.width, it.height)
                    val thumbnail = ThumbnailUtils.extractThumbnail(it, dimension, dimension)
                    val scaledImage = Bitmap.createScaledBitmap(thumbnail, imageSize, imageSize, false)
                    imageBitmap = it
                    resultText = classifyImage(scaledImage, context)
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error processing camera image", e)
                    resultText = "Error"
                }
            }
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data
            uri?.let {
                try {
                    val image = if (Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, it)
                        ImageDecoder.decodeBitmap(source)
                    }
                    val scaledImage = Bitmap.createScaledBitmap(image, imageSize, imageSize, false)
                    imageBitmap = image
                    resultText = classifyImage(scaledImage, context)
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error processing gallery image", e)
                    resultText = "Error"
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            imageBitmap?.let {
                Image(bitmap = it.asImageBitmap(), contentDescription = null, modifier = Modifier.size(220.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Klasifikasi : $resultText", style = TextStyle(fontSize = 20.sp))
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        cameraLauncher.launch(cameraIntent)
                    } else {
                        ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.CAMERA), 100)
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF388E3C)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Buka Kamera", color = Color.White)
            }
            Spacer(modifier = Modifier.height(13.dp))
            Button(
                onClick = {
                    val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    galleryLauncher.launch(galleryIntent)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF388E3C)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Buka Galeri", color = Color.White)
            }
        }
    }
}

fun classifyImage(image: Bitmap, context: Context): String {
    return try {
        Log.d("classifyImage", "Memuat model")
        val model = Model.newInstance(context)

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 32, 32, 3), DataType.FLOAT32)
        val byteBuffer = ByteBuffer.allocateDirect(4 * 32 * 32 * 3).apply {
            order(ByteOrder.nativeOrder())
        }

        Log.d("classifyImage", "Mengonversi gambar ke byte buffer")
        val intValues = IntArray(32 * 32)
        image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)
        var pixel = 0
        for (i in 0 until 32) {
            for (j in 0 until 32) {
                val value = intValues[pixel++]
                byteBuffer.putFloat(((value shr 16) and 0xFF) * (1f / 255))
                byteBuffer.putFloat(((value shr 8) and 0xFF) * (1f / 255))
                byteBuffer.putFloat((value and 0xFF) * (1f / 255))
            }
        }

        inputFeature0.loadBuffer(byteBuffer)

        Log.d("classifyImage", "Model memproses input")
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
        val confidences = outputFeature0.floatArray

        val classes = arrayOf("Plastik PETE", "Plastik HDPE", "Plastik PVC", "Plastik LDPE", "Plastik PP", "Plastik PS", "Other")
        val maxIndex = confidences.indices.maxByOrNull { confidences[it] } ?: -1

        Log.d("classifyImage", "Model selesai mengklasifikasi")
        model.close()
        classes.getOrElse(maxIndex) { "Unknown" }
    } catch (e: IOException) {
        Log.e("classifyImage", "Kesalahan saat memuat model", e)
        "Error"
    } catch (e: Exception) {
        Log.e("classifyImage", "Kesalahan tidak terduga", e)
        "Error"
    }
}
