package pe.edu.upc.pokedex.ui.screens.pokemons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import okio.blackholeSink
import pe.edu.upc.pokedex.data.remote.Pokemon

@Composable
fun Pokemons(pokemons: List<Pokemon>, selectPokemon: (String) -> Unit) {
    LazyColumn {

        itemsIndexed(pokemons) { index, item ->
            PokemonItem(
                item,
                index + 1
            ) {
                selectPokemon("${index + 1}")
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PokemonItem(pokemon: Pokemon, index: Int, selectPokemon: () -> Unit) {
    Card(
        elevation = 2.dp,
        onClick = {
            selectPokemon ()
        }) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index}.png",
                contentDescription = null,
                modifier =  Modifier.clip(CircleShape),
                contentScale = ContentScale.Fit
                )
            Text(text = pokemon.name)
        }
    }
}
