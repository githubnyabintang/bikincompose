package com.example.bikincompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bikincompose.ui.theme.*

data class Review(
        val id: Int,
        val userName: String,
        val comment: String,
        val sentiment: SentimentType,
        val date: String
)

enum class SentimentType {
    POSITIVE,
    NEGATIVE,
    NEUTRAL
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SentimentScreen(onBack: () -> Unit = {}) {
    Scaffold(
            topBar = {
                TopAppBar(
                        title = { Text("Analisis Sentimen", fontWeight = FontWeight.Bold) },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        },
                        actions = {
                            IconButton(onClick = {}) {
                                Icon(Icons.Default.Search, contentDescription = "Search")
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundWhite)
                )
            },
            containerColor = BackgroundWhite
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(horizontal = 24.dp)) {
            SentimentSummarySection()
            Spacer(modifier = Modifier.height(24.dp))
            Text("Daftar Ulasan", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            ReviewList(mockReviews)
        }
    }
}

@Composable
fun SentimentSummarySection() {
    Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
    ) {
        Row(
                modifier = Modifier.padding(24.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
        ) {
            SentimentStat(label = "Positif", count = "1,840", color = GreenPrimary)
            SentimentStat(label = "Netral", count = "450", color = AmberAction)
            SentimentStat(label = "Negatif", count = "160", color = Color(0xFFE53935))
        }
    }
}

@Composable
fun SentimentStat(label: String, count: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
                text = count,
                style = MaterialTheme.typography.headlineSmall,
                color = color,
                fontWeight = FontWeight.Bold
        )
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
    }
}

@Composable
fun ReviewList(reviews: List<Review>) {
    LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
    ) { items(reviews) { review -> ReviewItem(review) } }
}

@Composable
fun ReviewItem(review: Review) {
    Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceCard),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                            modifier =
                                    Modifier.size(32.dp)
                                            .clip(CircleShape)
                                            .background(GreenPrimary.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                    ) {
                        Text(
                                review.userName.take(1),
                                color = GreenPrimary,
                                fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(review.userName, style = MaterialTheme.typography.titleSmall)
                }
                SentimentBadge(review.sentiment)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(review.comment, style = MaterialTheme.typography.bodyMedium, color = TextPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(review.date, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
        }
    }
}

@Composable
fun SentimentBadge(sentiment: SentimentType) {
    val (text, color) =
            when (sentiment) {
                SentimentType.POSITIVE -> "Positif" to GreenPrimary
                SentimentType.NEGATIVE -> "Negatif" to Color(0xFFE53935)
                SentimentType.NEUTRAL -> "Netral" to AmberAction
            }

    Surface(color = color.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp)) {
        Text(
                text = text,
                color = color,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                fontWeight = FontWeight.Bold
        )
    }
}

val mockReviews =
        listOf(
                Review(
                        1,
                        "Budi Santoso",
                        "Buku ini sangat membantu saya dalam memahami algoritma. Penjelasannya sangat detail.",
                        SentimentType.POSITIVE,
                        "2 jam yang lalu"
                ),
                Review(
                        2,
                        "Siti Aminah",
                        "Materi yang disampaikan cukup berat, tapi masih bisa diikuti pelan-pelan.",
                        SentimentType.NEUTRAL,
                        "5 jam yang lalu"
                ),
                Review(
                        3,
                        "Andi Wijaya",
                        "Aplikasi kadang force close saat saya mencoba membuka ulasan ini.",
                        SentimentType.NEGATIVE,
                        "1 hari yang lalu"
                ),
                Review(
                        4,
                        "Dewi Lestari",
                        "Sangat informatif dan UI aplikasinya sangat bersih. Suka sekali!",
                        SentimentType.POSITIVE,
                        "1 hari yang lalu"
                ),
                Review(
                        5,
                        "Rizky Fauzi",
                        "Biasa saja, tidak ada yang spesial dari konten bukunya.",
                        SentimentType.NEUTRAL,
                        "2 hari yang lalu"
                )
        )
