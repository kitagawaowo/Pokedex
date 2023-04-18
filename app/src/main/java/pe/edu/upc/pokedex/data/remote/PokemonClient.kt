package pe.edu.upc.pokedex.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object PokemonClient {

    private const val API_BASE_URL = "https://pokeapi.co/api/v2/"
    private var pokemonInterface: PokemonInterface? = null

    fun build(): PokemonInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        pokemonInterface = retrofit.create(PokemonInterface::class.java)
        return pokemonInterface as PokemonInterface
    }
}