package com.weathersnap.presentation.components

import android.text.format.DateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.weathersnap.data.local.entity.ReportEntity
import com.weathersnap.ui.theme.*
import com.weathersnap.utils.getWeatherCondition
import com.weathersnap.utils.toReadableSize

@Composable
fun AppHeader(

    onReportsClick: () -> Unit

) {

    Spacer(modifier = Modifier.height(24.dp))
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(

                brush = Brush.horizontalGradient(

                    colors = listOf(

                        HeaderStart,

                        HeaderEnd
                    )
                )
            )
            .padding(8.dp)
    ) {

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)) {

                Text(

                    text = "WeatherSnap",

                    color = DarkText,

                    fontSize = 12.sp,

                    fontWeight = FontWeight.Bold,

                    )

                Text(

                    text =
                        "Live weather reports with camera evidence",

                    color = DarkText.copy(alpha = 0.7f),

                    fontSize = 8.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 4.sp

                )
            }


            Button(

                onClick = onReportsClick,

                shape =
                    RoundedCornerShape(10.dp),

                colors =
                    ButtonDefaults.buttonColors(

                        containerColor =
                            Color(0xFF313313)
                    ),

                contentPadding =
                    PaddingValues(
                        horizontal = 12.dp,
                        vertical = 2.dp
                    )

            ) {

                Text(

                    text = "Reports",

                    color = HeaderStart,

                    fontSize = 8.sp
                )
            }
        }
    }
}

@Composable
fun CreateReportScreenAppHeader(

    onReportsClick: () -> Unit

) {

    Spacer(modifier = Modifier.height(24.dp))
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(

                brush = Brush.horizontalGradient(

                    colors = listOf(

                        HeaderEnd,

                        HeaderStart
                    )
                )
            )
            .padding(8.dp)
    ) {

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)) {

                Text(

                    text = "Create Report",

                    color = DarkText,

                    fontSize = 12.sp,

                    fontWeight = FontWeight.Bold,

                    )

                Text(

                    text =
                        "Capture, compress, annotate",

                    color = DarkText.copy(alpha = 0.7f),

                    fontSize = 8.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 4.sp

                )
            }


            Button(

                onClick = onReportsClick,

                shape =
                    RoundedCornerShape(30.dp),

                colors =
                    ButtonDefaults.buttonColors(

                        containerColor =
                            Color(0xFF313313)
                    ),

                contentPadding =
                    PaddingValues(
                        horizontal = 16.dp,
                        vertical = 2.dp
                    )

            ) {

                Text(

                    text = "Back",

                    color = HeaderStart,

                    fontSize = 8.sp
                )
            }
        }
    }
}

@Composable
fun SavedReportScreenAppHeader(
    reports: List<ReportEntity>,

    onReportsClick: () -> Unit

) {

    Spacer(modifier = Modifier.height(24.dp))
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        HeaderEnd,
                        HeaderStart
                    )
                )
            )
            .padding(8.dp)
    ) {

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)) {

                Text(

                    text = "Saved Report",

                    color = DarkText,

                    fontSize = 12.sp,

                    fontWeight = FontWeight.Bold,

                    )

                Text(

                    text =
                        reports.size.toString() + " report stored locally",

                    color = DarkText.copy(alpha = 0.7f),

                    fontSize = 8.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 4.sp

                )
            }


            Button(

                onClick = onReportsClick,

                shape =
                    RoundedCornerShape(30.dp),

                colors =
                    ButtonDefaults.buttonColors(

                        containerColor =
                            Color(0xFF313313)
                    ),

                contentPadding =
                    PaddingValues(
                        horizontal = 16.dp,
                        vertical = 2.dp
                    )

            ) {

                Text(

                    text = "Back",

                    color = HeaderStart,

                    fontSize = 8.sp
                )
            }
        }
    }
}


@Composable
fun WeatherSearchField(
    value: String,
    onValueChange: (String) -> Unit

) {

    OutlinedTextField(

        value = value,

        onValueChange = onValueChange,

        modifier = Modifier
            .fillMaxWidth(),

        label = {

            Text(

                text = "City",

                color = LightText,

                fontSize = 12.sp
            )
        },

        singleLine = true,

        shape = RoundedCornerShape(6.dp),

        textStyle = LocalTextStyle.current.copy(

            color = WhiteText,

            fontSize = 12.sp
        ),

        colors = OutlinedTextFieldDefaults.colors(

            focusedBorderColor = ButtonColor,

            unfocusedBorderColor = BorderColor,

            focusedContainerColor = Color.Transparent,

            unfocusedContainerColor = Color.Transparent,

            focusedTextColor = WhiteText,

            unfocusedTextColor = WhiteText,

            focusedLabelColor = ButtonColor,

            unfocusedLabelColor = LightText,

            cursorColor = ButtonColor
        )
    )
}


@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit

) {
    Button(

        onClick = onClick,

        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),

        shape = RoundedCornerShape(30.dp),

        colors = ButtonDefaults.buttonColors(

            containerColor = ButtonColor
        )
    ) {

        Text(

            text = text,
            color = DarkText,

            fontWeight = FontWeight.Normal,
            fontSize = 10.sp
        )
    }
}

@Composable
fun CustomeCamersCloseButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(30.dp),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            width = 1.5.dp,
            brush = SolidColor(Color.DarkGray)
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = WhiteText,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp
        )
    }
}


@Composable
fun WeatherInfoCard(
    title: String,
    value: String,
    backgroundColor: Color,
    textColors: Color
) {
    Card(
        modifier = Modifier.width(95.dp),

        shape = RoundedCornerShape(10.dp),

        colors = CardDefaults.cardColors(

            containerColor = backgroundColor
        )
    ) {
        Column(

            modifier = Modifier.padding(10.dp)
        ) {
            Text(

                text = title,

                color = LightText,

                fontSize = 10.sp,
                lineHeight = 8.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = value,
                color = textColors,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                lineHeight = 8.sp
            )
        }
    }
}

@Composable
fun SaveReportInfoCard(
    title: String,
    value: String,
    backgroundColor: Color,
    textColors: Color
) {
    Card(
        modifier = Modifier.width(150.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(

                text = title,

                color = LightText,

                fontSize = 10.sp,
                lineHeight = 8.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = value,
                color = textColors,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                lineHeight = 8.sp
            )
        }
    }
}


@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(

            color = ButtonColor
        )
    }
}

@Composable
fun SavedReportCard(report: ReportEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                SmallCardColor,
                                TempBoxColor
                            )
                        )
                    )
                    .padding(0.dp),
                contentAlignment = Alignment.Center
            ) {
                if (report.imagePath != null) {
                    Image(
                        painter = rememberAsyncImagePainter(report.imagePath),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = "Image Not Found",
                        color = LightText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal

                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = report.cityName ?: "Unknown City",
                            color = WhiteText,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            lineHeight = 12.sp
                        )
                        Text(
                            text = "${report?.condition ?: 0}",
                            color = LightText,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 12.sp
                        )
                        Text(
                            text = DateFormat.format("dd MMM yyyy, hh:mm a", report.timestamp)
                                .toString(),
                            color = LightText,
                            fontSize = 8.sp,
                            lineHeight = 5.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(
                                TempBoxColor
                            )
                            .padding(
                                horizontal = 10.dp, vertical = 6.dp
                            )
                    ) {
                        Text(
                            text = "${report.temperature}°C",
                            color = ButtonColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SaveReportInfoCard(
                        title = "Original",
                        value = "${report?.originalImageSize?.toReadableSize() ?: 0}",
                        backgroundColor = pressureBoxColor,
                        textColors = pressureTextColor
                    )
                    SaveReportInfoCard(
                        title = "Compressed",
                        value = "${report?.compressedImageSize?.toReadableSize() ?: 0}",
                        backgroundColor = humadityBoxColor,
                        textColors = humidityTextColor
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = SmallCardColor)
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = report.notes,
                        style = TextStyle(
                            color = WhiteText,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Light
                        )
                    )
                }

            }
        }
    }

}

@Composable
fun WeatherItem(
    title: String,
    value: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, color = LightText, fontSize = 13.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, color = WhiteText, fontWeight = FontWeight.Bold)
    }
}