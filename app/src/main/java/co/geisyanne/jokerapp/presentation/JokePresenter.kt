package co.geisyanne.jokerapp.presentation

import co.geisyanne.jokerapp.data.JokeCallback
import co.geisyanne.jokerapp.data.JokeRemoteDataSource
import co.geisyanne.jokerapp.model.Joke
import co.geisyanne.jokerapp.view.JokeFragment

class JokePresenter(
    private val view: JokeFragment,
    private val dataSource: JokeRemoteDataSource = JokeRemoteDataSource()
) : JokeCallback {

    fun findBy(categoryName: String) {
        view.showProgress()
        dataSource.findBy(categoryName, this)
    }

    override fun onSuccess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}