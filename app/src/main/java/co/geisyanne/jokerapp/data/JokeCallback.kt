package co.geisyanne.jokerapp.data

import co.geisyanne.jokerapp.model.Joke

interface JokeCallback {

    fun onSuccess(response: Joke)

    fun onError(response: String)

    fun onComplete()

}