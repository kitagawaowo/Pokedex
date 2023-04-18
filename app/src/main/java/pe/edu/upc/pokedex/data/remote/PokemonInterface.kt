package pe.edu.upc.pokedex.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonInterface {

    @GET("pokemon")
    fun getPokemons(): Call<PokemonResponse>

    @GET("pokemon/{index}")
    fun getPokemon(@Path("index") index: String): Call<Pokemon>
}