package com.weathersnap.persantation.camera

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.Alignment
import com.weathersnap.presentation.components.CustomeCamersCloseButton
import com.weathersnap.presentation.components.PrimaryButton
import com.weathersnap.ui.theme.BackgroundColor

@Composable
fun CustomCameraScreen(
    navController: NavController
) {

    BackHandler {
        navController.popBackStack()
    }
    val context = LocalContext.current

    val lifecycleOwner =
        LocalLifecycleOwner.current

    var imageCapture:
            ImageCapture? by remember {

        mutableStateOf(null)
    }



    Box(modifier = Modifier
            .fillMaxSize()
            .background(
                BackgroundColor)) {

        // FULL SCREEN CAMERA

        AndroidView(

            factory = {

                val previewView =
                    PreviewView(it)

                previewView.scaleType =
                    PreviewView.ScaleType.FILL_CENTER

                val cameraProviderFuture =
                    ProcessCameraProvider.getInstance(it)

                cameraProviderFuture.addListener({

                    val cameraProvider =
                        cameraProviderFuture.get()

                    val preview =
                        Preview.Builder()
                            .build()

                    preview.setSurfaceProvider(
                        previewView.surfaceProvider
                    )

                    imageCapture =
                        ImageCapture.Builder()
                            .build()

                    val cameraSelector =
                        CameraSelector.DEFAULT_BACK_CAMERA

                    try {

                        cameraProvider.unbindAll()

                        cameraProvider.bindToLifecycle(

                            lifecycleOwner,

                            cameraSelector,

                            preview,

                            imageCapture
                        )

                    } catch (e: Exception) {

                        e.printStackTrace()
                    }

                }, ContextCompat.getMainExecutor(it))

                previewView
            },

            modifier = Modifier.fillMaxSize()
        )

        // TOP CONTENT

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 10.dp,
                        vertical = 10.dp
                    ),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text = "Custom Camera",

                    color = Color.White,

                    fontSize = 14.sp,

                    style =
                        MaterialTheme.typography.headlineSmall
                )
                CustomeCamersCloseButton(

                    text = "Close"
                ) {
                    navController.popBackStack()
                }
            }
        }

        // BOTTOM BUTTON

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {
            Column() {
                PrimaryButton(
                    text = "Capture",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        capturePhoto(
                            context = context,
                            imageCapture =
                                imageCapture,
                            onImageCaptured = {
                                navController
                                    .previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.set(

                                        "captured_image",

                                        it.toString()
                                    )

                                navController.popBackStack()
                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.height(40.dp))
            }

        }


    }

}



private fun capturePhoto(

    context: Context,

    imageCapture: ImageCapture?,

    onImageCaptured:
        (Uri) -> Unit
) {

    val photoFile = File(

        context.cacheDir,

        SimpleDateFormat(

            "yyyyMMdd_HHmmss",

            Locale.US

        ).format(System.currentTimeMillis())
                + ".jpg"
    )


    val outputOptions =

        ImageCapture.OutputFileOptions
            .Builder(photoFile)
            .build()


    imageCapture?.takePicture(

        outputOptions,

        ContextCompat.getMainExecutor(
            context
        ),

        object :
            ImageCapture.OnImageSavedCallback {

            override fun onImageSaved(

                outputFileResults:
                ImageCapture.OutputFileResults
            ) {

                onImageCaptured(
                    Uri.fromFile(photoFile)
                )

                Toast.makeText(

                    context,

                    "Image Captured",

                    Toast.LENGTH_SHORT

                ).show()
            }

            override fun onError(

                exception:
                ImageCaptureException
            ) {

                exception.printStackTrace()
            }
        }
    )
}