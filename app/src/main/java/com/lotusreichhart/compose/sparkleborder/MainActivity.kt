package com.lotusreichhart.compose.sparkleborder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lotusreichhart.compose.sparkleborder.ui.theme.SparkleBorderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SparkleBorderTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF121212)),
                ) { innerPadding ->
                    DemoScreen(innerPadding)
                }
            }
        }
    }
}

val DarkBackground = Color(0xFF121212)
val CardBackground = Color(0xFF1E1E1E)
val InstaGradient = listOf(Color(0xFFF9CE34), Color(0xFFEE2A7B), Color(0xFF6228D7))
val GoldGradient = listOf(Color(0xFFFF5C00), Color(0xFFFFE14D), Color(0xFFB8860B))
val NeonCyan = listOf(Color.Cyan, Color.Blue, Color.Cyan)

@Composable
fun DemoScreen(
    paddingValues: androidx.compose.foundation.layout.PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item { HeaderSection() }

        item {
            DemoSection(title = "Social Media Avatar") {
                SocialAvatarDemo()
            }
        }

        item {
            DemoSection(title = "Premium Card Effect") {
                PremiumCardDemo()
            }
        }

        item {
            DemoSection(title = "Call To Action Buttons") {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NeonButtonDemo()
                    StatusIndicatorDemo()
                }
            }
        }

        item { Spacer(modifier = Modifier.height(50.dp)) }
    }
}

@Composable
fun HeaderSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "✨ Sparkle Border",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Library Showcase",
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun DemoSection(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title.uppercase(),
            color = Color.Gray,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        content()
    }
}

@Composable
fun SocialAvatarDemo() {
    Box(
        modifier = Modifier.size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .sparkleBorder(
                    brush = Brush.sweepGradient(InstaGradient),
                    backgroundColor = DarkBackground,
                    borderWidth = 4.dp,
                    shape = CircleShape,
                    animationDurationInMillis = 2500
                )
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(50.dp)
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-4).dp, y = (-4).dp)
                .size(24.dp)
                .background(Color.Blue, CircleShape)
                .border(2.dp, DarkBackground, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun PremiumCardDemo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(horizontal = 16.dp)
            .sparkleBorder(
                brush = Brush.linearGradient(GoldGradient),
                backgroundColor = CardBackground,
                borderWidth = 3.dp,
                shape = RoundedCornerShape(24.dp),
                animationDurationInMillis = 4000
            )
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFD700))
                Spacer(modifier = Modifier.width(8.dp))
                Text("GOLD MEMBER", color = Color(0xFFFFD700), fontWeight = FontWeight.Bold)
            }
            Column {
                Text(
                    "Lotus Reichhart",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Text("**** **** **** 1234", color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun NeonButtonDemo() {
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(160.dp)
            .sparkleBorder(
                brush = Brush.linearGradient(NeonCyan),
                backgroundColor = CardBackground,
                borderWidth = 2.dp,
                shape = RoundedCornerShape(50),
                animationDurationInMillis = 1000
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "CONNECT WALLET",
            color = Color.Cyan,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun StatusIndicatorDemo() {
    Box(
        modifier = Modifier
            .size(50.dp)
            .sparkleBorder(
                brush = Brush.sweepGradient(listOf(Color.Green, Color.Transparent)),
                backgroundColor = CardBackground,
                borderWidth = 3.dp,
                shape = CircleShape,
                animationDurationInMillis = 1000
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Default.Check, contentDescription = null, tint = Color.Green)
    }
}