package com.example.bpcare.ui.Screens.HomeScreen

import android.annotation.SuppressLint
import com.example.bpcare.data.Model.BloodPressure
import com.example.bpcare.ui.Screens.Authentication.Login.LoginScreenViewModel
import com.example.bpcare.data.repository.BPRepository
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bpcare.data.ViewModel.BloodPressureViewModel
import com.example.bpcare.ui.Navigation.ROUTES
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bpcare.ui.theme.BpCareTheme


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: BloodPressureViewModel = viewModel()
) {
    val latestReading by viewModel.latestReading.collectAsState()
    val loginViewModel: LoginScreenViewModel = viewModel()
    val message by loginViewModel.message.collectAsState()

    HomeScreenContent(
        latestReading = latestReading,
        message = message,
        onAddReadingClick = {
            navController.navigate(ROUTES.Add.name)
        },
        onViewHistoryClick = {
            navController.navigate(ROUTES.History.name)
        },
        onStatisticsClick = {
            navController.navigate(ROUTES.Statistics.name)
        },
        onLogoutClick = {
            loginViewModel.logout()
            navController.navigate(ROUTES.Login.name) {
                popUpTo(ROUTES.Home.name) { inclusive = true }
            }
        }
    )
}

@Composable
fun HomeScreenContent(
    latestReading: BloodPressure?,
    message: String,
    onAddReadingClick: () -> Unit,
    onViewHistoryClick: () -> Unit,
    onStatisticsClick: () -> Unit,
    onLogoutClick: () -> Unit,
    viewModel: HomeScreenViewModel = viewModel()


) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Hello,",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "BP Care",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                IconButton(onClick = onLogoutClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Logout",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            latestReading?.let { bp ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Text(
                            text = "Latest Reading",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "${bp.systolic}/${bp.diastolic}",
                                    style = MaterialTheme.typography.headlineLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 42.sp
                                )
                                Text(
                                    text = "mmHg",
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .background(
                                        color = getStatusColor(bp.status),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = bp.status,
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ShowChart,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Pulse: ${bp.pulse} bpm",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            } ?: run {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No readings yet. Add your first one!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Quick Actions",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionGrid(
                onAddClick = onAddReadingClick,
                onHistoryClick = onViewHistoryClick,
                onStatsClick = onStatisticsClick
            )

            Spacer(modifier = Modifier.weight(1f))

            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun ActionGrid(
    onAddClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onStatsClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ActionCard(
                title = "Add Reading",
                icon = Icons.Default.Add,
                onClick = onAddClick,
                modifier = Modifier.weight(1f),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
            ActionCard(
                title = "History",
                icon = Icons.Default.History,
                onClick = onHistoryClick,
                modifier = Modifier.weight(1f)
            )
        }
        ActionCard(
            title = "Detailed Statistics",
            icon = Icons.AutoMirrored.Filled.ShowChart,
            onClick = onStatsClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ActionCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun getStatusColor(status: String): Color {
    return when (status.lowercase()) {
        "normal" -> Color(0xFF4CAF50)
        "elevated" -> Color(0xFFFFC107)
        "hypertension stage 1" -> Color(0xFFFF9800)
        "hypertension stage 2" -> Color(0xFFF44336)
        else -> MaterialTheme.colorScheme.primary
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BpCareTheme {
        HomeScreenContent(
            latestReading = BloodPressure(
                systolic = 120,
                diastolic = 80,
                pulse = 70,
                status = "Normal"
            ),
            message = "Welcome to BP Care",
            onAddReadingClick = {},
            onViewHistoryClick = {},
            onStatisticsClick = {},
            onLogoutClick = {}
        )
    }
}