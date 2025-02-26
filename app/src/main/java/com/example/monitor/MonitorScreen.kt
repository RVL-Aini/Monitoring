MonitorScreen.kt
package com.example.monitor.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.monitor.data.Website
import com.example.monitor.viewmodel.MonitorViewModel

@Composable
fun MonitorScreen(viewModel: MonitorViewModel) {
    val context = LocalContext.current
    val websites by viewModel.websites.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn {
            items(websites) { site ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    elevation = 4.dp
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = site.url, style = MaterialTheme.typography.h6)
                            Text(text = "Периодичность: ${site.interval} мин")
                        }
                        Button(onClick = {
                            viewModel.removeWebsite(site)
                            Toast.makeText(context, "Удалено", Toast.LENGTH_SHORT).show()
                        }) {
                            Text("Удалить")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMonitorScreen() {
val fakeViewModel = remember { MonitorViewModel() }
MonitorScreen(viewModel = fakeViewModel)
}

