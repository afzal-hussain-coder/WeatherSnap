package com.weathersnap.persantation.report

import android.net.Uri
import android.text.format.DateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.weathersnap.ui.theme.*
import com.weathersnap.data.local.entity.ReportEntity
import com.weathersnap.presentation.components.CreateReportScreenAppHeader
import com.weathersnap.presentation.components.SavedReportCard
import com.weathersnap.presentation.components.SavedReportScreenAppHeader

@Composable
fun SavedReportsScreen(navController: NavController, viewModel: ReportViewModel = hiltViewModel()) {

    val reports by viewModel.reports.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .background(

                brush = Brush.verticalGradient(

                    colors = listOf(

                        weatherScreenBackgroundStart,

                        weatherScreenBackgroundEnd
                    )
                )
            )
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            // HEADER
            SavedReportScreenAppHeader(reports, onReportsClick = {


                navController.popBackStack()
            })

            Spacer(modifier = Modifier.height(16.dp))

            if (emptyList<ReportEntity>().isEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = CardColor)
                ) {
                    Column(modifier = Modifier.padding(10.dp).fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            TempBoxColor,
                                            humadityBoxColor
                                        )
                                    )
                                )
                                .padding(0.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No reports yet",
                                color = LightText,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(

                            text =
                                "Create and save a weather report to see image, notes, and weather details here",
                            modifier = Modifier.fillMaxWidth(),
                            color = WhiteText,
                            fontWeight = FontWeight.Normal,
                            fontSize = 8.sp,
                            lineHeight = 14.sp
                        )
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(reports) { report ->
                        SavedReportCard(report)
                    }
                }
            }
        }
    }
}


