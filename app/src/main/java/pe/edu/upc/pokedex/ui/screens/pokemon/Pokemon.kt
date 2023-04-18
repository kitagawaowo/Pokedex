package pe.edu.upc.pokedex.ui.screens.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import pe.edu.upc.pokedex.data.remote.Pokemon

@Composable
fun Pokemon(pokemon: Pokemon) {
    Column {
        Text(text = "Name: ${pokemon.name}")
        Text(text = "Height: ${pokemon.height}")
        Text(text = "Weight: ${pokemon.weight}")
    }
}