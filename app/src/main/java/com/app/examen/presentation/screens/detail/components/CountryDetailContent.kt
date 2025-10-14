package com.app.examen.presentation.screens.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.examen.domain.model.Country

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun CountryDetailContent(country: Country) {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            AsyncImage(
                model = country.flag,
                contentDescription = country.name,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                contentScale = ContentScale.Fit,
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = country.name,
                style = MaterialTheme.typography.headlineLarge,
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            DetailInfoRow(label = "Capital", value = country.capital ?: "N/A")
            DetailInfoRow(label = "Region", value = country.region)
            DetailInfoRow(
                label = "Population",
                value =
                    country.population?.let {
                        "%,d".format(it)
                    } ?: "N/A",
            )
            country.area?.let {
                DetailInfoRow(label = "Area", value = country.area.toString())
            }
        }

        if (country.languages.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Languages",
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    country.languages.forEach { language ->
                        Chip(text = language)
                    }
                }
            }
        }

        if (country.currencies.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Currencies",
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    country.currencies.forEach { currency ->
                        Chip(text = currency)
                    }
                }
            }
        }

        if (country.timezones.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Timezones",
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(8.dp))

                country.timezones.forEach { timezone ->
                    Text(
                        text = timezone,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}
