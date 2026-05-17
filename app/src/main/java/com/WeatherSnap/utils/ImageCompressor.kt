package com.weathersnap.utils
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

fun compressImage(
    context: Context,
    imageUri: Uri
): File {
    val inputStream =
        context.contentResolver
            .openInputStream(imageUri)
    val bitmap =
        BitmapFactory.decodeStream(inputStream)
    val compressedFile = File(
        context.cacheDir,
        "compressed_${System.currentTimeMillis()}.jpg"
    )

    val outputStream = FileOutputStream(compressedFile)

    bitmap.compress(
        Bitmap.CompressFormat.JPEG,
        40, // compression quality
        outputStream
    )
    outputStream.flush()
    outputStream.close()
    return compressedFile
}