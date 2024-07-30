package com.ustinovauliana.composelayoutsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ustinovauliana.composelayoutsapp.ui.theme.ComposeLayoutsAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            LayoutsApp(windowSizeClass)
        }
    }
}

@Composable
fun LayoutsApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            LayoutsAppPortrait()
        } else -> {
            LayoutsAppLandscape()
        }
    }
}

@Composable
private fun HomeBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_home))
            })
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_profile))
            })
    }
}

@Composable
fun LayoutsAppLandscape() {
    ComposeLayoutsAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                HomeNavigationRail()
                HomeScreen()
            }
        }
    }
}

@Composable
private fun HomeNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight(),
        ) {
            NavigationRailItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_home))
                })
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_profile))
                }
            )
        }
    }
}

@Preview
@Composable
fun LayoutsAppPortrait() {
    ComposeLayoutsAppTheme {
        Scaffold(
            bottomBar = {
                HomeBottomNavigation()
            }
        ) { paddingValues ->
            HomeScreen(Modifier.padding(paddingValues))
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar()
        HomeSection(titleId = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(titleId = R.string.favorite_collections) {
            FavouriteCollectionsGrid()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun HomeSection(
    @StringRes titleId: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            stringResource(id = titleId),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun FavouriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp),
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(
                imageId = item.drawable,
                textId = item.text,
                modifier = Modifier.height(180.dp)
            )
        }
    }
}

@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(imageId = item.drawable, textId = item.text, modifier = modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteCollectionCardPreview() {
    ComposeLayoutsAppTheme {
        FavoriteCollectionCard(
            imageId = R.drawable.fc2_nature_meditations,
            textId = R.string.fc2_nature_meditations,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun FavoriteCollectionCard(modifier: Modifier = Modifier, imageId: Int, textId: Int) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(255.dp)
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(id = textId),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun AlignYourBodyElement(modifier: Modifier = Modifier, imageId: Int, textId: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = textId),
            modifier
                .paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = { Text(stringResource(R.string.search)) },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
    )
}


@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    ComposeLayoutsAppTheme {
        SearchBar()
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyElementPreview() {
    ComposeLayoutsAppTheme {
        AlignYourBodyElement(
            imageId = R.drawable.ab1_inversions,
            textId = R.string.ab1_inversions,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyRowPreview() {
    ComposeLayoutsAppTheme {
        AlignYourBodyRow()
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteCollectionsGridPreview() {
    ComposeLayoutsAppTheme {
        FavouriteCollectionsGrid()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    ComposeLayoutsAppTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun HomeScreenPreview() {
    ComposeLayoutsAppTheme {
        HomeScreen()
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }


private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)
