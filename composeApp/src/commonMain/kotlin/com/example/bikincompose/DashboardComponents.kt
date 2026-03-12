package com.example.bikincompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bikincompose.ui.theme.*

enum class Screen {
    DASHBOARD,
    SENTIMEN,
    SMART,
    KRITERIA
}

@Composable
fun DashboardScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = BackgroundWhite) {
        LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item { DashboardTopBar() }
            item { HeroCard() }
            item { SummaryGrid() }
            item { QuickActionsSection() }
            item { ActivityLogSection() }
            item { SupportCard() }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopBar() {
    Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                    text = "Selamat Datang,",
                    style = MaterialTheme.typography.bodyLarge.copy(color = TextSecondary)
            )
            Text(text = "Admin BookSmart", style = MaterialTheme.typography.titleLarge)
        }
        Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {
            BadgedBox(badge = { Badge { Text("3") } }) {
                Icon(
                        Icons.Outlined.Notifications,
                        contentDescription = "Notifications",
                        tint = TextPrimary
                )
            }
            Box(
                    modifier = Modifier.size(40.dp).clip(CircleShape).background(GreenPrimary),
                    contentAlignment = Alignment.Center
            ) { Text("A", color = Color.White, fontWeight = FontWeight.Bold) }
        }
    }
}

@Composable
fun HeroCard() {
    Card(
            colors = CardDefaults.cardColors(containerColor = GreenPrimary),
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(32.dp)) {
            Surface(
                    color = Color.White.copy(alpha = 0.2f),
                    shape = CircleShape,
                    modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                        text = "SYSTEM HEALTH: STABLE",
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
            Text(
                    text = "Ringkasan Keputusan Admin.",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 24.dp)
            )
            Button(
                    onClick = {},
                    colors =
                            ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = GreenPrimary
                            ),
                    shape = CircleShape,
                    modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                        Icons.Filled.List,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                )
                Text("Cetak Laporan")
            }
        }
    }
}

@Composable
fun SummaryGrid() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        SummaryCard(
                title = "Buku Teranalisis",
                value = "150",
                icon = Icons.Filled.List,
                iconBg = BlueAction.copy(alpha = 0.1f),
                iconColor = BlueAction,
                modifier = Modifier.weight(1f)
        )
        SummaryCard(
                title = "Total Ulasan",
                value = "2,450",
                icon = Icons.Filled.Star,
                iconBg = AmberAction.copy(alpha = 0.1f),
                iconColor = AmberAction,
                modifier = Modifier.weight(1f)
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        SummaryCard(
                title = "Skor Sentimen",
                value = "88%",
                icon = Icons.Filled.ThumbUp,
                iconBg = GreenAccent.copy(alpha = 0.2f),
                iconColor = GreenPrimary,
                modifier = Modifier.weight(1f)
        )
        SummaryCard(
                title = "Akurasi LSTM",
                value = "94.2%",
                icon = Icons.Filled.CheckCircle,
                iconBg = TealAction.copy(alpha = 0.1f),
                iconColor = TealAction,
                modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun SummaryCard(
        title: String,
        value: String,
        icon: ImageVector,
        iconBg: Color,
        iconColor: Color,
        modifier: Modifier = Modifier
) {
    Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = modifier
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Box(
                    modifier = Modifier.size(48.dp).clip(CircleShape).background(iconBg),
                    contentAlignment = Alignment.Center
            ) { Icon(icon, contentDescription = title, tint = iconColor) }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                    text = value,
                    style =
                            MaterialTheme.typography.headlineMedium.copy(
                                    fontFamily = FontFamily.SansSerif
                            )
            )
            Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(color = TextSecondary)
            )
        }
    }
}

@Composable
fun QuickActionsSection() {
    Text("Aksi Utama", style = MaterialTheme.typography.titleLarge)
    Spacer(modifier = Modifier.height(16.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        QuickActionCard(
                title = "Mulai Analisis",
                icon = Icons.Filled.PlayArrow,
                color = TealAction,
                modifier = Modifier.weight(1f)
        )
        QuickActionCard(
                title = "Import Data",
                icon = Icons.Filled.Add,
                color = BlueAction,
                modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun QuickActionCard(title: String, icon: ImageVector, color: Color, modifier: Modifier = Modifier) {
    Card(
            colors = CardDefaults.cardColors(containerColor = color),
            shape = RoundedCornerShape(20.dp),
            modifier = modifier
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Icon(
                    icon,
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(title, color = Color.White, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun ActivityLogSection() {
    Text("Log Aktivitas Terbaru", style = MaterialTheme.typography.headlineMedium)
    Spacer(modifier = Modifier.height(16.dp))
    Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            ActivityItem(
                    title = "Perubahan Bobot",
                    subtitle = "Admin",
                    time = "10m ago",
                    dotColor = AmberAction
            )
            HorizontalDivider(
                    color = TextSecondary.copy(alpha = 0.1f),
                    modifier = Modifier.padding(horizontal = 24.dp)
            )
            ActivityItem(
                    title = "LSTM Analysis Success",
                    subtitle = "System",
                    time = "25m ago",
                    dotColor = GreenPrimary
            )
            HorizontalDivider(
                    color = TextSecondary.copy(alpha = 0.1f),
                    modifier = Modifier.padding(horizontal = 24.dp)
            )
            ActivityItem(
                    title = "Auto-sync Completed",
                    subtitle = "System",
                    time = "2h ago",
                    dotColor = BlueAction
            )
        }
    }
}

@Composable
fun ActivityItem(title: String, subtitle: String, time: String, dotColor: Color) {
    Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(12.dp).clip(CircleShape).background(dotColor))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(subtitle, style = MaterialTheme.typography.bodyLarge.copy(color = TextSecondary))
        }
        Text(time, style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary))
    }
}

@Composable
fun SupportCard() {
    Card(
            colors = CardDefaults.cardColors(containerColor = GreenPrimary),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
    ) {
        Row(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                        "Butuh Bantuan?",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                        "Akses panduan integrasi model LSTM.",
                        color = Color.White.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                    onClick = {},
                    colors =
                            ButtonDefaults.buttonColors(
                                    containerColor = GreenAccent,
                                    contentColor = GreenPrimary
                            ),
                    shape = CircleShape
            ) { Text("Support Center", fontWeight = FontWeight.Bold) }
        }
    }
}

@Composable
fun DashboardBottomNavigation(currentScreen: Screen, onScreenSelected: (Screen) -> Unit) {
    NavigationBar(
            containerColor = SurfaceCard,
            contentColor = TextSecondary,
            tonalElevation = 8.dp
    ) {
        NavigationBarItem(
                selected = currentScreen == Screen.DASHBOARD,
                onClick = { onScreenSelected(Screen.DASHBOARD) },
                icon = { Icon(Icons.Filled.Home, contentDescription = "Overview") },
                label = { Text("Overview") },
                colors =
                        NavigationBarItemDefaults.colors(
                                selectedIconColor = GreenPrimary,
                                selectedTextColor = GreenPrimary,
                                indicatorColor = GreenPrimary.copy(alpha = 0.1f),
                                unselectedIconColor = TextSecondary,
                                unselectedTextColor = TextSecondary
                        )
        )
        NavigationBarItem(
                selected = currentScreen == Screen.SENTIMEN,
                onClick = { onScreenSelected(Screen.SENTIMEN) },
                icon = { Icon(Icons.Filled.Face, contentDescription = "Sentimen") },
                label = { Text("Sentimen") },
                colors =
                        NavigationBarItemDefaults.colors(
                                selectedIconColor = GreenPrimary,
                                selectedTextColor = GreenPrimary,
                                indicatorColor = GreenPrimary.copy(alpha = 0.1f),
                                unselectedIconColor = TextSecondary,
                                unselectedTextColor = TextSecondary
                        )
        )
        NavigationBarItem(
                selected = currentScreen == Screen.SMART,
                onClick = { onScreenSelected(Screen.SMART) },
                icon = { Icon(Icons.Filled.Build, contentDescription = "SMART") },
                label = { Text("SMART") },
                colors =
                        NavigationBarItemDefaults.colors(
                                selectedIconColor = GreenPrimary,
                                selectedTextColor = GreenPrimary,
                                indicatorColor = GreenPrimary.copy(alpha = 0.1f),
                                unselectedIconColor = TextSecondary,
                                unselectedTextColor = TextSecondary
                        )
        )
        NavigationBarItem(
                selected = currentScreen == Screen.KRITERIA,
                onClick = { onScreenSelected(Screen.KRITERIA) },
                icon = { Icon(Icons.Filled.List, contentDescription = "Kriteria") },
                label = { Text("Kriteria") },
                colors =
                        NavigationBarItemDefaults.colors(
                                selectedIconColor = GreenPrimary,
                                selectedTextColor = GreenPrimary,
                                indicatorColor = GreenPrimary.copy(alpha = 0.1f),
                                unselectedIconColor = TextSecondary,
                                unselectedTextColor = TextSecondary
                        )
        )
    }
}
