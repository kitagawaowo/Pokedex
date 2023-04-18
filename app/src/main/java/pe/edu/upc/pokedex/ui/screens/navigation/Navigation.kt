package pe.edu.upc.pokedex.ui.screens.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.upc.pokedex.data.remote.Pokemon
import pe.edu.upc.pokedex.data.remote.PokemonClient
import pe.edu.upc.pokedex.data.remote.PokemonResponse
import pe.edu.upc.pokedex.ui.screens.pokemon.Pokemon
import pe.edu.upc.pokedex.ui.screens.pokemons.Pokemons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "pokemons") {

        composable("pokemons") {
            val pokemons = remember {
                mutableStateOf(emptyList<Pokemon>())
            }

            val pokemonInterface = PokemonClient.build()
            val getPokemons = pokemonInterface.getPokemons()

            getPokemons.enqueue(object : Callback<PokemonResponse> {
                override fun onResponse(
                    call: Call<PokemonResponse>,
                    response: Response<PokemonResponse>
                ) {
                    if (response.isSuccessful) {
                        pokemons.value = response.body()?.pokemons!!
                    }
                }

                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                }

            })
            Pokemons(
                pokemons = pokemons.value,
                selectPokemon = { index ->
                    navController.navigate("pokemon/$index")

                }
            )
        }

        composable(
            "pokemon/{index}",
            arguments = listOf(navArgument("index") { type = NavType.StringType })
        ) {
            val index = it.arguments?.getString("index") as String

            val pokemon = remember {
                mutableStateOf(Pokemon("", 0, 0))
            }

            val pokemonInterface = PokemonClient.build()
            val getPokemon = pokemonInterface.getPokemon(index)

            getPokemon.enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful) {
                        pokemon.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                }
            })

            Pokemon(pokemon = pokemon.value)
        }
    }
}